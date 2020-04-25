package com.darben.execdatareids.common;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * @description: model工具类
 * @author: darben
 * @create: 2020-03-11 10:49
 */
public class ModelUtils {

    /**
     * 将model转化为map
     * @param model
     * @return
     */
    public static Map<String, Object> modelToMap(Object model) {
        if (model == null) {
            return new HashMap<String, Object>();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        Method method;
        Field[] fields1 = model.getClass().getSuperclass().getDeclaredFields(); // 超类属性
        Field[] fields2 = model.getClass().getDeclaredFields(); // 本类属性
        List<Field> list = new ArrayList<Field>(Arrays.asList(fields1));
        List<Field> list2 = Arrays.asList(fields2);
        list.addAll(list2);
        for (Field field : list) {
            boolean isStatic = Modifier.isStatic(field.getModifiers());
            if(isStatic) {
                continue;	//去除静态成员
            }
            String getMethodName = getMethodName(field.getName());
            try {
                method = model.getClass().getMethod(getMethodName);
                map.put(field.getName(), method.invoke(model));
            } catch (NoSuchMethodException | SecurityException e) {
                e.printStackTrace();
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    private static String getMethodName(String name) {
        if (name != null && name.length() > 0) {
            return "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
        }
        return name;
    }
}
