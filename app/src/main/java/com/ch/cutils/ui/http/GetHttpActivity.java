package com.ch.cutils.ui.http;

import android.view.View;

import com.alibaba.fastjson.JSON;
import com.ch.cutils.R;
import com.ch.cutils.bean.ArticleModel;
import com.ch.cutils.databinding.AcGetHttpBinding;
import com.h.cheng.base.api.BasePresenter;
import com.h.cheng.base.base.BaseActivity;
import com.h.cheng.http.PsHttp;
import com.h.cheng.http.callback.BaseSubscriber;

import java.util.List;

/**
 * @author ch
 * @date 2020/8/14-10:36
 * @desc get/post
 */
public class GetHttpActivity extends BaseActivity<AcGetHttpBinding, BasePresenter> {
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ac_get_http;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void addListener() {

        binding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.rbGet.isChecked()) {
                    get();
                } else if (binding.rbPost.isChecked()) {
                    post();
                } else if (binding.rbJson.isChecked()) {
                    postJson();
                }

            }
        });

    }

    private void postJson() {
        String url = "https://www.wanandroid.com/lg/collect/add/json";

        PsHttp.postJson(url)
                .addParam("title", "收藏链接" + System.currentTimeMillis())
                .addParam("author", "001")
                .addParam("link", "https://www.jianshu.com/p/1ed80a1b761d")
                .asResponseList(ArticleModel.class)
                .subscribe(new BaseSubscriber<List<ArticleModel>>() {
                    @Override
                    public void onSuccess(List<ArticleModel> o) {
                        binding.tvContent.setText(JSON.toJSONString(o));
                    }

                    @Override
                    public void onError(String msg) {
                        showToast(msg);
                    }
                });
    }

    private void post() {
        String url = "https://www.wanandroid.com/lg/collect/add/json";
        PsHttp.post(url)
                .addParam("title", "收藏链接" + System.currentTimeMillis())
                .addParam("author", "001")
                .addParam("link", "https://www.jianshu.com/p/1ed80a1b761d")
                .asResponseList(ArticleModel.class)
                .subscribe(new BaseSubscriber<List<ArticleModel>>() {
                    @Override
                    public void onSuccess(List<ArticleModel> o) {
                        binding.tvContent.setText(JSON.toJSONString(o));
                    }

                    @Override
                    public void onError(String msg) {
                        showToast(msg);
                    }
                });
    }


    private void get() {
        String url = "https://www.wanandroid.com/project/list/1/json";
        PsHttp.get(url)
                .addUrlParam("cid", 294)
                .asResponseList(ArticleModel.class)
                .subscribe(new BaseSubscriber<List<ArticleModel>>() {
                    @Override
                    public void onSuccess(List<ArticleModel> o) {
                        binding.tvContent.setText(JSON.toJSONString(o));
                    }

                    @Override
                    public void onError(String msg) {
                        showToast(msg);
                    }
                });
    }
}
