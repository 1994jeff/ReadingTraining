package com.wj.training.readingtraining.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.VolleyError;
import com.wj.training.readingtraining.R;
import com.wj.training.readingtraining.base.BaseActivity;
import com.wj.training.readingtraining.base.BaseFragment;
import com.wj.training.readingtraining.bean.Customer;
import com.wj.training.readingtraining.common.VolleyInterface;
import com.wj.training.readingtraining.common.VolleyRequest;
import com.wj.training.readingtraining.ui.activity.MainActivity;
import com.wj.training.readingtraining.util.Constants;
import com.wj.training.readingtraining.widget.ReadView;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by jeff on 18-8-9.
 */

public class FragmentReading extends BaseFragment implements ReadView.FinishReadListener {

    ReadView readView;
    LinearLayout timeEndContainer;
    TextView timeEnd;
    int count = 3;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    String obj = (String) msg.obj;
                    timeEnd.setText(obj);
                    break;
                case 1:
                    timeEndContainer.setVisibility(View.GONE);
                    readView.setVisibility(View.VISIBLE);
                    readView.startAnimation();
                    break;
            }
        }
    };

    @Override
    protected void initView(View view) {

        String nameKey = "";
        Bundle bundle = getArguments();
        if(bundle!=null){
            nameKey = bundle.getString("articleName","");
        }

        getArticle(nameKey);

        readView = view.findViewById(R.id.readView);
        readView.setOnFinishReadListener(this);
        timeEndContainer = view.findViewById(R.id.timeEndContainer);
        timeEnd = view.findViewById(R.id.timeEnd);
        timeEnd.setText("3");
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (count>0){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count--;
                    Message message = Message.obtain();
                    message.what = 0;
                    message.obj = (count)+"";
                    handler.sendMessage(message);
                }
                Message message = Message.obtain();
                message.what = 1;
                handler.sendMessage(message);
            }
        }).start();
    }

    private void getArticle(String nameKey) {
        String url = Constants.BASE_URL+Constants.ARTILE_API+Constants.TOKEN+"&no="+nameKey;
        Log.i("jeffs",""+url);
        VolleyRequest.requestGet(getActivity(),url,"",null,new VolleyInterface(getActivity(), VolleyInterface.mListener, VolleyInterface.mErrorListener){
            @Override
            public void onSuccess(String result) {
                String json = result.replace("\\", "");
                json = json.substring(1, json.length() - 1);
                JSONObject object = (JSONObject) JSON.parse(json);
                String code = object.getString(RET_CODE);
                String msg = object.getString(RET_MSG);
                if(code.equals(SUCCESS)){
                    JSONArray jsonArray = object.getJSONArray(RET_DATA);
                    if(jsonArray!=null && jsonArray.size()>0){
                        int size = jsonArray.size();
                        if(size>0){
                            Customer customer = jsonArray.getObject(0,Customer.class);
                            if(customer!=null)
                            {
                                int mode = ((MainActivity)getActivity()).getSelectSpeedItem();
                                readView.setArticle(customer,mode);
                            }else{
                                showFragmentToast(R.string.unknown_error);
                            }
                        }else {
                            showFragmentToast(R.string.unknown_error);
                        }
                    }
                }else if(code.equals(FAILED)){
                    ((BaseActivity)getActivity()).showFragmentToast(msg);
                }
            }
            @Override
            public void onError(VolleyError error) {
                ((BaseActivity)getActivity()).showFragmentToast(R.string.unknown_error);
                ((BaseActivity)getActivity()).showFragmentToast(error.getMessage());
            }
        });
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_reading;
    }

    @Override
    public void onFinishReadListener(int readCharNum,int readPageNum) {
        readView.setVisibility(View.GONE);
        timeEnd.setText("阅读完毕,共"+readCharNum+"字，"+readPageNum+"页");
        timeEndContainer.setVisibility(View.VISIBLE);
    }
}
