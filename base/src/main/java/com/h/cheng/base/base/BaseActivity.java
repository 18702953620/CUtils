package com.h.cheng.base.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.h.cheng.base.R;
import com.h.cheng.base.api.BasePresenter;
import com.h.cheng.base.api.BaseView;
import com.h.cheng.base.utils.StatusBarUtils;
import com.jaeger.library.StatusBarUtil;

/**
 * @author ch
 * @date 2020/4/21-10:20
 * desc 基类
 */
public abstract class BaseActivity<B extends ViewDataBinding, P extends BasePresenter> extends AppCompatActivity implements BaseView {
    /**
     * context
     */
    public Context context;
    /**
     * dialog
     */
    private ProgressDialog dialog;
    /**
     * presenter
     */
    protected P presenter;
    /**
     * binding
     */
    protected B binding;

    /**
     * 创建 Presenter
     *
     * @return P
     */
    protected abstract P createPresenter();

    /**
     * 布局
     *
     * @return id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 添加监听
     */
    protected abstract void addListener();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setStatusBar();
        binding = DataBindingUtil.setContentView(this, getLayoutId());
        presenter = createPresenter();
        initView();
        addListener();
    }

    /**
     * 设置状态栏
     */
    protected void setStatusBar() {
    }

    /**
     * 白色状态栏
     */
    protected void setWhiteStatusBar() {
        //设置状态栏颜色;
        StatusBarUtil.setColorForSwipeBack(this,
                getResources().getColor(R.color.color_ffffff), 0);
        //暗色
        StatusBarUtils.statusBarLightMode(this, StatusBarUtils.statusBarLightMode(this));
    }

    /**
     * 黑色状态栏
     */
    public void setBlackStausBar() {
        StatusBarUtil.setColorForSwipeBack(this,
                getResources().getColor(R.color.color_333333), 0);
        //亮色
        StatusBarUtils.statusBarDarkMode(this, StatusBarUtils.statusBarLightMode(this));
    }

    /**
     * 设置 无状态栏 亮色模式（状态栏黑色字体）
     */
    public void setNoStatusBarByLight() {
        StatusBarUtil.setTranslucentForImageView(this, 0, null);
        StatusBarUtils.statusBarLightMode(this, StatusBarUtils.statusBarLightMode(this));
    }

    /**
     * 设置 无状态栏 暗色模式（状态栏白色字体）
     */
    public void setNoStatusBarByDrak() {
        StatusBarUtil.setTranslucentForImageView(this, 0, null);
        //亮色
        StatusBarUtils.statusBarDarkMode(this, StatusBarUtils.statusBarLightMode(this));
    }


    /**
     * @param color color
     * @return int
     */
    public int getValuesColor(int color) {
        return context.getResources().getColor(color);
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
    public String getTv(TextView tv) {
        return tv == null ? "" : tv.getText().toString().trim();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
    }

    /**
     * @param s s
     */
    public void showToast(String s) {
        Toast.makeText(context, s, Toast.LENGTH_LONG).show();
    }


    public void showFileDialog() {
        dialog = new ProgressDialog(context);
        dialog.setMessage("正在下载中,请稍后");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setMax(100);
        dialog.show();
    }

    public void hideFileDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }


    private void closeLoadingDialog() {
    }


    private void showLoadingDialog() {

    }

    @Override
    public void showLoading() {
        showLoadingDialog();
    }


    @Override
    public void hideLoading() {
        closeLoadingDialog();
    }


    @Override
    public void showError(String msg) {
        showToast(msg);
    }

    @Override
    public void onErrorCode(int code, String msg) {
        showToast(msg);
    }

    @Override
    public void showLoadingFileDialog() {
        showFileDialog();
    }

    @Override
    public void hideLoadingFileDialog() {
        hideFileDialog();
    }

    @Override
    public void onProgress(long totalSize, long downSize) {
        if (dialog != null) {
            dialog.setProgress((int) (downSize * 100 / totalSize));
        }
    }
}
