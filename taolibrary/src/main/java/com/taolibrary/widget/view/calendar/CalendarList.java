package com.taolibrary.widget.view.calendar;


import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.taolibrary.R;
import com.taolibrary.base.BaseRecyclerViewAdapter;
import com.taolibrary.base.single.SingleRvAdapter;
import com.taolibrary.widget.view.calendar.helper.CalendarUtils;
import com.taolibrary.widget.view.calendar.helper.DateBean;

import java.util.Date;
import java.util.List;


/**
 * 文件作者：余涛
 * 功能描述：日历控件  垂直list（多选）
 * 创建时间：2019/12/9 9:35
 */
public class CalendarList extends FrameLayout {
    private Context mContext;
    //周
    public CardView cv_calendar_week;  //周总布局(一级) --- CardView
    public LinearLayout ll_calendar_week;  //周总布局(二级) --- LinearLayout
    public TextView tv_calendar_week_seven;  //周日
    public TextView tv_calendar_week_one;  //周一
    public TextView tv_calendar_week_two;  //周二
    public TextView tv_calendar_week_three;  //周三
    public TextView tv_calendar_week_four;  //周四
    public TextView tv_calendar_week_five;  //周五
    public TextView tv_calendar_week_six;  //周六
    //日历---月list
    public RecyclerView rv_calendar;
    public MonthAdapter mMonthAdapter;
    public List<DateBean> mMonthList;
    //设置月历item的背景
    public RecyclerView rv_bg;
    public SingleRvAdapter<DateBean> mBgAdapter;
    public List<DateBean> mBgList;

    private DateBean startDate;//开始时间
    private DateBean endDate;//结束时间

    private boolean isAbleClick = true; //是否能点击 true:能点击  false:不能点击

    //监听数据获取接口
    private OnDateSelected mOnDateSelected;

    public CalendarList(Context context, AttributeSet attrs) { //布局时必须重写该项(双参数)
        super(context, attrs);
        mContext = context;
        initView();
        initData();
    }

