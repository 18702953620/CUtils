package com.ch.cutils;

import com.ch.cutils.bean.ArticleModel;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * @author ch
 * @date 2020/4/27-18:38
 * desc
 */
public interface AppApiServer {

    @GET("wxarticle/chapters/json")
    Flowable<List<ArticleModel>> getArticleList();
}
