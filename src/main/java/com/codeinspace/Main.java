package com.codeinspace;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    private static boolean filterLogFile(String inputFilePath, String outputFilePath, String includeFilter) {
        try {
            BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(inputFilePath));
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFilePath));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));

            Pattern pattern = Pattern.compile(includeFilter);
            while (reader.ready()) {
                String line = reader.readLine();
                if (pattern.matcher(line).matches()) {
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
        if (args.length < 1) {
            System.out.println("\nUsage: java -jar log-filter.jar <path-to-input-file> " +
                    "-o<path-to-output-file> -r\"include-filter-regex\"\n");

            System.out.println("NOTE:");
            System.out.println("Some terminals cannot handle arguments which contains double quotes," +
                    "in that case you can use the escape sequence \\u0022.");
            System.out.println("Some terminals will not pass non-ascii characters in arguments correctly," +
                    "in that case you can use the character unicode escape sequence.\n");
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

        System.out.println("Filtering log file using regex: " + regexFilter);

        boolean success = filterLogFile(inputFilePath, outputFilePath, regexFilter);
        if (!success) { LOGGER.severe("Failed to filter log file."); return; }

        LOGGER.info("Filtered output log written to: " + outputFilePath);
    }
}