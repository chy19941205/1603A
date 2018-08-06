package com.bwie.chuliangliang.chuliangliang20180806;

import android.nfc.Tag;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String[] mVals = new String[]{"苹果手机", "笔记本电脑", "电饭煲 ", "腊肉",
            "特产", "剃须刀", "宝宝", "康佳", "特产", "剃须刀", "宝宝", "康佳"};
    private LayoutInflater mInflater;
    private FlowLayout mFlowLayout;
    private TextView sou;
    private EditText editText;
    private TextView tv;
    private String s;
    private Button clear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
