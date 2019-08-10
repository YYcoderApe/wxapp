package com.zczp.entity;

import java.util.Date;

public class TbCity {
    private Integer cityId;

    private String cityName;

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