package com.h.cheng.http.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;

import java.io.IOException;
import java.util.List;

import okhttp3.Response;
import okio.BufferedSource;
import okio.Okio;

/**
 * @author ch
 * @date 2020/6/10-14:29
 * @desc
 */
public class ResponseParser<T> implements Parser<T> {

    private Class<T> tClass;

    public ResponseParser(Class<T> tClass) {
        this.tClass = tClass;
    }

    @Override
    public T parser(Response response) {
        BufferedSource buffer = Okio.buffer(response.body().source());
        try {
            String jsonString = buffer.readUtf8();
            JSONObject object = JSON.parseObject(jsonString);
            int statusCode = object.getIntValue("errorCode");
            if (statusCode == 0) {
                return JSON.parseObject(object.getString("data"), tClass, Feature.SupportNonPublicField);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                buffer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<T> parserList(Response response) {
        return null;
    }
}
