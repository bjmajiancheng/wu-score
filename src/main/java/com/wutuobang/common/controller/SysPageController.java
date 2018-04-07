package com.wutuobang.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by majiancheng on 2018/3/29.
 */
@Controller
public class SysPageController {

    @RequestMapping("/sys/{url}.html")
    public String page(@PathVariable("url") String url){
        return "sys/" + url + ".html";
    }

}
