package com.h.cheng.base.api.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.Okio;
import retrofit2.Converter;

/**
 * @author ch
 * @date 2020/1/6 17:48
 * @desc FastJsonResponseBodyConverter
 */
public class FastJsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private Type type;

    FastJsonResponseBodyConverter(Type type) {
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        BufferedSource buffer = Okio.buffer(value.source());
        String jsonString = buffer.readUtf8();
        try {
            JSONObject object = JSON.parseObject(jsonString);
            int statusCode = object.getIntValue("errorCode");
            if (statusCode == 0) {
                Object data = object.get("data");

                if (null == data) {
                    //返回null 既不会走成功 也不会走失败
                    return (T) "";
                }
                if (data instanceof String) {
                    return (T) data;
                }
                return JSON.parseObject(object.getString("data"), type, Feature.SupportNonPublicField);
            }
            String msg = object.getString("errorMsg");
            throw new RuntimeException(msg);
        } catch (Exception e) {
            throw new JSONException(e.getMessage());
        } finally {
            value.close();
            buffer.close();
        }
    }
}
