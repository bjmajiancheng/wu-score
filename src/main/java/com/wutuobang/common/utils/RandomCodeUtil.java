package com.wutuobang.common.utils;

import com.wutuobang.common.constant.CommonConstant;
import com.wutuobang.common.message.SmsUtil;

import java.util.Random;

/**
 * Created by majiancheng on 2018/7/15.
 */
public class RandomCodeUtil {

    /**
     * 随机生成数字
     *
     * @param codeSize
     * @return
     */
    public static String generRandomCode(int codeSize) {
        String codes = "1234567890";
        StringBuffer sb = new StringBuffer(codeSize);
        for (int i = 0; i < codeSize; i++) {
            char c = codes.charAt(new Random().nextInt(codes.length()));
            sb.append(c);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        try {
            SmsUtil.send("13717689765", String.format("系统提示：您的验证码为：%s，有效期为5分钟，请勿向他人提供收到的信息。", generRandomCode(4)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
