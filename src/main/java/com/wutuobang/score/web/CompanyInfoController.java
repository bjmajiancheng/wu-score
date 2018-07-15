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
import com.wutuobang.common.client.ShardJedisClient;
import com.wutuobang.common.constant.CommonConstant;
import com.wutuobang.common.message.SmsUtil;
import com.wutuobang.common.utils.RandomCodeUtil;
import com.wutuobang.common.utils.RedisUtil;
import com.wutuobang.common.utils.ResultParam;
import com.wutuobang.common.utils.UuidUtil;
import com.wutuobang.score.constant.CacheConstant;
import com.wutuobang.shiro.utils.ShiroUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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

    @Autowired
    private ShardJedisClient jedisClient;

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyInfoController.class);

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
            companyInfoModel.setCreateTime(new Date());
            if (StringUtils.isEmpty(companyInfoModel.getOperatorAddress())) {
                companyInfoModel.setOperatorAddress(StringUtils.EMPTY);
            }

            companyInfoService.insert(companyInfoModel);

            return ResultParam.SUCCESS_RESULT;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultParam.SYSTEM_ERROR_RESULT;
        }
    }

    /**
     * 公司信息修改页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/companyEdit.html")
    public String companyEdit(HttpServletRequest request) {
        return "company/companyEdit.html";
    }

    /**
     * 获取当前账户信息
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getCurrCompanyInfo", method = RequestMethod.GET)
    public ResultParam getCurrCompanyInfo(HttpServletRequest request) {
        Integer userId = ShiroUtils.getUserId();

        CompanyInfoModel currCompany = companyInfoService.getById(userId);
        if (currCompany != null) {
            currCompany.setPassword("******");
        }

        return new ResultParam(ResultParam.SUCCESS_RESULT, currCompany);
    }

    /**
     * 用人单位修改
     *
     * @param request
     * @param companyInfo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/companyEdit", method = RequestMethod.POST)
    public ResultParam companyEdit(HttpServletRequest request, @RequestParam("companyInfo") String companyInfo,
            @RequestParam("captcha") String captcha) {
        if (StringUtils.isEmpty(companyInfo)) {
            return ResultParam.PARAM_ERROR_RESULT;
        }

        String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
        if (!captcha.equalsIgnoreCase(kaptcha)) {
            return ResultParam.CAPTCHA_ERROR_RESULT;
        }

        try {
            CompanyInfoModel companyInfoModel = JSON.parseObject(companyInfo, CompanyInfoModel.class);

            companyInfoService.update(companyInfoModel);
            return ResultParam.SUCCESS_RESULT;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultParam.SYSTEM_ERROR_RESULT;
        }
    }

    /**
     * 忘记密码操作(发送验证码)
     *
     * @param request
     * @param userName 用户名
     * @param captcha  验证码
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/retrievePasswd", method = RequestMethod.POST)
    public ResultParam retrievePasswd(HttpServletRequest request, @RequestParam("userName") String userName,
            @RequestParam("captcha") String captcha) {
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(captcha)) {
            return ResultParam.PARAM_ERROR_RESULT;
        }

        String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
        if (!captcha.equalsIgnoreCase(kaptcha)) {
            return ResultParam.CAPTCHA_ERROR_RESULT;
        }

        try {
            List<CompanyInfoModel> companyInfos = companyInfoService
                    .find(Collections.singletonMap("userName", (Object) userName));
            if (CollectionUtils.isEmpty(companyInfos)) {
                return ResultParam.error("用户名未找到, 请重新输入用户名。");
            }

            CompanyInfoModel companyInfo = companyInfos.get(0);

            String randomCode = RandomCodeUtil.generRandomCode(4);

            jedisClient.setex(String.format(CacheConstant.RETRIEVE_PASSWD_CACHE_KEY, userName), randomCode, 5 * 60);
            //发送短信
            SmsUtil.send(companyInfo.getOperatorMobile(),
                    String.format("系统提示：您的验证码为：%s，有效期为5分钟，请勿向他人提供收到的信息。", randomCode));

            LOGGER.info("用户验证码:{}", randomCode);

            return ResultParam.ok("验证码发送成功!!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultParam.SYSTEM_ERROR_RESULT;
        }
    }

    /**
     * 验证短信验证码
     *
     * @param request
     * @param userName 用户名
     * @param msgCode  短信验证码
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/validMsgCode", method = RequestMethod.POST)
    public ResultParam validMsgCode(HttpServletRequest request, @RequestParam("userName") String userName,
            @RequestParam("msgCode") String msgCode) {
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(msgCode)) {
            return ResultParam.PARAM_ERROR_RESULT;
        }

        try {
            String randomCode = jedisClient.get(String.format(CacheConstant.RETRIEVE_PASSWD_CACHE_KEY, userName));
            if (randomCode == null) {
                return ResultParam.error("短信验证码已失效, 请重新发送。");
            }

            if (!msgCode.equals(randomCode)) {
                return ResultParam.error("短信验证码不正确, 请重新输入。");
            }

            String token = UuidUtil.generateUuid();
            jedisClient.setex(String.format(CacheConstant.EDIT_PASSWD_TOKEN_CACHE_KEY, userName), token, 60 * 60);

            return new ResultParam(ResultParam.SUCCESS_RESULT, token);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultParam.SYSTEM_ERROR_RESULT;
        }
    }

    /**
     * 修改密码
     *
     * @param request
     * @param userName 用户名
     * @param password 修改后密码
     * @param token    token标识
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/editPassword", method = RequestMethod.POST)
    public ResultParam editPassword(HttpServletRequest request, @RequestParam("userName") String userName,
            @RequestParam("password") String password, @RequestParam("token") String token) {
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            return ResultParam.error("用户名或密码不能为空。");
        }

        try {
            Object obj = jedisClient.get(String.format(CacheConstant.EDIT_PASSWD_TOKEN_CACHE_KEY, userName));
            if (obj == null) {
                return ResultParam.error("用户验证失败, 请重新操作找回密码。");
            }

            if (!token.equals(String.valueOf(obj))) {
                return ResultParam.error("用户验证失败, 请重新操作找回密码。");
            }

            List<CompanyInfoModel> companyInfoModels = companyInfoService
                    .find(Collections.singletonMap("userName", (Object) userName));
            if (CollectionUtils.isEmpty(companyInfoModels)) {
                return ResultParam.error("用户未找到, 请重新输入用户名。");
            }

            CompanyInfoModel companyInfoModel = companyInfoModels.get(0);

            CompanyInfoModel updateCompanyInfo = new CompanyInfoModel();
            updateCompanyInfo.setId(companyInfoModel.getId());
            updateCompanyInfo.setPassword(new Sha256Hash(password).toHex());

            companyInfoService.update(updateCompanyInfo);

            return ResultParam.ok("密码修改成功, 请使用新密码登录。");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultParam.SYSTEM_ERROR_RESULT;
        }
    }

}
