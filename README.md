# Log filtering using regular expressions 

This is a simple Java application for log filtering.

Usage:

    java -jar log-filter.jar <log_file_path> -o<output_file_path> -r<regex_filter>

Example (include all lines which contains the word *debug*, followed by *Bulk* after zero or more other characters, case insensitive):

    java -jar log-filter.jar files/log_sample -ofiles/log_filtered -r"(?i).*debug.*Bulk.*"

For more information on regular expressions, visit the [Oracle Help Center](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/regex/Pattern.html).

Note: The application is built with Java 17 but can be rebuilt with any version from 1.8 onward.
