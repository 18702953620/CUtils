package com.h.cheng.http.bean;

import java.util.List;

/**
 * @author ch
 * @date 2020/6/10-15:35
 * @desc
 */
public class PageList<T> {
    private List<T> datas;

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }
}
