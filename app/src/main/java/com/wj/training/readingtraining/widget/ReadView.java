package com.wj.training.readingtraining.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.wj.training.readingtraining.R;
import com.wj.training.readingtraining.bean.Customer;
import com.wj.training.readingtraining.util.PxDpUtil;

/**
 * Created by jfdeng@grandstream.cn on 18-8-1.
 */

public class ReadView extends ViewGroup {

    public interface FinishReadListener{
        void onFinishReadListener(int readCharSum,int readPageSum);
    }

    private FinishReadListener onFinishReadListener;

    public void setOnFinishReadListener(FinishReadListener onFinishReadListener) {
        this.onFinishReadListener = onFinishReadListener;
    }

    Animation animation;
    private TextView mContent;
    private TextView mInfo;
    private ImageView mImage;
    private Customer customer;
    private int mode;

    private int realPageNum;
    private int currentpPage;
    String[] readCharArrays = null;

    Integer readCharSum = new Integer(0);
    Integer readPageSum = new Integer(0);

    private DisplayMetrics metrics = new DisplayMetrics();

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
                    WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
                    windowManager.getDefaultDisplay().getMetrics(metrics);
                    int top = i1;
                    if(metrics.heightPixels>view.getMeasuredHeight()){
                        int diff = metrics.heightPixels - view.getMeasuredHeight();
                        top += diff/2;
                    }
                    view.layout(i, top, view.getMeasuredWidth(), top+view.getMeasuredHeight());
                    i1 += view.getMeasuredHeight();
                }
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private void init(AttributeSet attrs) {
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.read_layout, this);
        mContent = rootView.findViewById(R.id.content);
        mImage = rootView.findViewById(R.id.image);
        mInfo = rootView.findViewById(R.id.info);
        mInfo.setText("已阅读阅读字数：0,已翻页数：0");
        mInfo.setVisibility(GONE);
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);
        animation = new TranslateAnimation(0, 0, 0, metrics.heightPixels);
        animation.setFillAfter(false);//保持移动后位置
        animation.setFillBefore(true);
        animation.setDuration(2000);
        animation.setStartOffset(1000);
//        animation.setRepeatMode(Animation.REVERSE);//倒序重复，顺序重复
        animation.setRepeatCount(Animation.INFINITE);//播放次数
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
                currentpPage++;
                readCharSum+=mContent.getText().length();
                readPageSum=currentpPage;
                if(currentpPage<realPageNum){
                    mContent.setText(readCharArrays[currentpPage]);
                }else {
                    mContent.setText("");
                    mImage.clearAnimation();
                    if(onFinishReadListener!=null){
                        onFinishReadListener.onFinishReadListener(readCharSum,readPageSum);
                    }
                }
                mInfo.setText("已阅读阅读字数："+readCharSum+",已翻页数："+readPageSum);
            }
        });
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    public void startAnimation() {
        mImage.startAnimation(animation);
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    public void setDurationAndOffset(long duration,long offset){
        animation.setDuration(duration);
        animation.setStartOffset(offset);
    }

    public void stopAnimation() {
        mImage.clearAnimation();
    }

    public void setArticle(Customer customer,int mode) {
        this.customer = customer;
        this.mode = mode;
        calculateChar();
    }

    private void calculateChar() {
        int modeReadNum = 0;
        switch (mode){
            case 0:
                modeReadNum = 1000;
                break;
            case 1:
                modeReadNum = 2000;
                break;
            case 2:
                modeReadNum = 3000;
                break;
            case 3:
                modeReadNum = 4000;
                break;
            case 4:
                modeReadNum = 5000;
                break;
            case 5:
                modeReadNum = 6400;
                break;
            case 6:
                modeReadNum = 8000;
                break;
            case 7:
                modeReadNum = 9400;
                break;
            case 8:
                modeReadNum = 11000;
                break;
            case 9:
                modeReadNum = 12400;
                break;
            case 10:
                modeReadNum = 14000;
                break;
            case 11:
                modeReadNum = 15400;
                break;
            case 12:
                modeReadNum = 17000;
                break;
            case 13:
                modeReadNum = 19000;
                break;
            case 14:
                modeReadNum = 21000;
                break;
            case 15:
                modeReadNum = 23000;
                break;
            case 16:
                modeReadNum = 25000;
                break;
            case 17:
                modeReadNum = 33000;
                break;
        }

        String article = customer.getMobile().replace("n","\n");
        Log.i("jeff",article);
        //设置每页显示字数
        int pageCharNum = 250;

        //1.动画速度计算
        int pages = modeReadNum/pageCharNum;//文章字数足够的理想状态下每页时间
        int pageTime = 1*60*1000/pages;
        long duration = (long) (pageTime*0.7f);
        long offset = (long) (pageTime*0.3f);
        setDurationAndOffset(pageTime-500,500);

        //2.根据实际字数计算页数以及切分文章文字
        int totalLength = article.length();
        realPageNum = totalLength/pageCharNum;
        if(totalLength%pageCharNum>0){
            realPageNum++;
        }
        readCharArrays = new String[realPageNum];
        for(int i=0;i<realPageNum-1;i++){
            readCharArrays[i] = article.substring(i*pageCharNum,(i+1)*pageCharNum);
        }
        readCharArrays[realPageNum-1] = article.substring((realPageNum-1)*pageCharNum,totalLength);

        //3.初始化文字并记录当前的页数
        currentpPage = 0;
        mContent.setText(readCharArrays[currentpPage]);

        //4.开始动画播放
//        startAnimation();
    }


}
