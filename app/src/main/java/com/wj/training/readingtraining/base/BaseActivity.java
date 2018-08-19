package com.wj.training.readingtraining.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Toast;

import com.wj.training.readingtraining.R;
import com.wj.training.readingtraining.ui.fragment.FragmentReading;

/**
 * Created by jfdeng@grandstream.cn on 18-7-31.
 */

public class BaseActivity extends AppCompatActivity implements RetParam{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        getWindow().getDecorView().setBackground(getResources().getDrawable(R.mipmap.katong));
    }

    public void switchFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container,fragment);
        transaction.addToBackStack(fragment.getClass().getName());
        transaction.commitAllowingStateLoss();
    }

    public void showFragmentToast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

    public void showFragmentToast(int msg) {
        Toast.makeText(this,getString(msg),Toast.LENGTH_LONG).show();
    }

    public void switchFragmentByStack(FragmentReading fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.add(R.id.container,fragment);
        transaction.replace(R.id.container,fragment);
        transaction.addToBackStack(fragment.getClass().getName());
        transaction.commitAllowingStateLoss();
    }
}
