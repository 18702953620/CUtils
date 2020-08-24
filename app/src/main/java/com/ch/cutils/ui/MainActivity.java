package com.ch.cutils.ui;

import android.content.Intent;
import android.view.View;

import com.ch.cutils.R;
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
        binding.btnTabs.setOnClickListener(this);
        binding.btnTabs2.setOnClickListener(this);
        binding.btnPsView.setOnClickListener(this);
        binding.btnTabView.setOnClickListener(this);
        binding.btnSimpleFragment.setOnClickListener(this);
        binding.btnPreview.setOnClickListener(this);
        binding.btnWeb.setOnClickListener(this);
        binding.btnPick.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            //ps
            case R.id.btn_ps_view:
                intent.setClass(context, PsViewActivity.class);
                break;
            //仿美团tab
            case R.id.btn_tab_view:

                break;
            //简单列表
            case R.id.btn_simple_list:
                intent.setClass(context, ListActivity.class);
                break;
            //viewpager + tablayout
            case R.id.btn_tabs:
                intent.setClass(context, TabsActivity.class);
                break;
            //viewpager2 + tablayout
            case R.id.btn_tabs2:
                intent.setClass(context, Tabs2Activity.class);
                break;

            case R.id.btn_simple_fragment:
                intent.setClass(context, TabFragmentActivity.class);
                break;
            //大图预览
            case R.id.btn_preview:
                intent.setClass(context, ImgListActivity.class);
                break;
            //网页
            case R.id.btn_web:
                intent.setClass(context, WebDemoActivity.class);
                break;
            case R.id.btn_pick:
                intent.setClass(context, PickActivity.class);
                break;

            default:
        }
        startActivity(intent);
    }
}
