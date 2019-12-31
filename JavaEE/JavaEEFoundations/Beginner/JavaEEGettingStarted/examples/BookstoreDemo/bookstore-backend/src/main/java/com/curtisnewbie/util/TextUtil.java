package com.curtisnewbie.util;

public class TextUtil {

    public String removeDupliSpaces(String str) {
        return str.replaceAll("\\s+", " ");
    }
}
