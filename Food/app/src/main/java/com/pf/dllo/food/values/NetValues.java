package com.pf.dllo.food.values;

/**
 * Created by dllo on 16/11/22.
 * 网络值
 */
public class NetValues {

    // 百科
    public static String LIB_GV = "http://food.boohee.com/fb/v1/categories/list";
    public static String LIB_PEOPLE_SEARCH = "http://food.boohee.com/fb/v1/keywords?token=&user_key=";
   // 营养素排序
   public static String LIB_SORT = "http://food.boohee.com/fb/v1/foods/sort_types";
   public static String LIB_DETAIL_HEAD = "http://food.boohee.com/fb/v1/foods?kind=";
   public static String LIB_DETAIL_MID = "group";
   public static String LIB_DETAIL_TAIL = "&value=1(&sub_value=13)&order_by=1&page=1&order_asc=0";
    /**
     *
     kind = 分类 品牌 餐饮
     value = 点击的gridview的position
     order_by = 营养素排序的index
     order_ac = 0(高到低) 1(低到高)
     sub_values = 13(包装谷薯) 12(谷薯制品)11(天然谷薯) 右上角分类
     */



    // 首页
    public static final String HOME_HOME = "http://food.boohee.com/fb/v1/feeds/category_feed?page=1&category=1&per=10";
    // 测评
    public static final String HOME_EVA = "http://food.boohee.com/fb/v1/feeds/category_feed?page=1&category=2&per=10";
    public static final String HOME_EVA_HEAD = "http://food.boohee.com/fb/v1/feeds/category_feed?page=";
    public static final String HOME_EVA_TAIL = "&category=2&per=10";
    // 知识
    public static final String HOME_KNOW = "http://food.boohee.com/fb/v1/feeds/category_feed?page=1&category=3&per=10";
    // 美食
    public static final String HOME_DELI = "http://food.boohee.com/fb/v1/feeds/category_feed?page=1&category=4&per=10";
    public static final String HOME_DELI_HEAD = "http://food.boohee.com/fb/v1/feeds/category_feed?page=";
    public static final String HOME_DELI_TAIL = "&category=4&per=10";


}
