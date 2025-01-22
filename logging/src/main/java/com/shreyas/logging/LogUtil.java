package com.shreyas.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A basic logger utility class that simplifies logging operations.
 * For more advanced logging features, consider creating a logger instance with LoggerFactory rather than using utility.
 */
public final class LogUtil {

    // Private constructor to prevent instantiation
    private LogUtil() {
    }

    // Get the logger for the specified class
    public static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }

    // Simplified logging methods for common log levels

    public static void info(Class<?> clazz, String message) {
        getLogger(clazz).info(message);
    }

    public static void debug(Class<?> clazz, String message) {
        getLogger(clazz).debug(message);
    }

    public static void warn(Class<?> clazz, String message) {
        getLogger(clazz).warn(message);
    }

    public static void error(Class<?> clazz, String message) {
        getLogger(clazz).error(message);
    }

    public static void trace(Class<?> clazz, String message) {
        getLogger(clazz).trace(message);
    }

    // Logging with an exception
    public static void info(Class<?> clazz, String message, Throwable throwable) {
        getLogger(clazz).info(message, throwable);
    }

    public static void debug(Class<?> clazz, String message, Throwable throwable) {
        getLogger(clazz).debug(message, throwable);
    }

    public static void warn(Class<?> clazz, String message, Throwable throwable) {
        getLogger(clazz).warn(message, throwable);
    }

    public static void error(Class<?> clazz, String message, Throwable throwable) {
        getLogger(clazz).error(message, throwable);
    }

    public static void trace(Class<?> clazz, String message, Throwable throwable) {
        getLogger(clazz).trace(message, throwable);
    }

    // Logging with a format string
    public static void info(Class<?> clazz, String message, Object... args) {
        getLogger(clazz).info(message, args);
    }

    public static void debug(Class<?> clazz, String message, Object... args) {
        getLogger(clazz).info(message, args);
    }

    public static void warn(Class<?> clazz, String message, Object... args) {
        getLogger(clazz).info(message, args);
    }

    public static void error(Class<?> clazz, String message, Object... args) {
        getLogger(clazz).info(message, args);
    }

    public static void trace(Class<?> clazz, String message, Object... args) {
        getLogger(clazz).info(message, args);
    }
}


