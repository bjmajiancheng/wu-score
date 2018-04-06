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

import com.alibaba.fastjson.JSON;
import com.google.code.kaptcha.Constants;
import com.wutuobang.common.utils.ResultParam;
import com.wutuobang.shiro.utils.ShiroUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wutuobang.score.model.CompanyInfoModel;

import java.util.*;

import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author davdian
 * @version 1.0
 * @since 1.0
 */

@Controller
@RequestMapping(value = "/companyInfo")
public class CompanyInfoController {

    @Autowired
    private ICompanyInfoService companyInfoService;

    /**
     * 企业注册信息
     *
     * @param request
     * @param companyInfo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResultParam register(HttpServletRequest request, @RequestParam("companyInfo") String companyInfo,
            @RequestParam("captcha") String captcha) {
        if (StringUtils.isEmpty(companyInfo) || StringUtils.isEmpty(captcha)) {
            return ResultParam.PARAM_ERROR_RESULT;
        }

        String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
        if (!captcha.equalsIgnoreCase(kaptcha)) {
            return ResultParam.CAPTCHA_ERROR_RESULT;
        }

        try {

            CompanyInfoModel companyInfoModel = JSON.parseObject(companyInfo, CompanyInfoModel.class);
            if (companyInfoModel == null) {
                return ResultParam.PARAM_ERROR_RESULT;
            }

            CompanyInfoModel validateModel = companyInfoService.queryByUserName(companyInfoModel.getUserName());
            if (validateModel != null) {
                return ResultParam.error("用户名已注册, 请更换用户名!!!");
            }


            companyInfoModel.setPassword(new Sha256Hash(companyInfoModel.getPassword()).toHex());
            companyInfoModel.setRemark(StringUtils.EMPTY);
            companyInfoModel.setCtime(new Date());
            if(StringUtils.isNotEmpty(companyInfoModel.getOperatorAddress())) {
                companyInfoModel.setOperatorAddress(StringUtils.EMPTY);
            }

            companyInfoService.insert(companyInfoModel);

            return ResultParam.SUCCESS_RESULT;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultParam.SYSTEM_ERROR_RESULT;
        }
    }

}
