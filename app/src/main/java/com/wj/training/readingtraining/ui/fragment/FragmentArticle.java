package com.wj.training.readingtraining.ui.fragment;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.VolleyError;
import com.wj.training.readingtraining.R;
import com.wj.training.readingtraining.base.BaseActivity;
import com.wj.training.readingtraining.base.BaseFragment;
import com.wj.training.readingtraining.bean.Customer;
import com.wj.training.readingtraining.bean.User;
import com.wj.training.readingtraining.common.VolleyInterface;
import com.wj.training.readingtraining.common.VolleyRequest;
import com.wj.training.readingtraining.ui.activity.LoginActivity;
import com.wj.training.readingtraining.ui.activity.MainActivity;
import com.wj.training.readingtraining.ui.adapter.GridAdapter;
import com.wj.training.readingtraining.util.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeff on 18-8-9.
 */

public class FragmentArticle extends BaseFragment implements View.OnClickListener {

    private Button mInSchool;
    private Button mOutSchool;

    private GridView mGridView;
    private GridAdapter adapter;
    List<Customer> customers = new ArrayList<>();

    @Override
    protected void initView(View view) {
        mGridView = view.findViewById(R.id.grid_view);
        adapter = new GridAdapter(customers,getActivity());
        mInSchool = (Button) view.findViewById(R.id.inSchool);
        mInSchool.setOnClickListener(this);
        mOutSchool = (Button) view.findViewById(R.id.outSchool);
        mOutSchool.setOnClickListener(this);

        mGridView.setAdapter(adapter);
        mGridView.post(new Runnable() {
            @Override
            public void run() {
                float width = mGridView.getWidth();
                if(width>0 && width<600){
                    mGridView.setNumColumns(4);
//                    mGridView.setColumnWidth((int) ((width/2)-90));
//                    mGridView.setColumnWidth(2);
                }else if(width<800){
                    mGridView.setNumColumns(6);
//                    mGridView.setColumnWidth(2);
//                    mGridView.setColumnWidth((int) ((width/4)-90));
                }else if(width<1200){
                    mGridView.setNumColumns(8);
//                    mGridView.setColumnWidth(2);
//                    mGridView.setColumnWidth((int) ((width/6)-90));
                }else{
                    mGridView.setNumColumns(4);
//                    mGridView.setColumnWidth(2);
                }
            }
        });
        getCustomers(null);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int select = ((MainActivity)getActivity()).getSelectSpeedItem();
                if(select<0){
                    showFragmentToast(R.string.unselect_speed_mode);
                }else {
                    FragmentReading fragmentReading = new FragmentReading();
                    Bundle bundle = new Bundle();
                    bundle.putString("articleName",customers.get(i).getNo());
                    fragmentReading.setArguments(bundle);
                    ((MainActivity)getActivity()).switchFragmentByStack(fragmentReading);
                }
            }
        });
    }

    private List<Customer> getCustomers(Customer customer) {
        String url;
        if (customer!=null){
            url  = Constants.BASE_URL+Constants.ARTILE_API+Constants.TOKEN+"&level="+customer.getLevel();
        }else {
            url  = Constants.BASE_URL+Constants.ARTILE_API+Constants.TOKEN+"&level=1";
        }
        VolleyRequest.requestGet(getActivity(),url,"",null,new VolleyInterface(getActivity(), VolleyInterface.mListener, VolleyInterface.mErrorListener){
            @Override
            public void onSuccess(String result) {
                String json = result.replace("\\", "");
                json = json.substring(1, json.length() - 1);
                JSONObject object = (JSONObject) JSON.parse(json);
                Log.i("jeff","object = "+object);
                String code = object.getString(RET_CODE);
                String msg = object.getString(RET_MSG);
                if(code.equals(SUCCESS)){
                    JSONArray jsonArray = object.getJSONArray(RET_DATA);
                    if(jsonArray!=null && jsonArray.size()>0){
                        int size = jsonArray.size();
                        if(size>0){
                            customers.clear();
                            for (int i=0;i<size;i++){
                                Customer customer = jsonArray.getObject(i, Customer.class);
                                customers.add(customer);
                            }
                            adapter.swapData(customers);
                        }
                    }
                }else if(code.equals(FAILED)){
                    ((BaseActivity)getActivity()).showFragmentToast(msg);
                }
            }
            @Override
            public void onError(VolleyError error) {
                ((BaseActivity)getActivity()).showFragmentToast(R.string.unknown_error);
            }
        });
        return customers;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_article;
    }

    public void notifyAdapter(int i) {
        Customer customer = new Customer();
        customer.setLevel(i);
        getCustomers(customer);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.inSchool:
                notifyAdapter(1);
                break;
            case R.id.outSchool:
                notifyAdapter(2);
                break;
        }
    }
}
