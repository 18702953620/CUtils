package com.h.cheng.http.parser;

import java.util.List;

import okhttp3.Response;

/**
 * @author ch
 * @date 2020/6/10-14:06
 * @desc
 */
public interface Parser<T> {
    /**
     * 解析实体
     *
     * @param response response
     * @return T
     */
    T parser(Response response);

    /**
     * 解析实体
     *
     * @param response response
     * @return T
     */
    List<T> parserList(Response response);
}
