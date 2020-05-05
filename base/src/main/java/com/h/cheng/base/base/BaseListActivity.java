package com.h.cheng.base.base;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.h.cheng.base.R;
import com.h.cheng.base.api.BasePresenter;
import com.h.cheng.base.databinding.AcBaseListBinding;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

/**
 * @author ch
 * @date 2020/4/27-17:14
 * desc 基础 列表
 */
public class BaseListActivity<P extends BasePresenter> extends BaseActivity<AcBaseListBinding, P> {

    @Override
    protected P createPresenter() {
        return presenter;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ac_base_list;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void addListener() {
        binding.srlBaseList.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMore(refreshLayout);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refresh(refreshLayout);
            }
        });
    }

    /**
     * 设置标题
     *
     * @param text text
     */
    public void setTitle(@NonNull String text) {
        if (binding != null) {
            binding.title.setTitle(text);
        }
    }

    /**
     * 设置是否可以刷新 和加载更多
     *
     * @param refresh  refresh
     * @param loadmore loadmore
     */
    public void setRefreshLoadMore(boolean refresh, boolean loadmore) {
        if (binding != null) {
            binding.srlBaseList.setEnableRefresh(refresh);
            binding.srlBaseList.setEnableLoadMore(loadmore);
        }
    }

    /**
     * 刷新
     *
     * @param refreshLayout refreshLayout
     */
    protected void refresh(RefreshLayout refreshLayout) {

    }

    /**
     * 更多
     *
     * @param refreshLayout refreshLayout
     */
    protected void loadMore(RefreshLayout refreshLayout) {

    }

    /**
     * 设置 adapter
     *
     * @param quickAdapter quickAdapter
     */
    protected void setAdapter(@NonNull BaseQuickAdapter quickAdapter) {
        if (binding != null) {
            binding.rvBaseList.setLayoutManager(new LinearLayoutManager(context));
            binding.rvBaseList.setAdapter(quickAdapter);
        }
    }

    /**
     * 获取RecyclerView
     *
     * @return RecyclerView
     */
    public RecyclerView getRecyclerView() {
        return binding.rvBaseList;
    }
}
