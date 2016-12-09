package com.pf.dllo.food.activity.lib;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.pf.dllo.food.R;
import com.pf.dllo.food.activity.BaseActivity;
import com.pf.dllo.food.adapter.lib.LibAtyKind2Adapter;
import com.pf.dllo.food.adapter.lib.LibAtyKindAdapter;
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

public class LibKind2Aty extends BaseActivity implements NetListener<LibAtyBean>, View.OnClickListener {
    private LRecyclerView mRv;
    private LRecyclerViewAdapter lRecyclerViewAdapter;

    private int mCount = 1;

    private List<LibAtyBean.FoodsBean> datas, menuDatas;// rv

    private ImageButton mIbPop, mIbReturn;
    private LibAtyKind2Adapter mKind2Adapter;
    private LibAtyKindNextPopAdapter mNextPopAdapter;
//    private LibAtyKindMenuPopAdapter mMenuPopAdapter;


    // 最上面的popup
    private List<String> pDatas;
    private LibFraBean mLibFraBean;
    private LibAtyTitlePopAdapter mAdapter;
    /****************/
//    private MenuPopup mMenuPopup;
    private PopupWindow popupWindow;
    private SortPopup sortPopup;
    private TextView mTvKindAll, mTvNutRank;//第二个是营养素排行
    private String mKind;
    private int mPos;
    private int mId;
    private int mRPage;

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
        mTvKindAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopup();
                popupWindow.showAsDropDown(findViewById(R.id.kind_all_divider), 0, 0, Gravity.RIGHT);
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

