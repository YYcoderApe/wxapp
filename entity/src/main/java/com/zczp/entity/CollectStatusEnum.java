package com.zczp.entity;

import lombok.Getter;

@Getter
public enum CollectStatusEnum {
        RELIABILITY(1, "已点收藏"),
        UNRELIABILITY(0, "取消/未点收藏"),
        ;

        private Integer code;

        private String msg;

    CollectStatusEnum(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
    }

}
