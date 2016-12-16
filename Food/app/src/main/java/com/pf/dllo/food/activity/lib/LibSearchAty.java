package com.pf.dllo.food.activity.lib;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.pf.dllo.food.R;
import com.pf.dllo.food.activity.BaseActivity;
import com.pf.dllo.food.adapter.lib.LibAtySearchLvAdapter;
import com.pf.dllo.food.adapter.lib.LibSearchRvAdapter;
import com.pf.dllo.food.bean.lib.LibSearchBean;
import com.pf.dllo.food.db.SearchBean;
import com.pf.dllo.food.db.DBTool;
import com.pf.dllo.food.db.SearchBeanDao;
import com.pf.dllo.food.tools.ClearEditText;
import com.pf.dllo.food.tools.ItemDecoration.DividerItemDecoration;
import com.pf.dllo.food.tools.MyListView;
import com.pf.dllo.food.tools.OnSearchRvClick;
import com.pf.dllo.food.tools.net.NetHelper;
import com.pf.dllo.food.tools.net.NetListener;
import com.pf.dllo.food.values.NetValues;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * 搜索页
 */


public class LibSearchAty extends BaseActivity implements View.OnClickListener {
    private ClearEditText mClearEditText;
    private Toast mToast;
    private ImageButton mSearchBtn, mBackBtn;
    private RecyclerView mRecyclerView;
    private List<String> datas;
    // 上面的listview
    private MyListView mListView;
    private TextView tvTrash;// 垃圾
    private SearchBean bean;
    private Boolean hasSave;
    private SearchBeanDao beanDao;
    private List<SearchBean> list;// 搜索过的数据
    private List<String> foodName;//  具体食物名
    private LibAtySearchLvAdapter mLvAdapter; // lv的适配器
    private View headView,footView;
    private SearchBR mSearchBR;

    @Override
    protected int setLayout() {
        return R.layout.activity_lib_search_aty;
    }

    @Override
    protected void initView() {
        mClearEditText = (ClearEditText) findViewById(R.id.et_lib_search_aty);
        mSearchBtn = (ImageButton) findViewById(R.id.iv_lib_search_sea_aty);
        mBackBtn = (ImageButton) findViewById(R.id.btn_lib_search_aty_back);
        tvTrash = bindView(R.id.tv_lib_search_trash);
        mListView = bindView(R.id.lv_lib_search_history_aty);
        mRecyclerView = bindView(R.id.rv_library_search);

    }


    @Override
    protected void initData() {

        mLvAdapter = new LibAtySearchLvAdapter(this);
        ParseUrl();
        setClick(this, mBackBtn, mSearchBtn); // 看onClick接口

        // 将数据库展示出来
        showListView();

        setHeadNFoot();

//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(LibSearchAty.this, LibSearchDetailAty.class);
//
//                    intent.putExtra("name",list.get(position).getName());
//
//
//                startActivity(intent);
//            }
//        });

        mSearchBR = new SearchBR();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.pf.dllo.food.activity.lib.SEARCH_BR");
        registerReceiver(mSearchBR,intentFilter);

    }

    private void setHeadNFoot() {
        if (list.size() > 0 ){
            headView = LayoutInflater.from(LibSearchAty.this).inflate(R.layout.item_head_lib_search_aty,null);
            mListView.addHeaderView(headView);

            footView = LayoutInflater.from(this).inflate(R.layout.item_foot_lib_search_aty,null);
            LinearLayout linearLayout = (LinearLayout) footView.findViewById(R.id.ll_lib_search);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mLvAdapter.setClear();
                    DBTool.getInstance().deleteAllName();
                    mLvAdapter.setData(foodName);
                    mListView.removeHeaderView(headView);
                    mListView.removeFooterView(footView);
                }
            });
            mListView.addFooterView(footView);
        }else {
            mListView.removeHeaderView(headView);
            mListView.removeFooterView(footView);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mSearchBR);
    }

    private void showListView() {
        list = DBTool.getInstance().querySearchAll();
        foodName = new ArrayList<>();
        if (list != null) {

            for (int i = list.size(); i > 0; i--) {
                foodName.add(list.get(i - 1).getName());

            }
        }

        mLvAdapter.setClear();
        mLvAdapter.setData(foodName);
        mListView.setAdapter(mLvAdapter);
    }

    private void ParseUrl() {
        NetHelper.MyRequest(NetValues.LIB_SEARCH_RV, LibSearchBean.class, new NetListener<LibSearchBean>() {
            @Override
            public void successListener(LibSearchBean response) {
                datas = response.getKeywords();
                final LibSearchRvAdapter adapter = new LibSearchRvAdapter(LibSearchAty.this);
                adapter.setDatas(datas);
                mRecyclerView.setAdapter(adapter);
                GridLayoutManager manager = new GridLayoutManager(LibSearchAty.this, 2, GridLayoutManager.VERTICAL, false);
                mRecyclerView.setLayoutManager(manager);
                mRecyclerView.addItemDecoration(new DividerItemDecoration(LibSearchAty.this, 1));
                adapter.setOnSearchRvClick(new OnSearchRvClick() {
                    @Override
                    public void onRvClick(int pos, String name) {
                        Intent intent = new Intent(LibSearchAty.this, LibSearchDetailAty.class);
                        intent.putExtra("name", name);
                        intent.putExtra("pos", pos);
                        startActivity(intent);
                        bean = new SearchBean(null, name);
                        bean.setName(name);
                       DBTool.getInstance().insertSearchBean(bean);

                    }
                });
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }

    /**
     * 显示Toast消息
     *
     * @param msg
     */
    private void showToast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_lib_search_aty_back:
                finish();
                break;
            case R.id.iv_lib_search_sea_aty:

                mClearEditText.setShakeAnimation();
                if (TextUtils.isEmpty(mClearEditText.getText())) {
                    showToast("搜索不能为空");
                } else {
                    String name = mClearEditText.getText().toString();
                    if (!DBTool.getInstance().hasSave(name)) {
                        bean = new SearchBean(null, name);
                        DBTool.getInstance().insertSearchBean(bean);

                    } else {
                        bean = new SearchBean(null, name);
                        DBTool.getInstance().deleteByName(name);
                        DBTool.getInstance().insertSearchBean(bean);
                    }
                    Intent intent = new Intent(LibSearchAty.this, LibSearchDetailAty.class);
                    intent.putExtra("name", name);

                    startActivity(intent);

                }
                break;

        }

    }

    class SearchBR extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String result = intent.getStringExtra("result");
            if (result.equals("result")) {
                list = DBTool.getInstance().querySearchAll();
                foodName = new ArrayList<>();
                if (list != null) {

                    for (int i = list.size(); i > 0; i--) {
                        foodName.add(list.get(i - 1).getName());

                    }
                }
                mLvAdapter.setClear();
                Log.d("LibSearchAty", "foodName:" + foodName);
                mLvAdapter.setData(foodName);
                mListView.setAdapter(mLvAdapter);
//                Log.d("SearchBR", "foodName:" + foodName);
            }
        }
    }

}
