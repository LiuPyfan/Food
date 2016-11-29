package com.pf.dllo.food.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by dllo on 16/11/23.
 */
public class HomeVpAdapter extends FragmentPagerAdapter{

    private String[] titles = new String[]{"首页", "评测", "知识", "美食"};
    private List<Fragment>mFragments;


    public HomeVpAdapter(FragmentManager fm,List<Fragment>fragments) {
        super(fm);
        this.mFragments = fragments;
    }

    public void setFragments(List<Fragment> fragments) {
        this.mFragments = fragments;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments == null ? null:mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments == null ? 0 :mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
