package com.ch.cutils.ui;

import android.graphics.Color;

import androidx.fragment.app.Fragment;

import com.ch.cutils.fragment.TabFragment;
import com.ch.cutils.presenter.AppPresenter;
import com.h.cheng.base.base.BasePageActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ch
 * @date 2020/7/1-17:57
 * @desc
 */
public class TabFragmentActivity extends BasePageActivity<AppPresenter> {
    @Override
    protected Fragment buildFragment(int position) {
        TabFragment tabFragment = new TabFragment();
        return tabFragment;
    }

    @Override
    protected List<String> buildTitles() {
        List<String> lists = new ArrayList<>();
        lists.add("Tab1");
        lists.add("Tab2");
        return lists;
    }

    @Override
    protected void initView() {
        super.initView();
        setTitle("Tabs Fragment Demo");
        setTabIndicatorColor(Color.parseColor("#00ff00"));
        setTabTextColors(Color.parseColor("#00ff00"), Color.parseColor("#0000ff"));
    }
}
