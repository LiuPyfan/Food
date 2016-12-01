package com.pf.dllo.food.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.pf.dllo.food.R;
import com.pf.dllo.food.adapter.HomeVpAdapter;
import com.pf.dllo.food.bean.TabBean;
import com.pf.dllo.food.fragment.home.HomeDeliFragment;
import com.pf.dllo.food.fragment.home.HomeEvaFragment;
import com.pf.dllo.food.fragment.home.HomeHomeFragment;

import java.util.ArrayList;
import java.util.List;


import com.pf.dllo.food.fragment.home.HomeKnowFragment;
import com.pf.dllo.food.tools.ViewFindUtils;
import com.pf.dllo.food.values.NetValues;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {

    private List<Fragment> mFragments;
    private ViewPager mViewPager;


    private CommonTabLayout mTabLayout;
    private String[] titles = new String[]{"首页", "评测", "知识", "美食"};
    private ArrayList<CustomTabEntity> mTabBean = new ArrayList<>();
    private View mDecorView;
    @Override
    protected int setLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
//        mViewPager = (ViewPager) bindView(R.id.vp_home);
//        mTabLayout = (TabLayout) bindView(R.id.tl_home);
//        mTabLayout = bindView(R.id.tl_home);


    }

    @Override
    protected void initData() {

        mFragments = new ArrayList<>();
//        for (String title:titles) {

            mFragments.add( new HomeHomeFragment());
            mFragments.add(new HomeEvaFragment());
            mFragments.add(new HomeKnowFragment());
            mFragments.add(new HomeDeliFragment());

//        }
        for (int i = 0; i < titles.length; i++) {
//            mTabBean.add(new TabBean(titles[i], mIconSelectIds[i], mIconUnselectIds[i]));
            mTabBean.add(new TabBean(titles[i]));
        }

        mDecorView = getActivity().getWindow().getDecorView();

        mViewPager = ViewFindUtils.find(mDecorView,R.id.vp_home);
        HomeVpAdapter adapter = new HomeVpAdapter(getChildFragmentManager(),mFragments);
        adapter.setFragments(mFragments);
        mViewPager.setAdapter(adapter);

        mTabLayout = ViewFindUtils.find(mDecorView,R.id.tl_home);

        mTabLayout.setIndicatorAnimEnable(true);
        mTabLayout.setTabData(mTabBean);

        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mTabLayout.setCurrentTab(position);
                mViewPager.setCurrentItem(position);
            }
            @Override
            public void onTabReselect(int position) {

            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setCurrentItem(0);


    }

}
