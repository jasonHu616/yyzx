package com.hy.yyzx.common.utils.beanutils;

import com.hy.yyzx.common.modules.MapEntity;
import com.hy.yyzx.common.utils.ArrayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConvertToBean implements Serializable {
    private static final long serialVersionUID = 320459199363200809L;
    static Logger logger = LoggerFactory.getLogger(ConvertToBean.class);

    public static void main(String[] args) {

    }

    public ConvertToBean() {

    }


    public <T> List<T> fromResultSetList(ResultSet rs, Class<T> clsName) {
        return fromResultSetList(rs, clsName, null);
    }


    /**
     * 从ResultSet对象转换为List输出
     *
     * @param rs             {@link ResultSet}
     * @param excludeColumns String[] 不需要返回的字段数组
     * @param    clsName        {@link Class}&lt;T&gt;	转换类型Class
     * @return    {@link List}&lt;T&gt;
     */
    public <T> List<T> fromResultSetList(ResultSet rs, Class<T> clsName, String[] excludeColumns) {
        List<T> list = new ArrayList<T>();
        try {
            @SuppressWarnings("unchecked")
            Class<T> classEntity = (Class<T>) Class.forName(clsName.getName());
            ClassField[] fields = ClassConvertUtils.getClassFields(classEntity);
            findColumnMetaData(rs, fields);
            while (rs.next()) {
                list.add(convertTOBean(rs, clsName, fields, excludeColumns));
            }
        } catch (ClassNotFoundException e) {
            logger.error("初始化类时出错，需要初始化的类为：" + clsName.getName(), e);
            e.printStackTrace();
        } catch (SQLException e) {
            logger.error("读取数据记录出错：", e);
            e.printStackTrace();
        } finally {
        }
        return list;
    }


    private void findColumnMetaData(ResultSet rs, ClassField[] fields) {
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            for (ClassField field : fields) {
                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    String metaLable = metaData.getColumnName(i + 1);
                    if (metaLable.equalsIgnoreCase(field.getColumnName())) {
                        field.setDbMetaLabel(metaLable);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private <T> Method findMethod(Class<T> clazz, String method, Class<?>[] params) {
        Method[] methods = clazz.getDeclaredMethods();
        if (methods != null && methods.length > 0) {
            for (Method m : methods) {
                if (m.getName().equals(method) && Arrays.equals(m.getParameterTypes(), params)) return m;
            }
        }
        return null;
    }

    /**
     * 转换Map列表到实体
     *
     * @param list  {@link List}&lt; {@link MapEntity} &gt; 待转换List
     * @param clazz {@link Class}&lt;T&gt; 目的类型
     * @return    {@link List}&lt;T&gt;
     */
    public <T> List<T> convertToBeanList(List<MapEntity> list, Class<T> clazz) {
        if (list != null) {
            List<T> result = new ArrayList<T>();
            for (MapEntity map : list) {
                result.add(convertTOBean(map, clazz));
            }
            return result;
        }
        return null;
    }

    /**
     * 转换Map对象为实体
     *
     * @param map   {@link MapEntity} 通用对象
     * @param clazz {@link Class}&lt;T&gt;	转换类型Class
     * @return T
     */
    public <T> T convertTOBean(MapEntity map, Class<T> clazz) {
        T result = null;
        try {
            result = (T) clazz.newInstance();
            for (String key : map.keySet()) {
                String metStr = key.substring(0, 1).toUpperCase() + key.substring(1);
                //Class<?> clz = map.get(key).getClass();
                Object val = map.get(key);
                //System.out.println(metStr + "/" + clz.getName());

                Method meth = findMethod(clazz, "set" + metStr, new Class<?>[]{val.getClass()});
                if (meth != null) {
                    meth.invoke(result, val);
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return result;
    }


    public <T> T convertTOBean(ResultSet rs, Class<T> clazz, ClassField[] fields) {
        return convertTOBean(rs, clazz, fields, null);
    }

    /**
     * 转换ResultSet到对象
     *
     * @param rs             {@link ResultSet}	ResultSet对象
     * @param clazz          {@link Class}&lt;T&gt; 目标Class类型
     * @param fields         {@link ClassField}[] 属性列表
     * @param excludeColumns String[] 不需要转换返回的属性名称
     * @return T
     * @author Aidy
     */
    public <T> T convertTOBean(ResultSet rs, Class<T> clazz, ClassField[] fields, String[] excludeColumns) {
        T result = null;
        try {
            result = (T) clazz.newInstance();

            for (ClassField field : fields) {
                if (!field.getDbMetaLabel().equals("") && field.getField() != null && field.getSetterMethod() != null) {
                    if (excludeColumns == null || (excludeColumns != null && !ArrayUtil.findString(excludeColumns, field.getName()))) {

                        //System.out.println("FIELDLAB:" + field.getDbMetaLabel());
                        //System.out.println("ARG TYPE:" + field.getField().getType());
                        Object objValue = rs.getObject(field.getDbMetaLabel());
                        if (field.getField().getType() == String.class && objValue != null)
                            objValue = objValue.toString();
                        if (field.getField().getType() == Double.class && objValue != null)
                            objValue = Double.parseDouble(objValue.toString());
                        if ((field.getField().getType() == Integer.class || field.getField().getType() == int.class) && objValue != null)
                            objValue = Integer.parseInt(objValue.toString());
                        if (field.getField().getType() == Date.class && objValue != null && !objValue.equals(""))
                            objValue = Date.valueOf(objValue.toString());
                        //System.out.println("field:" + field.getDbMetaLabel() + ":("+objValue+")"+(objValue==null)+"/" +field.getField().getType());
                        if (
                                (field.getField().getType() != Timestamp.class && field.getField().getType() != Date.class)
                                        || ((field.getField().getType() == Timestamp.class || field.getField().getType() == Date.class) && objValue != null && !objValue.equals(""))
                                ) {
                            field.getSetterMethod().invoke(result, new Object[]{objValue});
                        }
                    }
                    //System.out.println("field:" + field.getDbMetaLabel() + "FIN");
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }


    /*private Object getFieldDefaultValue(ClassField field) {
        Object obj = "";
        if (field.getField().getType() == String.class)
            obj = "";
        if (field.getField().getType() == Integer.class)
            obj = 0;
        if (field.getField().getType() == Double.class)
            obj = 0.0;
        return obj;
    }*/
}
