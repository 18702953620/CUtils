package com.ch.cper.result;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import java.util.List;

/**
 * 作者： ch
 * 时间： 2019/3/7 0007-上午 10:54
 * 描述：
 * 来源：
 */

public class PermissHelper {

    private static final String TAG = "PermissHelper";
    private Context context;

    public static PermissHelper init(Context context) {
        return new PermissHelper(context);
    }

    private PermissHelper(Context context) {
        this.context = context;
    }

    private PermissFragment getRouterFragment() {
        if (context instanceof Activity) {
            PermissFragment routerFragment = findRouterFragment((Activity) context);
            if (routerFragment == null) {
                routerFragment = PermissFragment.newInstance();
                android.app.FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .add(routerFragment, TAG)
                        .commitAllowingStateLoss();
                fragmentManager.executePendingTransactions();
            }
            return routerFragment;
        }


        return null;
    }

    private PermissSupportFragment getSupportRouterFragment() {
        if (context instanceof FragmentActivity) {
            PermissSupportFragment routerFragment = findRouterFragment((FragmentActivity) context);
            if (routerFragment == null) {
                routerFragment = PermissSupportFragment.newInstance();
                FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .add(routerFragment, TAG)
                        .commitAllowingStateLoss();
                fragmentManager.executePendingTransactions();
            }
            return routerFragment;

        }

        return null;

    }

    /**
     * 请求权限
     *
     * @param permissions permissions
     * @param callback    callback
     */
    public void requestPermissions(String[] permissions, PermissCallBack callback) {
        if (context instanceof FragmentActivity) {
            getSupportRouterFragment().requestPermissions(permissions, callback);
        } else {
            getRouterFragment().requestPermissions(permissions, callback);
        }

    }

    /**
     * 请求权限
     *
     * @param permissions permissions
     * @param callback    callback
     */
    public void requestPermissions(List<String> permissions, PermissCallBack callback) {
        requestPermissions(permissions.toArray(new String[permissions.size()]), callback);
    }


    public void startActivityForResult(Intent intent, CallBack callback) {
        if (context instanceof FragmentActivity) {
            getSupportRouterFragment().startActivityForResult(intent, callback);
        } else {
            getRouterFragment().startActivityForResult(intent, callback);
        }
    }


    private PermissSupportFragment findRouterFragment(FragmentActivity activity) {
        return (PermissSupportFragment) activity.getSupportFragmentManager().findFragmentByTag(TAG);
    }

    private PermissFragment findRouterFragment(Activity activity) {
        return (PermissFragment) activity.getFragmentManager().findFragmentByTag(TAG);
    }


    public interface CallBack {
        /**
         * onActivityResult
         *
         * @param resultCode resultCode
         * @param data       data
         */
        void onActivityResult(int resultCode, Intent data);
    }

    public interface PermissCallBack {
        /**
         * 权限回调
         *
         * @param resultCode resultCode
         */
        void onPermissResult(int resultCode);
    }

}
