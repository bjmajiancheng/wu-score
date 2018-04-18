package com.wutuobang.shiro.realm;

import com.wutuobang.score.model.CompanyInfoModel;
import com.wutuobang.shiro.utils.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * Shiro权限标签(Velocity版)
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月3日 下午11:32:47
 */
public class VelocityShiro {

    /**
     * 是否拥有该权限
     *
     * @param permission 权限标识
     * @return true：是     false：否
     */
    public boolean hasPermission(String permission) {
        Subject subject = SecurityUtils.getSubject();
        return subject != null && subject.isPermitted(permission);
    }

    /**
     * 当前用户是否已登录
     *
     * @return
     */
    public boolean hasLogin() {
        CompanyInfoModel currUser = ShiroUtils.getUserEntity();
        if (currUser == null) {
            return false;
        }

        return true;
    }

}
