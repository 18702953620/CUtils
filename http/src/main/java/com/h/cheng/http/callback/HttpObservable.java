package com.h.cheng.http.callback;

import com.h.cheng.http.parser.Parser;

import org.reactivestreams.Subscriber;

import java.io.IOException;

import io.reactivex.Flowable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.subscriptions.DeferredScalarSubscription;
import io.reactivex.plugins.RxJavaPlugins;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author ch
 * @date 2020/6/10-11:27
 * @desc
 */
public class HttpObservable<T> extends Flowable<T> {

    private Call mCall;
    private OkHttpClient okClient;
    private Request request;
    private Parser<T> parser;


    public HttpObservable(OkHttpClient okClient, Request request, Parser<T> parser) {
        this.okClient = okClient;
        this.request = request;
        this.parser = parser;
    }

    @Override
    protected void subscribeActual(Subscriber<? super T> subscriber) {
        HttpDisposable d = new HttpDisposable(subscriber);
        subscriber.onSubscribe(d);
        if (d.isCancelled()) {
            return;
        }
        T value;
        try {
            value = execute(request);
        } catch (Throwable e) {
            Exceptions.throwIfFatal(e);
            if (!d.isCancelled()) {
                subscriber.onError(e);
            } else {
                RxJavaPlugins.onError(e);
            }
            return;
        }
        d.complete(value);
    }

    /**
     * 执行请求
     *
     * @param request request
     * @return T
     */
    private T execute(Request request) {
        mCall = newCall(okClient, request);
        Response networkResponse = null;
        try {
            networkResponse = mCall.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parser.parser(networkResponse);
    }


    /**
     * 所有的请求，最终都会调此方法拿到Call对象，然后执行请求
     *
     * @param client  client
     * @param request request
     * @return Call
     */
    public Call newCall(OkHttpClient client, Request request) {
        return client.newCall(request);
    }


    class HttpDisposable extends DeferredScalarSubscription<T> {
        /**
         * Creates a DeferredScalarSubscription by wrapping the given Subscriber.
         *
         * @param downstream the Subscriber to wrap, not null (not verified)
         */
        public HttpDisposable(Subscriber<? super T> downstream) {
            super(downstream);
        }


        @Override
        public void cancel() {
            cancelRequest(mCall);
            super.cancel();
        }
    }


    /**
     * 关闭请求
     *
     * @param call call
     */
    private void cancelRequest(Call call) {
        if (call != null && !call.isCanceled()) {
            call.cancel();
        }
    }
}
