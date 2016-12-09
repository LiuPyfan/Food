package com.pf.dllo.food.activity.lib;


import android.app.Activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
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
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.pf.dllo.food.R;
import com.pf.dllo.food.activity.BaseActivity;
import com.pf.dllo.food.adapter.lib.LibAtyKindAdapter;
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

public class LibKindAty extends BaseActivity {


    private List<LibAtyBean.FoodsBean> datas;
    private List<LibPopBean.TypesBean> pDatas;

    private ImageButton mPopIb,mIbReturn;


    /**
     * 上拉刷新的控件
     */
    private PullToRefreshListView mPullRefreshListView;
    private int i = 1;
    private LibAtyKindAdapter mAdapter,refresh1Adapter,refresh2Adapter;
    private PopupWindow mPopupWindow;

    // 最上面的popup
    private MenuPopup mMenuPopup;
    private TextView mTvKindAll , mTvNutRank;//第二个是营养素排行
    private String mKind;
    private int mPos;
    private int mId;
    private int mRPage;

    @Override
    protected int setLayout() {
        return R.layout.activity_lib_kind_aty;
    }

    @Override
    protected void initView() {


        mPullRefreshListView = (PullToRefreshListView) bindView(R.id.lv_lib_kind_aty);
        mPullRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);//

