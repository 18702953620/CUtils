package com.ch.cutils.ui;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;

import com.bumptech.glide.Glide;
import com.ch.cutils.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.h.cheng.base.base.BaseListActivity;
import com.h.cheng.base.common.preview.OnActivityReenter;
import com.h.cheng.base.common.preview.PreViewReenter;
import com.h.cheng.base.common.preview.BasePreviewActivity;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;

/**
 * @author ch
 * @date 2020/7/23-18:05
 * @desc
 */
public class ImgListActivity extends BaseListActivity {
    private BaseQuickAdapter<String, BaseViewHolder> quickAdapter;
    private ArrayList<String> lists;
    private OnActivityReenter reenter;

    @Override
    public BaseQuickAdapter<?, BaseViewHolder> getAdapter() {
        lists = new ArrayList<>();
        lists.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595839310898&di=99cece7095a6ea1518f05bb77732d710&imgtype=0&src=http%3A%2F%2Fimg.tupianzj.com%2Fuploads%2Fallimg%2F160221%2F9-160221142258.jpg");
        lists.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595839323578&di=2a3d4007b665f89c1d2f34979653d95d&imgtype=0&src=http%3A%2F%2Fimg.tupianzj.com%2Fuploads%2Fallimg%2F151229%2F9-151229215209-50.jpg");
        lists.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595839336987&di=b5b1675e7965fb4c364ce49855e2b33b&imgtype=0&src=http%3A%2F%2Fp1.ssl.cdn.btime.com%2Ft01bf15496e123945e1.jpg%3Fsize%3D768x1280");
        lists.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595839295848&di=0309cd4fcf422c436db1d02faa04e211&imgtype=0&src=http%3A%2F%2Fimg.improve-yourmemory.com%2Fpic%2Fb91d078d9906116b02259be5750e46b5-2.jpg");
        lists.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595839379363&di=90e2d0e921381ff94219fff32829ac6e&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fpic%2F0%2F2d%2F30f11272927.jpg");
        lists.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595838936226&di=5326f7d6d02f5ea56dabb2d3065fbd2d&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2017-12-11%2F5a2e3dd020a79.jpg");
        lists.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595839401948&di=531406cfab0bc9d5bc4094641491c2a5&imgtype=0&src=http%3A%2F%2Fwww.boruisz.com%2Fimages%2Fnfwwombsfz2g633pobsw4ltdn5wq%2Fimages%2F20140311%2Fsy_56511421248.jpg");
        lists.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595839429544&di=0d7ed6e3a11c3778395b8cc330415883&imgtype=0&src=http%3A%2F%2Fp3.ssl.cdn.btime.com%2Ft01355c8e8b57796351.jpg%3Fsize%3D500x714");
        lists.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595839456871&di=f2d498d60d7e0b7cb326ac460b3ce4fb&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Fsinacn02%2F295%2Fw640h455%2F20181113%2F7666-hnstwwr2992253.jpg");

        quickAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_img, lists) {
            @Override
            protected void convert(@NonNull BaseViewHolder helper, String item) {
                Glide.with(context).load(item)
                        .into((ImageView) helper.getView(R.id.iv_img));
            }
        };
        return quickAdapter;
    }

    @Override
    protected void setAdapter(@NonNull BaseQuickAdapter quickAdapter) {
        getRecyclerView().setLayoutManager(new GridLayoutManager(context, 3));
        getRecyclerView().setAdapter(quickAdapter);
        quickAdapter.bindToRecyclerView(getRecyclerView());


        setRefreshLoadMore(false, false);
        setTitle("大图预览");

        reenter = new PreViewReenter(this);

        getSharedPreferences().registerOnSharedPreferenceChangeListener();
    }

    @Override
    protected void addListener() {
        super.addListener();

        quickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                BasePreviewActivity.startPreView(ImgListActivity.this, lists, view, position);
            }
        });
    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
        reenter.onActivityReenter(resultCode, data, quickAdapter, R.id.iv_img);
    }

    @Override
    public void refresh(RefreshLayout refreshLayout) {

    }

    @Override
    public void loadMore(RefreshLayout refreshLayout) {

    }
}
