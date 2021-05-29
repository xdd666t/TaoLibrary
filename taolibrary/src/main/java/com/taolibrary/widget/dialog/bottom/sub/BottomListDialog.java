package com.taolibrary.widget.dialog.bottom.sub;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.taolibrary.R;
import com.taolibrary.adapter.dialog.BottomRecyclerAdapter;
import com.taolibrary.interfaces.dialog.IDialog;

import java.util.List;

/**
 * Author 余涛
 * Description 功能说明
 * Time 2019/6/16 22:56
 */
public class BottomListDialog extends Dialog {


    public void bottomListDialog(List list, final IDialog.OnBottomListListener onBottomListListener) {
        this.mList = list;
        mAdapter.updateData(mList);

        mAdapter.setOnClickListener(new BottomRecyclerAdapter.OnClickListener() {
            @Override
            public void onClick(int position) {
                onBottomListListener.onClick(position, false);
            }
        });

        mAdapter.setOnLongClickListener(new BottomRecyclerAdapter.OnLongClickListener() {
            @Override
            public void OnLongClick(int position) {
                onBottomListListener.onClick(position, true);
            }
        });

        show();
    }

    /*************************************分割线,上面为调用方法,下为dialog设置********************************/
    private Context mContext;
    private RecyclerView bottom_recycler;
    private RelativeLayout rl_top;
    private BottomRecyclerAdapter mAdapter;
    private Window mWindow;
    private List mList;

    public BottomListDialog(Context context) {
        super(context, R.style.DialogBottom_Tao);
        initData(context);
    }

    /**
     * 一些必备的实例数据,都放在此处
     * @param context
     */
    private void initData(Context context) {
        this.mContext = context;

        getLayoutId();   //设置布局

        bottom_recycler = findViewById(R.id.bottom_recycler); //绑定组件
        rl_top = findViewById(R.id.rl_top);

        initView();
        setDialogAttribute();  //设置dialog位于屏幕的位置
    }

    /**
     * 加载布局
     */
    private void getLayoutId() {
        setContentView(R.layout.t_dialog_bottom_list);
    }

    /**
     * 设置dialog一些属性
     */
    private void setDialogAttribute(){
        mWindow = this.getWindow();
        //设置dialog从底部弹出
        mWindow.setGravity(Gravity.BOTTOM);
        //去除系统自带的margin（注：去掉下边距） 设置背景为透明
        mWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //设置dialog在界面长宽(注：高度在android 10必须这样设置，使用wrap_content会有问题)
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        int maxHeight = windowManager.getDefaultDisplay().getHeight();
        mWindow.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        //点击外部消失  注: true 为消失   false 为不消失
        setCanceledOnTouchOutside(true);
        //设置dialog进入和退出的动画
        mWindow.setWindowAnimations(R.style.BottomAnim_Tao);
    }

    /**
     * 初始化组件
     */
    private void initView() {
        //设置RecyclerView管理器
        bottom_recycler.setLayoutManager(new LinearLayoutManager(mContext));
        //初始化适配器
        mAdapter = new BottomRecyclerAdapter(mList);
        //设置添加或删除item时的动画，这里使用默认动画
        bottom_recycler.setItemAnimator(new DefaultItemAnimator());
        //设置适配器
        bottom_recycler.setAdapter(mAdapter);
    }

    /**********************提供给外部一些接口************************/
    public BottomRecyclerAdapter getAdapter() {
        return mAdapter;
    }

    /**
     * 增加数据
     * @param str
     */
    public void addData(String str) {
        mAdapter.addData(str);
    }

    public void addData(int position, String str) {
        mAdapter.addData(position, str);
    }

    /**
     * 移除数据
     * @param position
     */
    public void removeData(int position) {
        if (mAdapter.removeData(position) == 0) {
            dismiss();
        }
    }


    /**
     * 设置最大高度限制（优化：适配android 10）
     * @param maxHeightRatio
     */
    public void setMaxHeight(final double maxHeightRatio) {
        //调用之前必须初始化设置
        final WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        int maxHeight = (int) (windowManager.getDefaultDisplay().getHeight() * maxHeightRatio);
        mWindow.setLayout(WindowManager.LayoutParams.MATCH_PARENT, maxHeight);
        /**
         * dialog当前高度要将所有的子view高度叠加起来得到，最重要的是RecyclerView高度动态变化，其子view可以准确测量起变化
         * 故各个子view叠加测出的高度，是准确实时变化的高度。
         * 如果使用final View view = this.getWindow().getDecorView()其view获取整体dialog高度，当RecyclerView减少到界限以下，
         * 此方法view测量出dialog高度仍然是限制的最大高度，即设置了最大高度后，该方法测量的高度将一直是限制的高度。
         * **/
        final View view = mWindow.getDecorView();
        view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                //重要：获取整个dialog当前高度，是各个子view叠加（relativeLayout高度 + recyclerView高度 ），不能直接使用回调里的“v”
                int currentHeight = bottom_recycler.getMeasuredHeight() + rl_top.getMeasuredHeight();
                int maxHeight = (int) (windowManager.getDefaultDisplay().getHeight() * maxHeightRatio);//最大高度为屏幕的maxHeightRatio倍
                if (currentHeight < maxHeight) {
                    mWindow.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                }else{
                    mWindow.setLayout(WindowManager.LayoutParams.MATCH_PARENT,  maxHeight);
                }
            }
        });

    }



}
