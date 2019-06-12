package com.ch.cutils.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.ch.cutils.R;

/**
 * 作者： ch
 * 时间： 2018/8/13 0013-上午 10:32
 * 描述：
 * 来源：
 */


public class PSTextView extends AppCompatTextView {
    /**
     * 边框 宽度
     */
    private int ps_border_width;
    /**
     * 边框颜色
     */
    private int ps_border_color = Color.TRANSPARENT;
    /**
     * 左上圆角
     */
    private int ps_top_left_radius;
    /**
     * 右上圆角
     */
    private int ps_top_right_radius;
    /**
     * 左下圆角
     */
    private int ps_bottom_left_radius;
    /**
     * 右下圆角
     */
    private int ps_bottom_right_radius;
    /**
     * 圆角
     */
    private int ps_radius;
    /**
     * 填充颜色
     */
    private int ps_btn_background_color = Color.TRANSPARENT;
    /**
     * 焦点时颜色
     */
    private int ps_focus_background_color;

    public PSTextView(Context context) {
        super(context);
    }

    public PSTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        parseAttrs(context, attrs);
    }

    public PSTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        parseAttrs(context, attrs);
    }

    /**
     * @param context
     * @param attrs
     */
    private void parseAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PSButton);
        if (typedArray == null) return;
        ps_border_width = typedArray.getDimensionPixelSize(R.styleable.PSButton_ps_border_width, 0);
        ps_border_color = typedArray.getColor(R.styleable.PSButton_ps_border_color, ps_border_color);
        ps_top_left_radius = typedArray.getDimensionPixelSize(R.styleable.PSButton_ps_top_left_radius, 0);
        ps_top_right_radius = typedArray.getDimensionPixelSize(R.styleable.PSButton_ps_top_right_radius, 0);
        ps_bottom_left_radius = typedArray.getDimensionPixelSize(R.styleable.PSButton_ps_bottom_left_radius, 0);
        ps_bottom_right_radius = typedArray.getDimensionPixelSize(R.styleable.PSButton_ps_bottom_right_radius, 0);
        ps_radius = typedArray.getDimensionPixelSize(R.styleable.PSButton_ps_radius, 0);
        ps_btn_background_color = typedArray.getColor(R.styleable.PSButton_ps_background_color, ps_btn_background_color);
        ps_focus_background_color = typedArray.getColor(R.styleable.PSButton_ps_focus_background_color, ps_btn_background_color);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        refresh();
    }

    private void refresh() {
        updateBackground(createDrawable());
    }

    private Drawable createDrawable() {
        //默认
        GradientDrawable defaultDrawable = new GradientDrawable();
        initRadius(defaultDrawable);

        defaultDrawable.setColor(ps_btn_background_color);

        if (ps_border_width != 0) {
            defaultDrawable.setStroke(ps_border_width, ps_border_color);
        }

        if (ps_focus_background_color != 0) {
            //按下时
            GradientDrawable focusDrawable = new GradientDrawable();
            initRadius(focusDrawable);
            focusDrawable.setColor(ps_focus_background_color);
            return getRippleDrawable(defaultDrawable, focusDrawable);
        }
        return defaultDrawable;
    }

    /**
     * 设置圆角
     *
     * @param defaultDrawable
     */
    private void initRadius(GradientDrawable defaultDrawable) {
        if (ps_radius > 0) {
            defaultDrawable.setCornerRadius(ps_radius);
        } else {
            defaultDrawable.setCornerRadii(new float[]{ps_top_left_radius, ps_top_left_radius, ps_top_right_radius, ps_top_right_radius,
                    ps_bottom_right_radius, ps_bottom_right_radius, ps_bottom_left_radius, ps_bottom_left_radius});
        }
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private Drawable getRippleDrawable(Drawable defaultDrawable, Drawable focusDrawable) {
        return new RippleDrawable(ColorStateList.valueOf(ps_focus_background_color), defaultDrawable, focusDrawable);
    }


    private void updateBackground(Drawable background) {
        if (background == null) return;
        if (Build.VERSION.SDK_INT >= 16) {
            this.setBackground(background);
        } else {
            this.setBackgroundDrawable(background);
        }
    }


}
