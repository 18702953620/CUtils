package com.h.cheng.http.request;

import com.h.cheng.http.PsHttp;
import com.h.cheng.http.parser.Parser;
import com.h.cheng.http.parser.ResponseListParser;
import com.h.cheng.http.parser.ResponseParser;

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

    protected String url;
    protected Method method;
    private Headers.Builder headBuilder;

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
