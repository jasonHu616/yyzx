package com.hy.yyzx.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author chg 2018/9/13
 */
public class ObjectToValueUtil {

    /**
     * 对象赋值
     *
     * @param class1 用于赋值的实体类
     * @param class2 需要待赋值的实体类
     * @param bool   是否父类
     * @throws Exception
     */
    public static void reflectionAttr(Object class1, Object class2, boolean bool) throws Exception {
        Class clazz1 = Class.forName(class1.getClass().getName());
        if (bool) {
            clazz1 = clazz1.getSuperclass();
        }
        Class clazz2 = Class.forName(class2.getClass().getName());
        //获取两个实体类的所有属性
        Field[] fields1 = clazz1.getDeclaredFields();
        Field[] fields2 = clazz2.getDeclaredFields();
        //遍历class1Bean，获取逐个属性值，然后遍历class2Bean查找是否有相同的属性，如有相同则赋值
        for (Field f1 : fields1) {
            if (f1.getName().equals("id"))
                continue;
            Object value = invokeGetMethod(class1, f1.getName(), null);
            for (Field f2 : fields2) {
                if (f1.getName().equals(f2.getName())) {
                    Object[] obj = new Object[1];
                    obj[0] = value;
                    invokeSetMethod(class2, f2.getName(), obj);
                }
            }
        }

    }

    /**
     * 直接读取对象的属性值, 忽略 private/protected 修饰符, 也不经过 getter
     *
     * @param object    : 子类对象
     * @param fieldName : 父类中的属性名
     * @return : 父类中的属性值
     */
    public static Object getFieldValue(Object object, String fieldName) {

        //根据 对象和属性名通过反射 调用上面的方法获取 Field对象
        Field field = getDeclaredField(object, fieldName);
        //抑制Java对其的检查
        field.setAccessible(true);
        try {
            //获取 object 中 field 所代表的属性值
            return field.get(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 直接设置对象属性值, 忽略 private/protected 修饰符, 也不经过 setter
     *
     * @param object    : 子类对象
     * @param fieldName : 父类中的属性名
     * @param value     : 将要设置的值
     */
    public static void setFieldValue(Object object, String fieldName, Object value) {
        //根据 对象和属性名通过反射 调用上面的方法获取 Field对象
        Field field = getDeclaredField(object, fieldName);
        //抑制Java对其的检查
        field.setAccessible(true);
        try {
            //将 object 中 field 所代表的值 设置为 value
            field.set(object, value);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 循环向上转型, 获取对象的 DeclaredField
     *
     * @param object    : 子类对象
     * @param fieldName : 父类中的属性名
     * @return 父类中的属性对象
     */
    public static Field getDeclaredField(Object object, String fieldName) {
        Field field = null;
        Class<?> clazz = object.getClass();
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                return field;
            } catch (Exception e) {
                //这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
                //如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了
            }
        }
        return null;
    }


    /**
     * 执行某个Field的getField方法
     *
     * @param clazz
     * @param fieldName
     * @param args
     * @return
     */
    private static Object invokeGetMethod(Object clazz, String fieldName, Object[] args) {
        return getFieldValue(clazz, fieldName);
    }

    /**
     * 执行某个Field的setField方法
     *
     * @param clazz
     * @param fieldName
     * @param args
     * @return
     */
    private static Object invokeSetMethod(Object clazz, String fieldName, Object[] args) {
        String methodName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        Method method = null;
        try {
            Class[] parameterTypes = new Class[1];
            Class c = Class.forName(clazz.getClass().getName());
            Field field = c.getDeclaredField(fieldName);
            parameterTypes[0] = field.getType();
            if (!methodName.equals("SerialVersionUID")) {
                method = c.getDeclaredMethod("set" + methodName, parameterTypes);
                return method.invoke(clazz, args);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


}
