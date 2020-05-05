package com.ch.cutils;

import android.content.Intent;
import android.view.View;

import com.ch.cutils.databinding.ActivityMainBinding;
import com.h.cheng.base.api.BasePresenter;
import com.h.cheng.base.base.BaseActivity;

public class MainActivity extends BaseActivity<ActivityMainBinding, BasePresenter> implements View.OnClickListener {

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void addListener() {
        binding.btnSimpleList.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn_simple_list:
                intent.setClass(context, ListActivity.class);
                break;

            default:
        }
        startActivity(intent);
    }
}
