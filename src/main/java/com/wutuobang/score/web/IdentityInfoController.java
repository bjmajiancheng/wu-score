package com.wutuobang.score.web;

import com.wutuobang.score.service.IIdentityInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by majiancheng on 2018/3/30.
 */
@Controller
@RequestMapping(value = "/identityInfo")
public class IdentityInfoController {

    @Autowired
    private IIdentityInfoService identityInfoService;

    /**
     * 前往新增用户页面
     *
     * @return
     */
    @RequestMapping(value = "/toAddIdentityInfo", method = RequestMethod.GET)
    public String toAddIdentityInfo() {
        return "application/applicationAdd.html";
    }
}
