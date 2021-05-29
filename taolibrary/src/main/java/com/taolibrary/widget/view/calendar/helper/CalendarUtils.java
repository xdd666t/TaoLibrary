package com.taolibrary.widget.view.calendar.helper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 文件作者：余涛
 * 功能描述：设置数据
 * 创建时间：2019/12/9 11:12
 */
public class CalendarUtils {

    Calendar currCalendar = Calendar.getInstance();
    private List<DateBean> mBgList = new ArrayList<>();

    /**
     * 生成日历数据
     * @param backMonth  往回几个月
     * @param monthCount  往回几个月为起点，往后获取几个月的数据
     * @return eg：当前为2018-12月，backMonth=5 monthCount=10 ，则返回：2018-8到2018-5
     */
    public List<DateBean> getDateList(int backMonth, int monthCount){
        List<DateBean> dateBeans = new ArrayList<>();
        currCalendar.add(Calendar.MONTH,-backMonth);
        currCalendar.set(Calendar.DAY_OF_MONTH,1);
        currCalendar.set(Calendar.HOUR_OF_DAY,0);
        currCalendar.set(Calendar.MILLISECOND,0);
        currCalendar.set(Calendar.SECOND,0);
        int monthIndex = 0;
        mBgList.clear();

        while(true){
            // 获取的是月的第一天
            if (currCalendar.get(Calendar.DAY_OF_MONTH)==1){
                if(monthIndex == monthCount){
                    break;
                }
                monthIndex++;

                //获取背景月
                DateBean bgBean = new DateBean();
                bgBean.setDetail( (currCalendar.get(Calendar.MONTH)+1) + "");
                bgBean.setMaxWeek(currCalendar.getActualMaximum(Calendar.WEEK_OF_MONTH));
                mBgList.add(bgBean);

                // 添加月份 --- 每月开头月
                DateBean dateBean = new DateBean();
                dateBean.setYear(currCalendar.get(Calendar.YEAR));
                dateBean.setMonth(currCalendar.get(Calendar.MONTH) + 1);
                dateBean.setDay(currCalendar.get(Calendar.DAY_OF_MONTH));
                dateBean.setDate(currCalendar.getTime());
                dateBean.setItemType(DateBean.TYPE_MONTH);
                dateBean.setItemState(DateBean.STATE_NORMAL);
                dateBeans.add(dateBean);

                //每月前面或后面空出来的日期
                int week = currCalendar.get(Calendar.DAY_OF_WEEK)-1;
                for(int i=0;i<week;++i){
                    dateBean = new DateBean();
                    dateBean.setItemType(DateBean.TYPE_NONE);
                    dateBeans.add(dateBean);
                }
            }

            // 添加日期
            DateBean dateBean = new DateBean();
            dateBean.setYear(currCalendar.get(Calendar.YEAR));
            dateBean.setMonth(currCalendar.get(Calendar.MONTH) + 1);
            dateBean.setDay(currCalendar.get(Calendar.DAY_OF_MONTH));
            dateBean.setDate(currCalendar.getTime());
            dateBean.setItemType(DateBean.TYPE_DAY);
            dateBean.setItemState(DateBean.STATE_NORMAL);
            dateBeans.add(dateBean);

            currCalendar.add(Calendar.DAY_OF_MONTH,1);
        }

        return dateBeans;
    }

    public List<DateBean> getBgList(){
        return mBgList;
    }


    /**
     * 获取之前第N个月(从当前月份,往前数N个月)
     * @param i n
     * @return
     */
    public String getLastMonthsN(int i) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -i);
        Date m = c.getTime();
        return sdf.format(m);
    }


}
