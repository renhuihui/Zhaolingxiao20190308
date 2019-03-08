package com.bwei.zhaolingxiao20190308.view.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bwei.zhaolingxiao20190308.R;
import com.bwei.zhaolingxiao20190308.data.ShoppingBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class GoodsAdapter extends BaseQuickAdapter<ShoppingBean.DataBean.ListBean,BaseViewHolder> {
    OnGoodsItemClickLisenter monGoodsItemClickLisenter;
    public void setOnGoodsItemClickLisenter(OnGoodsItemClickLisenter onGoodsItemClickLisenter){
        this.monGoodsItemClickLisenter = onGoodsItemClickLisenter;
    }
    public interface OnGoodsItemClickLisenter{
        void callBack();
    }
    public GoodsAdapter(int layoutResId, @Nullable List<ShoppingBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final ShoppingBean.DataBean.ListBean item) {
        helper.setText(R.id.text_goods,item.getTitle());
        helper.setText(R.id.text_price,item.getPrice()+"");
        ImageView imageView = helper.getView(R.id.imageView_goods);
        Glide.with(mContext).load(item.getImages()).into(imageView);
        CheckBox checkBoxss = helper.getView(R.id.checkbox_goods);
        checkBoxss.setOnCheckedChangeListener(null);
        checkBoxss.setChecked(item.isGoodsChecked());
        checkBoxss.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                item.setGoodsChecked(isChecked);
                monGoodsItemClickLisenter.callBack();
            }
        });
        Calcultor calcultor = helper.getView(R.id.calcultor);
        calcultor.setOnBackNum(new Calcultor.OnBackNum() {
            @Override
            public void jia(int numbers) {
                item.setDefaultNumber(numbers);
                monGoodsItemClickLisenter.callBack();
            }

            @Override
            public void jian(int numbers) {
                item.setDefaultNumber(numbers);
                monGoodsItemClickLisenter.callBack();
            }
        });

    }
}
