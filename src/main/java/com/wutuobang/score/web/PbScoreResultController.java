/*
 * 北京果敢时代科技有限公司
 * 北京市朝阳区望京SOHO T3 B座1607
 * 邮编：100022
 * 网址：www.davdian.com
 */

package com.wutuobang.score.web;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.wutuobang.common.utils.DateUtil;
import com.wutuobang.common.utils.PageData;
import com.wutuobang.common.utils.ResultParam;
import com.wutuobang.score.model.*;
import com.wutuobang.score.util.IdNumberReplaceUtil;
import com.wutuobang.score.view.SearchScoreView;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private IPbScoreRecordService pbScoreRecordService;

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
//                if (StringUtils.isEmpty(searchScoreView.getAcceptNumber()) && StringUtils.isEmpty(searchScoreView.getIdCardNumber())) {
                    return new ResultParam(ResultParam.SUCCESS_RESULT, new PageData<IdentityInfoModel>());
//                }
            }

            if (batchConfModel.getProcess() != 2) {
                return new ResultParam(ResultParam.SUCCESS_RESULT, new PageData<IdentityInfoModel>());
            }

            /*
            2018年12月6日，“积分查询”页面只能让申请人看到自己的信息，看不到其他人的信息
             */
            if(StringUtils.isEmpty(searchScoreView.getIdCardNumber())){
                return new ResultParam(ResultParam.SUCCESS_RESULT, new PageData<IdentityInfoModel>());
            }

            Map<String, Object> param = new HashMap<String, Object>();
            if (StringUtils.isNotEmpty(searchScoreView.getAcceptNumber())) {
                param.put("acceptNumber", searchScoreView.getAcceptNumber());
            }
            /*if (StringUtils.isNotEmpty(searchScoreView.getUserName())) {
                param.put("userName", searchScoreView.getUserName());
            }*/
            if (StringUtils.isNotEmpty(searchScoreView.getIdCardNumber())) {
                param.put("idNumber", searchScoreView.getIdCardNumber());
            }
            param.put("batchId", batchConfModel.getId());
            PageData<IdentityInfoModel> pageData = identityInfoService.findPage(param, searchScoreView.getPageNo());

            for (IdentityInfoModel identityInfo : pageData.getData()) {
                identityInfo.setIdNumber(IdNumberReplaceUtil.replaceIdNumber(identityInfo.getIdNumber()));
            }

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

            /*
            需要在表t_pb_score_record(类：PbScoreRecordModel)中查询打分项，由于PbScoreResult里面有重复分数，导致结果不准确；
             */
            List<PbScoreRecordModel> pbScoreRecord_3 = new ArrayList<PbScoreRecordModel>();//受教育程度
            List<PbScoreRecordModel> pbScoreRecord_14 = new ArrayList<PbScoreRecordModel>();//婚姻情况
            List<PbScoreRecordModel> pbScoreRecord_other = new ArrayList<PbScoreRecordModel>();//其他16项
            List<PbScoreRecordModel> pbScoreRecords = pbScoreRecordService.getByPersonId(identityInfoId);
            /*
            公布申请人是“打分完成”状态；申请人是取消资格状态的，只公布“取消资格”，不公布其它信息。
             */
            if (pbScoreRecords.size()>0 && identityInfo.getCancelStatus()==0) {

                for (PbScoreRecordModel p : pbScoreRecords){
                    p.setScore_value(p.getScore_value().setScale(2));
                    if (p.getIndicator_id()==3){
                        pbScoreRecord_3.add(p);
                    }
                    if (p.getIndicator_id() == 14){
                        pbScoreRecord_14.add(p);
                    }

                    if (p.getIndicator_id() != 3 && p.getIndicator_id() != 14){
                        pbScoreRecord_other.add(p);
                    }
                }

                /*
                1、受教育程度，只显示1条，人社和市教委，哪个分高显示哪个
                 */
                if (pbScoreRecord_3.get(0).getScore_value().longValue() > pbScoreRecord_3.get(1).getScore_value().longValue()){
                    pbScoreRecord_other.add(pbScoreRecord_3.get(0));
                }else {
                    pbScoreRecord_other.add(pbScoreRecord_3.get(1));
                }
                /*
                2、婚姻情况，只显示1条，人社打分和市民政局打分相加等于20时，显示10分，否则显示0分
                 */
                Long l1 = pbScoreRecord_14.get(0).getScore_value().longValue();
                Long l2 = pbScoreRecord_14.get(1).getScore_value().longValue();
                BigDecimal value_10 = new BigDecimal(10.00);
                BigDecimal value_0 = new BigDecimal(0.00);
                if ((l1+l2)==20.00){
                    pbScoreRecord_14.get(0).setScore_value(value_10.setScale(2));
                }else {
                    pbScoreRecord_14.get(0).setScore_value(value_0.setScale(2));
                }
                pbScoreRecord_other.add(pbScoreRecord_14.get(0));
            }


            mv.addObject("identityInfo", identityInfo);
