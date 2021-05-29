package com.demo.widget.activity.dialog;

import android.content.Context;
import android.os.Handler;
import android.widget.TextView;

import com.demo.R;
import com.demo.base.BaseActivity;
import com.demo.databinding.ActivityDialogBinding;
import com.taolibrary.base.single.SingleRvAdapter;
import com.taolibrary.interfaces.dialog.IDialog;
import com.taolibrary.widget.dialog.DialogLibrary;
import com.taolibrary.widget.dialog.prompt.PromptDialog;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import static com.taolibrary.BaseFunction.showToast;

/**
 * Author 余涛
 * Description 功能说明
 * Time 2019/1/26 13:49
 */
public class DialogActivity extends BaseActivity {
    private ActivityDialogBinding mBinding;
    private DialogLibrary mDialogLibrary;
    private Handler mHandler;

    private SingleRvAdapter<String> mSingleRvAdapter;
    private DialogVM mDialogVM;

    @Override
    protected void initInstance(Context context) {
        super.initInstance(this);
    }

    @Override
    protected void initView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_dialog);
        setContentView(mBinding.getRoot());
    }

    @Override
    protected void initData() {
        mDialogLibrary = new DialogLibrary(this);
        mHandler = new Handler();
        mDialogVM = new ViewModelProvider(this).get(DialogVM.class);

        skipFunction();  //跳转到功能模块
    }


    /**
     * 跳转到功能模块
     */
    private void skipFunction() {
        mDialogVM.getList().observe(this, new Observer<List<String>>() {
            @Override public void onChanged(List<String> strings) {
                mSingleRvAdapter.updateData(strings);
            }
        });

        //列表实现
        mBinding.rvDialog.setLayoutManager(new GridLayoutManager(this, 2));
        mSingleRvAdapter = new SingleRvAdapter<String>(R.layout.item_normal) {
            @Override
            protected void onBindDataToView(BaseViewHolder holder, String s, int position) {
                TextView textView = holder.getView(R.id.tv_content);
                textView.setText(mList.get(position));
            }
        };
        mSingleRvAdapter.setOnItemClickListener(new SingleRvAdapter.OnItemClickListener() {
            @Override public void onItemClick(int position) {
                switch (position){
                    case 0:
                        mPrompt();
                        break;
                    case 1:
                        mCommon();
                        break;
                    case 2:
                        mWait();
                        break;
                    case 3:
                        mBase();
                        break;
                    case 4:
                        mBottom();
                        break;
                    case 5:
                        mBottomQuit();
                        break;
                    case 6:
                        mBottomList();
                        break;
                }
            }
        });
        mBinding.rvDialog.setAdapter(mSingleRvAdapter);
    }



    /**
     * 提示dialog
     */
    private void mPrompt() {
//        mDialogLibrary.promptDialog("测试内容");
        PromptDialog promptDialog = new PromptDialog(this);
        promptDialog.showError("111111", false);

    }

    /**
     * 通用dialog
     */
    private void mCommon() {
        mDialogLibrary.commonDialog("测试内容", () -> {

        });
    }

    /**
     * 等待动画
     */
    private void mWait() {
        mDialogLibrary.waitDialog("测试内容");
        mHandler.postDelayed(mRunnable, 3*1000); //延时三秒关闭动画
    }

    Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            mDialogLibrary.closeWaitDialog();
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mHandler.removeCallbacks(mRunnable);
    }

    /**
     * 基础dialog调用
     */
    private void mBase() {
        mDialogLibrary.getBaseDialog().setCanceledOnTouchOutside(false);
        mDialogLibrary.baseDialog("测试标题", "测试内容", new IDialog.OnBaseDialogClick() {
            @Override
            public void positiveOnClick() {
            }
        });
    }


    /**
     * 底部dialog调用
     */
    private void mBottom() {
        mDialogLibrary.bottomDialog("测试一", "测试二", new IDialog.OnBottomDialogClick() {
            @Override
            public void onTopButtonClick() {
            }

            @Override
            public void onMidButonClick() {
            }
        });
    }

    /**
     * 底部退出dialog调用
     */
    private void mBottomQuit() {
        mDialogLibrary.getBottomQuitDialog().setCancelable(false);
        mDialogLibrary.bottomQuitDialog("是否要退出软件，请进行确认！", new IDialog.OnBottomQuitListener() {
            @Override
            public void onClick(boolean isAffirm) {
                if (isAffirm) showToast("巴拉");
            }
        });

    }

    /**
     * 底部列表
     */
    int i = 0;
    List<String> list;
    private void mBottomList() {
        list = new ArrayList<>();
        for (int j = 0; j < 3; j++) {
            list.add("增加一条数据" );
        }

//        mDialogLibrary.getBottomListDialog().setMaxHeight(0.9);
        mDialogLibrary.bottomListDialog(list, (position, isLongClick) -> {
            if (isLongClick){
                showToast("增加一条数据");
                mDialogLibrary.getBottomListDialog().addData(1,"增加一条数据");
            }

            if (!isLongClick) {
                showToast("删除一条数据");
                mDialogLibrary.getBottomListDialog().removeData(position);
            }
        });
    }

}
