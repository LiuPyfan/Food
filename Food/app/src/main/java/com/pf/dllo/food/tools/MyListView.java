package com.pf.dllo.food.tools;

import android.content.Context;
import android.icu.util.MeasureUnit;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by dllo on 2016/12/9.
 * 解决滑动冲突
 */

public class MyListView extends ListView{
    public MyListView(Context context) {
        this(context,null);
    }

    public MyListView(Context context, AttributeSet attrs) {
       this(context, attrs,0);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // 事件传递的入口,dispatchTouchEvent负责分发事件，在dispatch里又会调用onTouchEvent表示执行事件
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
//            return true;
//        }
//        return super.dispatchTouchEvent(ev);
//    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
