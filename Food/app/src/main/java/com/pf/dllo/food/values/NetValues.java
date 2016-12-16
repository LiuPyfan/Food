package com.pf.dllo.food.values;

/**
 * Created by dllo on 16/11/22.
 * 网络值
 */
public class NetValues {

    // 百科
    public static final String LIB_GV = "http://food.boohee.com/fb/v1/categories/list";
    // 搜索之大家都在搜
    public static final String LIB_SEARCH_RV = "http://food.boohee.com/fb/v1/keywords?token=6CnGjxv1F6JsbAgBH8mD";


    // 搜索跳转的ATY
    public static final String LIB_SEARCH_ATY_HEAD = " http://food.boohee.com/fb/v1/search?page="; // 1,2,3
    public static final String LIB_SEARCH_ATY_TAIL = "&order_asc=desc&q=";//苹果

    public static final String GV_LIB_SORT_HEAD = "http://food.boohee.com/fb/v1/foods?kind=";
    public static final String GV_LIB_SORT_MID = "&value=";
    public static final String GV_LIB_SORT_TAIL = "&order_by=1&page=";
    public static final String ATY_GV_LIB_SORT_TAIL = "&order_asc=0";

    // Menu排序
    public static final String LIB_MENU_POP_HEAD = "http://food.boohee.com/fb/v1/foods?kind="; //group
    public static final String LIB_MENU_POP_NEXT = "&value=";//2  fragment 的位置
    public static final String LIB_MENU_POP_SUBVALUE = "&sub_value=";//12,13,14  最上面除了全部的位置
    /**
     * 从 LIB_SORT 找到index 拼接到这个位置 点击pop里的gv 热量2 蛋白质3 脂肪4 膳食纤维5
     */
    public static final String LIB_MENU_POP_MID = "&order_by=1";//5
    public static final String LIB_MENU_POP_MORE = "&page="; //  刷新页
    public static final String LIB_MENU_POP_TAIL = "&order_asc=0";

    // 营养素排序
    public static final String LIB_SORT = "http://food.boohee.com/fb/v1/foods/sort_types";
    public static final String LIB_SORT_POPGV_HEAD = "http://food.boohee.com/fb/v1/foods?kind="; //group
    public static final String LIB_SORT_POPGV_NEXT = "&value=";//2  fragment 的位置
    public static final String LIB_SORT_POPGV_SUBVALUE = "&sub_value=";//12,13,14  最上面除了全部的位置
    /**
     * 从 LIB_SORT 找到index 拼接到这个位置 点击pop里的gv 热量2 蛋白质3 脂肪4 膳食纤维5
     */
    public static final String LIB_SORT_POPGV_MID = "&order_by=";//5
    public static final String LIB_SORT_POPGV_MORE = "&page="; //  刷新页
    public static final String LIB_SORT_POPGV_TAIL = "&order_asc=0";
    /**
     * kind = 分类 品牌 餐饮
     * value = 点击的gridview的position
     * order_by = 营养素排序的index
     * order_ac = 0(高到低) 1(低到高)
     * sub_values = 13(包装谷薯) 12(谷薯制品)11(天然谷薯) 右上角分类
     */

    // 首页
    public static final String HOME_HOME_HEAD = "http://food.boohee.com/fb/v1/feeds/category_feed?page=";
    public static final String HOME_HOME_TAIL = "&category=1&per=10";
    // 测评
    public static final String HOME_EVA_HEAD = "http://food.boohee.com/fb/v1/feeds/category_feed?page=";
    public static final String HOME_EVA_TAIL = "&category=2&per=10";
    // 知识
    public static final String HOME_KNOW_HEAD = "http://food.boohee.com/fb/v1/feeds/category_feed?page=";
    public static final String HOME_KNOW_TAIL = "&category=3&per=10";
    // 美食
    public static final String HOME_DELI_HEAD = "http://food.boohee.com/fb/v1/feeds/category_feed?page=";
    public static final String HOME_DELI_TAIL = "&category=4&per=10";


}
