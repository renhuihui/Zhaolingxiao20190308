package com.bwei.zhaolingxiao20190308.view.interfaces;

public interface IMainView<T> {
    public void onSuc(T t);
    public void onFail(String msg);
}
