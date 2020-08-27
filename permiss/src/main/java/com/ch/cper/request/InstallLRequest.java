package com.ch.cper.request;

import android.content.Context;

import com.ch.cper.base.Request;
import com.ch.cper.utils.IntentUtils;

/**
 * 作者： ch
 * 时间： 2019/5/9 0009-下午 2:14
 * 描述：
 * 来源：
 */

public class InstallLRequest extends InstallRequest implements Request {


    public InstallLRequest(Context context) {
        super(context);
    }

    @Override
    public void start() {
        if (file == null || !file.exists()) {
            callbackFailed();
            return;
        }
        if (canRequestPackageInstalls()) {
            callbackSucceed();
            context.startActivity(IntentUtils.getInstallIntent(context, file));
        } else {
            callbackFailed();
        }

    }

}
