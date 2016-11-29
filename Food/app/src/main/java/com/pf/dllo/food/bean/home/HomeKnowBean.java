package com.pf.dllo.food.bean.home;

import java.util.List;

/**
 * Created by dllo on 16/11/24.
 */
public class HomeKnowBean {

    /**
     * page : 1
     * total_pages : 30
     * feeds : [{"source":"大糖 营养师团 ","title":"心梗离你有多远？| 教你选对食物 降低风险","link":"http://jingxuan.guokr.com/pick/75627/","tail":"6833","images":["http://up.boohee.cn/house/u/food_library/home_news/news_454.png"],"item_id":849,"type":"food_news","content_type":1},{"source":"大糖 营养师团 ","title":"零运动基础？1000步运动替换法你也可以尝试！","link":"http://jingxuan.guokr.com/pick/75632/","tail":"14587","images":["http://up.boohee.cn/house/u/food_library/home_news/news_450.png"],"item_id":850,"type":"food_news","content_type":1},{"source":"企鹅吃喝指南","title":"我知道你为什么看不起喝茶包","link":"http://mp.weixin.qq.com/s?__biz=MjM5Mzc5NTk1OQ==&mid=2652999710&idx=1&sn=6023a0532ca312f8909d3c6f29560b05&chksm=bd44aa068a33231058823c917d5c156318597c28ed7849a87e3cdc3768effdcce8769ee8fdad&mpshare=1&scene=1&srcid=1010244UiU6G70YDrdbjPf9q#rd","tail":"7761","images":["http://up.boohee.cn/house/u/food_library/home_news/news_452.png"],"item_id":851,"type":"food_news","content_type":1},{"source":"康宝莱微刊","title":"饿怕了？减肥加餐未必会胖，这个你必须知道！","link":"http://mp.weixin.qq.com/s?__biz=MjM5NjU4OTg4NA==&mid=2652400770&idx=1&sn=ebc42cda426d011621b3c6593019c135&mpshare=1&scene=1&srcid=1009LtdXRB92TqkYRvZYvuff#rd","tail":"24723","images":["http://up.boohee.cn/house/u/food_library/home_news/news_441.png"],"item_id":847,"type":"food_news","content_type":1},{"source":"e患者说 ","title":"葡萄酒过期了还能喝吗？","link":"http://www.toutiao.com/a6335516178047729922/","tail":"3220","images":["http://up.boohee.cn/house/u/food_library/home_news/news_448.png"],"item_id":846,"type":"food_news","content_type":1},{"source":" 朱俊平 糖尿病之友","title":"适合糖友加餐的健康零食，你得会吃！","link":"http://mp.weixin.qq.com/s?__biz=MjM5NTAzMDI3Mg==&mid=2650988024&idx=1&sn=655d73548290f7d5f4307569cd915e39&mpshare=1&scene=1&srcid=1009lYfanUygRWPdPurH951J#rd","tail":"8280","images":["http://up.boohee.cn/house/u/food_library/home_news/news_446.png"],"item_id":845,"type":"food_news","content_type":1},{"source":"范志红","title":"长假之后，怎么调整状态？","link":"","tail":"3431","images":["http://up.boohee.cn/house/u/food_library/home_news/news_442.png"],"item_id":843,"type":"food_news","content_type":1},{"source":"物道","title":"寒露｜秋风一夜凉，枫林尽染暖阳色","link":"http://mp.weixin.qq.com/s?__biz=MzA4MzI1Mzc2MA==&mid=2653783646&idx=1&sn=1549d8c9387ad67f82603006a3dc2834&chksm=84206931b357e027cc07a9032a962e54168159dbc8aa66aa52a018d4f3da6b5ac8659fe3e9b4&mpshare=1&scene=1&srcid=1008iKrYlYdznvTbNFXKwnjq#rd","tail":"1953","images":["http://up.boohee.cn/house/u/food_library/home_news/news_440.png"],"item_id":842,"type":"food_news","content_type":1},{"source":"健康时报","title":"补血还是补肾，不同豆豆营养有啥差别？","link":"http://toutiao.com/a6338919538662539522/?tt_from=mobile_qq&utm_campaign=client_share&app=news_article&utm_source=mobile_qq&iid=5681682629&utm_medium=toutiao_ios","tail":"5704","images":["http://up.boohee.cn/house/u/food_library/home_news/news_444.png"],"item_id":844,"type":"food_news","content_type":1},{"source":"初夏之菡 食商","title":"食品包装上的标签，你误解了吗？","link":"http://mp.weixin.qq.com/s?__biz=MzAwNzk2NDk1Mg==&mid=2247483883&idx=1&sn=69b0c19ac1f06c59e6d62c3a2972e4cb&mpshare=1&scene=1&srcid=1007gu1WBlEays16hINL994Z#rd","tail":"7266","images":["http://up.boohee.cn/house/u/food_library/home_news/news_434.png"],"item_id":838,"type":"food_news","content_type":1}]
     */

    private String page;
    private int total_pages;
    private List<FeedsBean> feeds;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<FeedsBean> getFeeds() {
        return feeds;
    }

    public void setFeeds(List<FeedsBean> feeds) {
        this.feeds = feeds;
    }

    public static class FeedsBean {
        /**
         * source : 大糖 营养师团
         * title : 心梗离你有多远？| 教你选对食物 降低风险
         * link : http://jingxuan.guokr.com/pick/75627/
         * tail : 6833
         * images : ["http://up.boohee.cn/house/u/food_library/home_news/news_454.png"]
         * item_id : 849
         * type : food_news
         * content_type : 1
         */

        private String source;
        private String title;
        private String link;
        private String tail;
        private int item_id;
        private String type;
        private int content_type;
        private List<String> images;

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getTail() {
            return tail;
        }

        public void setTail(String tail) {
            this.tail = tail;
        }

        public int getItem_id() {
            return item_id;
        }

        public void setItem_id(int item_id) {
            this.item_id = item_id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getContent_type() {
            return content_type;
        }

        public void setContent_type(int content_type) {
            this.content_type = content_type;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
