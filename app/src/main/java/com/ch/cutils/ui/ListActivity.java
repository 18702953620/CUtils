package com.ch.cutils.ui;

import com.ch.cutils.adapter.ListAdapter;
import com.ch.cutils.bean.ArticleModel;
import com.ch.cutils.presenter.AppContract;
import com.ch.cutils.presenter.AppPresenter;
import com.ch.cutils.presenter.AppPresenter2;
import com.ch.cutils.view.AppView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.h.cheng.base.base.BaseListActivity;
import com.h.cheng.http.PsHttp;
import com.h.cheng.http.callback.BaseSubscriber;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ch
 * @date 2020/4/27-17:53
 * desc
 */
public class ListActivity extends BaseListActivity<AppPresenter2> implements AppContract.View {

    private ListAdapter listAdapter;

    @Override
    protected AppPresenter2 createPresenter() {
        return new AppPresenter2(this);
    }

    @Override
    protected void initView() {
        super.initView();
        setTitle("列表Demo");
//        presenter.getWxArticleList();
        String url = "https://wanandroid.com/wxarticle/chapters/json";
//        PsHttp.get(url)
//                .asResponseList(ArticleModel.class)
//                .subscribe(new BaseSubscriber<List<ArticleModel>>() {
//                    @Override
//                    public void onSuccess(List<ArticleModel> o) {
//                        listAdapter.setNewData(o);
//                    }
//
//                    @Override
//                    public void onError(String msg) {
//                        showToast(msg);
//                    }
//                });

//        PsHttp.post(url)
//                .addParam("page", 1)
//                .asResponseList(ArticleModel.class)
//                .subscribe(new BaseSubscriber<List<ArticleModel>>() {
//                    @Override
//                    public void onSuccess(List<ArticleModel> o) {
//                        listAdapter.setNewData(o);
//                    }
//
//                    @Override
//                    public void onError(String msg) {
//                        showToast(msg);
//                    }
//                });


        PsHttp.postJson(url)
                .addParam("time", "2020-07-31")
                .addUrlParam("page", 2)
                .addUrlParam("name", "郭霖")
                .asResponseList(ArticleModel.class)
                .subscribe(new BaseSubscriber<List<ArticleModel>>() {
                    @Override
                    public void onSuccess(List<ArticleModel> o) {
                        listAdapter.setNewData(o);
                    }

                    @Override
                    public void onError(String msg) {
                        showToast(msg);
                    }
                });

    }

    @Override
    public BaseQuickAdapter<?, BaseViewHolder> getAdapter() {
        listAdapter = new ListAdapter(null);
        return listAdapter;
    }

    @Override
    public void refresh(RefreshLayout refreshLayout) {
        ArticleModel articleModel = new ArticleModel();
        articleModel.setName("refresh");
        List<ArticleModel> models = new ArrayList<>();
        models.add(articleModel);
        listAdapter.setNewData(models);
        refreshLayout.finishRefresh(500);
    }

    @Override
    public void loadMore(RefreshLayout refreshLayout) {
        ArticleModel articleModel = new ArticleModel();
        articleModel.setName("loadMore");
        listAdapter.addData(articleModel);
        refreshLayout.finishLoadMore(500);
    }

    @Override
    public void onGetListSucc(List<ArticleModel> o) {
        listAdapter.setNewData(o);
    }

    @Override
    public void onGetInfoSucc(ArticleModel o) {

    }
}
