package com.h.cheng.http;

import android.util.Log;

import com.h.cheng.http.request.FileRequest;
import com.h.cheng.http.request.GetRequest;
import com.h.cheng.http.request.JsonRequest;
import com.h.cheng.http.request.PostRequest;
import com.h.cheng.http.utils.AppUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author ch
 * @date 2020/6/9-17:13
 * @desc
 */
public class PsHttp {

    private static final String TAG = "PsHttp";

    private static OkHttpClient client;

    public PsHttp() {
        client = getDefaultOkHttpClient();
    }

    /**
     * post 请求
     *
     * @param url url
     * @return [PostRequest]
     */
    public static PostRequest post(String url) {
        return new PostRequest(url);
    }

    /**
     * get 请求
     *
     * @param url url
     * @return @GetRequest
     */
    public static GetRequest get(String url) {
        return new GetRequest(url);
    }

    /**
     * Json
     *
     * @param url url
     * @return @JsonRequest
     */
    public static JsonRequest postJson(String url) {
        return new JsonRequest(url);
    }

    /**
     * post file
     *
     * @param url url
     * @return @FileRequest
     */
    public static FileRequest postFile(String url) {
        return new FileRequest(url);
    }


    /**
     * 连接、读写超时均为10s
     *
     * @return 返回默认的OkHttpClient对象
     */
    public static OkHttpClient getDefaultOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(getLogInterceptor())
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();
    }

    /**
     * 请求访问quest
     * response拦截器
     */
    public static Interceptor getLogInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                long startTime = System.currentTimeMillis();
                Response response = chain.proceed(chain.request());
                long endTime = System.currentTimeMillis();
                long duration = endTime - startTime;
                MediaType mediaType = null;
                String content = null;
                if (response.body() != null) {
                    mediaType = response.body().contentType();
                    content = response.body().string();
                    Log.e(TAG, "----------Request Start----------------");
                    Log.e(TAG, "| " + request.toString() + request.headers().toString());
                    Log.e(TAG, "| Response:" + AppUtils.unicodeToutf8(content));
                    Log.e(TAG, "----------Request End:" + duration + "毫秒----------");
                }
                return response.newBuilder()
                        .body(ResponseBody.create(content, mediaType))
                        .build();
            }
        };
    }
}
