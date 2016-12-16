package com.pf.dllo.food.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.pf.dllo.food.R;

public class WelcomeActivity extends BaseActivity{

    private ImageView ivWel;

    public static final int BASE_TIME = 1000;

    private CountTask countTask;

    @Override
    protected int setLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        ivWel = bindView(R.id.iv_welcome);
    }

    @Override
    protected void initData() {

        countTask =new CountTask();
        countTask.execute(0);
        ivWel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                countTask.cancel(true);
                finish();
                if (isFinishing()) {
                    finish();
                }
            }
        });
    }

    // 字
    class CountTask extends AsyncTask<Integer,Integer,String> {

        int time = 3;

        @Override
        protected String doInBackground(Integer... integers) {
            int all = integers[0];
            while (time > all) {
                publishProgress(time);
                time--;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "";
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            finish();
            if (isFinishing()) {
                finish();
            }
        }
    }
}
