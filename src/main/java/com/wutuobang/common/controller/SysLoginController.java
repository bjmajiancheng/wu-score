package com.wutuobang.common.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.wutuobang.common.utils.ResultParam;
import com.wutuobang.score.model.SystemNoticeModel;
import com.wutuobang.score.service.ISystemNoticeService;
import com.wutuobang.shiro.utils.ShiroUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 登录相关
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月10日 下午1:15:31
 */
@Controller
public class SysLoginController {

    @Autowired
    private Producer producer;

    @Autowired
    private ISystemNoticeService systemNoticeService;

    @RequestMapping("/captcha.jpg")
    public void captcha(HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存到shiro session
        ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
    }

    /**
     * 登录
     */
    @ResponseBody
    @RequestMapping(value = "/sys/login", method = RequestMethod.POST)
    public ResultParam login(String username, String password, String captcha) throws IOException {

        /*
            2018年10月30日17:00关闭单位注册、网上预审功能
             */
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String strTime = "2018-10-31 17:00";
        Date date2 = null;
        try {
            date2 = sdf.parse(strTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date2.getTime()<System.currentTimeMillis()){
            return ResultParam.error("2018年第二期居住证积分受理阶段已经关闭。积分结果将在12月公布，具体时间请关注网站通知。");
        }

        String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
        if (!captcha.equalsIgnoreCase(kaptcha)) {
            return ResultParam.error("验证码不正确");
        }

        try {
            Subject subject = ShiroUtils.getSubject();
            //sha256加密
            password = new Sha256Hash(password).toHex();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            subject.login(token);
        } catch (UnknownAccountException e) {
            return ResultParam.error(e.getMessage());
        } catch (IncorrectCredentialsException e) {
            return ResultParam.error(e.getMessage());
        } catch (LockedAccountException e) {
            return ResultParam.error(e.getMessage());
        } catch (AuthenticationException e) {
            return ResultParam.error("账户验证失败");
        }

        return ResultParam.ok();
    }

    /**
     * 退出
     */
    @RequestMapping(value = "/logout.html", method = RequestMethod.GET)
    public String logout() {
        ShiroUtils.logout();
        return "redirect:index.html";
    }

    /**
     * 跳转到主页
     *
     * @return
     */
    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("system/systemNoticeDetail.html");
        /*SystemNoticeModel systemNoticeModel = new SystemNoticeModel();
        systemNoticeModel.setType(3);*/
        SystemNoticeModel systemNoticeModel = systemNoticeService.getLastSystemNotice();
        mv.addObject("systemNotice", systemNoticeModel);
        return mv;
        /*return "redirect:systemNotice/systemNotice.html";*/
    }

    /**
     * 跳转到登录页面
     *
     * @return
     */
    @RequestMapping(value = "/login.html", method = RequestMethod.GET)
    public String login() {
        ShiroUtils.logout();
        return "login.html";
    }

    /**
     * 跳转到欢迎页面
     *
     * @return
     */
    @RequestMapping(value = "/welcome.html", method = RequestMethod.GET)
    public String welcome() {
        return "welcome.html";
    }

}
