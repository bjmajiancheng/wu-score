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

import com.wutuobang.common.utils.ResultParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wutuobang.score.model.OfficeModel;

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
@RequestMapping(value = "/office")
public class OfficeController {

    @Autowired
    private IOfficeService officeService;

    /**
     * 获取机关信息
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getOffices", method = RequestMethod.GET)
    public ResultParam getOffices(HttpServletRequest request, @RequestParam("parentId") Integer parentId) {
        if(parentId == null) {
            return ResultParam.PARAM_ERROR_RESULT;
        }

        try{
            List<OfficeModel> officeModels = officeService.getByParentId(parentId);

            return new ResultParam(ResultParam.SUCCESS_RESULT, officeModels);
        } catch(Exception e) {
            e.printStackTrace();
            return ResultParam.SYSTEM_ERROR_RESULT;
        }

    }

}
