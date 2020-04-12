package com.hy.yyzx.common.utils;

import java.io.Serializable;

/**
 * 数组工具
 * <p>
 * ArrayUtil
 *
 * @author Aidy
 * @version v1.0
 */
public class ArrayUtil implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 字符串数组查找
     *
     * @param array String[] String数组
     * @param str   Stirng 要查找的内容
     * @author Aidy
     * @return boolean
     */
    public static boolean findString(String[] array, String str) {
        if (array == null) return false;
        for (String s : array) {
            if (s.equals(str)) return true;
        }
        return false;
    }

    /**
     * Integer数组查找
     *
     * @param array Integer[] String数组
     * @param val   int 要查找的内容
     * @author Aidy
     * @return boolean
     */
    public static boolean findInteger(Integer[] array, int val) {
        if (array == null) return false;
        for (Integer s : array) {
            if (s.intValue() == val) return true;
        }
        return false;
    }

    /**
     * Object数组查找
     *
     * @param array Object[] String数组
     * @param obj   Object 要查找的内容
     * @author Aidy
     * @return boolean
     */
    public static boolean findObject(Object[] array, Object obj) {
        if (array == null) return false;
        for (Object s : array) {
            if (s.equals(obj)) return true;
        }
        return false;
    }

}
