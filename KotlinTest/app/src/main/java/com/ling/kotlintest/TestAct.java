package com.ling.kotlintest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

/**
 * Created by cuiqiang on 2017/2/13.
 */

public class TestAct extends Activity {

    private Button mBtnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        mBtnTest = (Button) findViewById(R.id.btn_one);
    }

    private void initView() {
       // mBtnTest.setOnClickListener(view -> Log.d("1111","1111"));

    }
}
