package com.pf.dllo.food.activity.lib;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.pf.dllo.food.R;
import com.pf.dllo.food.activity.BaseActivity;


import com.pf.dllo.food.adapter.lib.LibAtyKind2Adapter;
import com.pf.dllo.food.adapter.lib.LibAtyKindNextPopAdapter;
import com.pf.dllo.food.adapter.lib.LibAtyKindSearchNextPopAdapter;
import com.pf.dllo.food.adapter.lib.LibAtyPopAdapter;
import com.pf.dllo.food.adapter.lib.LibAtySearchKindAdapter;
import com.pf.dllo.food.adapter.lib.LibAtyTitlePopAdapter;
import com.pf.dllo.food.bean.LibFraBean;
import com.pf.dllo.food.bean.lib.LibAtyBean;
import com.pf.dllo.food.bean.lib.LibKindBean;
import com.pf.dllo.food.bean.lib.LibPopBean;
import com.pf.dllo.food.tools.net.NetHelper;
import com.pf.dllo.food.tools.net.NetListener;
import com.pf.dllo.food.values.NetValues;

import java.util.ArrayList;
import java.util.List;

import razerdp.basepopup.BasePopupWindow;

/**
 * 搜索详情
 */


public class LibSearchDetailAty extends BaseActivity implements NetListener<LibKindBean> {

    private EditText mEditText;// 搜索框

    private LRecyclerView mRv;
    private LRecyclerViewAdapter lRecyclerViewAdapter;

    private int mCount = 1;

    private List<LibKindBean.ItemsBean> datas;// rv

    private ImageButton mIbPop, mIbReturn;
    private LibAtySearchKindAdapter mKind2Adapter;
    private LibAtyKindSearchNextPopAdapter mNextPopAdapter;

    private SortPopup sortPopup;
    private TextView mTvNutRank;//第二个是营养素排行
    private String mKind;
    private int mPos;
    private int mId;
    private int mRPage;

    @Override
    protected int setLayout() {
        return R.layout.activity_lib_search_detail_aty;
    }

    @Override
    protected void initView() {
        mRv = (LRecyclerView) findViewById(R.id.rv_lib_search_detail_kind);
        mIbReturn = (ImageButton) findViewById(R.id.btn_lib_search_detail_aty_back);
        mIbPop = (ImageButton) findViewById(R.id.ib_lib_search_detail_aty_nutri_sort);
        mEditText = (EditText) findViewById(R.id.et_lib_search_detail_aty);
        mTvNutRank = (TextView) findViewById(R.id.tv_lib_search_detail_aty_nutri_sort);


    }


