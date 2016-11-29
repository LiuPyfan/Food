package com.pf.dllo.food.tools;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pf.dllo.food.R;

/**
 * Created by dllo on 16/11/23.
 */
public class MyItemView extends RelativeLayout{
    private static final String NAMESPACE = "http://schemas.android.com/apk/res/com.pf.dllo.food.tools";
    private static final String tag = "MyItemView";
    private String myPhoto;
    private String myCollect;
    private String myUpLoad;
    private String myOrder;
    private TextView mTvMy;

    public MyItemView(Context context) {
        this(context,null);
    }

    public MyItemView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = View.inflate(context, R.layout.item_my_item,this);
        this.addView(view);
        mTvMy = (TextView) view.findViewById(R.id.tv_item_my_photo);
        ImageView ivMyPhoto = (ImageView) mTvMy.findViewById(R.id.iv_item_my_photo);
        ImageView ivMyArrow = (ImageView) ivMyPhoto.findViewById(R.id.iv_item_my_arrow);
        initAttrs(attrs);
    }

    private void initAttrs(AttributeSet attrs) {
        myPhoto =attrs.getAttributeValue(NAMESPACE, "我的照片");
        myCollect =attrs.getAttributeValue(NAMESPACE, "我的收藏" );
        myUpLoad =attrs.getAttributeValue(NAMESPACE, "上传食物数据");
        myOrder =attrs.getAttributeValue(NAMESPACE,"我的订单");
        mTvMy.setText(myPhoto);

    }
}
