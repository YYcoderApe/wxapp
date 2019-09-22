package com.zczp.util;

public class RedisKeyUtil {

    //消息回复key
    public static final  String KEY_NEWS="NEWS_COUNT";
    //保存用户点可信数据的key
    public static final String MAP_KEY_RELIABILITY = "MAP_RELIABILITY";
    //保存招聘信息被点可信数量的key
    public static final String MAP_KEY_RELIABILITY_COUNT = "MAP_USER_RELIABILITY_COUNT";
    //保存用户点收藏数据的key
    public static final String MAP_KEY_COLLECT = "MAP_COLLECT";
    //保存被收藏招聘信息数量的key
    public static final String MAP_KEY_COLLECT_COUNT = "MAP_USER_COLLECT_COUNT";

    /**
     * 拼接点可信的用户id和被点招聘信息的id作为key。格式 222222::333333
     * 或用户ID和被收藏招聘信息Id。格式 222222::333333
     * @param openId 用户id
     * @param postId 被点招聘信息的id
     * @return
     */
    public static String getKey(String openId, int postId){
        StringBuilder builder = new StringBuilder();
        builder.append(openId);
        builder.append("::");
        builder.append(postId);
        return builder.toString();
    }
}
