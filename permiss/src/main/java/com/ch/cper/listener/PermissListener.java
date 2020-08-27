package com.ch.cper.listener;

import java.util.List;

/**
 * 作者： ch
 * 时间： 2019/5/7 0007-下午 5:02
 * 描述：
 * 来源：
 */

public interface PermissListener<T> {
    /**
     * 同意
     *
     * @param granted 申请的权限
     */
    void onGranted(List<T> granted);

    /**
     * 拒绝
     *
     * @param granted 拒绝的权限
     */
    void onDenied(List<T> granted);

}
