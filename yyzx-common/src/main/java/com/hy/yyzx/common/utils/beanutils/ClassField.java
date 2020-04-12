package com.hy.yyzx.common.utils.beanutils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * ClassField.java 实体类定义规则
 *
 * @author Aidy-M
 */
public class ClassField {
    private String name = "";
    private Field field = null;
    private Method setterMethod = null;
    private Method getterMethod = null;
    private String columnName = "";
    private String dbMetaLabel = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Method getSetterMethod() {
        return setterMethod;
    }

    public void setSetterMethod(Method setterMethod) {
        this.setterMethod = setterMethod;
    }

    public Method getGetterMethod() {
        return getterMethod;
    }

    public void setGetterMethod(Method getterMethod) {
        this.getterMethod = getterMethod;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDbMetaLabel() {
        return dbMetaLabel;
    }

    public void setDbMetaLabel(String dbMetaLabel) {
        this.dbMetaLabel = dbMetaLabel;
    }

}
