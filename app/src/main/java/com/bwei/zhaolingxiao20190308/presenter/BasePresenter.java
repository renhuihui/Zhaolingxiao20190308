package com.bwei.zhaolingxiao20190308.presenter;

public class BasePresenter<V> {
    private V view;

    public V getView() {
        return view;
    }

    public void setView(V view) {
        this.view = view;
    }
    public void unbind(){
        this.view = null;
    }
}
