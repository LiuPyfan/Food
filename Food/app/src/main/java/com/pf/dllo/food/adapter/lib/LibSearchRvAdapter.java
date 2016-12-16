package com.pf.dllo.food.adapter.lib;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pf.dllo.food.R;
import com.pf.dllo.food.tools.OnSearchRvClick;

import java.util.List;

/**
 * Created by dllo on 2016/12/7.
 */

public class LibSearchRvAdapter extends RecyclerView.Adapter<LibSearchRvAdapter.SearchViewHolder>{

    private List<String> datas;
    private Context context;
    private OnSearchRvClick mOnSearchRvClick;

    public LibSearchRvAdapter(OnSearchRvClick onSearchRvClick) {
        mOnSearchRvClick = onSearchRvClick;
    }

    public void setOnSearchRvClick(OnSearchRvClick onSearchRvClick) {
        mOnSearchRvClick = onSearchRvClick;
        notifyDataSetChanged();
    }

    public LibSearchRvAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<String> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public List<String> getDatas() {
        return datas;
    }

    // 创建缓存类的方法
    // 主要作用 绑定视图
    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 加载行布局
        View view = LayoutInflater.from(context).inflate(R.layout.item_lib_rv_search,parent,false);
        SearchViewHolder holder = new SearchViewHolder(view);
        return holder;
    }

    /**
     * 绑定实体类
     * 主要作用 绑定数据
     */
    @Override
    public void onBindViewHolder(final SearchViewHolder holder, int position) {
        holder.textView.setText(datas.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();
                mOnSearchRvClick.onRvClick(pos,datas.get(pos));
                Log.d("LibSearchRvAdapter", datas.get(pos));
            }
        });

    }
    // 返回数量
    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class SearchViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public SearchViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_item_searchrv);
        }
    }
}

