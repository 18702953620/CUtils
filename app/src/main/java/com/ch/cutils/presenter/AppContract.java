package com.ch.cutils.presenter;

import com.ch.cutils.bean.ArticleModel;
import com.h.cheng.base.api.BaseView;

import java.util.List;

/**
 * @author ch
 * @date 2020/5/5-16:44
 * desc
 */
public interface AppContract {

    interface View extends BaseView {
        void onGetListSucc(List<ArticleModel> o);

        void onGetInfoSucc(ArticleModel o);
    }

    interface Presenter {
        void getWxArticleList();
    }
}
