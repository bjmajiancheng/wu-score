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
import com.wutuobang.common.utils.PageData;
import com.wutuobang.common.utils.ResultParam;
import com.wutuobang.score.model.BatchConfModel;
import com.wutuobang.score.model.CompanyInfoModel;
import com.wutuobang.score.model.IdentityInfoModel;
import com.wutuobang.score.view.SearchScoreView;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.wutuobang.score.model.PbScoreResultModel;

import java.util.*;

import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author davdian
 * @version 1.0
 * @since 1.0
 */

@Controller
@RequestMapping(value = "/pbScoreResult")
public class PbScoreResultController {

    @Autowired
    private IPbScoreResultService pbScoreResultService;

    @Autowired
    private IIdentityInfoService identityInfoService;

    @Autowired
    private IBatchConfService batchConfService;

    @Autowired
    private ICompanyInfoService companyInfoService;

    /**
     * 跳转到积分查询页面
     *
     * @return
     */
    @RequestMapping("/searchScore.html")
    public String toSearchScoreHtml() {
        return "scoreResult/searchScore.html";
    }

    /**
     * 积分列表页面
     *
     * @param request
     * @param searchParam
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultParam list(HttpServletRequest request, @RequestParam("searchParam") String searchParam) {
        if (StringUtils.isEmpty(searchParam)) {
            return ResultParam.PARAM_ERROR_RESULT;
        }

        try {
            SearchScoreView searchScoreView = JSON.parseObject(searchParam, SearchScoreView.class);
            if (searchScoreView == null) {
                return ResultParam.PARAM_ERROR_RESULT;
            }

            //获取当前批次信息
            BatchConfModel batchConfModel = batchConfService.getBatchInfoByDate(new Date());

            if (batchConfModel.getProcess() != 2) {
                /*return new ResultParam(ResultParam.SUCCESS_RESULT, new PageData<IdentityInfoModel>());*/
            }

            Map<String, Object> param = new HashMap<String, Object>();
            if (StringUtils.isNotEmpty(searchScoreView.getAcceptNumber())) {
                param.put("acceptNumber", searchScoreView.getAcceptNumber());
            }
            if (StringUtils.isNotEmpty(searchScoreView.getUserName())) {
                param.put("userName", "%" + searchScoreView.getUserName() + "%");
            }
            if (StringUtils.isNotEmpty(searchScoreView.getIdCardNumber())) {
                param.put("idNumber", searchScoreView.getIdCardNumber());
            }
            param.put("batchId", batchConfModel.getId());
            PageData<IdentityInfoModel> pageData = identityInfoService.findPage(param, searchScoreView.getPageNo());

            return new ResultParam(ResultParam.SUCCESS_RESULT, pageData);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultParam.SYSTEM_ERROR_RESULT;
        }
    }

    /**
     * 获取申请人id积分详情
     *
     * @param request
     * @param identityInfoId
     * @return
     */
    @RequestMapping(value = "/detail/{identityInfoId}.html", method = RequestMethod.GET)
    public ModelAndView detail(HttpServletRequest request, @PathVariable("identityInfoId") Integer identityInfoId) {
        if (identityInfoId == null) {
            return new ModelAndView("500", "result", ResultParam.PARAM_ERROR_RESULT);
        }
        ModelAndView mv = new ModelAndView("scoreResult/scoreDetail.html");

        try {
            IdentityInfoModel identityInfo = identityInfoService.getById(identityInfoId);
            if (identityInfo == null) {
                return new ModelAndView("500", "result", ResultParam.PARAM_ERROR_RESULT);
            }

            CompanyInfoModel companyInfo = companyInfoService.getById(identityInfo.getCompanyId());
            if (companyInfo != null) {
                identityInfo.setCompanyName(companyInfo.getCompanyName());
            }

            List<PbScoreResultModel> pbScoreResults = pbScoreResultService.getByPersonId(identityInfoId);
            mv.addObject("identityInfo", identityInfo);
            mv.addObject("pbScoreResults", pbScoreResults);

        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("500", "result", ResultParam.PARAM_ERROR_RESULT);
        }
        return mv;
    }

    /**
     * 名单公示列表
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/publicityList.html")
    public String publicityList(HttpServletRequest request) {
        return "scoreResult/publicityList.html";
    }

}
