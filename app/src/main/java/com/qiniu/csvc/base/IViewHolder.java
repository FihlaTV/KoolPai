package com.qiniu.csvc.base;

public interface IViewHolder<DATA> {

    void bindView(DATA data, int position);
}
