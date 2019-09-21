package com.zczp.service_yycoder.impl;

import com.zczp.vo_yycoder.MyAskReplyVo;

import java.util.List;

public class QuickSort {
    public static void quickSort(MyAskReplyVo[] array, int low, int high){
        int i,j,t;
        MyAskReplyVo temp = new MyAskReplyVo();
        if(low>high){
            return ;
        }
        i=low;
        j=high;
        //temp就是基准位
        long time = array[j].getCommentList().get(0).getCommentTime().getTime();

        while (i<j) {
            //先看右边，依次往左递减
            while (time<=array[j].getCommentList().get(0).getCommentTime().getTime()&&i<j) {
                j--;
            }
            //再看左边，依次往右递增
            while (time>=array[i].getCommentList().get(0).getCommentTime().getTime()&&i<j) {
                i++;
            }
            //如果满足条件则交换
            if (i<j) {
                temp = array[j];
                array[j] = array[i];
                array[i] = temp;
            }

        }
        //最后将基准为与i和j相等位置的数字交换
        array[low] = array[i];
        array[i] = temp;
        //递归调用左半数组
        quickSort(array, low, j-1);
        //递归调用右半数组
        quickSort(array, j+1, high);
    }

}