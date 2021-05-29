package com.taolibrary.widget.dialog.middle.sub;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.taolibrary.R;
import com.taolibrary.interfaces.dialog.IDialog;

/**
 * Author 余涛
 * Description 基础通用dialog
 * Time 2019/1/9 16:33
 */
public class CommonDialog extends Dialog{

    public void commonDialog(String content, final IDialog.OnCommonDialogClick onCommonDialogClick) {
        initView();
        tv_title.setVisibility(View.GONE);  //隐藏标题
        this.content = content;
        initData();

        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCommonDialogClick.affirmOnClick();
                dismiss();
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        show();
    }

    public void commonDialog(String content, String affirmButtonName, final IDialog.OnCommonDialogClick onCommonDialogClick) {
        initView();
        tv_title.setVisibility(View.GONE);  //隐藏标题
        this.content = content;
        this.positiveName = affirmButtonName;
        initData();

        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCommonDialogClick.affirmOnClick();
                dismiss();
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        show();
    }

    public void commonDialog(String title, String content, String affirmButtonName, final IDialog.OnCommonDialogClick onCommonDialogClick) {
        initView();
        this.title = title;
        this.content = content;
        this.positiveName = affirmButtonName;
        initData();

        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCommonDialogClick.affirmOnClick();
                dismiss();
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        show();
    }

    public void commonDialog(String title, String titleColor, String content, String contentColor,
                             String affirmButtonName, String affirmColor, String cancelButtonName,
                             String cancelColor, final IDialog.OnCommonDialogHighClick onCommonDialogHighClick) {
        initView();
        this.title = title;
        this.titleColor = titleColor;
        this.content = content;
        this.contentColor = contentColor;
        this.positiveName = affirmButtonName;
        this.positiveNameColor = affirmColor;
        this.negativeName = cancelButtonName;
        this.negativeNameColor = cancelColor;
        initData();

        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCommonDialogHighClick.affirmOnClick();
                dismiss();
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCommonDialogHighClick.cancelOnClick();
                dismiss();
            }
        });
        show();
    }


    public TextView tv_content;  //内容
    public TextView tv_title;    //标题
    public TextView tv_submit;  //确定
    public TextView tv_cancel;  //取消
    public View v_blank_title;    //标题空格
    public View v_blank_content;  //内容空格

    private Context mContext;
    private String title;
    private String titleColor;
    private String content;
    private String contentColor;
    private String positiveName;
    private String positiveNameColor;
    private String negativeName;
    private String negativeNameColor;


    public CommonDialog(Context context) {
        super(context, R.style.Dialog_Tao);
        this.mContext = context;
        getLayoutId();
    }

    public CommonDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.mContext = context;
        getLayoutId();
    }

    /**
     * 加载布局
     */
    private void getLayoutId() {
        setContentView(R.layout.t_dialog_commom);
        setCanceledOnTouchOutside(false);
        initFindView();
    }

    /**
     * 初始化所有参数
     */
    private void initView() {
        title = "提示";
        titleColor = "#000000";
        content = "";
        contentColor = "#555555";
        positiveName = "确定";
        positiveNameColor = "#000000";
        negativeName = "取消";
        negativeNameColor = "#6C6C6C";
        tv_title.setVisibility(View.VISIBLE);   //显示标题标题
        v_blank_title.setVisibility(View.GONE);
        v_blank_content.setVisibility(View.GONE); //隐藏留白
    }

    private void initFindView(){

        v_blank_title = findViewById(R.id.white_title);
        v_blank_content =  findViewById(R.id.white_content);
        tv_content = findViewById(R.id.content);
        tv_title = findViewById(R.id.title);
        tv_submit = findViewById(R.id.submit);

        tv_cancel = findViewById(R.id.cancel);

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

        if(!TextUtils.isEmpty(positiveName)){
            tv_submit.setText(positiveName);
            tv_submit.setTextColor(Color.parseColor(positiveNameColor));
        }

        if(!TextUtils.isEmpty(negativeName)){
            tv_cancel.setText(negativeName);
            tv_cancel.setTextColor(Color.parseColor(negativeNameColor));
        }
    }



    /**
     * 隐藏标题布局
     * @return
     */
    public CommonDialog hideTitle() {
        initView();
        tv_title.setVisibility(View.GONE);   //隐藏标题

        return this;
    }

    /**
     * 隐藏内容布局
     * @return
     */
    public CommonDialog hideContent() {
        initView();

        v_blank_title.setVisibility(View.VISIBLE);
        v_blank_content.setVisibility(View.VISIBLE); //显示留白
        tv_content.setVisibility(View.GONE);   //隐藏内容

        return this;
    }


    public CommonDialog setFinish(){
        initData();
        return this;
    }

}
