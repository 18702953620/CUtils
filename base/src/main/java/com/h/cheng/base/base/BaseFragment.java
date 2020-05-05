package com.h.cheng.base.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.h.cheng.base.api.BasePresenter;


/**
 * @author ch
 * @date 2019/10/09 09:15
 * 基类Fragment
 */
public abstract class BaseFragment<B extends ViewDataBinding, P extends BasePresenter> extends Fragment {
    private View rootView;

    public Context context;
    /**
     * binding
     */
    protected B binding;

    /**
     * 控件是否初始化完成
     */
    private boolean isViewCreated;

    /**
     * 当前fragment是否加载过数据,如加载过数据，则不再加载
     */
    private boolean isLoadCompleted;
    /**
     * 是不是可见
     */
    private boolean isUiVisible;

    protected P presenter;


    /**
     * 懒加载,强制子类重写
     */
    protected abstract void loadData();

    /**
     * 获取布局id
     *
     * @return 布局id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化
     */
    protected abstract void initView();

    /**
     * 添加点击事件
     */
    protected abstract void addListener();

    /**
     * 创建 Presenter
     *
     * @return P
     */
    protected abstract P createPresenter();


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isUiVisible = isVisibleToUser;
        if (isVisibleToUser && isViewCreated && isUiVisible && !isLoadCompleted) {
            isLoadCompleted = true;
            loadData();
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (isViewCreated && isUiVisible) {
            loadData();
            isLoadCompleted = true;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        rootView = binding.getRoot();
        presenter = createPresenter();
        initView();
        addListener();
        isViewCreated = true;
        return rootView;
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        isUiVisible = !hidden;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onDestroy() {
        if (presenter != null) {
            presenter.detachView();
        }
        super.onDestroy();
    }

    /**
     * 打开指定的activity
     *
     * @param cls Class
     */
    public void startA(@NonNull Class<?> cls) {
        Intent intent = new Intent(context, cls);
        startActivity(intent);
    }


    /**
     * toast
     *
     * @param msg String
     */
    public void showtoast(@NonNull String msg) {
        Toast toast = Toast.makeText(context, null, Toast.LENGTH_SHORT);
        toast.setText(msg);
        toast.show();
    }


    /**
     * 显示加载动画
     */
    public void showLoadingDialog() {
    }

    /**
     * 隐藏加载动画
     */
    public void closeLoadingDialog() {
    }


    /**
     * 通过资源res获得view
     *
     * @param res res
     * @return View
     */
    public View getViewByRes(@LayoutRes int res) {
        return LayoutInflater.from(context).inflate(res, null);
    }


    /**
     * 获得TextView 的文本
     *
     * @param tv TextView
     * @return String
     */
    public String getTv(@NonNull TextView tv) {
        return tv.getText().toString().trim();
    }

    /**
     * @param color color
     * @return int
     */
    public int getValuesColor(@ColorRes int color) {
        return context.getResources().getColor(color);
    }


    private void showFileDialog() {

    }

    private void hideFileDialog() {

    }
}
