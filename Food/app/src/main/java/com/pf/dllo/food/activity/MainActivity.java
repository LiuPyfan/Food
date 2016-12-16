package com.pf.dllo.food.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.pf.dllo.food.R;
import com.pf.dllo.food.fragment.HomeFragment;
import com.pf.dllo.food.fragment.LibraryFragment;
import com.pf.dllo.food.fragment.MyFragment;

public class MainActivity extends BaseActivity {

    private RadioGroup mRgMain;

    private long exitTime;// 退出初始值

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


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {

            exit();
            return false;

        }
        return super.onKeyDown(keyCode, event);
    }


    private void exit() {

        if (System.currentTimeMillis() - exitTime > 2000) {
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        }
        else {
            finish();
            System.exit(0);
        }
    }
}
