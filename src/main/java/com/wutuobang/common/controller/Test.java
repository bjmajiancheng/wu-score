package com.wutuobang.common.controller;

import org.apache.shiro.crypto.hash.Sha256Hash;

/**
 * Created by majiancheng on 2018/3/28.
 */
public class Test {
    public static void main(String[] args) {
        String password = "admin";
        password = new Sha256Hash(password).toHex();
        System.out.println(password);
    }

}
