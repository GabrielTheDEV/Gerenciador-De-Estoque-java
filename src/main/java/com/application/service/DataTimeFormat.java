package com.application.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataTimeFormat {
    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String getTimeInfo(){
        return LocalDateTime.now().format(formatter);
    }
}
