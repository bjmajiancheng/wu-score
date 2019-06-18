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
import org.springframework.web.bind.annotation.*;

import com.wutuobang.score.model.RegionModel;

import java.util.*;

import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author davdian
 * @version 1.0
 * @since 1.0
 */

@Controller
@RequestMapping(value = "/region")
public class RegionController {

    @Autowired
    private IRegionService regionService;

    /**
     * 获取子规格信息
     *
     * @param request
     * @param regionId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/child/regions/{regionId}")
    public ResultParam getChildRegions(HttpServletRequest request, @PathVariable("regionId") Integer regionId) {
        if (regionId == null) {
            return ResultParam.PARAM_ERROR_RESULT;
        }

        try {
            List<RegionModel> childRegionModels = regionService.getByParendId(regionId);

            return new ResultParam(ResultParam.SUCCESS_RESULT, childRegionModels);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultParam.SYSTEM_ERROR_RESULT;
        }
    }

}
