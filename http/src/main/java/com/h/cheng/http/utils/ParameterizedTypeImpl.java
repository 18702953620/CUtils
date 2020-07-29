package com.h.cheng.http.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

/**
 * @author ch
 * @date 2020/6/10-16:24
 * @desc
 */
public class ParameterizedTypeImpl implements ParameterizedType {

    private Type ownerType;
    private Type rawType;
    private Type[] actualTypeArguments;

    public ParameterizedTypeImpl(Type rawType, Type[] actualTypeArguments) {
        this.rawType = rawType;
        this.actualTypeArguments = actualTypeArguments;
    }

    public ParameterizedTypeImpl(Type ownerType, Type rawType, Type[] actualTypeArguments) {
        this.ownerType = ownerType;
        this.rawType = rawType;
        this.actualTypeArguments = actualTypeArguments;
    }

    @NonNull
    @Override
    public Type[] getActualTypeArguments() {
        return actualTypeArguments;
    }

    @NonNull
    @Override
    public Type getRawType() {
        return rawType;
    }

    @Nullable
    @Override
    public Type getOwnerType() {
        return ownerType;
    }


    public static ParameterizedTypeImpl get(Type rawType, Type[] types) {
        int length = types.length;
        if (length > 1) {
            Type parameterizedType = new ParameterizedTypeImpl(types[length - 2], new Type[]{types[length - 1]});
            Type[] newTypes = Arrays.copyOf(types, length - 1);
            newTypes[newTypes.length - 1] = parameterizedType;
            return get(rawType, newTypes);
        }
        return new ParameterizedTypeImpl(rawType, new Type[]{types[0]});

    }


    //适用于多个泛型参数的类
    public static ParameterizedTypeImpl getParameterized(Type rawType, Type[] actualType) {
        return new ParameterizedTypeImpl(null, rawType, actualType);
    }
}
