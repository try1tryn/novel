package com.rong.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description: 手机号码校验
 * @author: QR
 * @create: 2020-01-03 10:11
 **/
public class PhoneNumJudge {

    /**
     * 检验是否为正确的手机号码
     * @param str
     * @return
     */
    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean isMatch = false;

        // 制定验证条件
        String regex = "^((13[0-9])|(14[579])|(15([0-3,5-9]))|(16[6])|(17[0135678])|(18[0-9]|19[89]))\\d{8}$";

        p = Pattern.compile(regex);
        m = p.matcher(str);
        isMatch = m.matches();
        return isMatch;
    }
}
