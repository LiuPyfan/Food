package com.pf.dllo.food.adapter.lib;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pf.dllo.food.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 2016/12/9.
 * 数据库搜索的listview
 */

public class LibAtySearchLvAdapter extends BaseAdapter {

    private List<String> data;
    private Context context;

    public LibAtySearchLvAdapter(Context context) {
        this.context = context;
        data = new ArrayList<>();
    }

    public void setData(List<String> data) {
        this.data = data;
//        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void setClear() {
        data.clear();
    }

    @Override
    public int getCount() {
        if (data.size()<=3){
            return data != null && data.size() != 0 ? data.size() : 0;
        }else {
            return 3;
        }
    }

    @Override
    public Object getItem(int position) {
        return data != null ? data.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HisViewHolder hisViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_lv_lib_search_aty, parent, false);
            hisViewHolder = new HisViewHolder(convertView);
            convertView.setTag(hisViewHolder);
        } else {
            hisViewHolder = (HisViewHolder) convertView.getTag();
        }
        hisViewHolder.tv.setText(data.get(position));
        return convertView;
    }

    class HisViewHolder {
        private TextView tv;

        public HisViewHolder(View view) {
            tv = (TextView) view.findViewById(R.id.tv_food_name);
        }
    }

}
