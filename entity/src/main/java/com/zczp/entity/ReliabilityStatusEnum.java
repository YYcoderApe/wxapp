package com.zczp.entity;

import lombok.Getter;

@Getter
public enum  ReliabilityStatusEnum {
        RELIABILITY(1, "已点可信"),
        UNRELIABILITY(0, "取消/未点可信"),
        ;

        private Integer code;

        private String msg;

    ReliabilityStatusEnum(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
    }

}
