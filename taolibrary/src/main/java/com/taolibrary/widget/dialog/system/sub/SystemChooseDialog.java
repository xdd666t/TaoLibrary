package com.taolibrary.widget.dialog.system.sub;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.taolibrary.R;
import com.taolibrary.interfaces.dialog.IDialog;

/**
 * Author 余涛
 * Description 一些基础
 * Time 2019/6/14 18:24
 */
public class SystemChooseDialog {

    private Context context;
    private AlertDialog alertDialog;

    public SystemChooseDialog(Context context) {
        this.context = context;
    }

    public void baseDialog(String title, String message, String positiveButtonName, String negativeButtonName,
                           final IDialog.OnBaseDialogHighClick onBaseDialogHighClick) {
         alertDialog = new AlertDialog.Builder(context, R.style.Dialog_Tao)
                .setTitle(title).setMessage(message)
                .setPositiveButton(positiveButtonName, new DialogInterface.OnClickListener() {//添加"Yes"按钮
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        onBaseDialogHighClick.positiveOnClick();
                    }
                })
                .setNegativeButton(negativeButtonName, new DialogInterface.OnClickListener() {//添加取消
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        onBaseDialogHighClick.negativeOnClick();
                    }
                }).create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }


    public void baseDialog(String title, String message, final IDialog.OnBaseDialogClick onBaseDialogClick) {
         alertDialog = new AlertDialog.Builder(context, R.style.DialogRound_Tao)
                .setTitle(title).setMessage(message)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加"Yes"按钮
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        onBaseDialogClick.positiveOnClick();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {//添加取消
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        //点击取消,不进行操作
                    }
                }).create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }

    public AlertDialog setBaseDialog() {
        if (alertDialog == null) alertDialog = new AlertDialog.Builder(context).create();
        return alertDialog;
    }

}
