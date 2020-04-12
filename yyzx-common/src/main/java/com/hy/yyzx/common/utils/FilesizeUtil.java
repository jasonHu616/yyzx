package com.hy.yyzx.common.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Aidy 2018/5/17 0017 14:04
 */
public class FilesizeUtil {

    /*public static void main(String[] args){
        String size = "12TB";
        long s = sizeToLong(size);
        System.out.println(s);
        String ss = longToSize(s);
        System.out.println(ss);
    }*/
    private static String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
    private static Map<String, Long> sizeMap;

    static {
        sizeMap = new HashMap<>();
        sizeMap.put("B", 1L);
        sizeMap.put("KB", 1024L);
        sizeMap.put("MB", 1024 * 1024L);
        sizeMap.put("GB", 1024 * 1024 * 1024L);
        sizeMap.put("TB", 1024 * 1024 * 1024 * 1024L);

    }

    public static String longToSize(long value) {

        long temp = value;
        int s = 0;
        String unit = "B";
        while (temp >= 1024 && s < units.length) {
            s++;
            temp = temp / 1024;
            unit = units[s];
        }
        return temp + unit;
    }

    public static long sizeToLong(String size) {
        long result = 0;
        String sizeStr = match("\\d+", size);
        String unitStr = match("(B|KB|MB|GB|TB)", size);
        result = sizeStr == null ? 0 : Long.parseLong(sizeStr);

        if (unitStr != null && !unitStr.equals("")) {
            long unitSize = sizeMap.get(unitStr);
            return result * (unitSize == 0 ? 1 : unitSize);
        }
        return result;
    }

    private static String match(String regx, String source) {
        Pattern sizePat = Pattern.compile(regx);
        Matcher matcher = sizePat.matcher(source);
        if (matcher.find()) {
            return matcher.group(0);
        }
        return null;
    }
}
