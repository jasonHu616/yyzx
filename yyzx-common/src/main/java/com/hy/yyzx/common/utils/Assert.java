package com.hy.yyzx.common.utils;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Aidy 2018-06-07 0007 17:04
 */
public class Assert extends org.springframework.util.Assert {

    private static boolean isZeroOrNull(Integer value) {
        return (value == null || value.intValue() == 0);
    }

    private static boolean isZeroOrNull(Double value) {
        return (value == null || value.intValue() == 0);
    }

    /**
     * 检测Integer是否为Null或0值
     *
     * @param value   {@link Integer}
     * @param message {@link String} 异常信息
     */
    public static void isZeroOrNull(Integer value, String message) {
        if (isZeroOrNull(value))
            throw new IllegalArgumentException(message);
    }

    /**
     * 检测Double是否为Null或0值
     *
     * @param value   {@link Integer}
     * @param message {@link String} 异常信息
     */
    public static void isZeroOrNull(Double value, String message) {
        if (isZeroOrNull(value))
            throw new IllegalArgumentException(message);
    }

    /**
     * 检测是否有0或空值
     *
     * @param values
     * @param message
     */
    public static void isAnyZero(Integer[] values, String message) {
        if (values != null) {
            for (Integer i : values) {
                isZeroOrNull(i, message);
            }
        }
    }

    /**
     * 检测是否全部为空或0
     *
     * @param values
     * @param message
     */
    public static void isAllZero(Integer[] values, String message) {
        boolean check = false;
        if (values != null) {
            for (Integer i : values) {
                if (!isZeroOrNull(i)) check = true;
            }
        }
        if (!check) throw new IllegalArgumentException(message);
    }


    /**
     * 检测字符串是否在指定数组内容中
     *
     * @param value      String 要检测的内容
     * @param message    String 异常消息
     * @param ignoreCase {@link Boolean} 是否忽略大小写
     * @param array      String[] 需要检测包含的数组
     */
    public static void inArray(String value, String message, boolean ignoreCase, String... array) {
        if (array != null) {
            boolean check = false;
            for (String s : array) {
                if ((!ignoreCase && value.equals(s)) || (ignoreCase && value.equalsIgnoreCase(s))) {
                    check = true;
                    break;
                }
            }
            if (!check) throw new IllegalArgumentException(message);
        }
    }

    /**
     * 检测Integer是否在指定数组内容中
     *
     * @param value   String 要检测的内容
     * @param message String 异常消息
     * @param array   String[] 需要检测包含的数组
     */
    public static void inArray(Integer value, String message, Integer... array) {
        if (array != null) {
            boolean check = false;
            for (Integer s : array) {
                if (value.intValue() == s.intValue()) {
                    check = true;
                    break;
                }
            }
            if (!check) throw new IllegalArgumentException(message);
        }
    }

    /**
     * 检测String是否包含中文
     *
     * @param value   {@link String}
     * @param message {@link String} 异常信息
     */
    public static void isContainsChinese(String value, String message) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(value);
        if (m.find()) {
            throw new IllegalArgumentException(message);
        }
    }
}
