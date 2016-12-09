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
import com.pf.dllo.food.adapter.lib.LibAtyKindMenuPopAdapter;
import com.pf.dllo.food.adapter.lib.LibAtyKindNextPopAdapter;
import com.pf.dllo.food.adapter.lib.LibAtyPopAdapter;
import com.pf.dllo.food.adapter.lib.LibAtyTitlePopAdapter;
import com.pf.dllo.food.bean.LibFraBean;
import com.pf.dllo.food.bean.lib.LibAtyBean;
import com.pf.dllo.food.bean.lib.LibPopBean;
import com.pf.dllo.food.tools.net.NetHelper;
import com.pf.dllo.food.tools.net.NetListener;
import com.pf.dllo.food.values.NetValues;

import java.util.ArrayList;
import java.util.List;

import razerdp.basepopup.BasePopupWindow;

public class LibKind3Aty extends BaseActivity implements NetListener<LibAtyBean> {
    private LRecyclerView mRv;
    private LRecyclerViewAdapter lRecyclerViewAdapter;

    private int mCount = 1;

    private List<LibAtyBean.FoodsBean> datas, menuDatas;// rv

    private ImageButton mIbPop, mIbReturn;
    private LibAtyKind2Adapter mKind2Adapter;
    private LibAtyKindNextPopAdapter mNextPopAdapter;
    private LibAtyKindMenuPopAdapter mMenuPopAdapter;


    // 最上面的popup
    private MenuPopup mMenuPopup;
    private SortPopup sortPopup;
    private TextView mTvKindAll, mTvNutRank;//第二个是营养素排行
    private String mKind;
    private int mPos;
    private int mId;
    private int mRPage;
    int loadingPage ;

    @Override
    protected int setLayout() {
        return R.layout.activity_lib_kind2_aty;
    }

