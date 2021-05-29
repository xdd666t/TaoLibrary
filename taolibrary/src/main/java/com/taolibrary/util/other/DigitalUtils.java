package com.taolibrary.util.other;

/**
 * 文件作者：余涛
 * 功能描述：数字转换工具
 * 创建时间：2019/11/3 18:38
 */
public class DigitalUtils {

    /**
     * 将阿拉伯数字转换为汉语数字
     * @param str 输入阿拉伯数字 eg:11
     * @return 返回 eg:十一
     */
    public static String toChinese(String str) {
        String[] s1 = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
        String[] s2 = { "十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千" };
        String result = "";
        int n = str.length();
        for (int i = 0; i < n; i++) {
            int num = str.charAt(i) - '0';
            if (i != n - 1 && num != 0) {
                result += s1[num] + s2[n - 2 - i];
            } else {
                result += s1[num];
            }
        }

        //将开头"一十"替换成"十"
        if (result.length() > 2 && result.substring(0, 2).equals("一十")) {
            result = result.replace("一十", "十");
        }

        return result;
    }

}
