package com.wutuobang.shiro.utils;

import com.wutuobang.score.model.CompanyInfoModel;
import com.wutuobang.shiro.entity.SysUserEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * Shiro工具类
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月12日 上午9:49:19
 */
public class ShiroUtils {

    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static CompanyInfoModel getUserEntity() {
        return (CompanyInfoModel) SecurityUtils.getSubject().getPrincipal();
    }

    public static Integer getUserId() {
        return getUserEntity().getId();
    }

    public static void setSessionAttribute(Object key, Object value) {
        getSession().setAttribute(key, value);
    }

    public static Object getSessionAttribute(Object key) {
        return getSession().getAttribute(key);
    }

    public static boolean isLogin() {
        return SecurityUtils.getSubject().getPrincipal() != null;
    }

    public static void logout() {
        SecurityUtils.getSubject().logout();
    }

    public static String getKaptcha(String key) {
        String kaptcha = String.valueOf(getSessionAttribute(key));
        getSession().removeAttribute(key);
        return kaptcha;
    }

}
