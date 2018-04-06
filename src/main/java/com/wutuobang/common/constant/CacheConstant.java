package com.wutuobang.common.constant;

/**
 * Created by majiancheng on 2018/4/6.
 */
public class CacheConstant {

    /**
     * redis过期时间,以秒为单位
     */
    // 一小时
    public final static int EXRP_HOUR = 60 * 60;

    // 一天
    public final static int EXRP_DAY = 60 * 60 * 24;

    // 一个月
    public final static int EXRP_MONTH = 60 * 60 * 24 * 30;

    //当前登录账户key
    public final static String CURR_USER_CACHE_KEY = "CURR_USER:%s";

}
