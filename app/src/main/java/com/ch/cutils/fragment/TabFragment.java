package com.ch.cutils.fragment;

import androidx.fragment.app.Fragment;

import com.ch.cutils.adapter.ListAdapter;
import com.ch.cutils.bean.ArticleModel;
import com.ch.cutils.presenter.AppPresenter;
import com.ch.cutils.view.AppView;
import com.h.cheng.base.base.BaseListFragment;
import com.h.cheng.base.base.BaseTabFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ch
 * @date 2020/7/1-17:59
 * @desc
 */
public class TabFragment extends BaseTabFragment<AppPresenter> implements AppView {

    private BaseListFragment fragment1;
    private BaseListFragment fragment2;
    private ListAdapter listAdapter1;
    private ListAdapter listAdapter2;

    @Override
    protected Fragment buildFragment(int position) {
        if (position == 0) {
            return fragment1;
        } else {
            return fragment2;
        }
    }

    @Override
    protected List<String> buildTitles() {
        List<String> lists = new ArrayList<>();
        lists.add("frag1");
        lists.add("frag2");
        return lists;
    }

    @Override
    protected void buildAdapter() {
        fragment1 = new BaseListFragment();
        fragment2 = new BaseListFragment();


        listAdapter1 = new ListAdapter(null);
        listAdapter2 = new ListAdapter(null);

        fragment1.setAdapter(listAdapter1);
        fragment2.setAdapter(listAdapter2);

        presenter.getWxArticleList();

        needTitle(false);
        needStatusBar(false);
    }

    @Override
    protected AppPresenter createPresenter() {
        return new AppPresenter(this);
    }

    @Override
    public void onGetListSucc(List<ArticleModel> o) {
        listAdapter1.setNewData(o);
        listAdapter2.setNewData(o);
    }

}
