package com.pf.dllo.food.tools.net;

import com.android.volley.VolleyError;

/**
 * Created on 2016/11/28.
 */

/**
 *  @硕哥告诉你
 *  2016/11/28上午10:59
 *  定义一个接口 两个方法 分别是成功时候的回调
 *  失败时候的回调
 */
public interface NetListener<T> {
    //参数 就是解析后的数据
    void successListener(T response);
    //参数 对错误信息进行操作
    void errorListener(VolleyError error);
}
