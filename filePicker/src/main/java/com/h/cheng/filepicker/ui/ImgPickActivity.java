package com.h.cheng.filepicker.ui;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.h.cheng.base.widget.recy.SpaceDecoration;
import com.h.cheng.filepicker.PsPickManager;
import com.h.cheng.filepicker.R;
import com.h.cheng.filepicker.bean.Directory;
import com.h.cheng.filepicker.bean.NormalFile;
import com.h.cheng.filepicker.callback.FilterResultCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ch
 * @date 2020/8/21-18:18
 * @desc 选择图片
 */
public class ImgPickActivity extends BasePickActivity<NormalFile> {

    private BaseQuickAdapter<NormalFile, BaseViewHolder> fileAdapter;

    private int spanCount;

    @Override
    protected void initView() {
        super.initView();
        fileAdapter = new BaseQuickAdapter<NormalFile, BaseViewHolder>(R.layout.item_pick_img) {
            @Override
            protected void convert(@NonNull BaseViewHolder helper, NormalFile item) {
                if (item.isSelected()) {
                    helper.setImageResource(R.id.iv_choose, R.mipmap.ic_img_checked);
                    helper.setVisible(R.id.view_shadow, true);
                } else {
                    helper.setVisible(R.id.view_shadow, false);
                    helper.setImageResource(R.id.iv_choose, R.mipmap.ic_img_uncheck);
                }

                Glide.with(context)
                        .load(item.getPath())
                        .apply(new RequestOptions()
                                .override(200, 200))
                        .into((ImageView) helper.getView(R.id.iv_img));
                helper.addOnClickListener(R.id.ll_choose);
            }
        };
        if (config != null) {
            spanCount = config.getSpanCount();
            if (spanCount <= 1) {
                spanCount = 2;
            }
        }

        binding.rvFilePick.setLayoutManager(new GridLayoutManager(context, spanCount));
        binding.rvFilePick.addItemDecoration(new SpaceDecoration(5, getValuesColor(R.color.color_333333)));
        binding.rvFilePick.setAdapter(fileAdapter);

        if (binding.rvFilePick.getItemAnimator() != null) {
            binding.rvFilePick.getItemAnimator().setChangeDuration(0);
        }


        PsPickManager.getImages(this, new FilterResultCallback<NormalFile>() {
            @Override
            public void onResult(List<Directory<NormalFile>> list) {
                refreshData(list);
            }
        }, suffix);
    }

    /**
     * 刷新数据
     *
     * @param directories directories
     */
    private void refreshData(List<Directory<NormalFile>> directories) {
        List<NormalFile> list = new ArrayList<>();
        for (Directory<NormalFile> directory : directories) {
            list.addAll(directory.getFiles());
        }

        for (NormalFile file : selectedList) {
            int index = list.indexOf(file);
            if (index != -1) {
                list.get(index).setSelected(true);
            }
        }

        fileAdapter.setNewData(list);
    }

    @Override
    protected void addListener() {
        super.addListener();
        fileAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                NormalFile item = fileAdapter.getItem(position);
                if (view.getId() == R.id.ll_choose) {
                    if (item != null) {
                        if (!item.isSelected() && selectedList.size() >= max) {
                            showToast("已达到选择上限");
                            return;
                        }
                        if (item.isSelected()) {
                            item.setSelected(false);
                            selectedList.remove(item);
                        } else {
                            if (!selectedList.contains(item)) {
                                selectedList.add(item);
                                item.setSelected(true);
                            }
                        }
                        fileAdapter.notifyItemChanged(position);
                    }
                    binding.tvSelect.setText("确定(" + (String.format("%d/%d", selectedList.size(), max)) + ")");
                }
            }
        });
    }
}
