package com.hotplace25.mobile.utils;

import android.text.format.DateFormat;

import java.util.Date;

/**
 * Created by khnam on 2018-01-10.
 */

public class StringManager {
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().equals(empty());
    }

    public static String empty() {
        return "";
    }

    public static String nvl(String v, String replaceV) {
        if(isNullOrEmpty(v)) return replaceV;
        return v;
    }

    public static String nvl(String v) {
        return nvl(v, empty());
    }

    public static String nvl(Object o, String replaceV) {
        if(o == null) return replaceV;
        return o.toString();
    }

    public static String nvl(Object o) {
        return nvl(o, empty());
    }

    public static String getDateFormat(String format, Date date) {
        if(isNullOrEmpty(format)) format = "yyyy-MM-dd kk:mm:ss";

        return new DateFormat().format(format, date).toString();
    }

    public static String getDateFormat(Date date) {
        return getDateFormat(null, date);
    }

    public static int getVersionToNumber(String version) {
        if(isNullOrEmpty(version)) return -1;

        StringBuffer sb = new StringBuffer();
        int len = version.length();
        char c;

        for(int i = 0; i < len; i++) {
            c = version.charAt(i);
            if(c != '.') sb.append(c);
        }

        return Integer.parseInt(sb.toString());
    }
}
