package com.zczp.util;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: YYcoder
 * @Date: 2019/7/15 13:52
 * @Description: 该类用于包装所有Ajax请求的响应结果
 */
@Component
public class AjaxResult implements Serializable {
    private int status;//状态码 ，200为正常，500为异常
    private String sData;//包装String类型的数据
    private Object oData;//包装Object类型的数据
    private List<Object> listData;//包装List类型的数据

    public AjaxResult() {
    }

    /*包装sData*/
    public AjaxResult(int status, String sData) {
        this.status = status;
        this.sData = sData;
    }

    /*包装oData*/
    public AjaxResult(int status, Object oData) {
        this.status = status;
        this.oData = oData;
    }

    /*包装ListData*/
    public AjaxResult(int status, List<Object> listData) {
        this.status = status;
        this.listData = listData;
    }

    /**
     * @param
     * @auther: YYcoder
     * @description: 默认的成功结果
     * @return: com.zczp.util.AjaxResult
     * @date: 2019/1/15 14:04
     */
    public AjaxResult ok() {
        return new AjaxResult(200, "成功！");
    }

    /**
     * @param oData
     * @auther: YYcoder
     * @description: 请求成功返回字符串
     * @return: com.zczp.util.AjaxResult
     * @date: 2019/7/15 14:06
     */
    public AjaxResult ok(String oData) {
        return new AjaxResult(200, oData);
    }

    /**
     * @param oData
     * @auther: YYcoder
     * @description: 请求成功返回对象
     * @return: com.zczp.util.AjaxResult
     * @date: 2019/7/15 14:08
     */
    public AjaxResult ok(Object oData) {
        return new AjaxResult(200, oData);
    }

    /**
     * @param listData
     * @auther: YYcoder
     * @description: 请求成功返回对象集合
     * @return: com.zczp.util.AjaxResult
     * @date: 2019/7/15 14:09
     */
    public AjaxResult ok(List<Object> listData) {
        return new AjaxResult(200, listData);
    }

    /**
     * @param
     * @auther: YYcoder
     * @description: 请求失败，服务器出现异常
     * @return: com.zczp.util.AjaxResult
     * @date: 2019/7/15 14:10
     */
    public AjaxResult error() {
        return new AjaxResult(500, "服务器异常");
    }

    /**
     * @param
     * @auther: YYcoder
     * @description: 请求失败，服务器出现异常,发送异常信息
     * @return: com.zczp.util.AjaxResult
     * @date: 2019/7/15 14:10
     */
    public AjaxResult error(String sData) {
        return new AjaxResult(500, sData);
    }

    public AjaxResult error(Object oData) {
        return new AjaxResult(500, oData);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getsData() {
        return sData;
    }

    public void setsData(String sData) {
        this.sData = sData;
    }

    public Object getoData() {
        return oData;
    }

    public void setoData(Object oData) {
        this.oData = oData;
    }

    public List<Object> getListData() {
        return listData;
    }

    public void setListData(List<Object> listData) {
        this.listData = listData;
    }
}
