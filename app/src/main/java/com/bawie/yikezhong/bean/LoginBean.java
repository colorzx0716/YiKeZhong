package com.bawie.yikezhong.bean;

/**
 * Created by 张肖肖 on 2017/11/27.
 * 登录的bean类
 *
 */


public class LoginBean {

    public String msg;
    public String code;
    public DataBean data;

    public static class DataBean {
        /**
         * age : null
         * appkey : null
         * appsecret : null
         * createtime : 2017-11-27T18:51:14
         * email : null
         * fans : null
         * follow : null
         * gender : 0
         * icon : https://www.zhaoapi.cn/images/146.jpg
         * latitude : null
         * longitude : null
         * mobile : 13717830672
         * money : 0
         * nickname : 昵称是一个萝卜
         * password : 123456
         * praiseNum : null
         * token : 929A310F425923598C7F3495BCAAA278
         * uid : 146
         * username : 13717830672
         */

        public Object age;
        public Object appkey;
        public Object appsecret;
        public String createtime;
        public Object email;
        public Object fans;
        public Object follow;
        public int gender;
        public String icon;
        public Object latitude;
        public Object longitude;
        public String mobile;
        public int money;
        public String nickname;
        public String password;
        public Object praiseNum;
        public String token;
        public int uid;
        public String username;
    }
}
