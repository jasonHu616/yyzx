package com.hy.yyzx.common.utils.beanutils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class ClassConvertUtils implements Serializable {
    private static final long serialVersionUID = -5126042247702474975L;

    public static void main(String[] args) {
		/*ClassField[] fo = getClassFields(User.class);
		for(ClassField f : fo){
			System.out.println(f.getName()+"/"+f.getGetterMethod().getName()+"/"+f.getSetterMethod().getName());
		}*/
    }

    /**
     * 取得类中的所有成员
     *
     * @param cls {@link Class}&lt;?&gt; 需要操作的类
     * @return    {@link ClassField}[]
     */
    public static ClassField[] getClassFields(Class<?> cls) {
        Class<?> classEntity = cls;
        List<ClassField> fieldList = new ArrayList<ClassField>();
        while (classEntity.getSuperclass() != null) {
            Field[] fields = classEntity.getDeclaredFields();
            for (Field f : fields) {
                if (!f.getName().equals("serialVersionUID")) {
                    ClassField classField = new ClassField();
                    classField.setName(f.getName());
                    classField.setField(f);
                    //if(f.isAnnotationPresent(ClassFieldToDB.class)){
                    //		ClassFieldToDB clsFieldToDB = f.getAnnotation(ClassFieldToDB.class);
                    //		classField.setColumnName(clsFieldToDB.DBColumnName());
                    //	}else{
                    classField.setColumnName(f.getName());
                    //	}
                    fieldList.add(classField);
                }
            }
            classEntity = classEntity.getSuperclass();
        }

        return findClassFieldMethods(fieldList, cls);
    }

    /**
     * 填充GET/SET方法名称
     *
     * @param list        {@link List}&lt; {@link ClassField} &gt;
     * @param classEntity {@link Class}&lt;?&gt;
     * @return    {@link ClassField}[]
     */
    private static ClassField[] findClassFieldMethods(List<ClassField> list, Class<?> classEntity) {
        Method[] methods = getClassMethods(classEntity);
        for (ClassField classField : list) {
            for (Method method : methods) {
                //String debug = method.getName()+"/" + ("get"+classField.getName()) + "/" + ("set"+classField.getName());
                if (method.getName().equalsIgnoreCase("get" + classField.getName()) || method.getName().equalsIgnoreCase("set" + classField.getName())) {
                    if (method.getName().toLowerCase().startsWith("set")) {
                        classField.setSetterMethod(method);
                    } else {
                        classField.setGetterMethod(method);
                    }
                }
            }
        }
        return list.toArray(new ClassField[list.size()]);
    }

    /**
     * 取得类中所有方法
     *
     * @param cls {@link Class}&lt;?&gt;
     * @return    {@link Method}[]
     */
    public static Method[] getClassMethods(Class<?> cls) {
        Class<?> classEntity = cls;
        List<Method> methodList = new ArrayList<Method>();
        while (classEntity.getSuperclass() != null) {
            Method[] methods = classEntity.getDeclaredMethods();
            for (Method method : methods) {
                methodList.add(method);
            }
            classEntity = classEntity.getSuperclass();
        }
        return methodList.toArray(new Method[methodList.size()]);
    }
}
