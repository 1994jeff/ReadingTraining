package com.wj.training.readingtraining.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.wj.training.readingtraining.bean.User;
import com.wj.training.readingtraining.ui.activity.LoginActivity;

/**
 * Created by jeff on 18-8-8.
 */

public class Constants {

    public static String[] SPEED_NAME = new String[]{
            "200~1000字/分钟",
            "1000~2000字/分钟",
            "2000~3000字/分钟",
            "3000~4000字/分钟",
            "4000~5000字/分钟",
            "5000~6400字/分钟",
            "6400~8000字/分钟",
            "8000~9400字/分钟",
            "9400~11000字/分钟",
            "11000~12400字/分钟",
            "12400~14000字/分钟",
            "14000~15400字/分钟",
            "15400~17000字/分钟",
            "17000~19000字/分钟",
            "19000~21000字/分钟",
            "21000~23000字/分钟",
            "23000~25000字/分钟",
            "25000~33000字/分钟"
    };

    public static int[] SPEED_D_VALUE = new int[]{

    };

    public static final String BASE_URL = "http://139.199.182.22/EBManagerSystem/api/";
    public static final String REGISTER_API = "addUser.do?";
    public static final String LOGIN_API = "toCheckLogin.do?";
    public static final String ARTILE_API = "queryArticleByCondition.do?";
    public static final String TOKEN = "token=spf1sn2fd2qw4dzdfkq";

    public static String getName(Context context){
        String id = context.getSharedPreferences("user_info",Context.MODE_PRIVATE).getString("name","");
        return id;
    }

    public static String getPsd(Context context){
        String id = context.getSharedPreferences("user_info",Context.MODE_PRIVATE).getString("psd","");
        return id;
    }

    public static String getStatus(Context context){
        String id = context.getSharedPreferences("user_info",Context.MODE_PRIVATE).getString("status","");
        return id;
    }

    public static void setUser(Context context, User user){
        SharedPreferences preferences = context.getSharedPreferences("user_info",Context.MODE_PRIVATE);
        preferences.edit().putString("name",user.getName()).putString("psd",user.getPsd())
                .putString("status",user.getStatus()).commit();

    }

    public static void clearUser(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("",Context.MODE_PRIVATE);
        preferences.edit().putString("name","").putString("psd","")
                .putString("status","").commit();
    }
}