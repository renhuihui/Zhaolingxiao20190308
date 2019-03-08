package com.bwei.zhaolingxiao20190308.view.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwei.zhaolingxiao20190308.R;

public class Calcultor extends LinearLayout {
    int number = 1;
    public Calcultor(Context context) {
        this(context,null);
    }

    public Calcultor(Context context,  AttributeSet attrs) {
        this(context, attrs,0);
    }

    public Calcultor(Context context,AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View inflate = LayoutInflater.from(context).inflate(R.layout.calcultor, this);
        Button jia = inflate.findViewById(R.id.jia);
        Button jian = inflate.findViewById(R.id.jian);
        final TextView num = inflate.findViewById(R.id.num);
        jia.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                number = number +1;
                num.setText(String.valueOf(number));
                monBackNum.jia(number);
            }
        });
        jian.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                number = number -1;
                if(number <0){
                    number = 0;
                    num.setText(String.valueOf(number));
                }
                num.setText(String.valueOf(number));
                monBackNum.jian(number);
            }
        });

    }
    OnBackNum monBackNum;
    public void setOnBackNum(OnBackNum onBackNum){
        this.monBackNum = onBackNum;
    }
    public interface OnBackNum{
        public void  jia(int numbers);
        public void  jian(int numbers);
    }
}
