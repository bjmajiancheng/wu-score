package com.wutuobang.common.utils;

import java.util.UUID;

/**
 * Created by majiancheng on 2016/12/27.
 */
public class UuidUtil {

    /**
     * 获得一个UUID
     *
     * @return String UUID
     */
    public static String generateUuid()
    {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
