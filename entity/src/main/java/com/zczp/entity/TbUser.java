package com.zczp.entity;

public class TbUser {
    private String openId;

    private String userName;

    private Long userPhone;

    private String userGender;

    private String userSchool;

    private String userPosttype;

    private String userSignature;

    private String userCompany;

    private String userImage;

    private String language;

    private String city;

    private String province;

    private String country;

    private Integer state;

    @Override
    public String toString() {
        return "TbUser{" +
                "openId='" + openId + '\'' +
                ", userName='" + userName + '\'' +
                ", userPhone=" + userPhone +
                ", userGender='" + userGender + '\'' +
                ", userSchool='" + userSchool + '\'' +
                ", userPosttype='" + userPosttype + '\'' +
                ", userSignature='" + userSignature + '\'' +
                ", userCompany='" + userCompany + '\'' +
                ", userImage='" + userImage + '\'' +
                ", language='" + language + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(Long userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender == null ? null : userGender.trim();
    }

    public String getUserSchool() {
        return userSchool;
    }

    public void setUserSchool(String userSchool) {
        this.userSchool = userSchool == null ? null : userSchool.trim();
    }

    public String getUserPosttype() {
        return userPosttype;
    }

    public void setUserPosttype(String userPosttype) {
        this.userPosttype = userPosttype == null ? null : userPosttype.trim();
    }

    public String getUserSignature() {
        return userSignature;
    }

    public void setUserSignature(String userSignature) {
        this.userSignature = userSignature == null ? null : userSignature.trim();
    }

    public String getUserCompany() {
        return userCompany;
    }

    public void setUserCompany(String userCompany) {
        this.userCompany = userCompany == null ? null : userCompany.trim();
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage == null ? null : userImage.trim();
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language == null ? null : language.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}