package com.wj.training.readingtraining.ui.activity;

import android.os.Bundle;

import com.wj.training.readingtraining.R;
import com.wj.training.readingtraining.base.BaseActivity;
import com.wj.training.readingtraining.widget.ReadView;

public class MainActivity extends BaseActivity {

    private ReadView mReadView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
    }

    private void initView() {
        mReadView = (ReadView) findViewById(R.id.readView);
        mReadView.startAnimation();
    }
}
