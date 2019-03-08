package com.bwei.zhaolingxiao20190308.presenter;

import com.bwei.zhaolingxiao20190308.data.Constant;
import com.bwei.zhaolingxiao20190308.data.ShoppingBean;
import com.bwei.zhaolingxiao20190308.model.HttpUtils;
import com.bwei.zhaolingxiao20190308.view.interfaces.IMainView;

public class ShoppingPresenter extends BasePresenter<IMainView<ShoppingBean>> {
    //单例
    private final HttpUtils httpUtils;

    public ShoppingPresenter(){
        httpUtils = HttpUtils.getInstance();
    }
    //加载
    public void loadData(){
        httpUtils.getData(Constant.SHOPPING, ShoppingBean.class, new HttpUtils.CallBackData<ShoppingBean>() {

            @Override
            public void Success(ShoppingBean shoppingBean) {
                getView().onSuc(shoppingBean);
            }

            @Override
            public void Fail(String msg) {
                getView().onFail(msg);
            }
        });
    }
}
