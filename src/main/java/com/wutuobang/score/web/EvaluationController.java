package com.wutuobang.score.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by majiancheng on 2018/4/14.
 */
@Controller
@RequestMapping("/evaluation")
public class EvaluationController {

    /**
     * 跳转到自助测评页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value="/autoEvaluation.html")
    public String autoEvaluation(HttpServletRequest request) {
        return "evaluation/autoEvaluation.html";
    }
}
