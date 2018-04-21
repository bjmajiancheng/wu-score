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
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.wutuobang.score.model.DictModel;

import java.util.*;

import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author davdian
 * @version 1.0
 * @since 1.0
 */

@Controller
@RequestMapping(value = "/dict")
public class DictController {

    @Autowired
    private IDictService dictService;

    /**
     * 根据字典英文名称获取字典信息
     *
     * @param request
     * @param alias
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getDicts", method = RequestMethod.GET)
    public ResultParam getDicts(HttpServletRequest request, @RequestParam("alias") String alias) {
        if (StringUtils.isEmpty(alias)) {
            return ResultParam.PARAM_ERROR_RESULT;
        }

        try {
            List<DictModel> dictModels = dictService.find(Collections.singletonMap("alias", (Object) alias));

            return new ResultParam(ResultParam.SUCCESS_RESULT, dictModels);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultParam.SYSTEM_ERROR_RESULT;
        }
    }

    /**
     * 获取新增申请人页面所有字典信息
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getApplicationDicts", method = RequestMethod.GET)
    public ResultParam getApplicationDicts(HttpServletRequest request) {
        try {
            List<String> aliasKeys = new ArrayList<String>();
            aliasKeys.add("hyzt");
            aliasKeys.add("professionType");
            aliasKeys.add("jobTitleLevel");
            aliasKeys.add("jobLevel");
            aliasKeys.add("applicantType");

            Map<String, List<DictModel>> dictModelMap = dictService.findMapByAliasKeys(aliasKeys);

            return new ResultParam(ResultParam.SUCCESS_RESULT, dictModelMap);

        } catch (Exception e) {
            e.printStackTrace();
            return ResultParam.SYSTEM_ERROR_RESULT;
        }
    }

}
