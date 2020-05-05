package com.h.cheng.base.base;

import com.h.cheng.base.R;
import com.h.cheng.base.api.BasePresenter;

/**
 * @author ch
 * @date 2020/4/27-19:09
 * desc
 */
public class BasePageActivity extends BaseActivity {
    @Override
    protected BasePresenter createPresenter() {
        return presenter;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ac_base_page;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void addListener() {

    }
}
