package com.ch.cutils.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ch.cutils.MyImgLoader;
import com.ch.cutils.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.h.cheng.base.common.preview.BasePreviewActivity;
import com.h.cheng.base.widget.recy.SpaceDecoration;
import com.h.cheng.filepicker.PsPickManager;
import com.h.cheng.filepicker.bean.NormalFile;
import com.h.cheng.filepicker.callback.OnLoadResultCallback;
import com.h.cheng.filepicker.config.PickConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ch
 * @date 2020/10/10-17:12
 * @desc
 */
public class ImagePickerView extends LinearLayout {
    private Context context;
    private int maxChoosePic = 1;
    private int spanCount = 3;
    private Drawable addRes;
    private SelectAdapter selectAdapter;
    private ArrayList<String> pathList;

    public ImagePickerView(@NonNull Context context) {
        super(context);
    }

    public ImagePickerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        parseAttrs(context, attrs);
        init();
    }

    public ImagePickerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        parseAttrs(context, attrs);
        init();
    }

    private void parseAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ImagePickerView);
        addRes = typedArray.getDrawable(R.styleable.ImagePickerView_ps_mv_add);
        maxChoosePic = typedArray.getInt(R.styleable.ImagePickerView_ps_mv_maxCount, 1);
        spanCount = typedArray.getInt(R.styleable.ImagePickerView_ps_mv_spanCount, 3);
        typedArray.recycle();
    }


    private void init() {
        RecyclerView rvChoose = new RecyclerView(context);
        pathList = new ArrayList<>();
        pathList.add(null);
        selectAdapter = new SelectAdapter(pathList);
        rvChoose.setLayoutManager(new GridLayoutManager(context, spanCount));
        rvChoose.setAdapter(selectAdapter);
        rvChoose.addItemDecoration(new SpaceDecoration(20, context.getResources().getColor(R.color.color_ffffff)));

        selectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (adapter.getData().get(position) == null) {
                    if (context instanceof FragmentActivity) {
                        PickConfig config = new PickConfig();
                        config.setMax(maxChoosePic);
                        config.setSpanCount(spanCount);
                        config.setTitle("请选择图片");
                        config.setBackColor(Color.parseColor("#ff0000"));
                        config.setTitleColor(Color.parseColor("#ffffff"));
                        config.setRightColor(Color.parseColor("#fff000"));
                        config.setImageLoader(new MyImgLoader());

                        PsPickManager.openImagePicker((FragmentActivity) context, config, new OnLoadResultCallback<NormalFile>() {
                            @Override
                            public void onResult(ArrayList<NormalFile> list) {
                                if (list != null) {
                                    ArrayList<String> lists = new ArrayList<>();
                                    for (NormalFile file : list) {
                                        String path = file.getPath();
                                        lists.add(path);
                                    }
                                    setPathList(lists);
                                }
                            }
                        });
                    }

                } else {
                    if (context instanceof Activity) {
                        BasePreviewActivity.startPreView((Activity) context, pathList, view, position);
                    }
                }
            }
        });
        LinearLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addView(rvChoose, params);
    }

    public ArrayList<String> getPathList() {
        ArrayList<String> strings = new ArrayList<>(pathList);
        strings.remove(null);
        return strings;
    }

    public void setPathList(ArrayList<String> pathList) {
        if (pathList != null && pathList.size() > 0) {
            pathList.remove(null);
            if (pathList.size() < maxChoosePic) {
                pathList.add(null);
            }
        }
        this.pathList = pathList;
        if (selectAdapter != null) {
            selectAdapter.setNewData(pathList);
        }
    }

    class SelectAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public SelectAdapter(List<String> list) {
            super(R.layout.item_img_picker, list);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            if (item == null) {
                helper.setGone(R.id.btn_del, false);
                helper.setImageDrawable(R.id.item_img, addRes);
            } else {
                helper.setGone(R.id.btn_del, true);
                Glide.with(context).load(item).into((ImageView) helper.getView(R.id.item_img));
            }
            helper.getView(R.id.btn_del).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    getData().remove(helper.getAdapterPosition());
                    if (getData().size() < maxChoosePic && !getData().contains(null)) {
                        getData().add(null);
                    }
                    notifyDataSetChanged();
                }
            });
        }
    }
}
