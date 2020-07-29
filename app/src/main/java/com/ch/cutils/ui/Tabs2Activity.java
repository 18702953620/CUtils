package com.ch.cutils.ui;

import android.graphics.Color;

import androidx.fragment.app.Fragment;

import com.ch.cutils.adapter.ListAdapter;
import com.ch.cutils.bean.ArticleModel;
import com.ch.cutils.fragment.AppFragment;
import com.ch.cutils.presenter.AppPresenter;
import com.ch.cutils.view.AppView;
import com.h.cheng.base.base.BaseListFragment;
import com.h.cheng.base.base.BasePage2Activity;
import com.h.cheng.base.base.BasePageActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ch
 * @date 2020/5/5-10:17
 * desc Tabs2
 */
public class Tabs2Activity extends BasePage2Activity<AppPresenter> implements AppView {

    private BaseListFragment fragment1;
    private BaseListFragment fragment2;
    private ListAdapter listAdapter1;
    private ListAdapter listAdapter2;

    @Override
    protected AppPresenter createPresenter() {
        return new AppPresenter(this);
    }

    @Override
    protected void initView() {
        super.initView();
        setTitle("Tabs2 Demo");
        setTabIndicatorColor(Color.parseColor("#00ff00"));
        setTabTextColors(Color.parseColor("#00ff00"), Color.parseColor("#0000ff"));
        fragment1 = new BaseListFragment();
        fragment2 = new BaseListFragment();


        listAdapter1 = new ListAdapter(null);
        listAdapter2 = new ListAdapter(null);

        fragment1.setAdapter(listAdapter1);
        fragment2.setAdapter(listAdapter2);

        presenter.getWxArticleList();
    }

    @Override
    protected Fragment buildFragment(int position) {
        return new AppFragment();
    }

    @Override
    protected List<String> buildTitles() {
        List<String> list = new ArrayList<>();
        list.add("TAB 1");
        list.add("TAB 2");
        return list;
    }

    @Override
    public void onGetListSucc(List<ArticleModel> o) {
        listAdapter1.setNewData(o);
        listAdapter2.setNewData(o);
    }
}
