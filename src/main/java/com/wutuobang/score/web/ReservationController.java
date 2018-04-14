package com.wutuobang.score.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by majiancheng on 2018/4/14.
 */
@Controller
@RequestMapping(value = "/reservation")
public class ReservationController {

    /**
     * 预约地点页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/reserveLocation.html")
    public String reserveLocation(HttpServletRequest request) {
        return "reservation/reserveLocation.html";
    }

    /**
     * 预约时间页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/reserveTime.html")
    public String reserveTime(HttpServletRequest request) {
        return "reservation/reserveTime.html";
    }

}
