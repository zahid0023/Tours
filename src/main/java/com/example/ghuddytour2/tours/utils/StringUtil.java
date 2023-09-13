package com.example.ghuddytour2.tours.utils;

import org.springframework.util.StringUtils;

public class StringUtil {
    public static String tagify(String str1, String str2) {
        // Remove special characters and spaces, and convert to lowercase
        String formattedStr1 = str1.replaceAll("[^a-zA-Z0-9]+", "-").toLowerCase();
        String formattedStr2 = str2.replaceAll("[^a-zA-Z0-9]+", "-").toLowerCase();

        // Concatenate the formatted strings with a hyphen
        String tag = formattedStr1 + "-" + formattedStr2;

        return tag;
    }

    public static String tourPackageName(String tourName, String packageTypeName) {
        return tourName + " - " + packageTypeName;
    }
}

