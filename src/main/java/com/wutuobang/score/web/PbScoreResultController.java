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
import com.wutuobang.common.utils.DateUtil;
import com.wutuobang.common.utils.PageData;
import com.wutuobang.common.utils.ResultParam;
import com.wutuobang.score.model.BatchConfModel;
import com.wutuobang.score.model.CompanyInfoModel;
import com.wutuobang.score.model.IdentityInfoModel;
import com.wutuobang.score.view.SearchScoreView;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
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

            long startTimestamp = DateUtil.getTheDayZeroTime(batchConfModel.getPublishBegin()).getTime();
            long endTimestamp = DateUtil.getNextDayZeroTime(batchConfModel.getPublishEnd()).getTime();
            if (System.currentTimeMillis() < startTimestamp || System.currentTimeMillis() > endTimestamp) {
                if (StringUtils.isEmpty(searchScoreView.getAcceptNumber()) && StringUtils
                        .isEmpty(searchScoreView.getUserName()) && StringUtils
                        .isEmpty(searchScoreView.getIdCardNumber())) {
                    return new ResultParam(ResultParam.SUCCESS_RESULT, new PageData<IdentityInfoModel>());
                }
            }

            if (batchConfModel.getProcess() != 2) {
                return new ResultParam(ResultParam.SUCCESS_RESULT, new PageData<IdentityInfoModel>());
            }

            Map<String, Object> param = new HashMap<String, Object>();
            if (StringUtils.isNotEmpty(searchScoreView.getAcceptNumber())) {
                param.put("acceptNumber", searchScoreView.getAcceptNumber());
            }
            if (StringUtils.isNotEmpty(searchScoreView.getUserName())) {
                param.put("userName", searchScoreView.getUserName());
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

            BatchConfModel batchconfModel = batchConfService.getById(identityInfo.getBatchId());
            Map<Integer, PbScoreResultModel> pbScoreResultMap = this.findAllMapPassScoreResult(batchconfModel);

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

            mv.addObject("status", pbScoreResultMap.get(identityInfo.getId()) == null ? 0 : 1);

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

    /**
     * 获取名单公示列表数据
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/publicityList")
    public ResultParam publicityListData(HttpServletRequest request, @RequestParam("searchParam") String searchParam) {
        if (StringUtils.isEmpty(searchParam)) {
            return ResultParam.PARAM_ERROR_RESULT;
        }

        Map<String, Object> param = JSON.parseObject(searchParam, Map.class);
        try {
            //获取当前批次信息
            BatchConfModel batchConfModel = batchConfService.getBatchInfoByDate(new Date());

            long startTimeStamp = DateUtil.getTheDayZeroTime(batchConfModel.getNoticeBegin()).getTime();
            long endTimeStamp = DateUtil.getNextDayZeroTime(batchConfModel.getNoticeEnd()).getTime();

            if (System.currentTimeMillis() < startTimeStamp || System.currentTimeMillis() > endTimeStamp) {
                return new ResultParam(ResultParam.SUCCESS_RESULT, new ArrayList<PbScoreResultModel>());
            }

            if (MapUtils.isEmpty(param) || param.get("queryStr") == null || StringUtils
                    .isEmpty((String) param.get("queryStr"))) {
                List<PbScoreResultModel> pbScoreResults = pbScoreResultService.findCurrBatch(batchConfModel.getId());

                return new ResultParam(ResultParam.SUCCESS_RESULT, filterScoreResult(batchConfModel, pbScoreResults));
            }

            String queryStr = (String) param.get("queryStr");
            param.put("queryStr", "%" + queryStr + "%");
            List<IdentityInfoModel> identityInfos = identityInfoService.find(param);
            if (CollectionUtils.isNotEmpty(identityInfos)) {

                List<PbScoreResultModel> finalPbScoreResult = new ArrayList<PbScoreResultModel>();

                Map<Integer, PbScoreResultModel> allMapScoreResult = findAllMapPassScoreResult(batchConfModel);

                for (IdentityInfoModel identityInfo : identityInfos) {
                    if (allMapScoreResult.get(identityInfo.getId()) != null) {
                        finalPbScoreResult.add(allMapScoreResult.get(identityInfo.getId()));
                    }
                }

                return new ResultParam(ResultParam.SUCCESS_RESULT,
                        filterScoreResult(batchConfModel, finalPbScoreResult));
            }

            return new ResultParam(ResultParam.SUCCESS_RESULT, new ArrayList<PbScoreResultModel>());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultParam.SYSTEM_ERROR_RESULT;
        }
    }

    /**
     * 获取所有通过的人员信息
     *
     * @param batchConfModel
     * @return
     */
    public List<PbScoreResultModel> findAllPassScoreResult(BatchConfModel batchConfModel) {
        List<PbScoreResultModel> pbScoreResults = pbScoreResultService.findCurrBatch(batchConfModel.getId());

        return filterScoreResult(batchConfModel, pbScoreResults);
    }

    /**
     * 获取所有通过的人员信息
     *
     * @param batchConfModel
     * @return
     */
    public Map<Integer, PbScoreResultModel> findAllMapPassScoreResult(BatchConfModel batchConfModel) {
        List<PbScoreResultModel> pbScoreResultModels = this.findAllPassScoreResult(batchConfModel);
        Map<Integer, PbScoreResultModel> pbScoreResultMap = new HashMap<Integer, PbScoreResultModel>();
        if (CollectionUtils.isNotEmpty(pbScoreResultModels)) {
            for (PbScoreResultModel pbScoreResultModel : pbScoreResultModels) {
                pbScoreResultMap.put(pbScoreResultModel.getPersonId(), pbScoreResultModel);
            }
        }

        return pbScoreResultMap;
    }

    /**
     * 过滤评分结果
     *
     * @param batchConfModel
     * @param scoreResultModels
     * @return
     */
    public List<PbScoreResultModel> filterScoreResult(BatchConfModel batchConfModel,
            List<PbScoreResultModel> scoreResultModels) {
        if (batchConfModel == null || CollectionUtils.isEmpty(scoreResultModels)) {
            return Collections.emptyList();
        }

        Collections.sort(scoreResultModels, new Comparator<PbScoreResultModel>() {

            @Override
            public int compare(PbScoreResultModel o1, PbScoreResultModel o2) {
                if (o1.getScoreValue() > o2.getScoreValue()) {
                    return -1;
                } else if (o1.getScoreValue() < o2.getScoreValue()) {
                    return 1;
                }
                return 0;
            }
        });

        List<PbScoreResultModel> finalScoreResults = new ArrayList<PbScoreResultModel>();
        if (batchConfModel.getIndicatorType() == 1) {
            for (PbScoreResultModel scoreResultModel : scoreResultModels) {
                if (scoreResultModel.getScoreValue() >= batchConfModel.getIndicatorValue()) {
                    finalScoreResults.add(scoreResultModel);
                }
            }
        } else if (batchConfModel.getIndicatorType() == 0) {
            int maxIndex = batchConfModel.getIndicatorValue();
            if (maxIndex > scoreResultModels.size()) {
                finalScoreResults.addAll(scoreResultModels);
            } else {
                finalScoreResults.addAll(scoreResultModels.subList(0, maxIndex - 1));
            }
        }

        return finalScoreResults;
    }

}
