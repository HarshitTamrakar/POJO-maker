package com.harshit.Pojomaker.utils;

public class ModelMakerUtility {

    public static String getClassName(String className) {
        return className.replaceFirst("" + className.charAt(0),
            ("" + className.charAt(0)).toUpperCase());
    }

}