    /**
     * 初始化view
     */
    private void initView() {
        //设置主界面
        addView(LayoutInflater.from(mContext).inflate(R.layout.t_view_calendar_list, this, false));
        //周
        cv_calendar_week = findViewById(R.id.cv_calendar_week);
        ll_calendar_week = findViewById(R.id.ll_calendar_week);
        tv_calendar_week_seven = findViewById(R.id.tv_calendar_week_seven);
        tv_calendar_week_one = findViewById(R.id.tv_calendar_week_one);
        tv_calendar_week_two = findViewById(R.id.tv_calendar_week_two);
        tv_calendar_week_three = findViewById(R.id.tv_calendar_week_three);
        tv_calendar_week_four = findViewById(R.id.tv_calendar_week_four);
        tv_calendar_week_five = findViewById(R.id.tv_calendar_week_five);
        tv_calendar_week_six = findViewById(R.id.tv_calendar_week_six);
        //月
        rv_calendar = findViewById(R.id.rv_calendar);
        rv_bg = findViewById(R.id.rv_bg); //月历背景
        rv_calendar.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                rv_bg.scrollBy(dx,dy); //背景跟随着滑动
            }
        });

        setListAdapter(); //设置列表Adapter
    }

    /**
     * 初始化数据
     */
    public void initData() {
        //获取日历数据
        CalendarUtils calendarUtils = new CalendarUtils();
        mMonthList = calendarUtils.getDateList(12,13);
        mBgList = calendarUtils.getBgList();

        //更新
        mMonthAdapter.updateData(mMonthList);
        mBgAdapter.updateData(mBgList);

        rv_calendar.scrollToPosition(mMonthList.size() - 1); //默认移到最后
        rv_bg.scrollToPosition(mBgList.size() - 1);
    }

    /**
     * 设置列表适配器
     */
    private void setListAdapter() {
        //设置月列表
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 7);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (mMonthList.get(position).getItemType() == DateBean.TYPE_MONTH) {
                    return 7;
                }else{
                    return 1;
                }
            }
        });
        rv_calendar.setLayoutManager(gridLayoutManager);
        mMonthAdapter = new MonthAdapter();
        rv_calendar.setAdapter(mMonthAdapter);
        mMonthAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                onClick(position);
            }
        });

        //设置背景
        rv_bg.setLayoutManager(new LinearLayoutManager(mContext));
        rv_bg.setAdapter(mBgAdapter = new SingleRvAdapter<DateBean>(R.layout.t_item_calendar_bg) {
            @Override
            protected void onBindDataToView(BaseViewHolder holder, DateBean dateBean, int position) {
                TextView tv_month_bg_low = holder.getView(R.id.tv_month_bg_low);
                TextView tv_month_bg_hight = holder.getView(R.id.tv_month_bg_hight);
                if (dateBean.getMaxWeek() == 6) {
                    tv_month_bg_hight.setText(""+dateBean.getDetail());
                    tv_month_bg_hight.setVisibility(VISIBLE);
                    tv_month_bg_low.setVisibility(GONE);
                }else {
                    tv_month_bg_low.setText(""+dateBean.getDetail());
                    tv_month_bg_low.setVisibility(VISIBLE);
                    tv_month_bg_hight.setVisibility(GONE);
                }
            }
        });
    }


    /**
     * 处理Item点击事件
     * @param position
     */
    private void onClick(int position) {
        DateBean dateBean = mMonthList.get(position);
        if (dateBean.getItemType() != DateBean.TYPE_DAY  || !isAbleClick) {
            return;
        }
        //如果没有选中开始日期则此次操作选中开始日期
        if (startDate == null) {
            startDate = dateBean;
            dateBean.setItemState(DateBean.STATE_BEGIN);
        } else if (endDate == null) {
            //如果选中了开始日期但没有选中结束日期，本次操作选中结束日期

            //如果当前点击的结束日期跟开始日期一致 则不做操作
            if (startDate == dateBean) {

            } else if (dateBean.getDate().getTime() < startDate.getDate().getTime()) {
                //当前点选的日期小于当前选中的开始日期 则本次操作重新选中开始日期
                startDate.setItemState(DateBean.STATE_NORMAL);
                startDate = dateBean;
                startDate.setItemState(DateBean.STATE_BEGIN);
            } else {
                //选中结束日期
                endDate = dateBean;
                endDate.setItemState(DateBean.STATE_END);
                setState();
                if(mOnDateSelected!=null){
                    mOnDateSelected.selected(startDate.getDate(), endDate.getDate());
                }
            }
        } else if (startDate != null && endDate != null) {
            //结束日期和开始日期都已选中
            clearState();
            //重新选择开始日期,结束日期清除
            startDate.setItemState(DateBean.STATE_NORMAL);
            startDate = dateBean;
            startDate.setItemState(DateBean.STATE_BEGIN);

            endDate.setItemState(DateBean.STATE_NORMAL);
            endDate = null;
        }

        mMonthAdapter.notifyDataSetChanged();
    }

    /**
     * 是否能点击item
     * @param enabled
     */
    @Override
    public void setEnabled(boolean enabled) {
        this.isAbleClick = enabled;
    }

    //选中中间的日期
    private void setState() {
        if (endDate != null && startDate != null) {
            int start = mMonthList.indexOf(startDate) + 1; //开始时间靠后一位
            int end = mMonthList.indexOf(endDate);
            for (; start < end; start++) {
                DateBean dateBean = mMonthList.get(start);
                if (dateBean.getDay() != 0) {
                    dateBean.setItemState(DateBean.STATE_SELECTED);
                }
            }
        }
    }

    //取消选中状态
    private void clearState() {
        if (endDate != null && startDate != null) {
            int start = mMonthList.indexOf(startDate) + 1;
            int end = mMonthList.indexOf(endDate);
            for (; start < end; start++) {
                DateBean dateBean = mMonthList.get(start);
                dateBean.setItemState(DateBean.STATE_NORMAL);
            }
        }
    }

    //取消选中状态
    public void clear() {
        for (int i = 0; i < mMonthList.size(); i++) {
            mMonthList.get(i).setItemState(DateBean.STATE_NORMAL);
        }
        mMonthAdapter.notifyDataSetChanged();
    }



    /***********************************************月列表适配器***************************************************/
    class MonthAdapter extends BaseRecyclerViewAdapter<DateBean> {
        @Override
        protected int getItemLayoutId(int viewType) {
            if (viewType == DateBean.TYPE_MONTH) {
                return R.layout.t_item_calendar_month;
            }else {
                return R.layout.t_item_calendar_day;
           }
        }

        @Override
        public int getItemViewType(int position) {
            if(getDataList().get(position).getItemType() == DateBean.TYPE_MONTH){
                return DateBean.TYPE_MONTH;
            }
            return DateBean.TYPE_DAY;
        }

        @Override
        protected void onBindDataToView(BaseViewHolder holder, DateBean dateBean, int position) {
            TextView tv_month = holder.getView(R.id.tv_month); //月标题
            RelativeLayout rl_day = holder.getView(R.id.rl_day); //日item背景
            TextView tv_day = holder.getView(R.id.tv_day); //日item

            if (dateBean.getItemType() == DateBean.TYPE_MONTH) { //设置头部
                tv_month.setText(dateBean.getYear() + "年" + dateBean.getMonth()+ "月");
                return;
            }

            //处理下点击事件  月开头和结尾 前后处的空白item禁止点击效果
            if(0 != dateBean.getDay()){
                tv_day.setText(""+dateBean.getDay());
            }else{
                tv_day.setText("");
                tv_day.setEnabled(false);
            }

            //设置item状态 被点击后状况
            if (dateBean.getItemState() == DateBean.STATE_BEGIN || dateBean.getItemState() == DateBean.STATE_END) {
                //开始日期或结束日期
                rl_day.setAlpha(1);
                rl_day.setBackgroundResource(R.drawable.bg_round_blue_10dp);
                tv_day.setTextColor(Color.WHITE);
            } else if (dateBean.getItemState() == DateBean.STATE_SELECTED) {
                //选中状态
                rl_day.setAlpha((float) 0.10);
                rl_day.setBackgroundColor(Color.parseColor("#00A8FF"));
                tv_day.setTextColor(Color.parseColor("#192A56"));
            } else {
                //正常状态
                rl_day.setBackgroundColor(Color.TRANSPARENT);
                tv_day.setTextColor(Color.parseColor("#192A56"));
            }
        }
    }


    public interface OnDateSelected{
        void selected(Date startDate, Date endDate);
    }
    public void setOnDateSelected(OnDateSelected onDateSelected) {
        this.mOnDateSelected = onDateSelected;
    }

}

