package com.devsuperior.dslist.utils;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public final class FunctionalUtils {
    private FunctionalUtils() {
        throw new IllegalStateException("Utility class");
    }

    private final static String BR_DATETIME_FORMAT = "dd/MM/yyyy HH:mm:ss";

    public static String formatCreationDate(LocalDateTime localDateTime) {
        return (Objects.isNull(localDateTime)) ? StringUtils.EMPTY : formatCreationDateBy(localDateTime);
    }

    public static String formatCreationDateBy(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern(BR_DATETIME_FORMAT));
    }
}
