package com.zczp.util;

public class RedisKeyUtil {

    //保存用户点可信数据的key
    public static final String MAP_KEY_RELIABILITY = "MAP_RELIABILITY";
    //保存招聘信息被点可信数量的key
    public static final String MAP_KEY_RELIABILITY_COUNT = "MAP_USER_RELIABILITY_COUNT";

    /**
     * 拼接点可信的用户id和被点招聘信息的id作为key。格式 222222::333333
     * @param userId 可信的用户id
     * @param postId 被点招聘信息的id
     * @return
     */
    public static String getKey(int userId, int postId){
        StringBuilder builder = new StringBuilder();
        builder.append(userId);
        builder.append("::");
        builder.append(postId);
        return builder.toString();
    }
}
