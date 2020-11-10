package com.h.cheng.filepicker.ui;

import android.content.Intent;
import android.view.View;

import com.h.cheng.base.api.BasePresenter;
import com.h.cheng.base.base.BaseActivity;
import com.h.cheng.filepicker.PsPickManager;
import com.h.cheng.filepicker.R;
import com.h.cheng.filepicker.bean.NormalFile;
import com.h.cheng.filepicker.config.PickConfig;
import com.h.cheng.filepicker.databinding.AcFilePickerBinding;

import java.util.ArrayList;

/**
 * @author ch
 * @date 2020/8/21-18:23
 * @desc 选择基类
 */
public class BasePickActivity extends BaseActivity<AcFilePickerBinding, BasePresenter> {

    protected String[] suffix;
    /**
     * 最大选择数
     */
    protected int max = 1;
    /**
     * 标题
     */
    protected String title = "选择文件";
    /**
     * 选中列表
     */
    protected ArrayList<NormalFile> selectedList = new ArrayList<>();

    protected PickConfig config;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ac_file_picker;
    }

    @Override
    protected void initView() {
        config = (PickConfig) getIntent().getSerializableExtra(PsPickManager.CONFIG);
        if (config != null) {
            //最大数量
            max = config.getMax();
            //筛选
            suffix = config.getSuffix();
            //标题
            title = config.getTitle();
            //背景颜色
            if (config.getBackColor() != 0) {
                binding.rlTitle.setBackgroundColor(config.getBackColor());
            }
            //返回键
            if (config.getBackRes() != 0) {
                binding.ivBack.setImageResource(config.getBackRes());
            }
            //标题颜色
            if (config.getTitleColor() != 0) {
                binding.tvTitle.setTextColor(config.getTitleColor());
            }
            //右边颜色
            if (config.getRightColor() != 0) {
                binding.tvSelect.setTextColor(config.getRightColor());
                binding.tvSelect.setPsBorderColor(config.getRightColor());
            }
            if (config.getSelectList() != null) {
                selectedList = config.getSelectList();
            }

            binding.tvTitle.setText(title);
            binding.tvSelect.setText("确定(" + (String.format("%d/%d", 0, max)) + ")");
        }
    }

    @Override
    protected void addListener() {
        binding.llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.tvSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedList.size() == 0) {
                    showToast("请至少选择一个");
                    return;
                }

                Intent intent = new Intent();
                intent.putParcelableArrayListExtra(PsPickManager.RESULT_PICK_FILE, selectedList);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
