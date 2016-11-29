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
import com.pf.dllo.food.bean.home.HomeKnowBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/11/24.
 */
public class KnowAdapter extends BaseAdapter{
   private List<HomeKnowBean.FeedsBean>datas;
    private Context mContext;
    public void setDatas(List<HomeKnowBean.FeedsBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public KnowAdapter(Context context) {
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
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_home_know,viewGroup,false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tvSource.setText(datas.get(i).getSource());
        holder.tvTitle.setText(datas.get(i).getTitle());
        holder.tvTail.setText(datas.get(i).getTail() + "");

        Glide.with(mContext).load(datas.get(i).getImages().get(0)).into(holder.mImageView);

        return view;
    }


    class ViewHolder {
        ImageView mImageView,ivWatch;
        TextView tvSource, tvTitle, tvTail;

        public ViewHolder(View view) {
            ivWatch = (ImageView) view.findViewById(R.id.iv_know_watch);
            mImageView = (ImageView) view.findViewById(R.id.iv_know_img);
            tvSource = (TextView) view.findViewById(R.id.tv_know_source);
            tvTitle = (TextView) view.findViewById(R.id.tv_know_title);
            tvTail = (TextView) view.findViewById(R.id.tv_know_tail);
        }
    }
}
