package com.wutuobang.score.util;

import org.apache.axis.utils.StringUtils;

/**
 * Created by majiancheng on 2018/7/15.
 */
public class IdNumberReplaceUtil {

    /**
     * 替换身份证号
     *
     * @param idNumber
     * @return
     */
    public static String replaceIdNumber(String idNumber) {
        if (StringUtils.isEmpty(idNumber)) {
            return "";
        }

        char[] charArr = idNumber.toCharArray();
        StringBuffer sb = new StringBuffer();
        int i = 0;

        int minNum = 6;
        int maxNum = 14;
        if (idNumber.length() == 15) {
            maxNum = 12;
        }
        for (char c : charArr) {
            if (i >= minNum && i < maxNum) {
                sb.append('*');
            } else {
                sb.append(c);
            }
            i++;
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(replaceIdNumber("130528901001081"));
    }

}
