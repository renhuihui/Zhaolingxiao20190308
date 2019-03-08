package com.bwei.zhaolingxiao20190308.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.zhaolingxiao20190308.R;
import com.bwei.zhaolingxiao20190308.data.ShoppingBean;
import com.bwei.zhaolingxiao20190308.presenter.ShoppingPresenter;
import com.bwei.zhaolingxiao20190308.view.adapter.ShoppingAdapter;
import com.bwei.zhaolingxiao20190308.view.interfaces.IMainView;

import java.util.List;

public class ShoppingActivity extends AppCompatActivity implements IMainView<ShoppingBean> {

    private ShoppingPresenter presenter;
    private RecyclerView recyclerView;
    private CheckBox checkBoxall;
    private TextView priceall;
    private List<ShoppingBean.DataBean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        initData();
    }

    private void initData() {
        recyclerView = findViewById(R.id.recyclerView);
        checkBoxall = findViewById(R.id.checkbox_all);
        priceall = findViewById(R.id.price_all);
        presenter = new ShoppingPresenter();
        presenter.setView(this);
        presenter.loadData();
    }

    @Override
    public void onSuc(ShoppingBean shoppingBean) {
        data = shoppingBean.getData();
        LinearLayoutManager manager = new LinearLayoutManager(ShoppingActivity.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        final ShoppingAdapter shoppingAdapter = new ShoppingAdapter(R.layout.shopping_item, data);
        recyclerView.setAdapter(shoppingAdapter);
        checkBoxall.setOnCheckedChangeListener(null);
        checkBoxall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < data.size(); i++) {
                    data.get(i).setShoppingchecked(checkBoxall.isChecked());
                    for (int j = 0; j < data.get(i).getList().size(); j++) {
                        data.get(i).getList().get(j).setGoodsChecked(checkBoxall.isChecked());
                    }
                }
                totalPrice();
                shoppingAdapter.notifyDataSetChanged();
            }
        });
        shoppingAdapter.setOnShopItemClickLisenter(new ShoppingAdapter.OnShopItemClickLisenter() {
            @Override
            public void callBack() {
                boolean result = true;
                for (int i = 0; i < data.size(); i++) {
                    result = result & data.get(i).isShoppingchecked();
                    for (int j = 0; j < data.get(i).getList().size(); j++) {
                        result = result & data.get(i).getList().get(j).isGoodsChecked();
                    }
                }
                totalPrice();
                checkBoxall.setChecked(result);
            }
        });
    }
    private void totalPrice(){
        double totalprice = 0;
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.get(i).getList().size(); j++) {
                if(data.get(i).getList().get(j).isGoodsChecked() == true){
                    int defaultNumber = data.get(i).getList().get(j).getDefaultNumber();
                    double price = data.get(i).getList().get(j).getPrice();
                    double count = defaultNumber * price;
                    totalprice = totalprice + count;
                }
            }
        }
        priceall.setText(""+totalprice);
    }

    @Override
    public void onFail(String msg) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unbind();
    }
}
