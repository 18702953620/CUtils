package com.h.cheng.web;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.widget.FrameLayout;

import com.h.cheng.base.api.BasePresenter;
import com.h.cheng.base.base.BaseActivity;
import com.h.cheng.web.databinding.ActivityBaseWebBinding;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/**
 * @author ch
 * @date 2020/8/4-13:46
 * @desc WebActivity
 */
public class WebActivity extends BaseActivity<ActivityBaseWebBinding, BasePresenter> {

    private ValueCallback<Uri[]> uploadFiles;
    private ValueCallback<Uri> uploadFile;
    private View myVideoView;
    private FrameLayout myNormalView;
    private IX5WebChromeClient.CustomViewCallback callback;
    /**
     * 链接
     */
    private static final String LOADURL = "LOADURL";
    /**
     * 富文本
     */
    private static final String LOADTEXT = "LOADTEXT";

    @Override
    protected BasePresenter createPresenter() {
        return presenter;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_base_web;
    }

    @Override
    protected void initView() {
        String url = getIntent().getStringExtra(LOADURL);

        if (!TextUtils.isEmpty(url)) {
            //加载链接
            binding.webView.loadUrl(url);
        }
        String text = getIntent().getStringExtra(LOADTEXT);

        if (!TextUtils.isEmpty(text)) {
            //加载一个富文本
            binding.webView.loadDataWithBaseURL("", getHtmlData(text), "text/html", "utf-8", null);
        }

    }

    /**
     * 加载html标签
     *
     * @param html bodyHTML
     * @return String
     */
    private String getHtmlData(String html) {
        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                "<style>img{max-width: 100%; width:auto; height:auto!important;}</style>" +
                "</head>";
        return "<html>" + head + "<body>" + html + "</body></html>";
    }

    /**
     * 加载一个网页
     *
     * @param context context
     * @param url     url
     */
    public static void loadUrl(Context context, String url) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(LOADURL, url);
        context.startActivity(intent);
    }

    /**
     * 加载一个富文本
     *
     * @param context context
     * @param text    text
     */
    public static void loadText(Context context, String text) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(LOADTEXT, text);
        context.startActivity(intent);
    }

    @Override
    protected void addListener() {
        binding.webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String url) {
                webView.loadUrl(url);
                return true;
            }
        });

        binding.webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void openFileChooser(ValueCallback<Uri> valueCallback, String s, String s1) {
                super.openFileChooser(valueCallback, s, s1);
                uploadFile = valueCallback;
                openFileChooseProcess();
            }

            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, FileChooserParams fileChooserParams) {

                uploadFiles = valueCallback;
                openFileChooseProcess();
                return true;
            }

            /**
             * 全屏播放配置
             */
            @Override
            public void onShowCustomView(View view,
                                         IX5WebChromeClient.CustomViewCallback customViewCallback) {
                FrameLayout normalView = findViewById(R.id.web_filechooser);
                ViewGroup viewGroup = (ViewGroup) normalView.getParent();
                viewGroup.removeView(normalView);
                viewGroup.addView(view);
                myVideoView = view;
                myNormalView = normalView;
                callback = customViewCallback;
            }

            @Override
            public void onHideCustomView() {
                if (callback != null) {
                    callback.onCustomViewHidden();
                    callback = null;
                }
                if (myVideoView != null) {
                    ViewGroup viewGroup = (ViewGroup) myVideoView.getParent();
                    viewGroup.removeView(myVideoView);
                    viewGroup.addView(myNormalView);
                }
            }

        });

        binding.webView.addJavascriptInterface(new WebViewJavaScriptFunction() {

            @Override
            public void onJsFunctionCalled(String tag) {

            }

            @JavascriptInterface
            public void onX5ButtonClicked() {
                enableX5FullscreenFunc();
            }

            @JavascriptInterface
            public void onCustomButtonClicked() {
                disableX5FullscreenFunc();
            }

            @JavascriptInterface
            public void onLiteWndButtonClicked() {
                enableLiteWndFunc();
            }

            @JavascriptInterface
            public void onPageVideoClicked() {
                enablePageVideoFunc();
            }
        }, "Android");

    }

    /**
     * 向webview发出信息
     */
    private void enableX5FullscreenFunc() {

        if (binding.webView.getX5WebViewExtension() != null) {
            Bundle data = new Bundle();

            // true表示标准全屏，false表示X5全屏；不设置默认false，
            data.putBoolean("standardFullScreen", false);

            // false：关闭小窗；true：开启小窗；不设置默认true，
            data.putBoolean("supportLiteWnd", false);

            // 1：以页面内开始播放，2：以全屏开始播放；不设置默认：1
            data.putInt("DefaultVideoScreen", 2);

            binding.webView.getX5WebViewExtension().invokeMiscMethod("setVideoParams", data);
        }
    }

    private void disableX5FullscreenFunc() {
        if (binding.webView.getX5WebViewExtension() != null) {
            Bundle data = new Bundle();

            // true表示标准全屏，会调起onShowCustomView()，false表示X5全屏；不设置默认false，
            data.putBoolean("standardFullScreen", true);

            // false：关闭小窗；true：开启小窗；不设置默认true，
            data.putBoolean("supportLiteWnd", false);

            // 1：以页面内开始播放，2：以全屏开始播放；不设置默认：1
            data.putInt("DefaultVideoScreen", 2);

            binding.webView.getX5WebViewExtension().invokeMiscMethod("setVideoParams", data);
        }
    }

    private void enableLiteWndFunc() {
        if (binding.webView.getX5WebViewExtension() != null) {
            Bundle data = new Bundle();

            // true表示标准全屏，会调起onShowCustomView()，false表示X5全屏；不设置默认false，
            data.putBoolean("standardFullScreen", false);

            // false：关闭小窗；true：开启小窗；不设置默认true，
            data.putBoolean("supportLiteWnd", true);

            // 1：以页面内开始播放，2：以全屏开始播放；不设置默认：1
            data.putInt("DefaultVideoScreen", 2);

            binding.webView.getX5WebViewExtension().invokeMiscMethod("setVideoParams", data);
        }
    }

    private void enablePageVideoFunc() {
        if (binding.webView.getX5WebViewExtension() != null) {
            Bundle data = new Bundle();

            // true表示标准全屏，会调起onShowCustomView()，false表示X5全屏；不设置默认false，
            data.putBoolean("standardFullScreen", false);

            // false：关闭小窗；true：开启小窗；不设置默认true，
            data.putBoolean("supportLiteWnd", false);

            // 1：以页面内开始播放，2：以全屏开始播放；不设置默认：1
            data.putInt("DefaultVideoScreen", 1);

            binding.webView.getX5WebViewExtension().invokeMiscMethod("setVideoParams", data);
        }
    }


    private void openFileChooseProcess() {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("*/*");
        startActivityForResult(Intent.createChooser(i, "test"), 0);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 0) {
                if (null != uploadFile) {
                    Uri result = data == null ? null
                            : data.getData();
                    uploadFile.onReceiveValue(result);
                    uploadFile = null;
                }
                if (null != uploadFiles) {
                    Uri result = data == null ? null
                            : data.getData();
                    uploadFiles.onReceiveValue(new Uri[]{result});
                    uploadFiles = null;
                }
            }
        } else if (resultCode == RESULT_CANCELED) {
            if (null != uploadFile) {
                uploadFile.onReceiveValue(null);
                uploadFile = null;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.webView.destroy();
    }
}