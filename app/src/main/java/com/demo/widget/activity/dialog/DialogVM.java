package com.demo.widget.activity.dialog;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

/**
 * 作者: 余涛
 * 功能描述: 请描述该文件的功能
 * 创建时间: 2020/5/27 23:30
 */
public class DialogVM extends ViewModel {
    private DialogModel mDialogModel = new DialogModel();

    //监听一些数据变化，view层做相应处理
    private MutableLiveData<List<String>> mList = new MutableLiveData<>();

    public DialogVM() {
        mList.postValue(mDialogModel.getList());
    }

    public MutableLiveData<List<String>> getList() {
        return mList;
    }
}
