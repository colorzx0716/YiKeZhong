package com.bawie.yikezhong.bean;

import java.util.List;

/**
 * Created by 张肖肖 on 2017/11/28.
 */

public class UserJoker {

    /**
     * msg : 获取段子列表成功
     * code : 0
     * data : [{"commentNum":null,"content":"You","createTime":"2017-11-30T09:28:15","imgUrls":null,"jid":281,"praiseNum":null,"shareNum":null,"uid":148,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/148.jpg","nickname":"And%20%20you","praiseNum":"null"}},{"commentNum":null,"content":"You","createTime":"2017-11-30T09:28:15","imgUrls":null,"jid":280,"praiseNum":null,"shareNum":null,"uid":148,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/148.jpg","nickname":"And%20%20you","praiseNum":"null"}},{"commentNum":null,"content":"电饭锅很健康","createTime":"2017-11-30T09:14:17","imgUrls":null,"jid":279,"praiseNum":null,"shareNum":null,"uid":142,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/142.jpg","nickname":"fghg","praiseNum":"null"}},{"commentNum":null,"content":"张鹤岗","createTime":"2017-11-30T08:57:40","imgUrls":"https://www.zhaoapi.cn/images/quarter/151196967322720171129233416.jpg|https://www.zhaoapi.cn/images/quarter/151197023083620171129234333.jpg|https://www.zhaoapi.cn/images/quarter/1512002490602ic_default_share.png|https://www.zhaoapi.cn/images/quarter/1512003459383gp1809635322017-11-13.png|https://www.zhaoapi.cn/images/quarter/1512003460008SHOUTAO_2017-11-03_10-38-27.jpg","jid":278,"praiseNum":null,"shareNum":null,"uid":188,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/188.jpg","nickname":"我是张鹤岗","praiseNum":"null"}},{"commentNum":null,"content":"张鹤岗","createTime":"2017-11-30T08:57:39","imgUrls":"https://www.zhaoapi.cn/images/quarter/151196967322720171129233416.jpg|https://www.zhaoapi.cn/images/quarter/151197023083620171129234333.jpg|https://www.zhaoapi.cn/images/quarter/1512002490602ic_default_share.png|https://www.zhaoapi.cn/images/quarter/1512003459383gp1809635322017-11-13.png","jid":277,"praiseNum":null,"shareNum":null,"uid":188,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/188.jpg","nickname":"我是张鹤岗","praiseNum":"null"}},{"commentNum":null,"content":"班上新来一位学生，名字叫\u201c马騳骉\u201d。开学点名了，班主任不知怎么念，就说：马叉叉到了没?语文老师有点文学素养，点名道：万马奔腾到了没?体育老师则直截了当叫：一群马到了没?历史老师对这个名字很不感冒：五马分尸到了没。数学老师更直接：马6到了没?美术老师最形象：徐悲鸿到了没?最后，还是教务处主任统一了叫法：马俩仨。万能的段友们，谁能告诉我他叫什么名字?","createTime":"2017-11-30T08:57:25","imgUrls":"https://www.zhaoapi.cn/images/quarter/151196967322720171129233416.jpg|https://www.zhaoapi.cn/images/quarter/151197023083620171129234333.jpg|https://www.zhaoapi.cn/images/quarter/1512002490602ic_default_share.png","jid":276,"praiseNum":null,"shareNum":null,"uid":98,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/98.jpg","nickname":"Mo","praiseNum":"null"}},{"commentNum":null,"content":"666","createTime":"2017-11-30T08:41:30","imgUrls":"https://www.zhaoapi.cn/images/quarter/151196967322720171129233416.jpg|https://www.zhaoapi.cn/images/quarter/151197023083620171129234333.jpg|https://www.zhaoapi.cn/images/quarter/1512002490602ic_default_share.png","jid":275,"praiseNum":null,"shareNum":null,"uid":188,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/188.jpg","nickname":"我是张鹤岗","praiseNum":"null"}},{"commentNum":null,"content":"dd","createTime":"2017-11-30T08:32:14","imgUrls":null,"jid":274,"praiseNum":null,"shareNum":null,"uid":148,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/148.jpg","nickname":"And%20%20you","praiseNum":"null"}},{"commentNum":null,"content":"dd","createTime":"2017-11-30T08:32:14","imgUrls":null,"jid":273,"praiseNum":null,"shareNum":null,"uid":148,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/148.jpg","nickname":"And%20%20you","praiseNum":"null"}},{"commentNum":null,"content":"aaa","createTime":"2017-11-30T08:29:45","imgUrls":null,"jid":272,"praiseNum":null,"shareNum":null,"uid":148,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/148.jpg","nickname":"And%20%20you","praiseNum":"null"}}]
     */

    public String msg;
    public String code;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * commentNum : null
         * content : You
         * createTime : 2017-11-30T09:28:15
         * imgUrls : null
         * jid : 281
         * praiseNum : null
         * shareNum : null
         * uid : 148
         * user : {"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/148.jpg","nickname":"And%20%20you","praiseNum":"null"}
         */

        public Object commentNum;
        public String content;
        public String createTime;
        public Object imgUrls;
        public int jid;
        public Object praiseNum;
        public Object shareNum;
        public int uid;
        public UserBean user;

        public static class UserBean {
            /**
             * age : null
             * fans : null
             * follow : null
             * icon : https://www.zhaoapi.cn/images/148.jpg
             * nickname : And%20%20you
             * praiseNum : null
             */

            public Object age;
            public String fans;
            public String follow;
            public String icon;
            public String nickname;
            public String praiseNum;
        }
    }
}
