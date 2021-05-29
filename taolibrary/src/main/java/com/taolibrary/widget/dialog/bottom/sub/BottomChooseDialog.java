package com.taolibrary.widget.dialog.bottom.sub;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.taolibrary.R;
import com.taolibrary.interfaces.dialog.IDialog;
import com.taolibrary.util.system.SystemUtils;

/**
 * Author 余涛
 * Description 功能说明
 * Time 2019/6/14 18:06
 */
public class BottomChooseDialog {


    private Context context;
    private Dialog dialog;

    public BottomChooseDialog(Context context) {
        this.context = context;
        initData();
    }

    /**
     * 初始化一些数据
     */
    private void initData() {
        dialog = new Dialog(context, R.style.DialogBottom_Tao);
        dialog.setCanceledOnTouchOutside(false); //点击其他部分不消失
    }

    public void bottomDialog(String topButtonName, String topColor, String midButtonName, String midColor,
                             String cancelButtonName, String cancelColor,
                             final IDialog.OnBottomDialogHighClick onBottomDialogHighClick) {
        View view = View.inflate(context, R.layout.t_dialog_bottom_sheet, null);
        dialog.setContentView(view);
        view.setMinimumHeight((int) (SystemUtils.getScreenHeight(context) * 0.23f));
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (SystemUtils.getScreenWidth(context) * 0.9f);
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        dialogWindow.setAttributes(lp);
        dialog.show();
        final TextView topText = (TextView) view.findViewById(R.id.tv_one);
        final TextView midText = (TextView) view.findViewById(R.id.tv_two);
        final Button cancel = (Button) view.findViewById(R.id.bt_cancle);

        topText.setText(topButtonName);
        midText.setText(midButtonName);
        cancel.setText(cancelButtonName);

        topText.setTextColor(Color.parseColor(topColor));
        midText.setTextColor(Color.parseColor(midColor));
        cancel.setTextColor(Color.parseColor(cancelColor));

        /**
         * 顶部按钮
         */
        topText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBottomDialogHighClick.onTopButtonClick();
                dialog.cancel();
            }
        });
        /**
         * 中间按钮
         */
        midText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBottomDialogHighClick.onMidButonClick();
                dialog.cancel();
            }
        });
        /**
         * 取消
         */
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBottomDialogHighClick.onCancelButtonClick();
                dialog.cancel();
            }
        });

    }

    public void bottomDialog(String topButtonName, String midButtonName,
                             final IDialog.OnBottomDialogClick onBottomDialogClick) {
        final Dialog dialog = new Dialog(context, R.style.Dialog_Tao);
        View view = View.inflate(context, R.layout.t_dialog_bottom_sheet, null);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false); //点击其他部分不消失
        view.setMinimumHeight((int) (SystemUtils.getScreenHeight(context) * 0.23f));
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (SystemUtils.getScreenWidth(context) * 0.9f);
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        dialogWindow.setAttributes(lp);
        dialog.show();
        final TextView topText = (TextView) view.findViewById(R.id.tv_one);
        final TextView midText = (TextView) view.findViewById(R.id.tv_two);
        final Button cancel = (Button) view.findViewById(R.id.bt_cancle);

        topText.setText(topButtonName);
        midText.setText(midButtonName);

        /**
         * 顶部按钮
         */
        topText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBottomDialogClick.onTopButtonClick();
                dialog.cancel();
            }
        });
        /**
         * 中间按钮
         */
        midText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBottomDialogClick.onMidButonClick();
                dialog.cancel();
            }
        });
        /**
         * 取消
         */
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

    }

    public Dialog setBottomDialog() {
        return dialog;
    }

}
