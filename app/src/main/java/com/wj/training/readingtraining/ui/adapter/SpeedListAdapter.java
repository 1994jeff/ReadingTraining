package com.wj.training.readingtraining.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wj.training.readingtraining.R;

import java.util.List;

/**
 * Created by jeff on 18-8-9.
 */

public class SpeedListAdapter extends BaseAdapter {

    List<String> stringList;
    Context context;

    public SpeedListAdapter(Context context, List<String> stringList) {
        this.stringList = stringList;
        this.context = context;
    }

    public void swapData(List<String> stringList) {
        this.stringList = stringList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return stringList != null ? stringList.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return stringList != null ? stringList.get(i) : "";
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder = null;
        if(view==null)
        {
            view = LayoutInflater.from(context).inflate(R.layout.speed_list, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        holder.mItemName.setText(stringList.get(i));
        return view;
    }

    public static class ViewHolder {
        public View rootView;
        public TextView mItemName;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.mItemName = (TextView) rootView.findViewById(R.id.item_name);
        }

    }
}
