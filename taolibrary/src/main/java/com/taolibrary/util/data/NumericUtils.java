package com.taolibrary.util.data;


import java.math.BigDecimal;

/**
 * 文件作者：余涛
 * 功能描述：描述该文件所实现的功能
 * 创建时间：2019/11/3 18:38
 */
public class NumericUtils {


    /**
     * 是否是数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }


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

        //将"零"去掉
        result = result.replace("零", "");

        return result;
    }


    /**
     * 格式化大数字
     * @param num
     * @return
     */
    public static String formatBigNum(String num) {
        if (StringUtils.isEmpty(num)) {
            // 数据为空直接返回0
            return "0";
        }
        try {
            StringBuffer sb = new StringBuffer();
            if (!isNumeric(num)) {
                // 如果数据不是数字则直接返回0
                return "0";
            }


            BigDecimal b0 = new BigDecimal("1000");
            BigDecimal b1 = new BigDecimal("10000");
            BigDecimal b2 = new BigDecimal("100000000");
            BigDecimal b3 = new BigDecimal(num);

            String formatedNum = "";//输出结果
            String unit = "";//单位

            if (b3.compareTo(b0) == -1) {
                sb.append(b3.toString());
            } else if ((b3.compareTo(b0) == 0 || b3.compareTo(b0) == 1)
                    || b3.compareTo(b1) == -1) {
                formatedNum = b3.divide(b0).toString();
                unit = "k";
            } else if ((b3.compareTo(b1) == 0 && b3.compareTo(b1) == 1)
                    || b3.compareTo(b2) == -1) {
                formatedNum = b3.divide(b1).toString();
                unit = "w";
            } else if (b3.compareTo(b2) == 0 || b3.compareTo(b2) == 1) {
                formatedNum = b3.divide(b2).toString();
                unit = "亿";
            }
            if (!"".equals(formatedNum)) {
                int i = formatedNum.indexOf(".");
                if (i == -1) {
                    sb.append(formatedNum).append(unit);
                } else {
                    i = i + 1;
                    String v = formatedNum.substring(i, i + 1);
                    if (!v.equals("0")) {
                        sb.append(formatedNum.substring(0, i + 1)).append(unit);
                    } else {
                        sb.append(formatedNum.substring(0, i - 1)).append(unit);
                    }
                }
            }
            if (sb.length() == 0)
                return "0";
            return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return num;
        }
    }



}
