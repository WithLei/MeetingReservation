package com.android.renly.meetingreservation.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {
    public static boolean isNull(String checkStr) {

        boolean result = false;

        if (null == checkStr){

            result = true;
        } else {
            if (checkStr.length() == 0) {

                result = true;
            }
        }
        return result;
    }

    public static boolean isNull(List<?> list) {

        boolean result = false;

        if (null == list){

            result = true;
        } else {
            if (list.size() == 0) {

                result = true;
            }
        }
        return result;
    }
    public static List<String> getRegEx(String input, String regex) {
        List<String> stringList = new ArrayList<>();
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        while (m.find())
            stringList.add(m.group());

        return stringList;
    }
}
