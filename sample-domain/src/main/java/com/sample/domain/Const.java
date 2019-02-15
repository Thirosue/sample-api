package com.sample.domain;

import java.time.format.DateTimeFormatter;

/**
 * 定数定義
 */
public class Const {

    /** ---- Session ---- **/
    public static final String SESSION_ID = "sid";
    public static final String SESSION_INFO = "info";
    public static final Integer SESSION_EXPIRES = 600;

    // yyyy/MM/dd HH:mm:ss
    public static final DateTimeFormatter YYYY_MM_DD_HHmmss = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

}
