package com.pf.dllo.food.bean.home;

import java.util.List;

/**
 * Created by dllo on 16/11/24.
 */
public class HomeEvaBean {


    /**
     * page : 1
     * total_pages : 7
     * feeds : [{"item_id":833,"source":"／NICE 健康／","title":"各类海鲜蛋白质含量排行","background":"http://one.boohee.cn/food/image/food_news/2016/10/5/80652b339a9ced1c318faa620dbfcac4","tail":"22176人阅读","type":"food_news","link":"http://mp.weixin.qq.com/s?__biz=MzI4MzEwOTMzMw==&mid=2649417868&idx=1&sn=369d9bb73ced27fd7f7582c1a0272792&mpshare=1&scene=1&srcid=10056q1aIBUvNiyftYRL8Knb#rd","content_type":4},{"item_id":818,"source":"／没牌子／","title":"难吃？好吃？我们尝到了沙拉酱的地狱和天堂","background":"http://one.boohee.cn/food/image/food_news/2016/9/28/bced6e60ca3a2ccc42b0be25e92e108a","tail":"13756人阅读","type":"food_news","link":"http://mp.weixin.qq.com/s?__biz=MzA3ODA0MDg3MA==&mid=2656200967&idx=2&sn=cc4da915308d861e23e44355af4e4373&mpshare=1&scene=1&srcid=0928OFmNXisxdDr0r7SnYuvn#rd","content_type":4},{"item_id":813,"source":"／网易LOFTER／","title":"牛油果vs无花果，谁才是美食届的水果Queen?","background":"http://one.boohee.cn/food/image/food_news/2016/9/27/e22cc428e21ec928a0bc5859d93d58e8","tail":"11704人阅读","type":"food_news","link":"http://mp.weixin.qq.com/s?__biz=MjM5MTA4MTE2MA==&mid=2650272527&idx=1&sn=5486ff70c2ee705d4db8d3b17afc44f2&chksm=beb9a4a889ce2dbe2c01aa0b9a91e1709bf149f858d973cf1eab53d18f16c5a55ce41814aa11","content_type":4},{"item_id":812,"source":"／企鹅吃喝指南／","title":"那些年，我们喝过的罐装咖啡","background":"http://one.boohee.cn/food/image/food_news/2016/9/27/7265166e6808db8bc3fa321cd56757c9","tail":"9158人阅读","type":"food_news","link":"http://mp.weixin.qq.com/s?__biz=MjM5Mzc5NTk1OQ==&mid=2652998659&idx=1&sn=90b369ab31bb2f58349e74061d0533b6&scene=0#wechat_redirect","content_type":4},{"item_id":809,"source":"／36Kr／","title":"硅谷公司食堂哪家强？","background":"http://one.boohee.cn/food/image/food_news/2016/9/26/a2af734425288b94f7061a1d439af6e3","tail":"5651人阅读","type":"food_news","link":"http://36kr.com/p/5053091.html","content_type":4},{"item_id":797,"source":"／就知道吃／","title":"20多家飞机餐大测评，你中过哪家的枪?","background":"http://one.boohee.cn/food/image/food_news/2016/9/24/444f65ec4f020dc6fd78386d2db74cee","tail":"8351人阅读","type":"food_news","link":"http://mp.weixin.qq.com/s?__biz=MzA4OTk0ODUwNQ==&mid=401940710&idx=1&sn=c8a9e7a2f6048859dee7f4e5fbea42d3&scene=24&srcid=0805GwS6sbWaisOZvAfDyiJB#wechat_redirect","content_type":4},{"item_id":776,"source":"／陈柳妍 消费者报道／","title":"8品牌蜂蜜测评：百花牌和同仁堂涉嫌造假","background":"http://one.boohee.cn/food/image/food_news/2016/8/11/8d36d7919cf30d8d5ca226c4d75786df","tail":"21568人阅读","type":"food_news","link":"http://mp.weixin.qq.com/s?__biz=MjM5MTY1NTI0MA==&mid=2651810996&idx=1&sn=1a9502e02a97b38479b7b9d36cc2725a&scene=24&srcid=0805ZWOZ04MYFP5nteicT3aP#wechat_redirect","content_type":4},{"item_id":783,"source":"／ 龙十兄 一期一会饮食指南／","title":"没想到维他是这样的\u201c不务正业\u201d","background":"http://one.boohee.cn/food/image/food_news/2016/8/12/6af4fadd6e426a1f372ca7553d477b95","tail":"14678人阅读","type":"food_news","link":"http://mp.weixin.qq.com/s?__biz=MzAxMTgzNzk4MQ==&mid=2650497836&idx=1&sn=3263230a57085d3789502f11d85b1130&scene=0#wechat_redirect","content_type":4},{"item_id":768,"source":"／食品饮料生鲜消费指南／","title":"我们做了一份\u201c最难喝饮料\u201d的测评报告","background":"http://one.boohee.cn/food/image/food_news/2016/8/11/d7bfbe668a05e1841ab68b0abe80a542","tail":"18308人阅读","type":"food_news","link":"http://mp.weixin.qq.com/s?__biz=MjM5NTcyMTE3MQ==&mid=2652363417&idx=2&sn=e0e29c5706f6dc0ab8b91f001c8bf742&scene=0#wechat_redirect","content_type":4},{"item_id":760,"source":"／ 就知道吃／","title":"喝遍30国41种果汁，我们用胃酸写出了最全果汁指南！","background":"http://one.boohee.cn/food/image/food_news/2016/8/11/5d99bfad74284d3f3623711f122fd92c","tail":"16335人阅读","type":"food_news","link":"http://toutiao.com/i6240399601052942849/","content_type":4}]
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
         * item_id : 833
         * source : ／NICE 健康／
         * title : 各类海鲜蛋白质含量排行
         * background : http://one.boohee.cn/food/image/food_news/2016/10/5/80652b339a9ced1c318faa620dbfcac4
         * tail : 22176人阅读
         * type : food_news
         * link : http://mp.weixin.qq.com/s?__biz=MzI4MzEwOTMzMw==&mid=2649417868&idx=1&sn=369d9bb73ced27fd7f7582c1a0272792&mpshare=1&scene=1&srcid=10056q1aIBUvNiyftYRL8Knb#rd
         * content_type : 4
         */

        private int item_id;
        private String source;
        private String title;
        private String background;
        private String tail;
        private String type;
        private String link;
        private int content_type;

        public int getItem_id() {
            return item_id;
        }

        public void setItem_id(int item_id) {
            this.item_id = item_id;
        }

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

        public String getBackground() {
            return background;
        }

        public void setBackground(String background) {
            this.background = background;
        }

        public String getTail() {
            return tail;
        }

        public void setTail(String tail) {
            this.tail = tail;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public int getContent_type() {
            return content_type;
        }

        public void setContent_type(int content_type) {
            this.content_type = content_type;
        }
    }
}
