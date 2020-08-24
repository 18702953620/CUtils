package com.h.cheng.http.request.base;

import com.h.cheng.http.PsHttp;
import com.h.cheng.http.callback.ObservableHttp;
import com.h.cheng.http.callback.ObservableListHttp;
import com.h.cheng.http.parser.Parser;
import com.h.cheng.http.parser.ResponseListParser;
import com.h.cheng.http.parser.ResponseParser;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @author ch
 * @date 2020/6/9-17:31
 * @desc
 */
public class BaseRequest implements IRequest, IHeader {
    /**
     * 请求链接
     */
    protected String url;
    /**
     * 请求方式
     */
    protected Method method;
    /**
     * 请求头
     */
    private Headers.Builder headBuilder;
    /**
     * url 参数
     */
    protected HashMap<String, String> urlParams;
    /**
     * body 参数
     */
    protected HashMap<String, String> bodyParams;

    @Override
    public Headers getHeaders() {
        return headBuilder == null ? null : headBuilder.build();
    }

    @Override
    public String getHeader(String key) {
        if (headBuilder != null) {
            return headBuilder.get(key);
        }
        return null;
    }

    @Override
    public void addHeader(String key, String value) {
        if (headBuilder == null) {
            headBuilder = new Headers.Builder();
        }
        headBuilder.add(key, value);
    }

    @Override
    public String url() {
        return url;
    }

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public RequestBody getRequestBody() {
        return null;
    }

    @Override
    public Request buildRequest() {
        //添加 url 参数
        addParamToUrl();

        Request.Builder builder = new Request.Builder();
        builder.url(url)
                .method(method.name(), getRequestBody());
        Headers headers = getHeaders();
        if (headers != null) {
            builder.headers(headers);
        }
        return builder.build();
    }


    /**
     * 修改 url
     *
     * @param url url
     */
    public void url(String url) {
        this.url = url;
    }


    /**
     * 添加 url 参数
     * <p>
     * 该参数会拼接到请求链接后面
     *
     * @param key   key
     * @param value value
     */
    public BaseRequest addUrlParam(String key, Object value) {
        if (urlParams == null) {
            urlParams = new HashMap<>();
        }
        urlParams.put(key, String.valueOf(value));
        return this;
    }

    /**
     * 添加 body 参数
     * <p>
     * GET 请求时，该参数不会生效
     * POST 请求时，该参数会添加在请求体重
     *
     * @param key   key
     * @param value value
     */
    public BaseRequest addBodyParam(String key, Object value) {
        if (bodyParams == null) {
            bodyParams = new HashMap<>();
        }
        bodyParams.put(key, String.valueOf(value));
        return this;
    }

    /**
     * 添加 url 参数到 url
     */
    private void addParamToUrl() {
        if (urlParams != null && urlParams.size() > 0) {
            StringBuilder result = new StringBuilder();
            for (String key : urlParams.keySet()) {
                if (result.length() == 0) {
                    result.append(url).append("?");
                } else {
                    result.append("&");
                }
                result.append(key).append("=").append(urlParams.get(key));
            }
            //拼接参数
            url(result.toString());
        }
    }


    /**
     * obj 返回
     *
     * @param tClass tClass
     * @param <T>    tClass
     * @return Flowable
     */
    public <T> Flowable<T> asResponse(Class<T> tClass) {
        return asParser(new ResponseParser<>(tClass));
    }

    /**
     * list 返回
     *
     * @param tClass tClass
     * @param <T>    tClass
     * @return Flowable
     */
    public <T> Flowable<List<T>> asResponseList(Class<T> tClass) {
        return asListParser(new ResponseListParser<>(tClass));
    }

    /**
     * object 解析
     *
     * @param parser 解析器
     * @param <T>    类型
     * @return Flowable
     */
    private <T> Flowable<T> asParser(Parser<T> parser) {
        return new ObservableHttp<>(PsHttp.getDefaultOkHttpClient(), buildRequest(), parser).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * list 解析
     *
     * @param parser 解析器
     * @param <T>    类型
     * @return Flowable
     */
    private <T> Flowable<List<T>> asListParser(Parser<T> parser) {
        return new ObservableListHttp<>(PsHttp.getDefaultOkHttpClient(), buildRequest(), parser).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
