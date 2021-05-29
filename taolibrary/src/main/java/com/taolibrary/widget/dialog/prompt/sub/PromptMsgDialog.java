package com.taolibrary.widget.dialog.prompt.sub;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.taolibrary.R;

/**
 * Author 余涛
 * Description 功能说明
 * Time 2019/6/14 18:28
 */
public class PromptMsgDialog extends Dialog {

    public void promptDialog(String title, String titleColor, String content, String contentColor, String buttonName,
                             String buttonColor, final OnPromptListener onPromptListenern) {
        this.title = title;
        this.titleColor = titleColor;
        this.content = content;
        this.contentColor = contentColor;
        this.simpleName = buttonName;
        this.simpleNameColor = buttonColor;
        this.onPromptListener = onPromptListenern;
        initData();

        tv_sim_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPromptListener.onAffirm();
                dismiss();
            }
        });

        show();
    }


    public void promptDialog(String title, String content) {
        tv_title.setVisibility(View.VISIBLE); //显示头部

        this.title = title;
        this.titleColor = titleColor;
        this.content = content;
        this.contentColor = contentColor;
        this.simpleName = "知道了";
        this.simpleNameColor = "#008FED";
        initData();

        tv_sim_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        show();
    }

    public void promptDialog(String content) {
        tv_title.setVisibility(View.GONE);  //隐藏头部

        this.title = title;
        this.titleColor = titleColor;
        this.content = content;
        this.contentColor = contentColor;
        this.simpleName = "知道了";
        this.simpleNameColor = "#008FED";
        initData();

        tv_sim_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        show();
    }


    public TextView tv_content;  //内容
    public TextView tv_title;   //标题
    public TextView tv_sim_submit; //"确定"单按钮
    public View v_blank_title;  //标题留白
    public View v_blank_content; //内容标题留白

    private Context mContext;
    private OnPromptListener onPromptListener;
    private String title;
    private String titleColor = "#000000";
    private String content;
    private String contentColor = "#555555";
    private String simpleName;
    private String simpleNameColor = "#008FED";


    public PromptMsgDialog(Context context) {
        super(context, R.style.Dialog_Tao);
        this.mContext = context;

        getLayoutId();
    }
    public PromptMsgDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.mContext = context;

        getLayoutId();
    }

    /**
     * 加载布局
     */
    private void getLayoutId() {
        setContentView(R.layout.t_dialog_prompt);
        setCanceledOnTouchOutside(false);
        initFindView();
    }

    private void initFindView(){
        v_blank_title = findViewById(R.id.white_title);
        v_blank_content = findViewById(R.id.white_content);
        tv_content = findViewById(R.id.content);
        tv_title = findViewById(R.id.title);
        tv_sim_submit = findViewById(R.id.simple_submit);

        tv_content.setMovementMethod(ScrollingMovementMethod.getInstance()); //设置内容可滚动
    }

    private void initData() {
        if(!TextUtils.isEmpty(title)){
            tv_title.setText(title);
            tv_title.setTextColor(Color.parseColor(titleColor));
        }

        if(!TextUtils.isEmpty(content)){
            tv_content.setText(content);
            tv_content.setTextColor(Color.parseColor(contentColor));
        }

        if(!TextUtils.isEmpty(simpleName)){
            tv_sim_submit.setText(simpleName);
            tv_sim_submit.setTextColor(Color.parseColor(simpleNameColor));
        }
    }


    /**
     * 按确定的监听按钮
     */
    public interface OnPromptListener{
        void onAffirm();
    }

}
