package com.wj.training.readingtraining.common;

import android.support.v4.app.Fragment;

import com.wj.training.readingtraining.base.BaseActivity;

/**
 */

public class ActivityManager {

    private BaseActivity mActivity;

    public void register(BaseActivity mActivity){
        this.mActivity = mActivity;
    }

    public void switchFragment(Fragment fragment){
        mActivity.switchFragment(fragment);
    }

    private ActivityManager(){};

    public static ActivityManager instance(){
        return A.activityManager;
    }

    private static class A{
        private static ActivityManager activityManager = new ActivityManager(){};
    }
}
