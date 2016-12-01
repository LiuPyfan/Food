package com.pf.dllo.food.adapter.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pf.dllo.food.R;
import com.pf.dllo.food.bean.home.HomeDeliBean;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/11/25.
 */
public class DeliAdapter extends BaseAdapter {

    private List<HomeDeliBean.FeedsBean> datas;
    private Context mContext;

    public static final int TYPE_ONE = 1;
    public static final int TYPE_TWO = 2;

    public static final int TYPE_COUNT = 3;

    public void setDatas(List<HomeDeliBean.FeedsBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }


    public void clean(){
        datas.clear();
    }

    public DeliAdapter(Context context) {
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
    public int getItemViewType(int position) {
        if (TYPE_ONE == datas.get(position).getContent_type()) {
            return TYPE_ONE;
        } else {
            return TYPE_TWO;
        }
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_COUNT;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        FYVH fyvh = null;
        DeliVH deliVH = null;

        if (view == null) {
            switch (getItemViewType(i)) {
                case TYPE_ONE:
                    view = LayoutInflater.from(mContext).inflate(R.layout.item_home_know, viewGroup, false);
                    fyvh = new FYVH(view);
                    view.setTag(fyvh);
                    break;
                case TYPE_TWO:

                    view = LayoutInflater.from(mContext).inflate(R.layout.item_home_deli, viewGroup, false);
                    deliVH = new DeliVH(view);
                    view.setTag(deliVH);

                    break;
            }
        } else {
            switch (getItemViewType(i)) {
                case TYPE_ONE:
                    fyvh = (FYVH) view.getTag();
                    break;
                case TYPE_TWO:
                    deliVH = (DeliVH) view.getTag();
                    break;
            }
        }
        switch (getItemViewType(i)) {
            case TYPE_ONE:
                fyvh.tvSource.setText(datas.get(i).getSource());
                fyvh.tvTitle.setText(datas.get(i).getTitle());
                fyvh.tvTail.setText(datas.get(i).getTail() + "");

                Glide.with(mContext).load(datas.get(i).getImages().get(0)).into(fyvh.mImageView);
                break;
            case TYPE_TWO:
                deliVH.tvDeliSource.setText(datas.get(i).getSource());
                deliVH.tvDeliTitle.setText(datas.get(i).getTitle());
                deliVH.tvDeliTail.setText(datas.get(i).getTail());
                Glide.with(mContext).load(datas.get(i).getImages().get(0)).into(deliVH.ivDeli1);
                Glide.with(mContext).load(datas.get(i).getImages().get(1)).into(deliVH.ivDeli2);
                Glide.with(mContext).load(datas.get(i).getImages().get(2)).into(deliVH.ivDeli3);
                break;
        }

        return view;
    }


    class FYVH {
        ImageView mImageView, ivWatch;
        TextView tvSource, tvTitle, tvTail;

        public FYVH(View view) {
            ivWatch = (ImageView) view.findViewById(R.id.iv_know_watch);
            mImageView = (ImageView) view.findViewById(R.id.iv_know_img);
            tvSource = (TextView) view.findViewById(R.id.tv_know_source);
            tvTitle = (TextView) view.findViewById(R.id.tv_know_title);
            tvTail = (TextView) view.findViewById(R.id.tv_know_tail);
        }
    }

    class DeliVH {

        TextView tvDeliTitle, tvDeliSource, tvDeliTail;
        ImageView ivDeli1, ivDeli2, ivDeli3;

        public DeliVH(View view) {
            tvDeliTitle = (TextView) view.findViewById(R.id.tv_deli_title);
            tvDeliSource = (TextView) view.findViewById(R.id.tv_deli_source);
            tvDeliTail = (TextView) view.findViewById(R.id.tv_deli_tail);
            ivDeli1 = (ImageView) view.findViewById(R.id.iv_deli_img1);
            ivDeli2 = (ImageView) view.findViewById(R.id.iv_deli_img2);
            ivDeli3 = (ImageView) view.findViewById(R.id.iv_deli_img3);
        }
    }


}