    @Override
    protected void initView() {
        mRv = bindView(R.id.rv_lib2_kind);
        mIbReturn = bindView(R.id.ib_lib2_kind_return);
        mIbPop = bindView(R.id.ib_lib2_kind_nutri_sort);
        mTvKindAll = bindView(R.id.tv_lib2_kind_all);
        mTvNutRank = bindView(R.id.tv_lib2_kind_nutri_sort);
        mIbReturn = bindView(R.id.ib_lib2_kind_return);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initData() {


        mTvKindAll.setVisibility(View.VISIBLE);

        // 返回
        mIbReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        /**
         * 最上面的popup
         */
        mMenuPopup = new MenuPopup(this);
        mTvKindAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMenuPopup.showPopupWindow(view);
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

        mKind2Adapter = new LibAtyKind2Adapter(this);

        StartUrl(getMid(mCount));
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
                StartUrl(getMid(mCount));
                mKind2Adapter.notifyDataSetChanged();
            }
        });
        // 上拉加载
        mRv.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mCount += 1;
                StartUrl(getMid(mCount));
            }
        });

    }

    public String getMid(int i) {
        Intent intent = getIntent();
        mKind = intent.getStringExtra("kind");
        mPos = intent.getIntExtra("pos", 0);
        mId = intent.getIntExtra("id", 0);
        String url = intent.getStringExtra("libUrl");
        String mid = url + i + NetValues.ATY_GV_LIB_SORT_TAIL;


        String refresh2Url = NetValues.LIB_SORT_POPGV_HEAD + mKind + NetValues.LIB_SORT_POPGV_NEXT
                + (mId) + NetValues.LIB_SORT_POPGV_MID + i + NetValues.LIB_SORT_POPGV_MORE + mRPage + NetValues.LIB_SORT_POPGV_TAIL;


        return refresh2Url;
    }

    private void StartUrl(String url) {
        NetHelper.MyRequest(url, LibAtyBean.class, this);
    }


    @Override
    public void successListener(LibAtyBean response) {
        List<LibAtyBean.FoodsBean> data = response.getFoods();


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

    class MenuPopup extends BasePopupWindow {

        private ListView mListView;
        private LibAtyTitlePopAdapter mAdapter;
        private List<String> datas;
        private int subValue;
        private LibFraBean mLibFraBean;

        public MenuPopup(Activity context) {
            super(context, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            Intent intent = LibKind3Aty.this.getIntent();
            final int pos = intent.getIntExtra("pos", 0);
            final int count = intent.getIntExtra("count", 0);

            mListView = (ListView) findViewById(R.id.lv_popup);

            mAdapter = new LibAtyTitlePopAdapter(context);

            //menupop的4个数据
            NetHelper.MyRequest(NetValues.LIB_GV, LibFraBean.class, new NetListener<LibFraBean>() {
                @Override
                public void successListener(LibFraBean response) {
                    mLibFraBean = new LibFraBean();
                    mLibFraBean = response;
                    datas = new ArrayList<String>();
                    datas.add("全部");
                    mTvKindAll.setText("全部");
                    for (int i = 0; i < count; i++) {
                        datas.add(response.getGroup().get(0).getCategories().get(pos).getSub_categories().get(i).getName());
                    }
                    mAdapter.setDatas(datas);

                    mListView.setAdapter(mAdapter);

                    // pop点击事件 刷新listview
                    mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            mMenuPopAdapter = new LibAtyKindMenuPopAdapter(LibKind3Aty.this);
                            if (i == 0) {
                                StartParse(getNextUrl(0));
                                mTvNutRank.setText("全部");
                            } else {
                                StartParse(getNextUrl(i));
                                mMenuPopAdapter.setClear();
                                mTvKindAll.setText(mLibFraBean.getGroup().get(0).getCategories().get(pos).getSub_categories().get(i - 1).getName());

                            }
                            lRecyclerViewAdapter = new LRecyclerViewAdapter(mMenuPopAdapter);
                            mRv.setAdapter(lRecyclerViewAdapter);
                            mMenuPopup.initExitAnimation();
                            mMenuPopup.dismiss();
                            // 下拉刷新
                            mRv.setOnRefreshListener(new OnRefreshListener() {
                                @Override
                                public void onRefresh() {
                                    mCount = 1;
                                    mMenuPopAdapter.setClear();
                                    StartParse(getNextUrl(mCount));
                                    mRv.refreshComplete();
                                    mMenuPopAdapter.notifyDataSetChanged();
                                }
                            });
                            // 上拉加载
                            mRv.setOnLoadMoreListener(new OnLoadMoreListener() {
                                @Override
                                public void onLoadMore() {
                                    new PutDataTask().execute();
//                                        mCount += 1;
//                                    StartParse(getNextUrl(++mCount));
                                    Log.d("MenuPopup", "loadingPage:" + loadingPage);
                                    StartParse(getNextUrl(loadingPage));

                                }
                            });
//
                        }
                    });

                }


                @NonNull
                private String getNextUrl(int i) {

                    String url = null;
                    Intent intent = getIntent();
                    mKind = intent.getStringExtra("kind");
                    mPos = intent.getIntExtra("pos", 0);
                    mId = intent.getIntExtra("id", 0);

                    if (i == 0) {
                        url = NetValues.LIB_SORT_POPGV_HEAD + mKind + NetValues.LIB_SORT_POPGV_NEXT
                                + (mId) + NetValues.LIB_SORT_POPGV_MID + mRPage + NetValues.LIB_SORT_POPGV_MORE + mRPage + NetValues.LIB_SORT_POPGV_TAIL;
                    } else {
                        subValue = mLibFraBean.getGroup().get(0).getCategories().get(pos).getSub_categories().get(i - 1).getId();

                            url = NetValues.LIB_SORT_POPGV_HEAD + mKind + NetValues.LIB_SORT_POPGV_NEXT
                                    + (mId) + NetValues.LIB_SORT_POPGV_SUBVALUE + subValue + NetValues.LIB_SORT_POPGV_MID + mRPage+ NetValues.LIB_SORT_POPGV_MORE + mRPage + NetValues.LIB_SORT_POPGV_TAIL;
                            Log.d("MenuPopup", url);


                    }
                    return url;
                }


                // 下拉刷新的异步任务
                 class PutDataTask extends AsyncTask<Integer, Void, Integer> {

                    @Override
                    protected Integer doInBackground(Integer... params) {
                        try {
                            Thread.sleep(1000);
                            loadingPage = 1;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return loadingPage;
                    }

                    @Override
                    protected void onPostExecute(Integer integer) {
                        super.onPostExecute(integer);
                        loadingPage += 1;

                        StartParse(getNextUrl(loadingPage));


                    }

                }

                public void StartParse(String url) {
                    NetHelper.MyRequest(url, LibAtyBean.class, new NetListener<LibAtyBean>() {
                        @Override
                        public void successListener(LibAtyBean response) {
//                            for (LibAtyBean.FoodsBean foodsBean : response.getFoods()) {
//                                Log.d("MenuPopup", foodsBean.getName());
//                            }
                            List<LibAtyBean.FoodsBean> data = response.getFoods();

                            menuDatas = data;

                            mMenuPopAdapter.setDatas(menuDatas);
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
            set.addAnimation(getScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 1, Animation.RELATIVE_TO_SELF, 0));
            set.addAnimation(getDefaultScaleAnimation());
            return set;
        }

        @Override
        protected Animation initExitAnimation() {
            AnimationSet set = new AnimationSet(true);
            set.setInterpolator(new DecelerateInterpolator());
            set.addAnimation(getScaleAnimation(1, 0, 1, 0, Animation.RELATIVE_TO_SELF, 1, Animation.RELATIVE_TO_SELF, 0));
            set.addAnimation(getDefaultScaleAnimation());
            return set;
        }

        @Override
        public void showPopupWindow(View v) {
            setRelativeToAnchorView(true);
//            setRelativePivot(RelativePivot.RIGHT);
            setRelativePivot(RelativePivot.BOTTOM);
            setOffsetY(v.getHeight() / 2);

            super.showPopupWindow(v);
        }

        @Override
        public View getClickToDismissView() {
            return null;
        }

        @Override
        public View onCreatePopupView() {
            return createPopupById(R.layout.menu_lib_aty_pop);
        }

        @Override
        public View initAnimaView() {
            return getPopupWindowView().findViewById(R.id.popup_contianer);
        }

    }


    class SortPopup extends BasePopupWindow {

        private GridView gvPop;
        private LibAtyPopAdapter popAdapter;
        private List<LibPopBean.TypesBean> sDatas;

        public SortPopup(Activity context) {
            super(context, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            Intent intent = LibKind3Aty.this.getIntent();
//            final int id =  intent.getIntExtra("id",0);
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
                    popAdapter = new LibAtyPopAdapter(LibKind3Aty.this);
                    popAdapter.setDatas(sDatas);
                    gvPop.setAdapter(popAdapter);
                    final LibPopBean finalPopBean = popBean;
                    gvPop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            mNextPopAdapter = new LibAtyKindNextPopAdapter(LibKind3Aty.this);

                            StartParse(getNextUrl(i));
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
                                    StartParse(getNextUrl(mCount));

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


                public void StartParse(String url) {
                    NetHelper.MyRequest(url, LibAtyBean.class, new NetListener<LibAtyBean>() {
                        @Override
                        public void successListener(LibAtyBean response) {
                            mKind2Adapter.setClear();
                            List<LibAtyBean.FoodsBean> data = response.getFoods();
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
