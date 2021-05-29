package com.demo.widget.activity.view.view;

import com.demo.base.BaseActivity;
import com.demo.databinding.ViewCalendarListBinding;

/**
 * 文件作者：余涛
 * 功能描述：描述该文件所实现的功能
 * 创建时间：2019/12/24 17:30
 */
public class CalendarListActivity extends BaseActivity {
    private ViewCalendarListBinding mBinding;


    @Override
    protected void initView() {
        mBinding = ViewCalendarListBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
//        cl_calendar.setOnDateSelected((startDate, endDate) -> showToast(startDate + "---"  + endDate));
    }

    @Override
    protected void initData() {

    }
}
