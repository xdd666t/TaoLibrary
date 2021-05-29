package com.taolibrary.widget.dialog.loading.sub;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.taolibrary.R;
import com.taolibrary.base.BaseDialog;


/**
 * Author 余涛
 * Description 功能说明
 * Time 2019/6/14 17:57
 */
public class WaitDialog extends BaseDialog {
    public LinearLayout llLoading;
    public ProgressBar pbLoading;
    public TextView tvLoading;

    private static WaitDialog instance;
    public static WaitDialog getInstance(Context context) {
        if (instance == null) {
            synchronized (WaitDialog.class) {
                if (instance == null) {
                    synchronized (WaitDialog.class) {
                        instance = new WaitDialog(context);
                    }
                }
            }
        }
        return instance;
    }

    public WaitDialog(Context context) {
        super(context, R.style.DialogNotDim_Tao);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.t_dialog_loading);

        llLoading = findViewById(R.id.ll_loading);
        pbLoading = findViewById(R.id.pb_loading);
        tvLoading = findViewById(R.id.tv_loading);
    }

    @Override
    protected void initData() {
        setCanceledOnTouchOutside(false);
    }

    public void waitDialog(String message, String background) {
        if (!"".equals(background)) {
            llLoading.setBackgroundColor(Color.parseColor(background));
        }
        tvLoading.setText(message);
        show();
    }

    /**
     * 关闭动画
     */
    public void closeWaitDialog() {
        dismiss();
    }


}
