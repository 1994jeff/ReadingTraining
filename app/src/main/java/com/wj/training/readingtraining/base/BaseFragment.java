package com.wj.training.readingtraining.base;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * Created by jfdeng@grandstream.cn on 18-7-31.
 */

public abstract class BaseFragment extends Fragment implements RetParam{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(getLayoutRes(),container,false);
        initView(view);
        return view;
    }

    protected abstract void initView(View view);

    protected abstract int getLayoutRes();

    public void showFragmentToast(String msg){
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }

    public void showFragmentToast(int msg){
        Toast.makeText(getActivity(),getString(msg),Toast.LENGTH_SHORT).show();
    }
}
