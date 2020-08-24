package com.ch.cutils.ui.http;

import android.view.View;

import com.ch.cutils.R;
import com.ch.cutils.databinding.AcHttpBinding;
import com.h.cheng.base.api.BasePresenter;
import com.h.cheng.base.base.BaseActivity;

/**
 * @author ch
 * @date 2020/8/10-15:11
 * @desc http
 */
public class HttpActivity extends BaseActivity<AcHttpBinding, BasePresenter> implements View.OnClickListener {
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ac_http;
    }

    @Override
    protected void initView() {


    }

    @Override
    protected void addListener() {
        binding.btnGet.setOnClickListener(this);
        binding.btnPost.setOnClickListener(this);
        binding.btnJson.setOnClickListener(this);
        binding.btnUpload.setOnClickListener(this);
        binding.btnDownload.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get:
                break;
            default:
        }

    }
}
