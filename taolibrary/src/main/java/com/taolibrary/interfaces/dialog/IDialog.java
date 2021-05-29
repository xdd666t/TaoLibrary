package com.taolibrary.interfaces.dialog;

import android.app.AlertDialog;
import android.app.Dialog;

import com.taolibrary.widget.dialog.DialogLibrary;
import com.taolibrary.widget.dialog.bottom.sub.BottomListDialog;
import com.taolibrary.widget.dialog.bottom.sub.BottomQuitDialog;
import com.taolibrary.widget.dialog.middle.sub.CommonDialog;
import com.taolibrary.widget.dialog.prompt.sub.PromptMsgDialog;

import java.util.List;

/**
 * Author 余涛
 * Description Dialog功能总接口，可直观的浏览功能模块
 * Time 2018/12/12 17:23
 */
public interface IDialog {

    /***************************************全局设置之类*************************************/
    /**
     * 设置commonDialog和promptDialog风格(只限这俩个)
     * @param themeResId
     * @return
     */
    DialogLibrary setDialogStyle(int themeResId);
    /***************************************全局设置之类*************************************/


    /**
     * 重载设置默认值,默认背景透明,字体和动画为白色. 默认动画为:11(多圆旋转)
     * @param message 传入需要等待的信息
     */
    void waitDialog(String message);


    /**
     *  默认背景透明,字体和动画为白色
     * @param message 传入提示用户等待信息
     * @param cartoonStyle 动画类型（0-17）
     */
    void waitDialog(String message, int cartoonStyle );

    /**
     *  高级详细设置等待动画
     * @param content 传入等待信息
     * @param cartoonStyle 动画类型
     * @param background  dialog显示背景
     */
    void waitDialog(String content, int cartoonStyle, String background );

    /**
     * 关闭等待动画
     */
    void closeWaitDialog();


    /**
     * 针对底部Dialog的一些设置
     * @return
     */
    Dialog getBottomDialog();

    /**
     * 底部弹出式，选择dialog
     * @param topButtonName 顶部button名
     * @param midButtonName  中间button名
     * @param onBottomDialogClick 接口监听点击事件
     */
    void bottomDialog(String topButtonName, String midButtonName, final OnBottomDialogClick onBottomDialogClick);

    /**
     *
     * @param topButtonName
     * @param topColor
     * @param midButtonName
     * @param midColor
     * @param cancelButtonName
     * @param cancelColor
     * @param onBottomDialogHighClick
     */
    void bottomDialog(String topButtonName, String topColor, String midButtonName, String midColor,
                      String cancelButtonName, String cancelColor,
                      final OnBottomDialogHighClick onBottomDialogHighClick);

    /**
     * bottomDialog辅助监听接口
     */
    interface OnBottomDialogClick{
        /**
         * 顶部按钮点击事件
         */
        void onTopButtonClick();

        /**
         * 中间按钮点击事件
         */
        void onMidButonClick();
    }

    interface OnBottomDialogHighClick{
        /**
         * 顶部按钮点击事件
         */
        void onTopButtonClick();

        /**
         * 中间按钮点击事件
         */
        void onMidButonClick();

        /**
         * 取消按钮点击事件
         */
        void onCancelButtonClick();
    }


    /**
     * 针对基础dialog的一些设置
     * 注：该方法必须写在调用baseDialog(...)之后才起作用
     * @return
     */
    AlertDialog getBaseDialog();

    /**
     * 重载
     * @param title
     * @param content
     */
    void baseDialog(String title, String content, OnBaseDialogClick onBaseDialogClick);

    interface OnBaseDialogClick{
        /**
         * 确定按钮点击事件
         */
        void positiveOnClick();
    }

    /**
     * 基础dialog
     * @param title 标题
     * @param content 主体信息
     * @param positiveButtonName position按钮名
     * @param negativeButtonName negative按钮名
     * @param onBaseDialogHighClick  监听点击事件
     */
    void baseDialog(String title, String content, String positiveButtonName, String negativeButtonName,
                    final OnBaseDialogHighClick onBaseDialogHighClick );

    interface OnBaseDialogHighClick{
        /**
         * 确定按钮点击事件
         */
        void positiveOnClick();

        /**
         * 取消按钮点击事件
         */
        void negativeOnClick();
    }


    /**
     * 对提示弹框进行一些系统设置
     * 可访问到该类的父类接口，设置setCancelable，setCanceledOnTouchOutside等等
     * @return
     */
    PromptMsgDialog getPromptDialog();

