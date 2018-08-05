package com.wj.training.readingtraining.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.wj.training.readingtraining.R;
import com.wj.training.readingtraining.util.PxDpUtil;

/**
 * Created by jfdeng@grandstream.cn on 18-8-1.
 */

public class ReadView extends ViewGroup {

    Animation animation;
    private TextView mContent;
    private TextView mInfo;
    private ImageView mImage;

    Integer readNum = new Integer(0);
    Integer readCount = new Integer(0);

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

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int count = getChildCount();
        Log.i("jeff", "onMeasure count="+count);
        if (count > 0) {
            measureChildren(widthMeasureSpec, heightMeasureSpec);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        final int count = getChildCount();
        if (count > 0) {
            for (int j = 0; j < count; j++) {
                View view = getChildAt(j);
                if (j == count - 1) {
                    view.layout(0, -view.getMeasuredHeight(), view.getMeasuredWidth(), 0);
                } else {
                    view.layout(i, i1, view.getMeasuredWidth(), i1+view.getMeasuredHeight());
                    i1 += view.getMeasuredHeight();
                }
            }
        }
        Log.i("jeff", "onLayout left="+i+",top="+i1+",right="+i2+",bottom="+i3);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i("jeff", "onDraw");
    }

    private void init(AttributeSet attrs) {
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.read_layout, this);
        mContent = rootView.findViewById(R.id.content);
        mImage = rootView.findViewById(R.id.image);
        mInfo = rootView.findViewById(R.id.info);
        mInfo.setText("已阅读阅读字数："+readNum+",已翻页数："+readCount);
        animation = new TranslateAnimation(0, 0, 0, getResources().getDimension(R.dimen.image_height));
        animation.setDuration(1000);
        animation.setStartOffset(500);
        animation.setFillAfter(false);//保持移动后位置
        animation.setFillBefore(true);
//        animation.setRepeatMode(Animation.REVERSE);//倒序重复，顺序重复
        animation.setRepeatCount(Animation.INFINITE);//播放次数
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.i("jeff","onAnimationStart");
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                Log.i("jeff","onAnimationEnd");
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.i("jeff","onAnimationRepeat");
                if(readCount<10){
                    readNum+=mContent.getText().length();
                    readCount++;
                    mInfo.setText("已阅读阅读字数："+readNum+",已翻页数："+readCount);
                }else {
                    mContent.setText("");
                    mImage.clearAnimation();
                }
            }
        });
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.i("jeff", "onFinishInflate");
    }

    public void startAnimation() {
        mImage.startAnimation(animation);
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        Log.i("jeff", "onWindowFocusChanged");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i("jeff", "onSizeChanged");
    }

    public void stopAnimation() {
        mImage.clearAnimation();
    }
}
