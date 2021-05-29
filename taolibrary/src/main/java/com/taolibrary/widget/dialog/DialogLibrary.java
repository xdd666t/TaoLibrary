package com.taolibrary.widget.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;

import com.taolibrary.R;
import com.taolibrary.widget.dialog.system.sub.SystemChooseDialog;
import com.taolibrary.widget.dialog.bottom.sub.BottomChooseDialog;
import com.taolibrary.widget.dialog.bottom.sub.BottomListDialog;
import com.taolibrary.widget.dialog.bottom.sub.BottomQuitDialog;
import com.taolibrary.widget.dialog.loading.sub.WaitDialog;
import com.taolibrary.widget.dialog.middle.sub.CommonDialog;
import com.taolibrary.widget.dialog.prompt.sub.PromptMsgDialog;
import com.taolibrary.interfaces.dialog.IDialog;

import java.util.List;

/**
 * Author 余涛
 * Description Dialog功能库接口实现
 * Time 2018/12/12 16:42
 */
public class DialogLibrary implements IDialog {
    private Context mContext;
    private WaitDialog mWaitDialog;   //等待Diaolog
    private CommonDialog mCommonDialog;  //通用dialog
    private PromptMsgDialog mPromptDialog;  //提示Dialog
    private SystemChooseDialog mBaseDialog;     //基础Dialog
    private BottomChooseDialog mBottomDialog; //底部Dialog
    private BottomQuitDialog mBottomQuitDialog; //底部退出Dialog
    private BottomListDialog mBottomListDialog; //底部列表Dialog

    private static DialogLibrary install = null;

    public static DialogLibrary getInstance(Context context) {
        synchronized(DialogLibrary.class) {
            if (install == null) {
                install = new DialogLibrary(context);
            }
        }

        return install;
    }


    public DialogLibrary(Context mContext){
        this.mContext = mContext;
    }

    /**
     * 初始化对象
     * 使用时才初始化,避免提前实例化全部对象,消耗性能
     */
    private void initObject(String name) {
        if ("wait".equals(name) && mWaitDialog == null)
            mWaitDialog = new WaitDialog(mContext);  //等待弹窗
        if ("common".equals(name) && mCommonDialog == null)
            mCommonDialog = new CommonDialog(mContext, R.style.Dialog_Tao);  //通用弹窗
        if ("prompt".equals(name) && mPromptDialog == null)
            mPromptDialog = new PromptMsgDialog(mContext, R.style.Dialog_Tao);  //提示弹窗
        if ("base".equals(name) && mBaseDialog == null)
            mBaseDialog = new SystemChooseDialog(mContext);      //基础Dialog
        if ("bottom".equals(name) && mBottomDialog == null)
            mBottomDialog = new BottomChooseDialog(mContext);  //底部Dialog
        if ("bottomQuit".equals(name) && mBottomQuitDialog == null)
            mBottomQuitDialog = new BottomQuitDialog(mContext);  //底部退出Dialog
        if ("bottomList".equals(name) && mBottomListDialog == null)
            mBottomListDialog = new BottomListDialog(mContext);  //底部退出Dialog
    }


    /**
     * 设置样式
     * @param themeResId
     * @return
     */
    public DialogLibrary setDialogStyle(int themeResId) {
        if (mCommonDialog != null || mPromptDialog != null) {
            mCommonDialog = null;
            mPromptDialog = null;
        }

        if(themeResId == 0){
            mCommonDialog = new CommonDialog(mContext, R.style.Dialog_Tao);
            mPromptDialog = new PromptMsgDialog(mContext, R.style.Dialog_Tao);
        }else{
            mCommonDialog = new CommonDialog(mContext, themeResId);
            mPromptDialog = new PromptMsgDialog(mContext, themeResId);
        }
        return this;
    }

    /*****************************调用Github上高级自定义等待动画----开始*********************************/
    @Override
    public void waitDialog( String message) {
        initObject("wait"); //取消类型,移除第三方库
        mWaitDialog.waitDialog(message, "");
    }

    @Override
    public void waitDialog(String message, int cartoonStyle) {
        initObject("wait");
        mWaitDialog.waitDialog(message, "");
    }

    @Override
    public void waitDialog(String message, int cartoonStyle, String background) {
        initObject("wait");
        mWaitDialog.waitDialog(message, background);
    }

    @Override
    public void closeWaitDialog() {
        mWaitDialog.closeWaitDialog();
    }
    /*****************************调用Github上高级自定义等待动画----结束*********************************/


    /*****************************基础对话控件----开始*********************************/
    @Override
    public AlertDialog getBaseDialog() {
        initObject("base");
        return mBaseDialog.setBaseDialog();
    }

