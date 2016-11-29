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
import com.pf.dllo.food.bean.home.HomeEvaBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/11/24.
 */
public class EvaAdapter extends BaseAdapter{
    private List<HomeEvaBean.FeedsBean>datas;
    private Context mContext;
    private HomeEvaBean bean;

    public void setBean(HomeEvaBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    public void setDatas(List<HomeEvaBean.FeedsBean> datas) {
//        this.datas = datas;
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }


    public void clean(){
        datas.clear();
    }

    public EvaAdapter(Context context) {
        mContext = context;
        datas = new ArrayList<>();
        bean = new HomeEvaBean();
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
            view = LayoutInflater.from(mContext).inflate(R.layout.item_home_eva,viewGroup,false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tvSource.setText(datas.get(i).getSource());
        holder.tvTitle.setText(datas.get(i).getTitle());
        holder.tvTail.setText(datas.get(i).getTail());
        Glide.with(mContext).load(datas.get(i).getBackground()).into(holder.mImageView);

        return view;
    }
    class ViewHolder{
        ImageView mImageView;
        TextView tvSource,tvTitle,tvTail;

        public ViewHolder(View view) {
            mImageView = (ImageView) view.findViewById(R.id.iv_eva_bg);
            tvSource = (TextView) view.findViewById(R.id.tv_eva_source);
            tvTitle = (TextView) view.findViewById(R.id.tv_eva_title);
            tvTail = (TextView) view.findViewById(R.id.tv_eva_tail);
        }

    }
}
