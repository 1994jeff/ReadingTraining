package com.wj.training.readingtraining.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.wj.training.readingtraining.R;
import com.wj.training.readingtraining.base.BaseActivity;
import com.wj.training.readingtraining.base.BaseFragment;
import com.wj.training.readingtraining.ui.adapter.SpeedListAdapter;
import com.wj.training.readingtraining.ui.fragment.FragmentArticle;
import com.wj.training.readingtraining.ui.fragment.FragmentReading;
import com.wj.training.readingtraining.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private SpeedListAdapter adapter;
    private ListView mSpeedList;


    private int selectSpeedItem = -1;

    public int getSelectSpeedItem() {
        return selectSpeedItem;
    }

    public void setSelectSpeedItem(int selectSpeedItem) {
        this.selectSpeedItem = selectSpeedItem;
    }

    FragmentArticle fragmentArticle = new FragmentArticle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
    }

    private void initView() {
        mSpeedList = (ListView) findViewById(R.id.speed_list);
        adapter = new SpeedListAdapter(this, getSpeedList());
        mSpeedList.setAdapter(adapter);
        switchFragment(fragmentArticle);
        mSpeedList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectSpeedItem = i;
            }
        });
    }

    private List<String> getSpeedList() {
        List<String> list = new ArrayList<>();
        for (String s : Constants.SPEED_NAME) {
            list.add(s);
        }
        return list;
    }

    @Override
    public void onBackPressed() {
        BaseFragment fragment = (BaseFragment) getSupportFragmentManager().findFragmentById(R.id.container);
        if(fragment instanceof FragmentReading){
        }else
        {
            getSupportFragmentManager().popBackStack();
        }
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {

    }
}
