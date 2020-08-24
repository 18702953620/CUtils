package com.h.cheng.http.request;

import com.h.cheng.http.request.base.BaseRequest;
import com.h.cheng.http.request.base.Method;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @author ch
 * @date 2020/8/10-11:34
 * @desc
 */
public class FileRequest extends BaseRequest {

    private HashMap<String, List<File>> mapFile;

    public FileRequest(String url) {
        this.url = url;
        this.method = Method.POST;
    }

    @Override
    public RequestBody getRequestBody() {
        if (mapFile.isEmpty()) {
            //表单提交，没有文件
            FormBody.Builder bodyBuilder = new FormBody.Builder();
            for (String key : urlParams.keySet()) {
                bodyBuilder.addEncoded(key, urlParams.get(key) == null ? "" : urlParams.get(key));
            }
            return bodyBuilder.build();
        } else {
            //表单提交，有文件
            MultipartBody.Builder multipartBodybuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);
            if (!bodyParams.isEmpty()) {
                for (String key : bodyParams.keySet()) {
                    multipartBodybuilder.addFormDataPart(key, bodyParams.get(key) == null ? "" : bodyParams.get(key));
                }
            }
            for (String key : mapFile.keySet()) {
                List<File> files = mapFile.get(key);
                if (files != null && files.size() > 0) {
                    for (File file : files) {
                        RequestBody fileBody = RequestBody.create(file, MediaType.parse("application/octet-stream"));
                        multipartBodybuilder.addFormDataPart(key, file.getName(), fileBody);
                    }
                }
            }
            return multipartBodybuilder.build();
        }
    }

    /**
     * 添加文件
     *
     * @param key  key
     * @param file file
     */
    public void addFile(String key, File file) {
        if (file == null || !file.exists()) {
            return;
        }

        if (mapFile == null) {
            mapFile = new HashMap<>();
        }
        List<File> fileList = mapFile.get(key);

        if (fileList == null) {
            fileList = new ArrayList<>();
        }

        fileList.add(file);
        mapFile.put(key, fileList);
    }

    /**
     * 添加文件
     *
     * @param key   key
     * @param files files
     */
    public void addFiles(String key, List<File> files) {
        if (files == null || files.size() == 0) {
            return;
        }
        if (mapFile == null) {
            mapFile = new HashMap<>();
        }
        List<File> fileList = mapFile.get(key);

        if (fileList == null) {
            fileList = new ArrayList<>();
        }

        fileList.addAll(files);
        mapFile.put(key, fileList);
    }
}
