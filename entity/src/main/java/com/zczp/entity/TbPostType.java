package com.zczp.entity;

import java.util.Date;

public class TbPostType {
    private Integer typeId;

    private String typeName;

    private Date typeNewDate;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public Date getTypeNewDate() {
        return typeNewDate;
    }

    public void setTypeNewDate(Date typeNewDate) {
        this.typeNewDate = typeNewDate;
    }
}