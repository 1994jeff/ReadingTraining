package com.wj.training.readingtraining;
import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.wj.training.readingtraining.bean.User;

/**
 * Created by jfdeng@grandstream.cn on 18-7-31.
 */

public class ReadingApplication extends Application   {

    //建立全局的请求队列
    public static RequestQueue mQueues;
    public User user;

    @Override
    public void onCreate() {
        super.onCreate();
        mQueues= Volley.newRequestQueue(getApplicationContext());
    }

    public void setUser(User u){
        user = u;
    }

    public User getUser(){
        return user;
    }

    public static RequestQueue getHttpQueue(){
        return mQueues;
    }

}
