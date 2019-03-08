/*
 * 北京果敢时代科技有限公司
 * 北京市朝阳区望京SOHO T3 B座1607
 * 邮编：100022
 * 网址：www.davdian.com
 */

package com.wutuobang.score.web;

import java.text.SimpleDateFormat;
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
import com.wutuobang.score.model.BatchConfModel;
import com.wutuobang.shiro.utils.ShiroUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
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

    @Autowired
    private IBatchConfService batchConfService;

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

            /*
            2018年10月30日17:00关闭单位注册、网上预审功能
             */
            /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String strTime = "2018-10-30 17:00";
            Date date2 = sdf.parse(strTime);
            if (date2.getTime()<System.currentTimeMillis()){
                return ResultParam.error("2018年第二期居住证积分受理阶段网上注册、预审已经关闭。积分结果将在12月公布，具体时间请关注网站通知。");
            }*/



            /*
        从表 t_batch_conf 中取字段值，CLOSE_LOGIN_TIME（关闭登录功能时间）、OPEN_LOGIN_TIME（打开登录功能时间）
         */
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("status", 1);//
            List<BatchConfModel> batchConfs = batchConfService.find(params);
            //由于条件为1 的查询结果结果不是预期的一条，所以挑选了一个状态为1 的记录
            Date closeLogintime = new Date();
            Date openLoginTime = new Date();
            boolean flag = true;
            for (BatchConfModel batchConf : batchConfs) {
                if (batchConf.getStatus() == 1) {
                    closeLogintime = batchConf.getCloseLoginTime();
                    openLoginTime = batchConf.getOpenLoginTime();
                    flag = false;
                }
            }
            if (flag) {
                return ResultParam.error("此时间段不受理积分落户，详情请关注重要通知！");
            }
            if (closeLogintime.getTime() < System.currentTimeMillis() && System.currentTimeMillis() < openLoginTime.getTime()) {
                return ResultParam.error("居住证积分受理阶段网上注册已经关闭，详情请关注重要通知！");
            }


            CompanyInfoModel companyInfoModel = JSON.parseObject(companyInfo, CompanyInfoModel.class);
            if (companyInfoModel == null) {
                return ResultParam.PARAM_ERROR_RESULT;
            }

//            if (companyInfoModel.getCompanyName().equals("天津市宏丰昌皮革制品有限公司") || companyInfoModel.getCompanyName().equals("天津思慕轩科技有限公司") ||
//                    companyInfoModel.getCompanyName().equals("天津宝驰汽车维修服务中心") || companyInfoModel.getSocietyCode().equals("911202225961159249")  ||
//                    companyInfoModel.getSocietyCode().equals("91120105MA05J3TH7U")  || companyInfoModel.getSocietyCode().equals("91120103767600685K")  ){
//                return ResultParam.error("因提交虚假材料，根据津发改社会【2018】26号文件精神，取消以后3年内申报资格！");
//            }

            List<CompanyInfoModel> companyInfos = companyInfoService
                    .findByCompanyNameOrCode(companyInfoModel.getCompanyName(), companyInfoModel.getSocietyCode());
            if (CollectionUtils.isNotEmpty(companyInfos)) {
                return ResultParam.error("单位名称或统一社会信用代码已注册,请更换公司名称和统一社会信用代码!!!");
            }

            CompanyInfoModel validateModel = companyInfoService.queryByUserName(companyInfoModel.getUserName());
            if (validateModel != null) {
                return ResultParam.error("用户名已注册, 请更换用户名!!!");
            }

            companyInfoModel.setPassword(new Sha256Hash(companyInfoModel.getPassword()).toHex());
            companyInfoModel.setRemark(StringUtils.EMPTY);
            companyInfoModel.setCreateTime(new Date());
            companyInfoModel.setStatus(0);
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

            CompanyInfoModel currCompany = companyInfoService.getById(ShiroUtils.getUserId());

            if (currCompany != null) {
                String message = "用人单位信息修改成功!";
                Integer status = currCompany.getStatus();

                boolean isUploadbusinessLicense = StringUtils.isEmpty(currCompany.getBusinessLicenseSrc()) && StringUtils.isNotEmpty(companyInfoModel.getBusinessLicenseSrc());//是否修改营业执照

                if (isUploadbusinessLicense) {
                    //修改营业执照信息
                    currCompany.setBusinessLicenseSrc(companyInfoModel.getBusinessLicenseSrc());
                }

                if (status == null || status != 1) {
                    //如果企业信息不修改,不改变字段的值
                    if (!currCompany.isOperatorEquals(companyInfoModel)) {
                        currCompany.setStatus(1);
                    } else if (!isUploadbusinessLicense) {
                        message = "每一期只可以修改一次用人单位信息,请确定有信息修改后保存";
                    }
                } else if (!isUploadbusinessLicense) {
                    message = "每一期只可以修改一次用人单位信息";
                }

                if ("用人单位信息修改成功!".equals(message)) {
                    currCompany.setChangeDate(companyInfoModel);
                    companyInfoService.update(currCompany);
                    companyInfoService.insertCompanyEditRecord(currCompany);
                    return new ResultParam(0, message);
                } else {
                    return new ResultParam(3, message);
                }
            } else {
                return ResultParam.SYSTEM_ERROR_RESULT;
            }

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
