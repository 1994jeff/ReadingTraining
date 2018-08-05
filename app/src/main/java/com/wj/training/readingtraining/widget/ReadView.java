package com.wj.training.readingtraining.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wj.training.readingtraining.R;

/**
 * Created by jfdeng@grandstream.cn on 18-8-1.
 */

public class ReadView extends RelativeLayout {

    Animation animation;

    private TextView mContent;
    private TextView mInfo;
    private ImageView mImage;

    private int screenHight;

    long SLEEP_TIME = 1000;
    long ANIMATION_TIME = 3000;

    public ReadView(Context context) {
        super(context);
        init(null);
    }

    public ReadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ReadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        View root = LayoutInflater.from(getContext()).inflate(R.layout.read_layout, this);
        initView(root);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void startAnimation(){
        mImage.startAnimation(animation);
//        mContent.startAnimation(animation);
    }

    public void stopAnimation(){
    }

    public void resetAnimation(){
    }

    class AnimationThread implements Runnable{
        @Override
        public void run() {
            while (true){
                try {
                    Thread.sleep(SLEEP_TIME);

                    postInvalidate();
                    mImage.setAnimation(animation);
                    animation.start();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void initView(View root) {
        mContent = root.findViewById(R.id.content);
        mInfo = root.findViewById(R.id.info);
        mImage = root.findViewById(R.id.image);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            getDisplay().getMetrics(displayMetrics);
            screenHight = displayMetrics.heightPixels;
        }else {
            WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            screenHight = displayMetrics.heightPixels;
        }
        Log.d(this.getClass().getName(),screenHight+"");
//        new Thread(new AnimationThread()).start();
        animation = new TranslateAnimation(0,0,0,screenHight);
        animation.setDuration(ANIMATION_TIME);
    }


}
