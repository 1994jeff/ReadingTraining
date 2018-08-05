package com.wj.training.readingtraining.util;

import android.content.Context;

/**
 * Created by jeff on 18-8-5.
 */

public class PxDpUtil {

    public static int px2dp(Context context, float px){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px/scale+0.5f);
    }

    public static int dp2px(Context context, float dp){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp*scale+0.5f);
    }

}
