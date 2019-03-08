package com.bwei.zhaolingxiao20190308.view.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.bumptech.glide.Glide;
import com.bwei.zhaolingxiao20190308.R;
import com.bwei.zhaolingxiao20190308.data.ShoppingBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class ShoppingAdapter extends BaseQuickAdapter<ShoppingBean.DataBean,BaseViewHolder> {

    private CheckBox checkBoxs;
    private RecyclerView recyclerViews;

    OnShopItemClickLisenter monShopItemClickLisenter;
    private GoodsAdapter goodsAdapter;

    public void setOnShopItemClickLisenter(OnShopItemClickLisenter onShopItemClickLisenter){
        this.monShopItemClickLisenter = onShopItemClickLisenter;
    }
    public interface OnShopItemClickLisenter{
        void callBack();
    }

    public ShoppingAdapter(int layoutResId, @Nullable List<ShoppingBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final ShoppingBean.DataBean item) {
        List<ShoppingBean.DataBean.ListBean> list = item.getList();
        helper.setText(R.id.text_shop,item.getSellerName());
        checkBoxs = helper.getView(R.id.checkbox_shop);
        recyclerViews = helper.getView(R.id.recyclerView_shop);
        LinearLayoutManager manager = new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
        recyclerViews.setLayoutManager(manager);
        goodsAdapter = new GoodsAdapter(R.layout.goods_item, list);
        recyclerViews.setAdapter(goodsAdapter);
        checkBoxs.setChecked(item.isShoppingchecked());
        goodsAdapter.setOnGoodsItemClickLisenter(new GoodsAdapter.OnGoodsItemClickLisenter() {
            @Override
            public void callBack() {
                boolean result = true;
                for (int i = 0; i < item.getList().size(); i++) {
                    result = result & item.getList().get(i).isGoodsChecked();
                }
                checkBoxs.setChecked(result);
                goodsAdapter.notifyDataSetChanged();
                monShopItemClickLisenter.callBack();
            }
        });
        checkBoxs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < item.getList().size(); i++) {
                    item.getList().get(i).setGoodsChecked(checkBoxs.isChecked());
                }
                item.setShoppingchecked(item.isShoppingchecked());
                notifyDataSetChanged();
                monShopItemClickLisenter.callBack();
            }
        });

    }
}
