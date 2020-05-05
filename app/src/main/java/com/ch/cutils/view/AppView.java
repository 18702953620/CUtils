package com.ch.cutils.view;

import com.ch.cutils.bean.ArticleModel;
import com.h.cheng.base.api.BaseView;

import java.util.List;

/**
 * @author ch
 * @date 2020/4/27-18:41
 * desc
 */
public interface AppView extends BaseView {
    void onGetListSucc(List<ArticleModel> o);
}
