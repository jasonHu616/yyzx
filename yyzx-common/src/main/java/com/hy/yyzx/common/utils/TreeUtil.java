package com.hy.yyzx.common.utils;

import com.hy.yyzx.common.modules.MapEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author DGY 2019年8月27日 15:43:01
 */
public class TreeUtil implements Serializable {
    static Logger logger = LoggerFactory.getLogger(TreeUtil.class);

    /**
     * 对象转换为Map
     *
     * @param object
     * @param filters
     * @return
     * @throws IllegalAccessException
     */
    private static MapEntity toMap(Object object, Map<String, String> filters) throws IllegalAccessException {
        MapEntity map = new MapEntity();
        Class<?> clz = object.getClass();
        while (!clz.equals(Object.class)) {
            Field[] fields = clz.getDeclaredFields();
            if (fields != null) {
                for (Field f : fields) {
                    if (!java.lang.reflect.Modifier.isFinal(f.getModifiers()) && (filters == null || filters.get(f.getName()) != null)) {
                        f.setAccessible(true);
                        map.put(filters == null ? f.getName() : filters.get(f.getName()), f.get(object));
                        f.setAccessible(false);
                    }
                }
            }
            clz = clz.getSuperclass();
        }
        return map;
    }

    private static List<MapEntity> findChild(Collection<?> datalist, Integer parentid, Map<String, String> filters) {
        List<MapEntity> list = null;
        if (datalist != null) {
            for (Object o : datalist) {
                try {
                    Field field = o.getClass().getDeclaredField("parentid");
                    field.setAccessible(true);
                    Integer pid = (Integer) field.get(o);
                    if ((pid == null && parentid == null) || (pid != null && parentid != null && pid.intValue() == parentid.intValue())) {
                        if (list == null) list = new ArrayList<>();
                        MapEntity map = toMap(o, filters);
                        List<MapEntity> children = findChild(datalist, map.getInt("id"), filters);
                        if (children != null) map.put("children", children);
                        list.add(map);
                    }
                    field.setAccessible(false);
                } catch (Exception e) {
                    logger.error("转换错误", e);
                }
            }
        }
        return list;
    }

    /**
     * @param datalist
     * @param filters
     * @return
     */
    public static List<MapEntity> getTreeData(Collection<?> datalist, Map<String, String> filters,Integer parentid) {
        return findChild(datalist, parentid, filters);
    }
}
