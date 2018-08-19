package com.wj.training.readingtraining.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wj.training.readingtraining.R;
import com.wj.training.readingtraining.bean.Customer;

import java.util.List;

/**
 * Created by jeff on 18-8-9.
 */

public class GridAdapter extends BaseAdapter {

    List<Customer> customerList;
    Context context;

    public GridAdapter(List<Customer> customerList, Context context) {
        this.customerList = customerList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return customerList != null ? customerList.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return customerList != null ? customerList.get(i) : null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void swapData(List<Customer> customers){
        this.customerList = customers;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.grid_item, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.mTitle.setText(customerList.get(i).getName());
        holder.mArticleContent.setText(customerList.get(i).getMobile());
        return view;
    }

    public static class ViewHolder {
        public View rootView;
        public TextView mTitle;
        public TextView mArticleContent;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.mTitle = (TextView) rootView.findViewById(R.id.title);
            this.mArticleContent = (TextView) rootView.findViewById(R.id.articleContent);
        }

    }
}
