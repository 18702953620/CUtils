package com.h.cheng.http.callback;


import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @author ch
 * 时间： 2019/11/21 14:05
 * 描述：
 * 来源：
 */
public abstract class BaseSubscriber<T> extends DisposableSubscriber<T> {


    private boolean isShowDialog;


    public BaseSubscriber() {
    }

    public BaseSubscriber(boolean isShowDialog) {
        this.isShowDialog = isShowDialog;
    }

    @Override
    protected void onStart() {
        super.onStart();
//        if (view != null && isShowDialog) {
//            view.showLoading();
//        }
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
//        if (view != null && isShowDialog) {
//            view.hideLoading();
//        }
        BaseException be = null;

        if (e != null) {

            if (e instanceof ConnectException
                    || e instanceof UnknownHostException) {
                //   连接错误
                be = new BaseException(BaseException.CONNECT_ERROR_MSG, e, BaseException.CONNECT_ERROR);
            } else if (e instanceof InterruptedIOException) {
                //  连接超时
                be = new BaseException(BaseException.CONNECT_TIMEOUT_MSG, e, BaseException.CONNECT_TIMEOUT);
            } else if (e instanceof JSONException
                    || e instanceof ParseException) {
                //  解析错误
                be = new BaseException(BaseException.PARSE_ERROR_MSG, e, BaseException.PARSE_ERROR);
            } else {
                be = new BaseException(BaseException.OTHER_MSG, e, BaseException.OTHER);
            }
        }

        if (be != null) {
            onError(be.getErrorMsg());
        } else {
            onError(BaseException.OTHER_MSG);
        }
    }

    @Override
    public void onComplete() {
//        if (view != null && isShowDialog) {
//            view.hideLoading();
//        }
    }

    /**
     * 请求成功
     *
     * @param o o
     */
    public abstract void onSuccess(T o);

    /**
     * 请求失败
     *
     * @param msg 信息
     */
    public abstract void onError(String msg);
}
