package com.codeinspace;

import java.io.*;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    private static boolean filterLogFile(String inputFilePath, String outputFilePath, String includeFilter) {
        try {
            BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(inputFilePath));
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFilePath));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));

            while (reader.ready()) {
                String line = reader.readLine();
                if (line.matches(includeFilter)) {
                    writer.write(line);
                    writer.newLine();
                }
            }

            writer.close();
            reader.close();
            return true;
        } catch (Exception e) {
            LOGGER.severe("Failed to process file: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello, World!");

        if (args.length < 1) {
            LOGGER.severe("Usage: java -jar log-filter.jar <path-to-input-file> " +
                    "-o<path-to-output-file> -r\"include-filter-regex\"");
            return;
        }

        String inputFilePath = args[0];
        String outputFilePath = "output.txt";
        String regexFilter = ".*";

        for (int i = 1; i < args.length; i++) {
            if (args[i].startsWith("-o")) {
                outputFilePath = args[i].substring(2);
            } else if (args[i].startsWith("-r")) {
                regexFilter = args[i].substring(2);
            }
        }

        boolean success = filterLogFile(inputFilePath, outputFilePath, regexFilter);
        if (!success) { LOGGER.severe("Failed to filter log file."); return; }

        LOGGER.info("Filtered output log written to: " + outputFilePath);
    }
}