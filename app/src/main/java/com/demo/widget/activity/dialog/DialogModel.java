package com.demo.widget.activity.dialog;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: 余涛
 * 功能描述: 请描述该文件的功能
 * 创建时间: 2020/5/27 23:30
 */
public class DialogModel {

    public List<String> getList() {
        List<String> list = new ArrayList<>();
        list.add("提示弹窗");
        list.add("通用弹窗");
        list.add("等待加载");
        list.add("基础弹窗");
        list.add("底部弹窗");
        list.add("底部退出弹窗");
        list.add("底部列表弹窗");
        return list;
    }

}
