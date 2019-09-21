package com.zczp.entity;

public class TbCompany {
    private Integer companyId;

    private String companyName;

    private String companyLogo;

    private Integer count;

    private Integer companyState;

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo == null ? null : companyLogo.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCompanyState() {
        return companyState;
    }

    public void setCompanyState(Integer companyState) {
        this.companyState = companyState;
    }
}