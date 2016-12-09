package com.pf.dllo.food.adapter.lib;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pf.dllo.food.R;
import com.pf.dllo.food.bean.lib.LibAtyBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/12/1.
 * lib详情的适配器
 */
public class LibAtyKindAdapter extends BaseAdapter {
    private List<LibAtyBean.FoodsBean> datas;
    private Context mContext;


    public void setDatas(List<LibAtyBean.FoodsBean> datas) {
        this.datas = datas;

        notifyDataSetChanged();
    }


    public void clean() {
        datas.clear();
    }

    public LibAtyKindAdapter(Context context) {
        mContext = context;
        datas = new ArrayList<>();

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
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_lib_aty_sort, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tvName.setText(datas.get(i).getName());
        holder.tvCalory.setText(datas.get(i).getCalory());

        Glide.with(mContext).load(datas.get(i).getThumb_image_url()).into(holder.mImageView);


        if (datas.get(i).getHealth_light() == 1) {
            Glide.with(mContext).load(R.mipmap.ic_food_light_green).into(holder.ivHealth);
        } else if (datas.get(i).getHealth_light() == 2) {
            Glide.with(mContext).load(R.mipmap.ic_food_light_yellow).into(holder.ivHealth);
        } else
            Glide.with(mContext).load(R.mipmap.ic_food_light_red).into(holder.ivHealth);
        return view;
    }

    class ViewHolder {
        ImageView mImageView, ivHealth;
        TextView tvName, tvCalory;

        public ViewHolder(View view) {
            mImageView = (ImageView) view.findViewById(R.id.iv_lib_aty_thumb_url);
            tvName = (TextView) view.findViewById(R.id.tv_lib_aty_name);
            tvCalory = (TextView) view.findViewById(R.id.tv_lib_aty_calory);
            ivHealth = (ImageView) view.findViewById(R.id.iv_lib_aty_health_light);


        }

    }
}
