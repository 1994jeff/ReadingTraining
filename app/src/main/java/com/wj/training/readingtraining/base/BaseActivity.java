package com.wj.training.readingtraining.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.wj.training.readingtraining.R;

/**
 * Created by jfdeng@grandstream.cn on 18-7-31.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
//        getWindow().getDecorView().setBackground(getResources().getDrawable(R.drawable.ic_launcher_foreground));
    }
}
