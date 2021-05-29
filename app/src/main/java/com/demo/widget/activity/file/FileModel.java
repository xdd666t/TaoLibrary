package com.demo.widget.activity.file;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: 余涛
 * 功能描述: 请描述该文件的功能
 * 创建时间: 2020/5/27 23:38
 */
public class FileModel {

    public List<String> getList() {
        List<String> list = new ArrayList<>();
        list.add("下载文件");
        list.add("获取链接文件名");
        list.add("打开文件");
        return list;
    }

}
