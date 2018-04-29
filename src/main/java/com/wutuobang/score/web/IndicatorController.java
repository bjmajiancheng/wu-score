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
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wutuobang.score.model.IndicatorModel;

import java.util.*;

import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author davdian
 * @version 1.0
 * @since 1.0
 */

@Controller
@RequestMapping(value = "/indicator")
public class IndicatorController {

    @Autowired
    private IIndicatorService indicatorService;

    /**
     * 获取所有的指标信息
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAllIndicators", method = RequestMethod.GET)
    public ResultParam getAllIndicators(HttpServletRequest request) {
        try {
            List<IndicatorModel> allIndicators = indicatorService.getAllIndicators();
            if(CollectionUtils.isEmpty(allIndicators)) {
                return new ResultParam(ResultParam.SUCCESS_RESULT, Collections.emptyMap());
            }
            Map<Integer, IndicatorModel> indicatorMap = new HashMap<Integer, IndicatorModel>();
            for(IndicatorModel indicatorModel : allIndicators) {
                indicatorMap.put(indicatorModel.getIndexNum(), indicatorModel);
            }

            return new ResultParam(ResultParam.SUCCESS_RESULT, indicatorMap);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultParam.SYSTEM_ERROR_RESULT;
        }
    }

}
