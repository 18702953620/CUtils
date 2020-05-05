package com.ch.cutils.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ch.cutils.R;
import com.ch.cutils.bean.ArticleModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author ch
 * @date 2020/4/27-18:00
 * desc
 */
public class ListAdapter extends BaseQuickAdapter<ArticleModel, BaseViewHolder> {
    public ListAdapter(@Nullable List<ArticleModel> data) {
        super(R.layout.item_simple_list, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ArticleModel item) {
        helper.setText(R.id.tv_title, item.getName());
    }
}
