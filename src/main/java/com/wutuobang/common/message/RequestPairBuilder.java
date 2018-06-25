package com.wutuobang.common.message;

/**
 * Created by majiancheng on 2018/6/25.
 */
public class RequestPairBuilder extends RequestPair {

    /**
     * 构建短信请求体
     *
     * @param key
     * @param value
     * @return
     */
    public static RequestPair build(String key, Object value) {
        return new RequestPair(key, value);
    }

}
