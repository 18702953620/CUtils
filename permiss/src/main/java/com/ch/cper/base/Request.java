package com.ch.cper.base;

import com.ch.cper.listener.PermissListener;

/**
 * 作者： ch
 * 时间： 2019/5/7 0007-下午 4:57
 * 描述：
 * 来源：
 */

public interface Request {
    /**
     * 监听
     *
     * @param granted 权限列表
     * @return Request
     */
    Request listener(PermissListener<String> granted);

    /**
     * 权限
     *
     * @param permissions permissions
     * @return Request
     */
    Request permission(String... permissions);

    /**
     * 开始请求
     */
    void start();
}
