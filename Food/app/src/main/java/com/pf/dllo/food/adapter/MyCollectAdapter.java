package com.pf.dllo.food.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by dllo on 2016/12/13.
 */

public class MyCollectAdapter extends FragmentPagerAdapter {
    private List<Fragment> frumChFragments;
    private String[] frumTitles = new String[]{"文章","食物"};
    public MyCollectAdapter(FragmentManager fm, List<Fragment>frumChFragments) {
        super(fm);
        this.frumChFragments = frumChFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return frumChFragments.get(position);
    }

    @Override
    public int getCount() {
        return frumChFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return frumTitles[position];
    }
}
