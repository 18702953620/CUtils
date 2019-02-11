package com.ch.cutils.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者： ch
 * 时间： 2019/1/17 0017-下午 2:51
 * 描述： 状态栏
 * 来源：
 */
public class StatusBar extends View {
    public StatusBar(Context context) {
        this(context, null);
    }

    public StatusBar(Context context,
                     @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StatusBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = 0;
        int resourceId = this.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            height = this.getResources().getDimensionPixelSize(resourceId);
        }
        setMeasuredDimension(getMeasuredWidth(), height);
    }
}
