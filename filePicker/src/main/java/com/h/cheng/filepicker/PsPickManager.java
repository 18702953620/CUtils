package com.h.cheng.filepicker;

import android.content.Intent;

import androidx.fragment.app.FragmentActivity;
import androidx.loader.app.LoaderManager;

import com.h.cheng.base.helper.result.ResultHelper;
import com.h.cheng.filepicker.bean.NormalFile;
import com.h.cheng.filepicker.callback.FileLoaderCallbacks;
import com.h.cheng.filepicker.callback.FilterResultCallback;
import com.h.cheng.filepicker.callback.OnLoadResultCallback;
import com.h.cheng.filepicker.config.Config;
import com.h.cheng.filepicker.ui.FilePickActivity;
import com.h.cheng.filepicker.ui.ImgPickActivity;

import java.util.ArrayList;

/**
 * @author ch
 * @date 2020/8/21-14:39
 * @desc
 */
public class PsPickManager {
    /**
     * 图片
     */
    public static final int TYPE_IMAGE = 0;
    /**
     * 视频
     */
    public static final int TYPE_VIDEO = 1;
    /**
     * 音频
     */
    public static final int TYPE_AUDIO = 2;
    /**
     * 文件
     */
    public static final int TYPE_FILE = 3;

    public static final String RESULT_PICK_FILE = "ResultPickFILE";
    /**
     * 配置
     */
    public static final String CONFIG = "CONFIG";

    /**
     * 选择文件
     *
     * @param activity activity
     * @param config   config
     * @param callback callback
     */
    public static void openFilePicker(FragmentActivity activity, Config config, final OnLoadResultCallback<NormalFile> callback) {
        Intent intent = new Intent(activity, FilePickActivity.class);
        intent.putExtra(CONFIG, config);
        ResultHelper
                .init(activity)
                .startActivityForResult(intent, new ResultHelper.CallBack() {
                    @Override
                    public void onActivityResult(int resultCode, Intent data) {
                        if (resultCode == FragmentActivity.RESULT_OK) {
                            if (data != null) {
                                ArrayList<NormalFile> list = data.getParcelableArrayListExtra(RESULT_PICK_FILE);
                                if (callback != null) {
                                    callback.onResult(list);
                                }
                            }
                        }
                    }
                });
    }

    /**
     * 选择图片
     *
     * @param activity activity
     * @param config   config
     * @param callback callback
     */
    public static void openImagePicker(FragmentActivity activity, Config config, final OnLoadResultCallback<NormalFile> callback) {
        Intent intent = new Intent(activity, ImgPickActivity.class);
        intent.putExtra(CONFIG, config);
        ResultHelper
                .init(activity)
                .startActivityForResult(intent, new ResultHelper.CallBack() {
                    @Override
                    public void onActivityResult(int resultCode, Intent data) {
                        if (resultCode == FragmentActivity.RESULT_OK) {
                            if (data != null) {
                                ArrayList<NormalFile> list = data.getParcelableArrayListExtra(RESULT_PICK_FILE);
                                if (callback != null) {
                                    callback.onResult(list);
                                }
                            }
                        }
                    }
                });
    }

    /**
     * 查询文件
     *
     * @param activity activity
     * @param callback callback
     * @param suffix   suffix
     */
    public static void getFiles(FragmentActivity activity, FilterResultCallback callback, String[] suffix) {
        LoaderManager.getInstance(activity).initLoader(3, null,
                new FileLoaderCallbacks(activity, callback, TYPE_FILE, suffix));

    }

    /**
     * 查询图片
     *
     * @param activity activity
     * @param callback callback
     * @param suffix   suffix
     */
    public static void getImages(FragmentActivity activity, FilterResultCallback callback, String[] suffix) {
        LoaderManager.getInstance(activity).initLoader(2, null,
                new FileLoaderCallbacks(activity, callback, TYPE_IMAGE, suffix));

    }
}