    /**
     * 简单提示dialog
     * @param content 内容
     */
    void promptDialog(String content);

    /**
     * 简单提示dialog
     * @param title 标题
     * @param content 内容
     */
    void promptDialog(String title, String content);

    /**
     * 简单提示dialog
     * @param title 标题
     * @param titleColor 标题颜色
     * @param content  提示信息
     * @param messageColor  提示信息字体颜色
     * @param buttonName 按钮名
     * @param buttonColor 按钮字体颜色
     * @paramOnPromptListener 监听点击按钮接口
     * @return 返回DialogLibrary类型
     */
    void promptDialog(String title, String titleColor,  String content, String messageColor, String buttonName,String buttonColor,
                      final PromptMsgDialog.OnPromptListener onPromptListener);

    interface OnPromptDialogClick{
        /**
         * 按钮点击事件
         */
        void onClick();
    }


    /**
     * 间接设置Dialog一些属性
     * 可访问到该类的父类接口，设置setCancelable，setCanceledOnTouchOutside等等
     * @return
     */
    CommonDialog getCommonDialog();

    /**
     * 通用dialog
     */
    void commonDialog(String content, final OnCommonDialogClick onCommonDialogClick);

    /**
     * 通用dialog(无标题)
     * @param content 展示内容
     * @param affirmButtonName 确认按钮名
     * @param onCommonDialogClick 确认按钮点击事件
     */
    void commonDialog(String content, String affirmButtonName, final OnCommonDialogClick onCommonDialogClick);

    /**
     * 通用高阶dialog
     * @param title  标题
     * @param content 内容
     * @param affirmButtonName 确定按钮名
     * @param onCommonDialogClick 确定按钮监听事件
     */
    void commonDialog(String title,  String content, String affirmButtonName, final OnCommonDialogClick onCommonDialogClick);

    /**
     * 通用超详细dialog
     * @param title 标题
     * @param titleColor 标题颜色
     * @param content 内容
     * @param contentColor 内容颜色
     * @param affirmButtonName 确定按钮名
     * @param affirmColor 确定按钮颜色
     * @param cancelButtonName 取消按钮名
     * @param cancelColor 取消按钮颜色
     * @param onCommonDialogHighClick 确定按钮监听事件
     */
    void commonDialog(String title, String titleColor, String content, String contentColor,
                              String affirmButtonName, String affirmColor, String cancelButtonName, String cancelColor,
                              final OnCommonDialogHighClick onCommonDialogHighClick);

    interface OnCommonDialogClick{

        /**
         * 确认按钮
         */
        void affirmOnClick();
    }

    interface OnCommonDialogHighClick{

        /**
         * 确认按钮
         */
        void affirmOnClick();

        /**
         * 取消按钮
         */
        void cancelOnClick();
    }


    /**
     * 将自定义的BottomQuitDialog实例的对象暴露给用户，提供充分接口使用户diy
     * 用户可以通过该方法调用BottomQuitDialog中的控件，可以设置字体颜色大小样式等等
     * 也可访问到该类的父类接口，设置setCancelable，setCanceledOnTouchOutside等等
     * @return
     */
    BottomQuitDialog getBottomQuitDialog();

    /**
     * 底部退出dialog
     * 只允许传入内容
     * @param content 传入内容
     * @param onBottomQuitListener
     */
    void bottomQuitDialog(String content, OnBottomQuitListener onBottomQuitListener);

    /**
     * 可传入标题 内容 确认按钮 取消按钮 信息
     * @param title
     * @param content
     * @param affirmButton
     * @param cancelButton
     * @param onBottomQuitListener
     */
    void bottomQuitDialog(String title, String content, String affirmButton, String cancelButton, OnBottomQuitListener onBottomQuitListener);

    interface OnBottomQuitListener {
        /**
         * 是否确认  true:代表确认  false:代表取消
         * @param isAffirm
         */
        void onClick(boolean isAffirm);
    }


    /**
     * 通过此方法将BottomListDialog实例暴露给用户
     * 用户可以通过此方法调用到删除,增加数据等方法
     * @return
     */
    BottomListDialog getBottomListDialog();

    /**
     * 底部列表dialog
     * @param list
     * @param onBottomListListener
     */
    void bottomListDialog(List<String> list, OnBottomListListener onBottomListListener);

    interface OnBottomListListener {
        void onClick(int position, boolean isLongClick);
    }


}
