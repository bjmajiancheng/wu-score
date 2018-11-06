/*
 * 北京果敢时代科技有限公司
 * 北京市朝阳区望京SOHO T3 B座1607
 * 邮编：100022
 * 网址：www.davdian.com
 */

package com.wutuobang.score.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wutuobang.common.utils.PageData;
import com.wutuobang.common.utils.ResultParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wutuobang.score.model.SystemNoticeModel;

import java.util.*;

import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author davdian
 * @version 1.0
 * @since 1.0
 */

@Controller
@RequestMapping(value = "/systemNotice")
public class SystemNoticeController {

    @Autowired
    private ISystemNoticeService systemNoticeService;

    /**
     * 获取相关政策、重要通知或办理指南 列表信息
     *
     * @param request
     * @param type    1、相关政策，2、办理指南；2、重要通知
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResultParam list(HttpServletRequest request, @RequestParam("type") Integer type,
            @RequestParam("pageNo") Integer pageNo) {
        if (type == null) {
            return ResultParam.PARAM_ERROR_RESULT;
        }

        try {
            PageData<SystemNoticeModel> pageData = systemNoticeService.findPage(type, pageNo);

            return new ResultParam(ResultParam.SUCCESS_RESULT, pageData);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultParam.SYSTEM_ERROR_RESULT;
        }
    }

    /**
     * 获取详细信息
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/detailPage", method = RequestMethod.GET)
    public ModelAndView detailPage(HttpServletRequest request, @RequestParam("id") Integer id) {
        if (id == null) {
            return new ModelAndView("500", "result", ResultParam.PARAM_ERROR_RESULT);
        }

        try {
            ModelAndView mv = new ModelAndView("system/systemNoticeDetail.html");

            mv.addObject("systemNotice", systemNoticeService.getById(id));

            return mv;
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("500", "result", ResultParam.PARAM_ERROR_RESULT);
        }
    }

    /**
     * 获取相关政策列表页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/systemPolicy.html")
    public String systemPolicy(HttpServletRequest request) {
        return "system/systemPolicy.html";
    }

    /**
     * 获取办理指南列表页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/guidebook.html")
    public String guidebook(HttpServletRequest request) {
        return "system/guidebook.html";
    }

    /**
     * 跳转到系统通知页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/systemNotice.html")
    public ModelAndView systemNotice(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("system/systemNoticeDetail.html");


        SystemNoticeModel systemNoticeModel = systemNoticeService.getLastSystemNotice();
//        systemNoticeModel = null;
//        if(systemNoticeModel == null) {
//            systemNoticeModel = new SystemNoticeModel();
//            systemNoticeModel.setType(3);
//        }
        mv.addObject("systemNotice", systemNoticeModel);
        return mv;
    }

    /**
     * 获取流程说明页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/liucheng.html")
    public String liucheng(HttpServletRequest request) {
        return "system/liucheng.html";
    }

        @RequestMapping("/peitao.html")
    public String peitao(HttpServletRequest request) {return "system/peitao.html";}

    @RequestMapping("/peitao2.html")
    public String peitao2(HttpServletRequest request) {return "system/peitao2.html";}

    @RequestMapping("/peitao3.html")
    public String peitao3(HttpServletRequest request) {return "system/peitao3.html";}

    @RequestMapping("/peitao4.html")
    public String peitao4(HttpServletRequest request) {return "system/peitao4.html";}



}
