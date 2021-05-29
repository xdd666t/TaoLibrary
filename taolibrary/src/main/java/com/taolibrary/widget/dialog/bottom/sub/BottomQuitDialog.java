package com.taolibrary.widget.dialog.bottom.sub;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.cardview.widget.CardView;


import com.taolibrary.R;
import com.taolibrary.interfaces.dialog.IDialog;

/**
 * Author 余涛
 * Description 功能说明
 * Time 2019/6/16 22:56
 */
public class BottomQuitDialog extends Dialog {

    /**
     * 只提供修改内容
     * @param content
     * @param onBottomQuitListener
     */
    public void bottomQuitDialog(String content, final IDialog.OnBottomQuitListener onBottomQuitListener) {
        this.tv_content.setText(content);
        this.cd_quit_affirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBottomQuitListener.onClick(true);
                dismiss();
            }
        });
        this.cd_quit_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBottomQuitListener.onClick(false);
                dismiss();
            }
        });
        show();
    }

    /**
     * 提供标题 内容 确认按钮  取消按钮的修改
     * @param title
     * @param content
     * @param affirmButton
     * @param cancelButton
     * @param onBottomQuitListener
     */
    public void bottomQuitDialog(String title, String content, String affirmButton, String cancelButton, final IDialog.OnBottomQuitListener onBottomQuitListener) {
        this.tv_title.setText(title);
        this.tv_content.setText(content);
        this.tv_quit_affirm.setText(affirmButton);
        this.tv_quit_cancel.setText(cancelButton);
        cd_quit_affirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBottomQuitListener.onClick(true);
                dismiss();
            }
        });
        cd_quit_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBottomQuitListener.onClick(false);
                dismiss();
            }
        });
        show();
    }


    /*************************************分割线,上面为调用方法,下为dialog设置********************************/
    private Context context;

    public TextView tv_title;
    public TextView tv_content;
    //确认
    public CardView cd_quit_affirm;
    public TextView tv_quit_affirm;
    //取消
    public CardView cd_quit_cancel;
    public TextView tv_quit_cancel;

    public BottomQuitDialog(Context context) {
        super(context, R.style.DialogBottom_Tao);
        initData(context);
    }

    public BottomQuitDialog(Context context, int themeResId) {
        super(context, themeResId);
        initData(context);
    }

    /**
     * 一些必备的实例数据,都放在此处
     * @param context
     */
    private void initData(Context context) {
        this.context = context;

        getLayoutId();   //设置布局
        setViewLocation();  //设置dialog位于屏幕的位置
        initView();
    }

    /**
     * 加载布局
     */
    private void getLayoutId() {
        setContentView(R.layout.t_dialog_bottom_quit);
    }

    /**
     * 设置dialog位于屏幕底部
     */
    private void setViewLocation(){
        Window window = this.getWindow();
        //设置dialog从底部弹出
        window.setGravity(Gravity.BOTTOM);
        //去除系统自带的margin（注：去掉下边距）
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //设置dialog在界面中的属性(注：去掉左右边距)
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        //设置dialog进入和退出的动画
        window.setWindowAnimations(R.style.BottomAnim_Tao);
    }

    /**
     * 初始化UI控件(绑定)
     */
    private void initView() {
        tv_title = findViewById(R.id.bottom_quit_title);
        tv_content = findViewById(R.id.bottom_quit_content);
        //确认
        cd_quit_affirm = findViewById(R.id.cd_quit_affirm);
        tv_quit_affirm = findViewById(R.id.tv_quit_affirm);
        //取消
        cd_quit_cancel = findViewById(R.id.cd_quit_cancel);
        tv_quit_cancel = findViewById(R.id.tv_quit_cancel);


        setCanceledOnTouchOutside(false);   //点击外部不消失  注: true 为消失   false 为不消失
        setParam();  //设置基础参数 大小 粗细之类
    }

    /**
     * 设置基础参数
     */
    @SuppressLint("ResourceAsColor")
    private void setParam() {
        //设置字体类型  常规类型,粗体
        tv_title.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tv_title.setTextSize(18);
        tv_title.setText("温馨提示");

        tv_content.setTextSize(15);
        tv_content.setText("是否退出软件，请确认操作！");

        tv_quit_affirm.setText("确认");
        cd_quit_affirm.setCardElevation(0);  //取消阴影

        tv_quit_cancel.setText("取消");
        cd_quit_cancel.setCardElevation(0);  //取消阴影
    }


}

