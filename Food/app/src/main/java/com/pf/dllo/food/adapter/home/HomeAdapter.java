package com.pf.dllo.food.adapter.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pf.dllo.food.R;
import com.pf.dllo.food.bean.home.HomeHomeBean;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dllo on 16/11/24.
 */
public class HomeAdapter extends RecyclerView.Adapter {
    private List<HomeHomeBean.FeedsBean> datas;
    private Context mContext;


    public static final int CONTENT_CONTENT = 5;
    public static final int CONTENT_AD = 6;


    public void setDatas(List<HomeHomeBean.FeedsBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public HomeAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        return datas.get(position).getContent_type();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case CONTENT_AD:
                View viewAd= LayoutInflater.from(mContext).inflate(R.layout.item_home_ad, parent, false);
                holder = new HomeAdVH(viewAd); // 易出现类型转换异常
                break;
            case CONTENT_CONTENT:
                View viewContent = LayoutInflater.from(mContext).inflate(R.layout.item_home_content, parent, false);
                holder = new HomeContentVH(viewContent);
                break;
        }
        return holder;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
      int type = getItemViewType(position);
        switch (type) {
            case CONTENT_AD:
                HomeAdVH adVH = (HomeAdVH) holder;
//                adVH.mImageView
                Glide.with(mContext).load(datas.get(position).getCard_image()).into(adVH.mImageView);
                break;
            case CONTENT_CONTENT:
                HomeContentVH contentVH = (HomeContentVH) holder;
                contentVH.tvTitle.setText(datas.get(position).getTitle());
                contentVH.tvPublish.setText(datas.get(position).getPublisher());
                contentVH.tvLike.setText(datas.get(position).getLike_ct()+ "");
                Glide.with(mContext).load(datas.get(position).getCard_image()).into(contentVH.ivHome);
                Glide.with(mContext).load(datas.get(position).getPublisher_avatar()).into(contentVH.ivAvatar);
                break;
        }
    }


    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class HomeAdVH extends RecyclerView.ViewHolder {
        private ImageView mImageView;

        public HomeAdVH(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_item_home_ad);
        }
    }

    class HomeContentVH extends RecyclerView.ViewHolder {
        private TextView tvPublish, tvTitle, tvLike;
        private ImageView ivHome;
        private CircleImageView ivAvatar;

        public HomeContentVH(View itemView) {
            super(itemView);
            ivAvatar = (CircleImageView) itemView.findViewById(R.id.iv_item_home_avatar);
            ivHome = (ImageView) itemView.findViewById(R.id.iv_item_home_card);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_item_home_title);
            tvPublish = (TextView) itemView.findViewById(R.id.tv_item_home_publisher);
            tvLike = (TextView) itemView.findViewById(R.id.tv_item_home_like);
        }
    }

}
