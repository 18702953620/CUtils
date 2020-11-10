package com.h.cheng.filepicker.config;

import com.h.cheng.filepicker.bean.NormalFile;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author ch
 * @date 2020/8/21-15:41
 * @desc 选择器 配置
 */
public class PickConfig implements Serializable {
    /**
     * 最大选择数
     */
    private int max;
    /**
     * 标题
     */
    private String title;
    /**
     * 筛选条件
     */
    private String[] suffix;
    /**
     * 返回键
     */
    private int backRes;
    /**
     * 背景颜色
     */
    private int backColor;
    /**
     * 标题颜色
     */
    private int titleColor;
    /**
     * 右边文字颜色
     */
    private int rightColor;
    /**
     * 一行展示数目
     * 文件无效
     */
    private int spanCount;

    /**
     * 选中的条目
     */
    private ArrayList<NormalFile> selectList;
    /**
     * 图片加载器
     */
    private ImageLoader imageLoader;

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    public void setImageLoader(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    public ArrayList<NormalFile> getSelectList() {
        return selectList;
    }

    public void setSelectList(ArrayList<NormalFile> selectList) {
        this.selectList = selectList;
    }


    public int getSpanCount() {
        return spanCount;
    }

    public void setSpanCount(int spanCount) {
        this.spanCount = spanCount;
    }

    public int getBackRes() {
        return backRes;
    }

    public void setBackRes(int backRes) {
        this.backRes = backRes;
    }

    public int getBackColor() {
        return backColor;
    }

    public void setBackColor(int backColor) {
        this.backColor = backColor;
    }

    public int getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(int titleColor) {
        this.titleColor = titleColor;
    }

    public int getRightColor() {
        return rightColor;
    }

    public void setRightColor(int rightColor) {
        this.rightColor = rightColor;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getSuffix() {
        return suffix;
    }

    public void setSuffix(String[] suffix) {
        this.suffix = suffix;
    }
}
