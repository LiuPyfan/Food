package com.pf.dllo.food.bean.lib;

import java.util.List;

/**
 * Created by dllo on 2016/12/9.
 */

public class LibKindBean {
    /**
     * page : 1
     * total_pages : 73
     * tags : [{"type":"tags","name":"仁果类水果"},{"type":"tags","name":"加餐"},{"type":"tags","name":"全年"}]
     * items : [{"id":469,"code":"pingguo_junzhi","name":"苹果","thumb_image_url":"http://s.boohee.cn/house/food_mid/mid_photo_2015126214658469.jpg","is_liquid":false,"health_light":1,"weight":"100","calory":"54","type":"food"},{"id":481,"code":"qingxiangjiaopingguo","name":"青香蕉苹果","thumb_image_url":"http://s.boohee.cn/house/food_mid/mid_photo_20152493717481.jpg","is_liquid":false,"health_light":1,"weight":"100","calory":"52","type":"food"},{"id":501,"code":"pingguoli","name":"苹果梨","thumb_image_url":"http://s.boohee.cn/house/food_mid/m_pingguoli.jpg","is_liquid":false,"health_light":1,"weight":"100","calory":"53","type":"food"},{"id":470,"code":"fupingguo","name":"伏苹果","thumb_image_url":"http://s.boohee.cn/house/food_mid/m_fupingguow9.jpg","is_liquid":false,"health_light":1,"weight":"100","calory":"48","type":"food"},{"id":473,"code":"hongfushipingguo","name":"红富士苹果","thumb_image_url":"http://s.boohee.cn/house/food_mid/mid_photo_201513115391473.jpg","is_liquid":false,"health_light":1,"weight":"100","calory":"49","type":"food"},{"id":471,"code":"guoguangpingguo","name":"国光苹果","thumb_image_url":"http://s.boohee.cn/house/food_mid/m_guoguangpingguo.jpg","is_liquid":false,"health_light":1,"weight":"100","calory":"56","type":"food"},{"id":1402,"code":"pingguojiang","name":"苹果酱","thumb_image_url":"http://s.boohee.cn/house/food_mid/mid_photo_2010316144781402.jpg","is_liquid":false,"health_light":1,"weight":"100","calory":"278","type":"food"},{"id":472,"code":"hanpingguo","name":"旱苹果","thumb_image_url":"http://s.boohee.cn/house/food_mid/m_hanpingg9.jpg","is_liquid":false,"health_light":1,"weight":"100","calory":"34","type":"food"},{"id":1332,"code":"pingguofu","name":"苹果脯","thumb_image_url":"http://s.boohee.cn/house/food_mid/mid_photo_2010330161501332.jpg","is_liquid":false,"health_light":1,"weight":"100","calory":"340","type":"food"},{"id":102416,"code":"fddce61d","name":"薄荷 水果好冷冻干蓝莓&苹果","thumb_image_url":"http://s.boohee.cn/house/upload_food/2015/6/18/4853723_1434611898mid.jpg","is_liquid":false,"health_light":1,"weight":"100","calory":"395","type":"food"},{"id":475,"code":"hongxingpingguo","name":"红星苹果","thumb_image_url":"http://s.boohee.cn/house/food_mid/m_hongxingpg.jpg","is_liquid":false,"health_light":1,"weight":"100","calory":"58","type":"food"},{"id":485,"code":"zhuguangpingguo","name":"祝光苹果","thumb_image_url":"http://s.boohee.cn/house/food_mid/m_zhuguangpguo.jpg","is_liquid":false,"health_light":1,"weight":"100","calory":"50","type":"food"},{"id":478,"code":"huangxiangjiaopingguo","name":"黄香蕉苹果","thumb_image_url":"http://s.boohee.cn/house/food_mid/m_huangxiangjiaopg.gif","is_liquid":false,"health_light":1,"weight":"100","calory":"53","type":"food"},{"id":484,"code":"yindupingguo","name":"印度苹果","thumb_image_url":"http://s.boohee.cn/house/food_mid/m_yindupingg.jpg","is_liquid":false,"health_light":1,"weight":"100","calory":"54","type":"food"},{"id":474,"code":"hongxiangjiaopingguo","name":"红香蕉苹果","thumb_image_url":"http://s.boohee.cn/house/food_mid/m_hongxiangjiaopg.jpg","is_liquid":false,"health_light":1,"weight":"100","calory":"51","type":"food"},{"id":483,"code":"xiangyupingguo","name":"香玉苹果","thumb_image_url":"http://s.boohee.cn/house/food_mid/m_xiangyupingg.jpg","is_liquid":false,"health_light":1,"weight":"100","calory":"62","type":"food"},{"id":476,"code":"hongyupingguo","name":"红玉苹果","thumb_image_url":"http://s.boohee.cn/house/food_mid/m_hongyupingguo.jpg","is_liquid":false,"health_light":1,"weight":"100","calory":"52","type":"food"},{"id":18159,"code":"woermapingguopai","name":"沃尔玛苹果派","thumb_image_url":"http://s.boohee.cn/house/upload_food/2008/12/13/162087_1229110760mid.jpg","is_liquid":false,"health_light":2,"weight":"100","calory":"282","type":"food"},{"id":18377,"code":"Vinamitpingguogan","name":"Vinamit苹果干","thumb_image_url":"http://s.boohee.cn/house/upload_food/2009/1/11/192215_1231660787mid.jpg","is_liquid":false,"health_light":2,"weight":"100","calory":"346","type":"food"},{"id":479,"code":"huangyuanshuaipingguo","name":"黄元帅苹果","thumb_image_url":"http://s.boohee.cn/house/food_mid/mid_photo_2010225479.jpg","is_liquid":false,"health_light":1,"weight":"100","calory":"59","type":"food"}]
     */

    private int page;
    private int total_pages;
    private List<TagsBean> tags;
    private List<ItemsBean> items;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<TagsBean> getTags() {
        return tags;
    }

    public void setTags(List<TagsBean> tags) {
        this.tags = tags;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class TagsBean {
        /**
         * type : tags
         * name : 仁果类水果
         */

        private String type;
        private String name;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class ItemsBean {
        /**
         * id : 469
         * code : pingguo_junzhi
         * name : 苹果
         * thumb_image_url : http://s.boohee.cn/house/food_mid/mid_photo_2015126214658469.jpg
         * is_liquid : false
         * health_light : 1
         * weight : 100
         * calory : 54
         * type : food
         */

        private int id;
        private String code;
        private String name;
        private String thumb_image_url;
        private boolean is_liquid;
        private int health_light;
        private String weight;
        private String calory;
        private String type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getThumb_image_url() {
            return thumb_image_url;
        }

        public void setThumb_image_url(String thumb_image_url) {
            this.thumb_image_url = thumb_image_url;
        }

        public boolean isIs_liquid() {
            return is_liquid;
        }

        public void setIs_liquid(boolean is_liquid) {
            this.is_liquid = is_liquid;
        }

        public int getHealth_light() {
            return health_light;
        }

        public void setHealth_light(int health_light) {
            this.health_light = health_light;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getCalory() {
            return calory;
        }

        public void setCalory(String calory) {
            this.calory = calory;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
