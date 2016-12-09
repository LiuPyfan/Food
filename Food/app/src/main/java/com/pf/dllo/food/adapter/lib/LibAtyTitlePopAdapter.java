package com.pf.dllo.food.adapter.lib;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pf.dllo.food.R;

import java.util.List;

/**
 * Created by dllo on 16/12/2.
 */
public class LibAtyTitlePopAdapter extends BaseAdapter {

    private List<String> datas;

    private Context mContext;



    public void setDatas(List<String> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public LibAtyTitlePopAdapter(Context context) {
        mContext = context;
    }


    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int i) {
        return datas == null ? null : datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LibVH libVH = null;

        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_lib_title_pop, viewGroup, false);
            libVH = new LibVH(view);
            view.setTag(libVH);

        } else {

            libVH = (LibVH) view.getTag();


        }

        libVH.tvPop.setText(datas.get(i));



        return view;
    }

    class LibVH {
        TextView tvPop;


        public LibVH(View view) {
            tvPop = (TextView) view.findViewById(R.id.tv_item_lib_title_pop);

        }
    }


}
