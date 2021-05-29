package com.taolibrary.widget.view.calendar.helper;

import java.util.Date;

/**
 * 文件作者：余涛
 * 功能描述：描述该文件所实现的功能
 * 创建时间：2019/12/9 9:59
 */
public class DateBean {
    //item类型
    public static int TYPE_NONE = 0;//日期item
    public static int TYPE_DAY = 1;//日期item
    public static int TYPE_MONTH = 2;//月份item
    //item状态
    public static int STATE_BEGIN = 1;//开始日期
    public static int STATE_END = 2;//结束日期
    public static int STATE_SELECTED = 3;//选中状态
    public static int STATE_NORMAL = 4;//正常状态


    private int year;//年
    private int month;//月份
    private int day;  //日
    private Date date;//具体日期
    private int itemType;  //item类型
    private int itemState; //item状态

    //为月历背景设置的字段
    private String detail; //背景携带信息
    private int maxWeek;  //布局类型  5:月有五周   6:月有六周


    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public int getItemState() {
        return itemState;
    }

    public void setItemState(int itemState) {
        this.itemState = itemState;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getMaxWeek() {
        return maxWeek;
    }

    public void setMaxWeek(int maxWeek) {
        this.maxWeek = maxWeek;
    }
}
