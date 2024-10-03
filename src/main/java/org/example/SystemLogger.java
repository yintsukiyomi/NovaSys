package org.example;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class SystemLogger {
    private static final Logger logger = LoggerFactory.getLogger(SystemLogger.class);

    public static void log(String message) {
        logger.info(message);
    }
}
