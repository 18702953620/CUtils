package com.h.cheng.base.api;

/**
 * @author ch
 * @date 2020/4/21-10:20
 * desc  BaseView
 */
public interface BaseView {
    /**
     * 显示dialog
     */
    void showLoading();

    /**
     * 显示下载文件dialog
     */

    void showLoadingFileDialog();

    /**
     * 隐藏下载文件dialog
     */

    void hideLoadingFileDialog();

    /**
     * 下载进度
     *
     * @param totalSize 总长度
     * @param downSize  已下载
     */

    void onProgress(long totalSize, long downSize);

    /**
     * 隐藏 dialog
     */

    void hideLoading();

    /**
     * 显示错误信息
     *
     * @param msg msg
     */
    void showError(String msg);


    /**
     * @param code code
     * @param msg  msg
     */
    void onErrorCode(int code, String msg);


}
