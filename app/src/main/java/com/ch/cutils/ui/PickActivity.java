package com.ch.cutils.ui;

import android.graphics.Color;
import android.view.View;

import com.ch.cper.CPermission;
import com.ch.cper.PermissGroup;
import com.ch.cper.listener.PermissListener;
import com.ch.cutils.MyImgLoader;
import com.ch.cutils.R;
import com.ch.cutils.databinding.AcPickBinding;
import com.h.cheng.base.api.BasePresenter;
import com.h.cheng.base.base.BaseActivity;
import com.h.cheng.filepicker.PsPickManager;
import com.h.cheng.filepicker.bean.NormalFile;
import com.h.cheng.filepicker.callback.OnLoadResultCallback;
import com.h.cheng.filepicker.config.PickConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ch
 * @date 2020/8/21-16:02
 * @desc
 */
public class PickActivity extends BaseActivity<AcPickBinding, BasePresenter> {

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ac_pick;
    }

    @Override
    protected void initView() {
        binding.btnFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CPermission.with(context)
                        .permiss()
                        .permission(PermissGroup.STORAGE)
                        .listener(new PermissListener<String>() {
                            @Override
                            public void onGranted(List<String> granted) {
                                openFile();
                            }

                            @Override
                            public void onDenied(List<String> granted) {

                            }
                        }).start();
            }
        });
        binding.btnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CPermission.with(context)
                        .permiss()
                        .permission(PermissGroup.STORAGE)
                        .listener(new PermissListener<String>() {
                            @Override
                            public void onGranted(List<String> granted) {
                                openImage();
                            }

                            @Override
                            public void onDenied(List<String> granted) {

                            }
                        }).start();
            }
        });

    }

    private void openFile() {
        PickConfig config = new PickConfig();
        config.setMax(1);
        config.setSuffix(new String[]{"xlsx", "xls", "doc", "docx", "ppt", ".pptx", "pdf"});
        config.setTitle("请选择文件");
        config.setBackColor(Color.parseColor("#ff0000"));
        config.setTitleColor(Color.parseColor("#ffffff"));
        config.setRightColor(Color.parseColor("#fff000"));

        PsPickManager.openFilePicker(this, config, new OnLoadResultCallback<NormalFile>() {
            @Override
            public void onResult(ArrayList<NormalFile> list) {
                if (list != null) {
                    StringBuilder builder = new StringBuilder();
                    for (NormalFile file : list) {
                        String path = file.getPath();
                        builder.append(path + "\n");
                    }
                    binding.tvContent.setText(builder.toString());
                }
            }
        });
    }

    private void openImage() {
        PickConfig config = new PickConfig();
        config.setMax(9);
        config.setSpanCount(4);
        config.setTitle("请选择图片");
        config.setBackColor(Color.parseColor("#ff0000"));
        config.setTitleColor(Color.parseColor("#ffffff"));
        config.setRightColor(Color.parseColor("#fff000"));
        config.setImageLoader(new MyImgLoader());

        PsPickManager.openImagePicker(this, config, new OnLoadResultCallback<NormalFile>() {
            @Override
            public void onResult(ArrayList<NormalFile> list) {
                if (list != null) {
                    ArrayList<String> lists = new ArrayList<>();
                    StringBuilder builder = new StringBuilder();
                    for (NormalFile file : list) {
                        String path = file.getPath();
                        builder.append(path + "\n");
                        lists.add(path);
                    }
                    binding.pickImg.setPathList(lists);
                    binding.tvContent.setText(builder.toString());
                }
            }
        });
    }

    @Override
    protected void addListener() {

    }
}
