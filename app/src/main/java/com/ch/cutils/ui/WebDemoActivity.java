package com.ch.cutils.ui;

import android.view.View;

import com.ch.cutils.R;
import com.ch.cutils.databinding.AcWebDemoBinding;
import com.h.cheng.base.api.BasePresenter;
import com.h.cheng.base.base.BaseActivity;
import com.h.cheng.web.WebActivity;

/**
 * @author ch
 * @date 2020/8/4-16:14
 * @desc
 */
public class WebDemoActivity extends BaseActivity<AcWebDemoBinding, BasePresenter> implements View.OnClickListener {
    private String text = "<p><span style=\"font-family: 仿宋_GB2312; background-color: rgb(255, 255, 255); color: rgb(51, 51, 51);\">亲爱的新同学：24=333</span><br/></p><p style=\"-webkit-tap-highlight-color: transparent; box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; padding: 0px; font-size: 14px; background-color: rgb(255, 255, 255); color: rgb(51, 51, 51); font-family: &quot;Microsoft YaHei&quot;, 华文细黑, &quot;WenQuanYi Microhei&quot;, &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; text-indent: 32px; line-height: 29px;\"><span style=\"-webkit-tap-highlight-color: transparent; box-sizing: border-box; font-size: 16px; font-family: 仿宋_GB2312;\">你好！为了使你能够顺利入学，请按如下要求做好准备，按时到我院报到。</span></p><p style=\"-webkit-tap-highlight-color: transparent; box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; padding: 0px; font-size: 14px; background-color: rgb(255, 255, 255); color: rgb(51, 51, 51); font-family: &quot;Microsoft YaHei&quot;, 华文细黑, &quot;WenQuanYi Microhei&quot;, &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; text-indent: 32px; line-height: 29px;\"><span style=\"-webkit-tap-highlight-color: transparent; box-sizing: border-box; font-size: 16px; font-family: 黑体;\">一、报到时间、地点</span></p><p style=\"-webkit-tap-highlight-color: transparent; box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; padding: 0px; font-size: 14px; background-color: rgb(255, 255, 255); color: rgb(51, 51, 51); font-family: &quot;Microsoft YaHei&quot;, 华文细黑, &quot;WenQuanYi Microhei&quot;, &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; text-indent: 32px; line-height: 29px;\"><span style=\"-webkit-tap-highlight-color: transparent; box-sizing: border-box; font-size: 16px; font-family: 楷体_GB2312;\">（一）报到时间</span></p><p style=\"-webkit-tap-highlight-color: transparent; box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; padding: 0px; font-size: 14px; background-color: rgb(255, 255, 255); color: rgb(51, 51, 51); font-family: &quot;Microsoft YaHei&quot;, 华文细黑, &quot;WenQuanYi Microhei&quot;, &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; text-indent: 32px; line-height: 29px;\"><span style=\"-webkit-tap-highlight-color: transparent; box-sizing: border-box; font-size: 16px; font-family: 仿宋_GB2312;\">请于2018年9月15日凭《广东海洋大学寸金学院录取通知书》（以下简称录取通知书）和本人身份证到学院报到（原则上不得提前或延后报到），如有特殊原因不能按期报到的，须在9月14日前通过电告或传真申请向学院招生办公室申述理由。未能按期报到，且未申述理由的，将取消入学资格。</span></p><p style=\"-webkit-tap-highlight-color: transparent; box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; padding: 0px; font-size: 14px; background-color: rgb(255, 255, 255); color: rgb(51, 51, 51); font-family: &quot;Microsoft YaHei&quot;, 华文细黑, &quot;WenQuanYi Microhei&quot;, &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; text-indent: 32px; line-height: 29px;\"><span style=\"-webkit-tap-highlight-color: transparent; box-sizing: border-box; font-size: 16px; font-family: 楷体_GB2312;\">（二）报到地点</span></p><p style=\"-webkit-tap-highlight-color: transparent; box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; padding: 0px; font-size: 14px; background-color: rgb(255, 255, 255); color: rgb(51, 51, 51); font-family: &quot;Microsoft YaHei&quot;, 华文细黑, &quot;WenQuanYi Microhei&quot;, &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; text-indent: 32px; line-height: 29px;\"><span style=\"-webkit-tap-highlight-color: transparent; box-sizing: border-box; font-weight: 700;\"><span style=\"-webkit-tap-highlight-color: transparent; box-sizing: border-box; font-size: 16px; font-family: 仿宋_GB2312;\">1.</span></span><span style=\"-webkit-tap-highlight-color: transparent; box-sizing: border-box; font-weight: 700;\"><span style=\"-webkit-tap-highlight-color: transparent; box-sizing: border-box; font-size: 16px; font-family: 仿宋_GB2312;\">新湖校区地址：广东省湛江市麻章区教育城新坡路1号；</span></span></p>";

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ac_web_demo;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void addListener() {
        binding.btnUrl.setOnClickListener(this);
        binding.btnText.setOnClickListener(this);
        binding.btnVideo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //加载网页
            case R.id.btn_url:
                WebActivity.loadUrl(context, "https://www.jianshu.com/p/f9eeded90f43");
                break;
            //富文本
            case R.id.btn_text:
                WebActivity.loadText(context, text);
                break;
            //视频
            case R.id.btn_video:
                WebActivity.loadUrl(context, "https://dy-frontend.video.ums.uc.cn/video/wemedia/63491d133c4141c6b0f91839e55255af/364ab4c621532267de9cc42508bf7d72-2562570424-2-0-3.mp4?auth_key=1596610638-f7bcc57bab254d66a40ea8a1139107bd-0-1bea8a727bfb09efb5d137aa125881c7");
                break;
            default:
        }

    }
}
