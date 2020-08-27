package com.ch.cper.base;

/**
 * 作者： ch
 * 时间： 2019/5/8 0008-下午 1:56
 * 描述：
 * 来源：
 */

public interface Option {
    /**
     * 请求权限
     *
     * @return Request
     */
    Request permiss();

    /**
     * 打开设置
     *
     * @return Request
     */
    Request setting();

    /**
     * 安装应用
     *
     * @return Request
     */
    Request install();
}
