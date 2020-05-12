package com.h.cheng.base.base;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.h.cheng.base.R;
import com.h.cheng.base.adapter.BaseTabs2Adapter;
import com.h.cheng.base.adapter.BaseTabsAdapter;
import com.h.cheng.base.api.BasePresenter;
import com.h.cheng.base.databinding.AcBasePage2Binding;
import com.h.cheng.base.databinding.AcBasePageBinding;

import java.util.List;

/**
 * @author ch
 * @date 2020/4/27-19:09
 * desc
 */
public abstract class BasePage2Activity<P extends BasePresenter> extends BaseActivity<AcBasePage2Binding, P> {

    private BaseTabs2Adapter tabsAdapter;

    /**
     * 创建  Fragment
     *
     * @param position position
     * @return Fragment
     */
    protected abstract Fragment buildFragment(int position);

    /**
     * 创建标题
     *
     * @return List
     */
    protected abstract List<String> buildTitles();


    private TabLayoutMediator tabLayoutMediator;

    @Override
    protected P createPresenter() {
        return presenter;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ac_base_page2;
    }

    @Override
    protected void initView() {
        tabsAdapter = new BaseTabs2Adapter(this, buildTitles()) {
            @Override
            protected Fragment getFragmentInPosition(int position) {
                return buildFragment(position);
            }
        };
        binding.vpBase.setAdapter(tabsAdapter);

        tabLayoutMediator = new TabLayoutMediator(binding.tbBase, binding.vpBase, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(buildTitles().get(position));
            }
        });
        tabLayoutMediator.attach();
    }

    @Override
    protected void addListener() {

    }


    /**
     * 设置标题
     *
     * @param text text
     */
    public void setTitle(@NonNull String text) {
        if (binding != null) {
            binding.title.setTitle(text);
        }
    }

    /**
     * 设置tab text color
     *
     * @param normalColor 正常
     * @param selectColor 选中
     */
    public void setTabTextColors(int normalColor, int selectColor) {
        binding.tbBase.setTabTextColors(normalColor, selectColor);
    }

    /**
     * 设置下标的颜色
     *
     * @param color color
     */
    public void setTabIndicatorColor(int color) {
        binding.tbBase.setSelectedTabIndicatorColor(color);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (tabLayoutMediator != null) {
            tabLayoutMediator.detach();
        }
    }
}
