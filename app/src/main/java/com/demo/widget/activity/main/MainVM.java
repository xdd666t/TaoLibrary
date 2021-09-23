package com.demo.widget.activity.main;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;


/**
 * 作者: 余涛
 * 功能描述: 请描述该文件的功能
 * 创建时间: 2020/5/25 22:52
 */
public class MainVM extends ViewModel {
    //动态绑定View变化，类似小程序的setData
    public ObservableField<String> msg = new ObservableField<>();
    //监听一些数据变化，view层做相应处理
    private final MutableLiveData<List<String>> mList = new MutableLiveData<>();


    public MainVM(){
        msg.set("葬爱");

        MainModel mainModel = new MainModel();
        mList.postValue(mainModel.getList());
    }


    public MutableLiveData<List<String>> getList() {
        return mList;
    }
}