    @Override
    protected void initData() {

        mIbReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEditText != null) {

                    Intent intent = new Intent("com.pf.dllo.food.activity.lib.SEARCH_BR");
                    intent.putExtra("result", "result");// 将返回的结果给数据库判断
                    sendBroadcast(intent);
                }
                finish();


            }
        });

        /**
         * 营养素排序的popup
         */
        sortPopup = new SortPopup(this);
        mIbPop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortPopup.showPopupWindow(view);
            }
        });

        mKind2Adapter = new LibAtySearchKindAdapter(this);
        StartUrl(getKindUrl(mCount));
        lRecyclerViewAdapter = new LRecyclerViewAdapter(mKind2Adapter);
        mRv.setAdapter(lRecyclerViewAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRv.setLayoutManager(manager);


        // 下拉刷新
        mRv.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                mCount = 1;
                mKind2Adapter.setClear();
                StartUrl(getKindUrl(mCount));
                mKind2Adapter.notifyDataSetChanged();
            }
        });
        // 上拉加载
        mRv.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mCount += 1;
                StartUrl(getKindUrl(mCount));
            }
        });

    }


    public String getKindUrl(int i) {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        mEditText.setText(name);
        String kindUrl = NetValues.LIB_SEARCH_ATY_HEAD + i + NetValues.LIB_SEARCH_ATY_TAIL + name;
        Log.d("LibSearchDetailAty", kindUrl);
        return kindUrl;
    }

    private void StartUrl(String url) {
        NetHelper.MyRequest(url, LibKindBean.class, this);
    }


    @Override
    public void successListener(LibKindBean response) {
        List<LibKindBean.ItemsBean> data = response.getItems();

        if (datas == null) {
            datas = data;
        } else {
            for (int j = 0; j < data.size(); j++) {
                datas.add(data.get(j));
            }
        }
        mKind2Adapter.setDatas(datas);
    }

    @Override
    public void errorListener(VolleyError error) {
    }

    class SortPopup extends BasePopupWindow {

        private GridView gvPop;
        private LibAtyPopAdapter popAdapter;
        private List<LibPopBean.TypesBean> sDatas;

        public SortPopup(Activity context) {
            super(context, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            Intent intent = LibSearchDetailAty.this.getIntent();
            final int pos = intent.getIntExtra("pos", 0);
            final int count = intent.getIntExtra("count", 0);
            // pop的gv
            gvPop = (GridView) findViewById(R.id.gv_lib_aty_pop);
            // gv点击事件
            NetHelper.MyRequest(NetValues.LIB_SORT, LibPopBean.class, new NetListener<LibPopBean>() {
                @Override
                public void successListener(LibPopBean response) {
                    sDatas = response.getTypes();
                    // 变pop的字
                    LibPopBean popBean = new LibPopBean();
                    popBean = response;
                    popAdapter = new LibAtyPopAdapter(LibSearchDetailAty.this);
                    popAdapter.setDatas(sDatas);
                    gvPop.setAdapter(popAdapter);
                    final LibPopBean finalPopBean = popBean;
                    gvPop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            mNextPopAdapter = new LibAtyKindSearchNextPopAdapter(LibSearchDetailAty.this);

                            StartParse(getKindUrl(i));
                            // 变pop字
                            mTvNutRank.setText(finalPopBean.getTypes().get(i).getName());
                            lRecyclerViewAdapter = new LRecyclerViewAdapter(mNextPopAdapter);
                            mRv.setAdapter(lRecyclerViewAdapter);

                            sortPopup.initExitAnimation();
                            sortPopup.dismiss();


                            // 下拉刷新
                            mRv.setOnRefreshListener(new OnRefreshListener() {
                                @Override
                                public void onRefresh() {
                                    mCount = 1;
                                    mNextPopAdapter.setClear();
                                    StartParse(getNextUrl(mCount));
                                    mRv.refreshComplete();
                                    mNextPopAdapter.notifyDataSetChanged();
                                }
                            });
                            // 上拉加载
                            mRv.setOnLoadMoreListener(new OnLoadMoreListener() {
                                @Override
                                public void onLoadMore() {
                                    mCount += 1;
                                    StartParse(getKindUrl(mCount));

                                }
                            });


                        }
                    });

                }

                @NonNull
                private String getNextUrl(int i) {

                    String order = sDatas.get(i).getIndex() + "";
                    return NetValues.LIB_SORT_POPGV_HEAD + mKind + NetValues.LIB_SORT_POPGV_NEXT
                            + (mId) + NetValues.LIB_SORT_POPGV_MID + order + NetValues.LIB_SORT_POPGV_MORE + mRPage + NetValues.LIB_SORT_POPGV_TAIL;

                }

                public String getKindUrl(int i) {
                    Intent intent = getIntent();
                    String name = intent.getStringExtra("name");

                    String kindUrl = NetValues.LIB_SEARCH_ATY_HEAD + i + NetValues.LIB_SEARCH_ATY_TAIL + name;
                    Log.d("LibSearchDetailAty", kindUrl);
                    return kindUrl;
                }


                public void StartParse(String url) {
                    NetHelper.MyRequest(url, LibKindBean.class, new NetListener<LibKindBean>() {
                        @Override
                        public void successListener(LibKindBean response) {
                            mKind2Adapter.setClear();
                            List<LibKindBean.ItemsBean> data = response.getItems();
                            if (datas == null) {
                                datas = data;
                            } else {
                                for (int j = 0; j < data.size(); j++) {
                                    datas.add(data.get(j));
                                }
                            }
                            mNextPopAdapter.setDatas(datas);
                        }

                        @Override
                        public void errorListener(VolleyError error) {

                        }
                    });
                }

                @Override
                public void errorListener(VolleyError error) {

                }
            });


        }


        @Override
        protected Animation initShowAnimation() {
            AnimationSet set = new AnimationSet(true);
            set.setInterpolator(new DecelerateInterpolator());
            set.addAnimation(getScaleAnimation(1, 1, 0, 1, Animation.RELATIVE_TO_SELF, 1, Animation.RELATIVE_TO_SELF, 0));
            set.addAnimation(getDefaultAlphaAnimation());
            set.addAnimation(getTranslateAnimation(Animation.RELATIVE_TO_SELF, 1, 1000));
            return set;
        }

        @Override
        protected Animation initExitAnimation() {
            AnimationSet set = new AnimationSet(true);
            set.setInterpolator(new DecelerateInterpolator());
            set.addAnimation(getScaleAnimation(1, 1, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF));
            set.addAnimation(getTranslateAnimation(Animation.RELATIVE_TO_SELF, 1, 1000));
            return set;
        }

        @Override
        public void showPopupWindow(View v) {
            setRelativeToAnchorView(true);
            setRelativePivot(RelativePivot.LEFT);
            setOffsetY(v.getHeight());
            super.showPopupWindow(v);
        }

        @Override
        public View getClickToDismissView() {
            return null;
        }

        @Override
        public View onCreatePopupView() {
            return createPopupById(R.layout.pop_lib_aty);
        }

        @Override
        public View initAnimaView() {
            return getPopupWindowView().findViewById(R.id.root_ll_lib_aty_gv);
        }

    }

}