    private void initPopup() {
        popupWindow = new PopupWindow(this);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        Intent intent = LibKind2Aty.this.getIntent();
        final int pos = intent.getIntExtra("pos", 0);
        final int count = intent.getIntExtra("count", 0);
        View view = getLayoutInflater().inflate(R.layout.menu_lib_aty_pop, null);
        final ListView mListView = (ListView) view.findViewById(R.id.lv_popup);
        mAdapter = new LibAtyTitlePopAdapter(LibKind2Aty.this);
        NetHelper.MyRequest(NetValues.LIB_GV, LibFraBean.class, new NetListener<LibFraBean>() {
            @Override
            public void successListener(LibFraBean response) {
                mLibFraBean = new LibFraBean();
                mLibFraBean = response;
                pDatas = new ArrayList<String>();
                pDatas.add("全部");
//                mTvKindAll.setText("全部");
                for (int i = 0; i < count; i++) {
                    pDatas.add(response.getGroup().get(0).getCategories().get(pos).getSub_categories().get(i).getName());
                }

            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
        mAdapter.setDatas(pDatas);
        mListView.setAdapter(mAdapter);

        mListView.setItemsCanFocus(false);
        mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        popOnClick(pos, mListView);


        popupWindow.setContentView(view);
        // 点击外面让popup消失
        popupWindow.setOutsideTouchable(true);

        //在PopupWindow里面就加上下面代码，让键盘弹出时，不会挡住pop窗口。
        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        //点击空白处时，隐藏掉pop窗口
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        backgroundAlpha(1f);

        //添加pop窗口关闭事件
        popupWindow.setOnDismissListener(new poponDismissListener());

    }

    private void popOnClick(final int pos, ListView mListView) {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                mKind2Adapter = new LibAtyKind2Adapter(LibKind2Aty.this);
                if (position == 0) {
                    StartUrl(getMid(0));
                    mTvNutRank.setText("全部");
                } else {
                    mKind2Adapter.setClear();
                    StartParse(getMenuUrl(position,mCount));
//                    Log.d("LibKind2Aty", getMenuUrl(position, mCount) + "****" + position + "****" + mCount);
                    mTvKindAll.setText(mLibFraBean.getGroup().get(0).getCategories().get(pos).getSub_categories().get(position-1).getName());

                }
                lRecyclerViewAdapter = new LRecyclerViewAdapter(mKind2Adapter);
                mRv.setAdapter(lRecyclerViewAdapter);
                // 下拉刷新
                mRv.setOnRefreshListener(new OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        mCount = 1;
                        mKind2Adapter.setClear();
                        StartUrl(getMid(mCount));
                        mRv.refreshComplete();
                        mKind2Adapter.notifyDataSetChanged();
                    }
                });
                // 上拉加载
                mRv.setOnLoadMoreListener(new OnLoadMoreListener() {
                    @Override
                    public void onLoadMore() {
//                        StartUrl(getNextUrl(++mCount));
                        mCount += 1;
                        StartUrl(getMid(mCount));
                    }
                });
            }
        });
    }

    class poponDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            backgroundAlpha(1f);
        }

    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }


    public String getMid(int i) {
        Intent intent = getIntent();
        mKind = intent.getStringExtra("kind");
        mPos = intent.getIntExtra("pos", 0);
        mId = intent.getIntExtra("id", 0);
//        String url = intent.getStringExtra("libUrl");
//        String mid = url + i + NetValues.ATY_GV_LIB_SORT_TAIL;
        String refresh2Url = NetValues.LIB_SORT_POPGV_HEAD + mKind + NetValues.LIB_SORT_POPGV_NEXT
                + (mId) + NetValues.LIB_SORT_POPGV_MID + i + NetValues.LIB_SORT_POPGV_MORE + mRPage
                + NetValues.LIB_SORT_POPGV_TAIL;


        return refresh2Url;
    }

    private void StartUrl(String url) {
        NetHelper.MyRequest(url, LibAtyBean.class, this);
    }

    private void StartParse(String url) {
        NetHelper.MyRequest(url, LibAtyBean.class, new NetListener<LibAtyBean>() {
            @Override
            public void successListener(LibAtyBean response) {
                List<LibAtyBean.FoodsBean> data = response.getFoods();


//                if (datas == null) {
                    datas = data;
//                } else {
//                    for (int j = 0; j < data.size(); j++) {
//                        datas.add(data.get(j));
//                    }
//                }
                mKind2Adapter.setDatas(datas);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }

    @NonNull
    private String getNextUrl(int i) {

        String url = null;
        Intent intent = getIntent();
        int pos = intent.getIntExtra("pos", 0);
        int subValue;
        mKind = intent.getStringExtra("kind");
        mPos = intent.getIntExtra("pos", 0);
        mId = intent.getIntExtra("id", 0);
        subValue = mLibFraBean.getGroup().get(0).getCategories().get(pos).getSub_categories().get(i).getId();
        url = NetValues.LIB_SORT_POPGV_HEAD + mKind + NetValues.LIB_SORT_POPGV_NEXT + (mId)
                + NetValues.LIB_SORT_POPGV_SUBVALUE + subValue + NetValues.LIB_SORT_POPGV_MID + mRPage
                + NetValues.LIB_SORT_POPGV_MORE + mCount + NetValues.LIB_SORT_POPGV_TAIL;
        return url;
    }

    @NonNull
    private String getMenuUrl(int sub,int page) {

        String url = null;
        Intent intent = getIntent();
        int pos = intent.getIntExtra("pos", 0);
        int subValue;
        mKind = intent.getStringExtra("kind");
        mPos = intent.getIntExtra("pos", 0);
        mId = intent.getIntExtra("id", 0);
//        if (i == 0) {
//            url = NetValues.LIB_SORT_POPGV_HEAD + mKind + NetValues.LIB_SORT_POPGV_NEXT
//                    + (mId) + NetValues.LIB_SORT_POPGV_MID + mRPage + NetValues.LIB_SORT_POPGV_MORE + mRPage + NetValues.LIB_SORT_POPGV_TAIL;
//        } else {
        subValue = mLibFraBean.getGroup().get(0).getCategories().get(pos).getSub_categories().get(sub -1).getId();


        url = NetValues.LIB_MENU_POP_HEAD + mKind + NetValues.LIB_MENU_POP_NEXT + (mId)
                + NetValues.LIB_MENU_POP_SUBVALUE  + subValue + NetValues.LIB_MENU_POP_MID
                + NetValues.LIB_MENU_POP_MORE + page+ NetValues.LIB_MENU_POP_TAIL;

        return url;
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

    @Override
    public void onClick(View v) {

    }

    class SortPopup extends BasePopupWindow {

        private GridView gvPop;
        private LibAtyPopAdapter popAdapter;
        private List<LibPopBean.TypesBean> sDatas;

        public SortPopup(Activity context) {
            super(context, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            Intent intent = LibKind2Aty.this.getIntent();
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
                    popAdapter = new LibAtyPopAdapter(LibKind2Aty.this);
                    popAdapter.setDatas(sDatas);
                    gvPop.setAdapter(popAdapter);
                    final LibPopBean finalPopBean = popBean;
                    gvPop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            mNextPopAdapter = new LibAtyKindNextPopAdapter(LibKind2Aty.this);

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
