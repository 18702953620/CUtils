package com.h.cheng.filepicker.ui;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.h.cheng.filepicker.PsPickManager;
import com.h.cheng.filepicker.R;
import com.h.cheng.filepicker.bean.Directory;
import com.h.cheng.filepicker.bean.NormalFile;
import com.h.cheng.filepicker.callback.FilterResultCallback;
import com.h.cheng.filepicker.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ch
 * @date 2020/8/21-13:58
 * @desc 文件选择界面
 */
public class FilePickActivity extends BasePickActivity<NormalFile> {
    private BaseQuickAdapter<NormalFile, BaseViewHolder> fileAdapter;

    @Override
    protected void initView() {
        super.initView();
        fileAdapter = new BaseQuickAdapter<NormalFile, BaseViewHolder>(R.layout.item_file_pick) {
            @Override
            protected void convert(@NonNull BaseViewHolder helper, NormalFile item) {
                helper.setText(R.id.tv_name, Utils.extractFileNameWithSuffix(item.getPath()));
                if (item.isSelected()) {
                    helper.setImageResource(R.id.iv_choose, R.mipmap.ic_pick_checked);
                } else {
                    helper.setImageResource(R.id.iv_choose, R.mipmap.ic_pick_uncheck);
                }

                String path = item.getPath();
                if (path.endsWith("xls") || path.endsWith("xlsx") || path.endsWith("XLS") || path.endsWith("XLSX")) {
                    helper.setImageResource(R.id.iv_file, R.mipmap.ic_pick_excel);
                } else if (path.endsWith("doc") || path.endsWith("docx") || path.endsWith("DOC") || path.endsWith("DOCX")) {
                    helper.setImageResource(R.id.iv_file, R.mipmap.ic_pick_word);
                } else if (path.endsWith("ppt") || path.endsWith("pptx") || path.endsWith("PPT") || path.endsWith("PPTX")) {
                    helper.setImageResource(R.id.iv_file, R.mipmap.ic_pick_ppt);
                } else if (path.endsWith("pdf") || path.endsWith("PDF")) {
                    helper.setImageResource(R.id.iv_file, R.mipmap.ic_pick_pdf);
                } else if (path.endsWith("txt") || path.endsWith("TXT")) {
                    helper.setImageResource(R.id.iv_file, R.mipmap.ic_pick_txt);
                } else {
                    helper.setImageResource(R.id.iv_file, R.mipmap.ic_pick_file);
                }

                helper.addOnClickListener(R.id.ll_choose);
            }
        };
        binding.rvFilePick.setLayoutManager(new LinearLayoutManager(context));
        binding.rvFilePick.setAdapter(fileAdapter);
        if (binding.rvFilePick.getItemAnimator() != null) {
            binding.rvFilePick.getItemAnimator().setChangeDuration(0);
        }
        PsPickManager.getFiles(this, new FilterResultCallback<NormalFile>() {
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
            for (NormalFile f : list) {
                if (f.equals(file)) {
                    int index = list.indexOf(file);
                    if (index != -1) {
                        list.get(index).setSelected(true);
                    }
                }
            }
        }

        fileAdapter.setNewData(list);
    }

    @Override
    protected void addListener() {
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
