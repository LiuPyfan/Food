package com.pf.dllo.food.activity.lib;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.pf.dllo.food.R;
import com.pf.dllo.food.activity.BaseActivity;
import com.pf.dllo.food.adapter.lib.LibSearchRvAdapter;
import com.pf.dllo.food.bean.lib.LibSearchBean;
import com.pf.dllo.food.tools.ClearEditText;
import com.pf.dllo.food.tools.ItemDecoration.DividerItemDecoration;
import com.pf.dllo.food.tools.OnSearchRvClick;
import com.pf.dllo.food.tools.net.NetHelper;
import com.pf.dllo.food.tools.net.NetListener;
import com.pf.dllo.food.values.NetValues;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class LibSearchAty extends BaseActivity implements View.OnClickListener {
    private ClearEditText mClearEditText;
    private Toast mToast;
    private ImageButton mSearchBtn, mBackBtn;
    private RecyclerView mRecyclerView;
    private List<String> datas;
    private TextView tvTrash;// 垃圾

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

        mRecyclerView = bindView(R.id.rv_library_search);

    }


    @Override
    protected void initData() {

        ParseUrl();
        setClick(this, mBackBtn, mSearchBtn); // 看onClick接口

    }

    private void ParseUrl() {
        NetHelper.MyRequest(NetValues.LIB_SEARCH_RV, LibSearchBean.class, new NetListener<LibSearchBean>() {
            @Override
            public void successListener(LibSearchBean response) {
                datas = response.getKeywords();
                LibSearchRvAdapter adapter = new LibSearchRvAdapter(LibSearchAty.this);
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
                    showToast("用户名不能为空");
                } else {
                    String name = mClearEditText.getText().toString();
                    try {
                        String newKey = new String(name.getBytes("gbk"), "utf-8");

                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(LibSearchAty.this, LibSearchDetailAty.class);
                    intent.putExtra("name", name);
                    startActivity(intent);

                }
                break;

        }

    }


}
