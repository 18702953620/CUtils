package com.ch.cutils.ui;

import android.graphics.Color;

import androidx.fragment.app.Fragment;

import com.ch.cutils.fragment.AppFragment;
import com.ch.cutils.presenter.AppPresenter;
import com.h.cheng.base.base.BasePageActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ch
 * @date 2020/5/5-10:17
 * desc Tabs
 */
public class TabsActivity extends BasePageActivity<AppPresenter> {

    @Override
    protected void initView() {
        super.initView();
        setTitle("Tabs Demo");
        setTabIndicatorColor(Color.parseColor("#00ff00"));
        setTabTextColors(Color.parseColor("#00ff00"), Color.parseColor("#0000ff"));
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
        list.add("TAB 3");
        list.add("TAB 4");
        return list;
    }
}