//            mv.addObject("pbScoreResults", pbScoreResults);
            mv.addObject("pbScoreRecords",pbScoreRecord_other);

//            mv.addObject("status", pbScoreResultMap.get(identityInfo.getId()) == null ? 0 : 1);
            mv.addObject("status", (identityInfo.getHallStatus()==8) ? 0 : 1);

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

            if (MapUtils.isEmpty(param) || param.get("queryStr") == null || StringUtils.isEmpty((String) param.get("queryStr"))) {
                List<PbScoreResultModel> pbScoreResults = pbScoreResultService.findCurrBatch(batchConfModel.getId());

                if (CollectionUtils.isNotEmpty(pbScoreResults)) {
                    for (PbScoreResultModel pbScoreResult : pbScoreResults) {
                        pbScoreResult.setPersonIdNum(IdNumberReplaceUtil.replaceIdNumber(pbScoreResult.getPersonIdNum()));
                    }
                }

                List<PbScoreRecordModel> pbScoreRecordModels = pbScoreRecordService.getPublicList(batchConfModel.getId());

//                return new ResultParam(ResultParam.SUCCESS_RESULT, filterScoreResult(batchConfModel, pbScoreResults));
                return new ResultParam(ResultParam.SUCCESS_RESULT, pbScoreRecordModels);
            }

            String queryStr = (String) param.get("queryStr");
            param.put("queryStr", "%" + queryStr + "%");
            List<IdentityInfoModel> identityInfos = identityInfoService.find(param);
            if (CollectionUtils.isNotEmpty(identityInfos)) {

                List<PbScoreResultModel> finalPbScoreResult = new ArrayList<PbScoreResultModel>();

                Map<Integer, PbScoreResultModel> allMapScoreResult = findAllMapPassScoreResult(batchConfModel);

                for (IdentityInfoModel identityInfo : identityInfos) {
                    PbScoreResultModel tmpScoreResult = allMapScoreResult.get(identityInfo.getId());
                    if (tmpScoreResult != null) {
                        tmpScoreResult
                                .setPersonIdNum(IdNumberReplaceUtil.replaceIdNumber(tmpScoreResult.getPersonIdNum()));

                        finalPbScoreResult.add(tmpScoreResult);
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
     * 获取名单公示列表数据22222222222--重写
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/publicityList2")
    public ResultParam publicityListData2(HttpServletRequest request, @RequestParam("searchParam") String searchParam) {
        if (StringUtils.isEmpty(searchParam)) {
            return ResultParam.PARAM_ERROR_RESULT;
        }

        Map<String, Object> param = JSON.parseObject(searchParam, Map.class);
        try {
            //获取当前批次信息
            BatchConfModel batchConfModel = batchConfService.getBatchInfoByDate(new Date());

            long startTimeStamp = DateUtil.getTheDayZeroTime(batchConfModel.getNoticeBegin()).getTime();
            long endTimeStamp = DateUtil.getNextDayZeroTime(batchConfModel.getNoticeEnd()).getTime();

            //若不在公示时间内，就不显示名单公示的内容0
            if (System.currentTimeMillis() < startTimeStamp || System.currentTimeMillis() > endTimeStamp) {
                return new ResultParam(ResultParam.SUCCESS_RESULT, new PageData<PbScoreRecordModel>());
            }

            //若没输入查询的内容，就分页显示
            if (MapUtils.isEmpty(param) || param.get("queryStr") == null || StringUtils.isEmpty((String) param.get("queryStr"))) {
//                List<PbScoreResultModel> pbScoreResults = pbScoreResultService.findCurrBatch(batchConfModel.getId());
//
//                if (CollectionUtils.isNotEmpty(pbScoreResults)) {
//                    for (PbScoreResultModel pbScoreResult : pbScoreResults) {
//                        pbScoreResult.setPersonIdNum(IdNumberReplaceUtil.replaceIdNumber(pbScoreResult.getPersonIdNum()));
//                    }
//                }
//
//                List<PbScoreRecordModel> pbScoreRecordModels = pbScoreRecordService.getPublicList(batchConfModel.getId());

//                return new ResultParam(ResultParam.SUCCESS_RESULT, filterScoreResult(batchConfModel, pbScoreResults));
//                return new ResultParam(ResultParam.SUCCESS_RESULT, pbScoreRecordModels);

                /*
                获取分页的数据,0：总人数选取；1：分数线选取；
                 */
//                PageData<IdentityInfoModel> pageData = identityInfoService.findPage(param, searchScoreView.getPageNo());
                Integer pageNo = (Integer) param.get("pageNo");
                List<PbScoreRecordModel> pbScoreRePbulicList = pbScoreRecordService.findPublicPage(batchConfModel.getId(), batchConfModel.getIndicatorType() ,
                        batchConfModel.getIndicatorValue(), pageNo);
                PageData<PbScoreRecordModel> pageData2 = new PageData<PbScoreRecordModel>();
                for (PbScoreRecordModel p : pbScoreRePbulicList){
                    p.setPerson_id_num(IdNumberReplaceUtil.replaceIdNumber(p.getPerson_id_num()));
                }
                pageData2.setData(pbScoreRePbulicList);
                int publicPageCount = pbScoreRecordService.findPublicPageCount(batchConfModel);//获得总页数
                pageData2.setRecordsTotal(publicPageCount);
                return new ResultParam(ResultParam.SUCCESS_RESULT,pageData2);

            }

            /*
            2018年12月17日 名单公示
            当查询区域有值传入后台时，传给前台一条数据
             */
            String queryStr = (String) param.get("queryStr");
            param.put("queryStr", "%" + queryStr + "%");
//            List<PbScoreRecordModel> pbScoreRePbulicList = pbScoreRecordService.findPublicPage(batchConfModel.getId(), batchConfModel.getIndicatorType() ,
//                    batchConfModel.getIndicatorValue(), pageNo);

            List<PbScoreRecordModel> pbScoreRecordModel = pbScoreRecordService.findOnePbScoreRecord(queryStr, batchConfModel.getId());
            if (pbScoreRecordModel.size()>0){
                pbScoreRecordModel.get(0).setPerson_id_num(IdNumberReplaceUtil.replaceIdNumber(pbScoreRecordModel.get(0).getPerson_id_num()));
                PageData<PbScoreRecordModel> pageData_one = new PageData<PbScoreRecordModel>();
                pageData_one.setData(pbScoreRecordModel);
                pageData_one.setPageCount(1);
                return new ResultParam(ResultParam.SUCCESS_RESULT,pageData_one);
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
                finalScoreResults.addAll(scoreResultModels.subList(0, maxIndex));
            }
        }

        return finalScoreResults;
    }

}
