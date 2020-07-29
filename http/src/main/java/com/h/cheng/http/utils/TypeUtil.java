package com.h.cheng.http.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author ch
 * @date 2020/6/10-14:31
 * @desc
 */
public class TypeUtil {


    /**
     * 获取泛型类型
     *
     * @param clazz 类类型
     * @param index 第几个泛型
     * @return Type
     */
    public static Type getActualTypeParameter(Class clazz, int index) {
        Type superclass = clazz.getGenericSuperclass();
        if (!(superclass instanceof ParameterizedType)) {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameter = (ParameterizedType) superclass;

        return parameter.getActualTypeArguments()[index];
    }
}
