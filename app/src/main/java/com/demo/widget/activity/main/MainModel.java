package com.demo.widget.activity.main;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: 余涛
 * 功能描述: 请描述该文件的功能
 * 创建时间: 2020/5/27 22:10
 */
public class MainModel {

    public List<String> getList() {
        List<String> list = new ArrayList<>();
        list.add("进入Dialog");
        list.add("进入File");
        list.add("进入DB");
        list.add("进入Net");
        list.add("进入View");
        list.add("进入Test");

        return list;
    }

}
