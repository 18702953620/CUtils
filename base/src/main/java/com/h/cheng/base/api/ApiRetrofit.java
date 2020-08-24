package com.h.cheng.base.api;

import android.util.Log;


import androidx.annotation.NonNull;

import com.h.cheng.base.api.fastjson.FastJsonConverterFactory;
import com.h.cheng.base.utils.AppUtils;

import java.io.IOException;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * @author ch
 * 时间： 2016/12/27.13:56
 * 描述：ApiRetrofit
 * 来源：
 */
public class ApiRetrofit {

    private static final String BASE_SERVER_URL = "https://wanandroid.com/";

    private static ApiRetrofit apiRetrofit;
    private ApiServer apiServer;
    private Retrofit retrofit;

    private static final String TAG = "ApiRetrofit";

    /**
     * 请求访问quest
     * response拦截器
     */
    private Interceptor interceptor = new Interceptor() {
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
                    .body(ResponseBody.create(mediaType, content))
                    .build();
        }
    };


    private ApiRetrofit() {
        //添加log拦截器
        OkHttpClient client = new OkHttpClient.Builder()
                //公共参数 链接替换
                .addInterceptor(new BasicParamsInterceptor.Builder().build())
                //添加log拦截器
                .addInterceptor(interceptor)
                //禁用代理
                .proxy(Proxy.NO_PROXY)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

        //添加自定义的解析器
        //支持RxJava2
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_SERVER_URL)
                //添加自定义的解析器
                //支持RxJava2
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();

        apiServer = retrofit.create(ApiServer.class);
    }

    public static ApiRetrofit getInstance() {
        if (apiRetrofit == null) {
            synchronized (Object.class) {
                if (apiRetrofit == null) {
                    apiRetrofit = new ApiRetrofit();
                }
            }
        }
        return apiRetrofit;
    }

    public ApiServer getApiService() {
        return apiServer;
    }

    /**
     * 获取 ApiService
     *
     * @param serviceCls serviceCls
     * @param <T>        T
     * @return T
     */
    public <T> T getService(@NonNull final Class<T> serviceCls) {
        return retrofit.create(serviceCls);
    }
}
