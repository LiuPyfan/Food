package com.pf.dllo.food.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.pf.dllo.food.R;
import com.pf.dllo.food.adapter.MyAdapter;
import com.pf.dllo.food.bean.MyBean;
import com.pf.dllo.food.tools.ScreenSetUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends BaseFragment {


    private ListView mListView;


    private int[] left = new int[]{R.mipmap.ic_my_photo, R.mipmap.ic_my_collect, R.mipmap.ic_my_upload, R.mipmap.ic_my_order};
    private int[] right = new int[]{R.mipmap.ic_arrow_right_default, R.mipmap.ic_arrow_right_default, R.mipmap.ic_arrow_right_default, R.mipmap.ic_arrow_right_default};
    private String[] title = new String[]{"我的照片", "我的收藏", "上传食物数据", "我的订单"};


    @Override
    protected int setLayout() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView(View view) {
        mListView = bindView(R.id.lv_my);
    }

    @Override
    protected void initData() {
        MyAdapter adapter = new MyAdapter(getContext());


            adapter.setDatas(left, right, title);

        mListView.setAdapter(adapter);


    }


}
