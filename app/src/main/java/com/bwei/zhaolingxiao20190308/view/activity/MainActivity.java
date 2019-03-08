package com.bwei.zhaolingxiao20190308.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bwei.zhaolingxiao20190308.R;
import com.bwei.zhaolingxiao20190308.view.zidingyi.LiuLayout;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private LiuLayout liuLayout;
    private Button buttonclear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        buttonclear = findViewById(R.id.buttoncle);
        //点击跳转
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ShoppingActivity.class));
            }
        });
        //流式布局
        final EditText editText = findViewById(R.id.editText);
        Button buttons = findViewById(R.id.buttons);
        liuLayout = findViewById(R.id.liuLayout);
        buttons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strings = editText.getText().toString();
                TextView textView = new TextView(MainActivity.this);
                textView.setText(strings);
                liuLayout.addView(textView);
            }
        });
        //清空流式布局
        buttonclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                liuLayout.removeAllViews();
            }
        });
    }
}
