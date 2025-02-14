# Log filtering using regular expressions 

This is a simple Java application for log filtering.

Usage:

    java -jar log-filter.jar <log_file_path> -o<output_file_path> -r<regex_filter>

Example:

    java -jar log-filter.jar files/log_sample -ofiles/log_filtered -r"(?i).*debug.*Bulk.*"
    
