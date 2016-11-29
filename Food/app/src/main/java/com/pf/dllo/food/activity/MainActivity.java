package com.pf.dllo.food.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import com.pf.dllo.food.R;
import com.pf.dllo.food.fragment.HomeFragment;
import com.pf.dllo.food.fragment.LibraryFragment;
import com.pf.dllo.food.fragment.MyFragment;

public class MainActivity extends BaseActivity {

    private RadioGroup mRgMain;

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        mRgMain = bindView(R.id.rg_main);

    }

    @Override
    protected void initData() {
        mRgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                FragmentManager mManager = getSupportFragmentManager();
                FragmentTransaction mTransaction = mManager.beginTransaction();
                switch (id){
                    case R.id.rb_library:
                        mTransaction.replace(R.id.fl_main,new LibraryFragment());
                        break;
                    case R.id.rb_homepage:
                        mTransaction.replace(R.id.fl_main, new HomeFragment());
                        break;
                    case R.id.rb_my:
                        mTransaction.replace(R.id.fl_main, new MyFragment());
                        break;
                }
                mTransaction.commit();
            }
        });
        mRgMain.check(R.id.rb_library);
    }

}
