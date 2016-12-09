package com.pf.dllo.food.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dllo on 16/11/22.
 */
public abstract class BaseFragment extends Fragment {
    public Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(setLayout(),container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    protected abstract int setLayout();

    protected abstract void initView(View view);

    protected abstract void initData();

    public <T extends View> T bindView(int id){
        return (T)getView().findViewById(id);
    }

    protected void setClick(View.OnClickListener onClickListener,View ... views){
        for (View view :views) {
            view.setOnClickListener(onClickListener);
        }
    }
}