        mPopIb = bindView(R.id.ib_lib_kind_nutri_sort);
        mTvKindAll = bindView(R.id.tv_lib_kind_all);
        mTvNutRank = bindView(R.id.tv_lib_kind_nutri_sort);
        mIbReturn = bindView(R.id.ib_lib_kind_return);
    }

    @Override
    protected void initData() {

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
         * 第二个popup
         */
        initPop();
        mAdapter = new LibAtyKindAdapter(this);
        StartUrl(getMid(1));
        mPullRefreshListView.setAdapter(mAdapter);
        mPullRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(
                    PullToRefreshBase<ListView> refreshView) {
                //这里写下拉刷新的任务
                new PutDataTask().execute();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                //这里写上拉加载更多的任务
                new GetDataTask().execute();
            }
        });
    }

    // 第二个 pop
    private void initPop() {

        mPopupWindow = new PopupWindow(this);
        mPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        mPopupWindow.setHeight((int) getResources().getDimension(R.dimen.library_aty_pop_height));
        View popView = getLayoutInflater().inflate(R.layout.pop_lib_aty, null);
        final GridView gvPop = (GridView) popView.findViewById(R.id.gv_lib_aty_pop);
        // pop里gv的网络解析
        NetHelper.MyRequest(NetValues.LIB_SORT, LibPopBean.class, new NetListener<LibPopBean>() {
            @Override
            public void successListener(LibPopBean response) {

                pDatas = response.getTypes();
                LibAtyPopAdapter popAdapter = new LibAtyPopAdapter(LibKindAty.this);
                popAdapter.setDatas(pDatas);
                gvPop.setAdapter(popAdapter);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
        // pop里gv的行布局点击事件
        gvPop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // 点击变字
                mTvNutRank.setText(pDatas.get(i).getName());
                // pop字变红
                if (mTvNutRank.getText().toString() == pDatas.get(i).getName()){
//                    LibAtyPopAdapter.LibVH VH = new LibAtyPopAdapter.LibVH(adapterView);
//                    VH.getTvNut().setTextColor(Color.RED);
                }
                //获取type里的index 拼接主界面listView的根据pop里gv的刷新网址
                String order = pDatas.get(i).getIndex() + "";
                // 1的接口
                String refresh1Url = NetValues.LIB_SORT_POPGV_HEAD + mKind + NetValues.LIB_SORT_POPGV_NEXT
                        + (mPos + 1) + NetValues.LIB_SORT_POPGV_MID + order +NetValues.LIB_SORT_POPGV_MORE+ mRPage+ NetValues.LIB_SORT_POPGV_TAIL;
                // 2.3的接口
                String refresh2Url = NetValues.LIB_SORT_POPGV_HEAD + mKind + NetValues.LIB_SORT_POPGV_NEXT
                        + (mId) + NetValues.LIB_SORT_POPGV_MID + order + NetValues.LIB_SORT_POPGV_MORE+ mRPage+ NetValues.LIB_SORT_POPGV_TAIL;
//                        + (mPos) + NetValues.LIB_SORT_POPGV_MID + order + NetValues.LIB_SORT_POPGV_MORE+ getPage(i)+ NetValues.LIB_SORT_POPGV_TAIL;


                if (mKind == "group") {
                    NetHelper.MyRequest(refresh1Url, LibAtyBean.class, new NetListener<LibAtyBean>() {
                        @Override
                        public void successListener(LibAtyBean response) {
                            mAdapter.clean();
                            refresh1Adapter = new LibAtyKindAdapter(LibKindAty.this);
                            List<LibAtyBean.FoodsBean> data = response.getFoods();
                            if (datas == null) {
                                datas = data;
                            } else {
                                for (int j = 0; j < data.size(); j++) {
                                    datas.add(data.get(j));
                                }
                            }
                            refresh1Adapter.setDatas(datas);
                            mPullRefreshListView.setAdapter(refresh1Adapter);
                            refresh1Adapter.notifyDataSetChanged();
                            mPullRefreshListView.getRefreshableView();
                            mPullRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                }
                            });
                        }


                        @Override
                        public void errorListener(VolleyError error) {

                        }
                    });

                }else {

                NetHelper.MyRequest(refresh2Url, LibAtyBean.class, new NetListener<LibAtyBean>() {
                    @Override
                    public void successListener(LibAtyBean response) {
                        mAdapter.clean();
                        refresh2Adapter = new LibAtyKindAdapter(LibKindAty.this);
                        List<LibAtyBean.FoodsBean> data = response.getFoods();
                        if (datas == null) {
                            datas = data;
                        } else {
                            for (int j = 0; j < data.size(); j++) {
                                datas.add(data.get(j));
                            }
                        }
                        refresh2Adapter.setDatas(datas);
                        mPullRefreshListView.setAdapter(refresh2Adapter);
                        refresh2Adapter.notifyDataSetChanged();
                        mPullRefreshListView.getRefreshableView();
                        mPullRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            }
                        });
                    }


                    @Override
                    public void errorListener(VolleyError error) {

                    }
                });
                }

            }
        });

        // 点击箭头弹出pop
        mPopIb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.setAnimationStyle(R.style.pop_anim);
                mPopupWindow.showAtLocation(LibKindAty.this.findViewById(R.id.lib_aty_pop_divider), Gravity.NO_GRAVITY, 0, (int) getResources().getDimension(R.dimen.pop_lib_location));
            }
        });

        mPopupWindow.setContentView(popView);
        mPopupWindow.setOutsideTouchable(true);
        //在PopupWindow里面就加上下面代码，让键盘弹出时，不会挡住pop窗口。
        mPopupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        //点击空白处时，隐藏掉pop窗口
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        backgroundAlpha(1f);

        //添加pop窗口关闭事件
        mPopupWindow.setOnDismissListener(new poponDismissListener());

    }

    // 第二个pop

    /**
     * 添加新笔记时弹出的popWin关闭的事件，主要是为了将背景透明度改回来
     *
     * @author cg
     */
    class poponDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {

            backgroundAlpha(1f);
        }

    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }

    public String getMid(int i) {
        Intent intent = getIntent();
        mKind = intent.getStringExtra("kind");
        mPos = intent.getIntExtra("pos", 0);
        mId = intent.getIntExtra("id",0);
        String url = intent.getStringExtra("libUrl");
        String mid = url + i + NetValues.ATY_GV_LIB_SORT_TAIL;
        return mid;
    }

    public int getPage(int page){
         page = i ;
        mRPage = page;
        return mRPage;
    }

    private void StartUrl(String url) {

        NetHelper.MyRequest(getMid(i), LibAtyBean.class, new NetListener<LibAtyBean>() {
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
                mAdapter.setDatas(datas);

                mPullRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    }
                });


            }


            @Override
            public void errorListener(VolleyError error) {

            }
        });

    }

    // 上拉加载的异步任务
    private class GetDataTask extends AsyncTask<Integer, Void, Integer> {

        @Override
        protected Integer doInBackground(Integer... params) {
            try {
                Thread.sleep(2000);
                i += 1;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return i;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            StartUrl(getMid(integer));

            mAdapter.notifyDataSetChanged();
            // Call onRefreshComplete when the list has been refreshed.
            mPullRefreshListView.onRefreshComplete();
        }

    }


    // 下拉刷新的异步任务
    private class PutDataTask extends AsyncTask<Integer, Void, Integer> {

        @Override
        protected Integer doInBackground(Integer... params) {
            try {
                Thread.sleep(2000);
                i = 1;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return i;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
        i += 1;
            mAdapter.clean();
            StartUrl(getMid(integer));

            mAdapter.notifyDataSetChanged();
            // Call onRefreshComplete when the list has been refreshed.
            mPullRefreshListView.onRefreshComplete();
        }

    }

    class MenuPopup extends BasePopupWindow {

        private ListView mListView;
        private LibAtyTitlePopAdapter mAdapter;
        private List<String> datas;

        public MenuPopup(Activity context) {
            super(context, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            Intent intent = LibKindAty.this.getIntent();
//            final int id =  intent.getIntExtra("id",0);
            final int pos = intent.getIntExtra("pos", 0);
            final int count = intent.getIntExtra("count", 0);

            mListView = (ListView) findViewById(R.id.lv_popup);

            mAdapter = new LibAtyTitlePopAdapter(context);
            NetHelper.MyRequest(NetValues.LIB_GV, LibFraBean.class, new NetListener<LibFraBean>() {
                @Override
                public void successListener(LibFraBean response) {
                    datas = new ArrayList<String>();
                    datas.add("全部");
                    for (int i = 0; i < count; i++) {
                        datas.add(response.getGroup().get(0).getCategories().get(pos).getSub_categories().get(i).getName());
                    }
                    mAdapter.setDatas(datas);
                    mListView.setAdapter(mAdapter);

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
//            setOffsetY(v.getHeight() / 2);
            setOffsetY(v.getHeight());
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

}
