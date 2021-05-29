package com.demo.widget.activity.view;

import android.content.Intent;
import android.widget.TextView;

import com.demo.R;
import com.demo.base.BaseActivity;
import com.demo.databinding.ActivityViewBinding;
import com.demo.widget.activity.view.view.CalendarListActivity;
import com.demo.widget.activity.view.view.RoundViewActivity;
import com.taolibrary.base.single.SingleRvAdapter;
import com.taolibrary.widget.ui.AutoUi;

import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

/**
 * 作者: 余涛
 * 功能描述: 请描述该文件的功能
 * 创建时间: 2019/12/10 21:48
 */
public class UserViewActivity extends BaseActivity {
    ActivityViewBinding mBinding;

    private SingleRvAdapter<String> mSingleRvAdapter;
    private UserViewVM mUserViewVM;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_view);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_view);

        AutoUi.getInstance().asWidth(mBinding.getRoot());
    }

    @Override
    protected void initData() {
        mUserViewVM = new ViewModelProvider(this).get(UserViewVM.class);

        setList();
    }

    private void setList() {
        mUserViewVM.getList().observe(this, new androidx.lifecycle.Observer<List<String>>() {
            @Override public void onChanged(List<String> strings) {
                mSingleRvAdapter.updateData(strings);
            }
        });

        mBinding.rvView.setLayoutManager(new GridLayoutManager(this, 2));
        mBinding.rvView.setAdapter(mSingleRvAdapter = new SingleRvAdapter<String>(R.layout.item_normal) {
            @Override
            protected void onBindDataToView(BaseViewHolder holder, String s, int position) {
                ((TextView) holder.getView(R.id.tv_content)).setText(s);
            }
        });
        mSingleRvAdapter.setOnItemClickListener((SingleRvAdapter.OnItemClickListener) position -> {
            switch (position) {
                case 0:
                    startActivity(new Intent(UserViewActivity.this, CalendarListActivity.class));
                    break;
                case 1:
                    startActivity(new Intent(UserViewActivity.this, RoundViewActivity.class));
                    break;
            }
        });
    }

}
