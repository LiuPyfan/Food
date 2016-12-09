package com.pf.dllo.food.adapter.lib;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pf.dllo.food.R;
import com.pf.dllo.food.bean.lib.LibPopBean;

import java.util.List;

/**
 * Created by dllo on 16/12/2.
 */
public class LibAtyPopAdapter extends BaseAdapter {

    private List<LibPopBean.TypesBean> datas;

    private Context mContext;

    public void setDatas(List<LibPopBean.TypesBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public LibAtyPopAdapter(Context context) {
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
            view = LayoutInflater.from(mContext).inflate(R.layout.item_lib_pop, viewGroup, false);
            libVH = new LibVH(view);
            view.setTag(libVH);

        } else {

            libVH = (LibVH) view.getTag();


        }

        libVH.tvNut.setText(datas.get(i).getName());



        return view;
    }


   public class LibVH {
        TextView tvNut;


       public LibVH(View view) {
            tvNut = (TextView) view.findViewById(R.id.tv_pop_nutri);
        }

        public TextView getTvNut() {
            return tvNut;
        }

        public void setTvNut(TextView tvNut) {
            this.tvNut = tvNut;
        }
    }


}

