package com.pf.dllo.food.adapter.lib;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pf.dllo.food.R;
import com.pf.dllo.food.bean.lib.LibAtyBean;
import com.pf.dllo.food.tools.OnRvClick;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/12/5.
 */
public class LibAtyKindMenuPopAdapter extends RecyclerView.Adapter<LibAtyKindMenuPopAdapter.LibAtyKindVH>{
    private List<LibAtyBean.FoodsBean> datas;
    private Context mContext;

    public LibAtyKindMenuPopAdapter(Context context) {
        mContext = context;
        datas = new ArrayList<>();
    }

    public List<LibAtyBean.FoodsBean> getDatas() {
        return datas;
    }

    public void setDatas(List<LibAtyBean.FoodsBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public void setClear() {
        datas.clear();
    }

    @Override
    public LibAtyKindVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_lib_aty_sort,parent,false);
        LibAtyKindVH kindVH = new LibAtyKindVH(view);
        return kindVH;
    }

    @Override
    public void onBindViewHolder(final LibAtyKindVH holder, final int position) {
        holder.tvName.setText(datas.get(position).getName());
        holder.tvCalory.setText(datas.get(position).getCalory());

        Glide.with(mContext).load(datas.get(position).getThumb_image_url()).into(holder.mImageView);

        if (datas.get(position).getHealth_light() == 1) {
            Glide.with(mContext).load(R.mipmap.ic_food_light_green).into(holder.ivHealth);
        } else if (datas.get(position).getHealth_light() == 2) {
            Glide.with(mContext).load(R.mipmap.ic_food_light_yellow).into(holder.ivHealth);
        } else
            Glide.with(mContext).load(R.mipmap.ic_food_light_red).into(holder.ivHealth);

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int pos = holder.getAdapterPosition();
////                int types = datas.get(position).getContent_type();
//               mOnRvClick.onRvClick(pos);
////                mOnRvClick.onRvClick(pos,types);
//}
//
//        });

    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }


    private OnRvClick mOnRvClick;

    public void setOnRvClick(OnRvClick onRvClick) {
        mOnRvClick = onRvClick;
    }



    class  LibAtyKindVH extends RecyclerView.ViewHolder{
        ImageView mImageView, ivHealth;
        TextView tvName, tvCalory;
        public LibAtyKindVH(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_lib_aty_thumb_url);
            tvName = (TextView) itemView.findViewById(R.id.tv_lib_aty_name);
            tvCalory = (TextView) itemView.findViewById(R.id.tv_lib_aty_calory);
            ivHealth = (ImageView) itemView.findViewById(R.id.iv_lib_aty_health_light);
        }
    }
}
