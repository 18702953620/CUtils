package com.ch.cutils.presenter;

import com.ch.cutils.AppApiServer;
import com.ch.cutils.bean.ArticleModel;
import com.ch.cutils.view.AppView;
import com.h.cheng.base.api.ApiRetrofit;
import com.h.cheng.base.api.BasePresenter;
import com.h.cheng.base.api.BaseSubscriber;

import java.util.List;

/**
 * @author ch
 * @date 2020/4/27-18:41
 * desc
 */
public class AppPresenter extends BasePresenter<AppView> {

    protected AppApiServer appApiServer = ApiRetrofit.getInstance().getService(AppApiServer.class);

    public AppPresenter(AppView baseView) {
        super(baseView);
    }

    /**
     * 获取文章列表
     */
    public void getWxArticleList() {
        addDisposable(appApiServer.getArticleList(), new BaseSubscriber<List<ArticleModel>>(baseView) {
            @Override
            public void onSuccess(List<ArticleModel> o) {
                baseView.onGetListSucc(o);
            }

            @Override
            public void onError(String msg) {
                baseView.showError(msg);
            }
        });
    }
}
