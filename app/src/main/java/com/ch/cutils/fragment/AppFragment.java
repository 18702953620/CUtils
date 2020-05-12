package com.ch.cutils.fragment;

import android.graphics.Color;

import com.ch.cutils.R;
import com.ch.cutils.databinding.FmAppBinding;
import com.h.cheng.base.api.BasePresenter;
import com.h.cheng.base.base.BaseFragment;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author ch
 * @date 2020/5/5-10:19
 * desc
 */
public class AppFragment extends BaseFragment<FmAppBinding, BasePresenter> {


    @Override
    protected void loadData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fm_app;
    }

    @Override
    protected void initView() {
        ArrayList<Integer> allColors = new ArrayList<>();
        allColors.add(Color.parseColor("#5890FF"));
        allColors.add(Color.parseColor("#8E30FF"));
        allColors.add(Color.parseColor("#FF7878"));
        allColors.add(Color.parseColor("#FBD06C"));
        allColors.add(Color.parseColor("#2CC197"));
        allColors.add(Color.parseColor("#48DB5B"));
        getRootView().setBackgroundColor(allColors.get(new Random().nextInt(6)));
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
