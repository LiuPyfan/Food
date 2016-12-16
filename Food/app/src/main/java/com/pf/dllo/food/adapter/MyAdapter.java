package com.pf.dllo.food.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pf.dllo.food.R;
import com.pf.dllo.food.bean.MyBean;

import java.util.List;

/**
 * Created by dllo on 16/11/23.
 */
public class MyAdapter extends BaseAdapter {

    private Context mContext;
    private int[] left = new int[]{R.mipmap.ic_my_photo, R.mipmap.ic_my_collect, R.mipmap.ic_my_upload, R.mipmap.ic_my_order};
    private int[] right = new int[]{R.mipmap.ic_arrow_right_default, R.mipmap.ic_arrow_right_default, R.mipmap.ic_arrow_right_default, R.mipmap.ic_arrow_right_default};
    private String[] title ;

    public MyAdapter(Context context) {
        this.mContext = context;

    }

    public void setDatas(int[] i, int[] j, String[] str) {

        this.left = i;
        this.right = j;
        this.title = str;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public Object getItem(int i) {
        return title == null ? null : title[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        VH vh = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_my_item, viewGroup, false);
            vh = new VH(view);
            view.setTag(vh);
        } else {
            vh = (VH) view.getTag();
        }
        vh.ivRight.setImageResource(left[i]);
        vh.ivLeft.setImageResource(right[i]);
        vh.mTextView.setText(title[i]);
        return view;
    }


    class VH {
        TextView mTextView;
        ImageView ivRight, ivLeft;

        public VH(View view) {
            mTextView = (TextView) view.findViewById(R.id.tv_item_my_photo);
            ivRight = (ImageView) view.findViewById(R.id.iv_item_my_photo);
            ivLeft = (ImageView) view.findViewById(R.id.iv_item_my_arrow);
        }
    }
}