    @Override
    public void baseDialog(String title, String message, String positiveButtonName, String negativeButtonName,
                           final OnBaseDialogHighClick onBaseDialogHighClick) {
        initObject("base");
        mBaseDialog.baseDialog(title, message, positiveButtonName, negativeButtonName, onBaseDialogHighClick);
    }

    @Override
    public void baseDialog(String title, String message, final OnBaseDialogClick onBaseDialogClick) {
        initObject("base");
        mBaseDialog.baseDialog(title, message, onBaseDialogClick);
    }
    /*****************************基础对话控件----结束*********************************/

    /*****************************简单提示控件----开始*********************************/
    @Override
    public PromptMsgDialog getPromptDialog() {
        initObject("prompt");
        return mPromptDialog;
    }

    @Override
    public void promptDialog(String title, String titleColor, String content, String contentColor, String buttonName,
                             String buttonColor, final PromptMsgDialog.OnPromptListener onPromptListener) {
        initObject("prompt");
        mPromptDialog.promptDialog(title, titleColor, content, contentColor, buttonName, buttonColor, onPromptListener);
    }

    @Override
    public void promptDialog(String title, String content) {
        initObject("prompt");
        mPromptDialog.promptDialog(title, content);
    }

    @Override
    public void promptDialog(String content) {
        initObject("prompt");
        mPromptDialog.promptDialog(content);
    }
    /*****************************简单提示控件----结束*********************************/


    /*****************************通用dialog控件----开始*********************************/
    @Override
    public CommonDialog getCommonDialog() {
        initObject("common");
        return mCommonDialog;
    }

    @Override
    public void commonDialog(String content, final OnCommonDialogClick onCommonDialogClick) {
        initObject("common");
        mCommonDialog.commonDialog(content, onCommonDialogClick);
    }

    @Override
    public void commonDialog(String content, String affirmButtonName, final OnCommonDialogClick onCommonDialogClick) {
        initObject("common");
        mCommonDialog.commonDialog(content, affirmButtonName, onCommonDialogClick);
    }

    @Override
    public void commonDialog(String title, String content, String affirmButtonName, final OnCommonDialogClick onCommonDialogClick) {
        initObject("common");
        mCommonDialog.commonDialog(title, content, affirmButtonName, onCommonDialogClick);
    }

    @Override
    public void commonDialog(String title, String titleColor, String content, String contentColor,
                             String affirmButtonName, String affirmColor, String cancelButtonName,
                             String cancelColor, final OnCommonDialogHighClick onCommonDialogHighClick) {
        initObject("common");
        mCommonDialog.commonDialog(title, titleColor, content, contentColor, affirmButtonName, affirmColor,
                cancelButtonName, cancelColor, onCommonDialogHighClick);
    }

    /*****************************通用dialog控件----结束*********************************/



    /*****************************底部选择控件----开始*********************************/
    @Override
    public Dialog getBottomDialog() {
        initObject("bottom");
        return mBottomDialog.setBottomDialog();
    }

    @Override
    public void bottomDialog(String topButtonName, String topColor, String midButtonName, String midColor,
                             String cancelButtonName, String cancelColor,
                             final OnBottomDialogHighClick onBottomDialogHighClick) {
        initObject("bottom");
        mBottomDialog.bottomDialog(topButtonName, topColor, midButtonName, midColor, cancelButtonName, cancelColor,
                onBottomDialogHighClick);
    }

    @Override
    public void bottomDialog(String topButtonName, String midButtonName,
                             final OnBottomDialogClick onBottomDialogClick) {
        initObject("bottom");
        mBottomDialog.bottomDialog(topButtonName, midButtonName, onBottomDialogClick);
    }
    /*****************************底部选择控件----结束*********************************/


    /*****************************底部选择控件----结束*********************************/
    @Override
    public BottomQuitDialog getBottomQuitDialog() {
        initObject("bottomQuit");
        return mBottomQuitDialog;
    }

    @Override
    public void bottomQuitDialog(String content, OnBottomQuitListener onBottomQuitListener ) {
        initObject("bottomQuit");
        mBottomQuitDialog.bottomQuitDialog(content, onBottomQuitListener);
    }

    @Override
    public void bottomQuitDialog(String title, String content, String affirmButton, String cancelButton, OnBottomQuitListener onBottomQuitListener) {
        initObject("bottomQuit");
        mBottomQuitDialog.bottomQuitDialog(title, content, affirmButton, cancelButton, onBottomQuitListener);
    }


    /*****************************底部选择控件----结束*********************************/

    /*****************************底部选择控件----结束*********************************/
    @Override
    public BottomListDialog getBottomListDialog() {
        initObject("bottomList");
        return mBottomListDialog;
    }

    @Override
    public void bottomListDialog(List<String> list, OnBottomListListener onBottomListListener) {
        initObject("bottomList");
        mBottomListDialog.bottomListDialog(list, onBottomListListener);
    }

    /*****************************底部选择控件----结束*********************************/

}
