package com.ch.cutils;

import com.ch.cutils.adapter.ListAdapter;
import com.ch.cutils.bean.ArticleModel;
import com.ch.cutils.presenter.AppPresenter;
import com.ch.cutils.view.AppView;
import com.h.cheng.base.base.BaseListActivity;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ch
 * @date 2020/4/27-17:53
 * desc
 */
public class ListActivity extends BaseListActivity<AppPresenter> implements AppView {

    private ListAdapter listAdapter;

    @Override
    protected AppPresenter createPresenter() {
        return new AppPresenter(this);
    }

    @Override
    protected void initView() {
        setTitle("列表Demo");
        listAdapter = new ListAdapter(null);
        setAdapter(listAdapter);
        presenter.getWxArticleList();
    }

    @Override
    protected void refresh(RefreshLayout refreshLayout) {
        ArticleModel articleModel = new ArticleModel();
        articleModel.setName("refresh");
        List<ArticleModel> models = new ArrayList<>();
        models.add(articleModel);
        listAdapter.setNewData(models);
        refreshLayout.finishRefresh(500);
    }

    @Override
    protected void loadMore(RefreshLayout refreshLayout) {
        ArticleModel articleModel = new ArticleModel();
        articleModel.setName("loadMore");
        listAdapter.addData(articleModel);
        refreshLayout.finishLoadMore(500);
    }

    @Override
    public void onGetListSucc(List<ArticleModel> o) {
        listAdapter.setNewData(o);
    }
}
