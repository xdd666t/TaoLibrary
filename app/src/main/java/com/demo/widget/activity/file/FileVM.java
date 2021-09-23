package com.demo.widget.activity.file;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

/**
 * 作者: 余涛
 * 功能描述: 请描述该文件的功能
 * 创建时间: 2020/5/27 23:38
 */
public class FileVM extends ViewModel {
    //监听一些数据变化，view层做相应处理
    private final MutableLiveData<List<String>> mList = new MutableLiveData<>();

    public FileVM() {
        FileModel fileModel = new FileModel();
        mList.postValue(fileModel.getList());
    }

    public MutableLiveData<List<String>> getList() {
        return mList;
    }
}
