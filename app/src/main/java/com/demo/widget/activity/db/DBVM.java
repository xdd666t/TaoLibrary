package com.demo.widget.activity.db;

import android.database.Cursor;
import android.view.View;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.orhanobut.logger.Logger;


/**
 * 文件作者：余涛
 * 功能描述：描述该文件所实现的功能
 * 创建时间：2020/5/28 18:24
 */
public class DBVM extends ViewModel {
    private ObservableField<String> mQueryContent = new ObservableField<>();

    public DBVM() {

    }

    /**
     * 查询操作
     */
    public void query(Cursor cursor) {
        //利用游标遍历所有数据对象
        String name, showData = "";
        while (cursor.moveToNext()) {
            name = cursor.getString(cursor.getColumnIndex("name"));
            showData = showData + "\n" + name;
        }
        mQueryContent.set(showData);
    }


    public ObservableField<String> getQueryContent() {
        return mQueryContent;
    }
}
