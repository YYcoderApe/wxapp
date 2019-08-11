package com.zczp.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 实体 属性描述 这里只是简单示例，你可以自定义相关用户信息
 */
@Data
public class WxAccount {

    public WxAccount(String wxOpenid, String sessionKey) {
        this.wxOpenid = wxOpenid;
        this.sessionKey = sessionKey;
    }

    private String wxOpenid;
    private String sessionKey;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastTime;

}
