package ru.bakushkin.dto.constant;

import java.time.format.DateTimeFormatter;

public final class Constants {

    private Constants() {

    }

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static final String QUEUE_STATUS = "statusQueue";

    public static final String QUEUE_MESSAGE = "messageQueue";
}
