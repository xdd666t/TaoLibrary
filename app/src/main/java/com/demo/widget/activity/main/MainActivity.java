package com.demo.widget.activity.main;

import android.content.Context;
import android.content.Intent;
import android.os.Debug;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.demo.R;
import com.demo.base.BaseActivity;
import com.demo.bean.TestBean;
import com.demo.databinding.ActivityMainBinding;
import com.demo.widget.activity.TestActivity;
import com.demo.widget.activity.db.DBActivity;
import com.demo.widget.activity.dialog.DialogActivity;
import com.demo.widget.activity.file.FileActivity;
import com.demo.widget.activity.net.NetActivity;
import com.demo.widget.activity.view.UserViewActivity;
import com.orhanobut.logger.Logger;
import com.taolibrary.base.single.SingleRvAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

public class MainActivity extends BaseActivity {
    private ActivityMainBinding mBinding;
    private Context context = this;
    private Intent mIntent;
    private SingleRvAdapter<String> mSingleRvAdapter;
    MainVM mMainVM;

    @Override
    protected void initView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //和当前Activity生命周期绑定
        mMainVM = new ViewModelProvider(this).get(MainVM.class);
        mBinding.setViewModel(mMainVM);
    }

    @Override
    protected void initData() {
        mIntent = new Intent();
        setAdapter();
    }

    private void setAdapter() {
        mMainVM.getList().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                mSingleRvAdapter.updateData(strings);
            }
        });

        //列表实现
        mBinding.rvModel.setLayoutManager(new GridLayoutManager(this, 2));
        mSingleRvAdapter = new SingleRvAdapter<String>(R.layout.item_normal) {
            @Override
            protected void onBindDataToView(BaseViewHolder holder, String s, int position) {
                TextView textView = holder.getView(R.id.tv_content);
                textView.setText(mList.get(position));
            }
        };
        mSingleRvAdapter.setOnItemClickListener(new SingleRvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                switch (position) {
                    case 0:
                        //开始埋点，“app”是最后生成的性能分析文件
                        Debug.startMethodTracing("App");

                        mIntent.setClass(context, DialogActivity.class);
                        startActivity(mIntent);

                        //埋点结束，期间start 到 stop 之间的代码，就是你要测试的代码范围
                        Debug.stopMethodTracing();
                        break;
                    case 1:
                        mIntent.setClass(context, FileActivity.class);
                        startActivity(mIntent);
                        break;
                    case 2:
                        mIntent.setClass(context, DBActivity.class);
                        startActivity(mIntent);
                        break;
                    case 3:
                        mIntent.setClass(context, NetActivity.class);
                        startActivity(mIntent);
                        break;
                    case 4:
                        mIntent.setClass(context, UserViewActivity.class);
                        startActivity(mIntent);
                        break;
                    case 5:
                        mIntent.setClass(context, TestActivity.class);
                        startActivity(mIntent);
                        break;
                }
            }
        });
        mBinding.rvModel.setAdapter(mSingleRvAdapter);
    }

}
