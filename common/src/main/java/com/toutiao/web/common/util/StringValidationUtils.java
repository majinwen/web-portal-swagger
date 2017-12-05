package com.toutiao.web.common.util;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * zhangjinglei 2017/9/4 下午6:34
 */
public class StringValidationUtils {
    private static boolean isMatch(String regex, String orginal){
        if (orginal == null || orginal.trim().equals("")) {
            return false;
        }
        orginal=orginal.trim();
        Pattern pattern = Pattern.compile(regex);
        Matcher isNum = pattern.matcher(orginal);
        return isNum.matches();
    }


    /**
     * 是否是整数，不区分正负
     * @param orginal
     * @return
     */

    public static boolean isWholeNumber(String orginal) {
        return isMatch("[+-]{0,1}[1-9]\\d*", orginal) || isMatch("[+-]{0,1}0", orginal);
    }



    /**
     * 是否是 小数，不区分正负
     * @param orginal
     * @return
     */
    public static boolean isDecimal(String orginal){
        return isMatch("[-+]{0,1}0\\.\\d+|[-+]{0,1}[1-9]\\d*\\.\\d+", orginal)|| isMatch("[+-]{0,1}0", orginal);
    }

    /**
     * 是否是数字
     * @param orginal
     * @return
     */
    public static boolean isNumber(String orginal){
        return isWholeNumber(orginal) || isDecimal(orginal);
    }



}
