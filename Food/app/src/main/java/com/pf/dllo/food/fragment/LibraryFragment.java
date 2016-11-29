package com.pf.dllo.food.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.pf.dllo.food.R;
import com.pf.dllo.food.activity.LibSearchAty;
import com.pf.dllo.food.adapter.LibraryGvAdapter;
import com.pf.dllo.food.bean.LibFraBean;
import com.pf.dllo.food.tools.ITextChanged;
import com.pf.dllo.food.tools.MyScrollView;
import com.pf.dllo.food.tools.VolleyInstance;
import com.pf.dllo.food.tools.VolleyResult;
import com.pf.dllo.food.tools.net.NetHelper;
import com.pf.dllo.food.tools.net.NetListener;
import com.pf.dllo.food.values.NetValues;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LibraryFragment extends BaseFragment {

    private MyScrollView mScrollView;


    private GridView mGvLib,mGvBrand,mGvChain;
    private Button mBtnSearch;


    public static LibraryFragment newInstance() {

        Bundle args = new Bundle();

        LibraryFragment fragment = new LibraryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_library;
    }

    @Override
    protected void initView(View view) {
        mScrollView = bindView(R.id.scroll_root);
        mGvLib = bindView(R.id.gv_library_category);
        mGvBrand = bindView(R.id.gv_library_brand);
        mGvChain = bindView(R.id.gv_library_chain);
        mBtnSearch = bindView(R.id.btn_library_search);// 搜索


    }

    @Override
    protected void initData() {

        mScrollView.setScrollViewListener(new MyScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(int x, int y, int oldx, int oldy) {
            }
        });
        // 网络请求
        NetHelper.MyRequest(NetValues.LIB_GV, LibFraBean.class, new NetListener<LibFraBean>() {
            @Override
            public void successListener(LibFraBean response) {
                List<LibFraBean.GroupBean.CategoriesBean> datas = response.getGroup().get(0).getCategories();
                LibraryGvAdapter libraryAdapter = new LibraryGvAdapter(mContext);
                libraryAdapter.setDatas(datas);
                mGvLib.setAdapter(libraryAdapter);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
        VolleyInstance.getInstance().startRequest(NetValues.LIB_GV, new VolleyResult() {
            @Override
            public void getSuccess(String resultStr) {
                Gson gson = new Gson();
                LibFraBean bean = gson.fromJson(resultStr,LibFraBean.class);
                List<LibFraBean.GroupBean.CategoriesBean> datas = bean.getGroup().get(1).getCategories();
                LibraryGvAdapter brandAdapter = new LibraryGvAdapter(mContext);
                brandAdapter.setDatas(datas);
                mGvBrand.setAdapter(brandAdapter);

            }

            @Override
            public void getFailure() {

            }
        });
        VolleyInstance.getInstance().startRequest(NetValues.LIB_GV, new VolleyResult() {
            @Override
            public void getSuccess(String resultStr) {
                Gson gson = new Gson();
                LibFraBean bean = gson.fromJson(resultStr,LibFraBean.class);
                List<LibFraBean.GroupBean.CategoriesBean> datas = bean.getGroup().get(2).getCategories();
                LibraryGvAdapter chainAdapter = new LibraryGvAdapter(mContext);
                chainAdapter.setDatas(datas);
                mGvChain.setAdapter(chainAdapter);

            }

            @Override
            public void getFailure() {

            }
        });

        mBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent().setClass(mContext, LibSearchAty.class);
                startActivity(intent);
            }
        });


    }


}
