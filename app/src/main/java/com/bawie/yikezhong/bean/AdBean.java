package com.bawie.yikezhong.bean;

import java.util.List;

/**
 * Created by 张肖肖 on 2017/11/29.
 */

public class AdBean {


    /**
     * msg :
     * code : 0
     * data : [{"aid":1,"createtime":"2017-11-28T19:35:04","icon":"https://www.zhaoapi.cn/images/quarter/ad1.png","productId":null,"title":"花生油","type":0,"url":"http://m.mv14449315.icoc.bz/index.jsp"},{"aid":2,"createtime":"2017-11-28T19:35:04","icon":"https://www.zhaoapi.cn/images/quarter/ad2.png","productId":null,"title":"京东女人节","type":0,"url":"http://m.mv14449315.icoc.bz/index.jsp"},{"aid":3,"createtime":"2017-11-28T19:35:04","icon":"https://www.zhaoapi.cn/images/quarter/ad3.png","productId":null,"title":"国庆大惠战","type":0,"url":"http://m.mv14449315.icoc.bz/index.jsp"},{"aid":4,"createtime":"2017-11-28T19:35:04","icon":"https://www.zhaoapi.cn/images/quarter/ad4.png","productId":"1","title":"北京稻香村 稻香村中秋节月饼 老北京月饼礼盒655g","type":1,"url":""}]
     */

    public String msg;
    public String code;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * aid : 1
         * createtime : 2017-11-28T19:35:04
         * icon : https://www.zhaoapi.cn/images/quarter/ad1.png
         * productId : null
         * title : 花生油
         * type : 0
         * url : http://m.mv14449315.icoc.bz/index.jsp
         */

        public int aid;
        public String createtime;
        public String icon;
        public Object productId;
        public String title;
        public int type;
        public String url;
    }
}
