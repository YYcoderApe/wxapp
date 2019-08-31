package com.zczp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class TbCity {
    private Integer cityId;

    private String cityName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date cityNewDate;

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public Date getCityNewDate() {
        return cityNewDate;
    }

    public void setCityNewDate(Date cityNewDate) {
        this.cityNewDate = cityNewDate;
    }
}