package com.baseProject.android.util.parser;

/**
 * @author Abbas Asadi
 */
public class MessageParser {
    public static String parseOtpMessage(String string) {
        StringBuilder stringBuilder = new StringBuilder(string);

        return stringBuilder.substring(string.indexOf(":") + 1, string.lastIndexOf(" ")).trim();
        // return stringBuilder.toString();
    }

}
