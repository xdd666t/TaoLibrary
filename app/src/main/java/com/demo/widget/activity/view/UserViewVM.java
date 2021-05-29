package com.demo.widget.activity.view;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

/**
 * 文件作者：余涛
 * 功能描述：描述该文件所实现的功能
 * 创建时间：2020/5/28 17:44
 */
public class UserViewVM extends ViewModel {
    private UserViewModel mUserViewModel = new UserViewModel();

    //监听一些数据变化，view层做相应处理
    private MutableLiveData<List<String>> mList = new MutableLiveData<>();

    public UserViewVM() {
        mList.postValue(mUserViewModel.getList());
    }

    public MutableLiveData<List<String>> getList() {
        return mList;
    }

}
