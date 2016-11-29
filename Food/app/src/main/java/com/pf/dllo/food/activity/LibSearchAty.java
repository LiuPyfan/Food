package com.pf.dllo.food.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.pf.dllo.food.R;
import com.pf.dllo.food.tools.ClearEditText;

import java.io.UnsupportedEncodingException;

public class LibSearchAty extends BaseActivity implements View.OnClickListener{
    private ClearEditText mClearEditText;
    private Toast mToast;
    private ImageButton mSearchBtn,mBackBtn;

    @Override
    protected int setLayout() {
        return R.layout.activity_lib_search_aty;
    }

    @Override
    protected void initView() {
        mClearEditText = (ClearEditText) findViewById(R.id.et_lib_search_aty);
        mSearchBtn = (ImageButton) findViewById(R.id.iv_lib_search_sea_aty);
        mBackBtn = (ImageButton) findViewById(R.id.btn_lib_search_aty_back);
        setClick(this,mBackBtn,mSearchBtn);

    }



    @Override
    protected void initData() {

    }
    /**
     * 显示Toast消息
     * @param msg
     */
    private void showToast(String msg){
        if(mToast == null){
            mToast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        }else{
            mToast.setText(msg);
        }
        mToast.show();
    }

    @Override
    public void onClick(View view) {


        switch (view.getId()){
            case R.id.btn_lib_search_aty_back:
                finish();
                break;
            case R.id.iv_lib_search_sea_aty:

                if(TextUtils.isEmpty(mClearEditText.getText())){
                    mClearEditText.setShakeAnimation();
                    showToast("用户名不能为空");
                }else {
                    String key = mClearEditText.getText().toString();
                    try {
                        String newKey = new String(key.getBytes("gbk"),"utf-8");
                        Log.d("LibSearchAty", newKey);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                }
                break;
        }

    }


}
