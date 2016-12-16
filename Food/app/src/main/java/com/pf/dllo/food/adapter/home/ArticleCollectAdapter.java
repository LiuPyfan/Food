package com.pf.dllo.food.adapter.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pf.dllo.food.R;
import com.pf.dllo.food.db.CollectBean;
import com.pf.dllo.food.tools.ScreenSetUtils;

import java.util.List;

/**
 * Created by dllo on 2016/12/13.
 */

public class ArticleCollectAdapter extends BaseAdapter {

    private List<CollectBean> titles;
//    private List<String> titles;
    private Context mContext;

    public ArticleCollectAdapter(Context context) {
        mContext = context;
    }

    public void setClear(){
        titles.clear();
    }

    public void setTitles(List<CollectBean> titles) {
        this.titles = titles;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return titles == null ? 0 :titles.size();
    }

    @Override
    public Object getItem(int position) {
        return titles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_collect_lv,parent,false);
            int height = ScreenSetUtils.getScreenHeight(mContext);
            convertView.setMinimumHeight(height / 12);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvTitle.setText(titles.get(position).getTitle());
        return convertView;
    }

    class ViewHolder {
        TextView tvTitle;

        public ViewHolder(View view) {
            tvTitle = (TextView) view.findViewById(R.id.tv_item_my_collect);
        }
    }
}
