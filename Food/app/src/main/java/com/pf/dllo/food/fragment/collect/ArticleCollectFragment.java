package com.pf.dllo.food.fragment.collect;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pf.dllo.food.R;
import com.pf.dllo.food.activity.home.HomeEvaAty;
import com.pf.dllo.food.adapter.home.ArticleCollectAdapter;
import com.pf.dllo.food.db.CollectBean;
import com.pf.dllo.food.db.DBTool;
import com.pf.dllo.food.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 文章的收藏
 */
public class ArticleCollectFragment extends BaseFragment {
    private ListView mListView;
    //    private List<CollectBean> list;
    private List<CollectBean> list;
    private ArticleCollectAdapter mAdapter;

    public ArticleCollectFragment() {
        // Required empty public constructor
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_article_collect;
    }

    @Override
    protected void initView(View view) {
        mListView = bindView(R.id.lv_article_collect);
    }

    @Override
    protected void initData() {
        list = new ArrayList<>();

        list = DBTool.getInstance().queryAll();

        mAdapter = new ArticleCollectAdapter(mContext);
        mAdapter.setTitles(list);
        mListView.setAdapter(mAdapter);


        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (DBTool.getInstance().queryAll() != null) {
                    String delData = list.get(position).getTitle().toString();
                    DBTool.getInstance().deleteByTitle(delData);
                    mAdapter.setClear();
                }
                List<CollectBean> newList = DBTool.getInstance().queryAll();
                mAdapter.setTitles(newList);
                mAdapter.notifyDataSetChanged();
                return false;
            }
        });
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                List<CollectBean> newList = DBTool.getInstance().queryAll();
//                String url = newList.get(position).getUrl();
//                Log.d("ArticleCollectFragment", url);
//                Intent intent = new Intent(mContext,HomeEvaAty.class);
//                intent.putExtra("url",url);
//                startActivity(intent);
//            }
//        });

    }

}
