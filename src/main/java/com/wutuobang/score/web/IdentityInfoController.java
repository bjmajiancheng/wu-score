package com.wutuobang.score.web;

import com.alibaba.fastjson.JSON;
import com.google.code.kaptcha.Constants;
import com.wutuobang.common.utils.PageData;
import com.wutuobang.common.utils.ResultParam;
import com.wutuobang.score.constant.Constant;
import com.wutuobang.score.model.*;
import com.wutuobang.score.service.*;
import com.wutuobang.score.view.EvaluationView;
import com.wutuobang.score.view.IndicatorView;
import com.wutuobang.shiro.utils.ShiroUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by majiancheng on 2018/3/30.
 */
@Controller
@RequestMapping(value = "/identityInfo")
public class IdentityInfoController {

    @Autowired
    private IIdentityInfoService identityInfoService;

    @Autowired
    private IHouseMoveService houseMoveService;

    @Autowired
    private IHouseRelationshipService houseRelationshipService;

    @Autowired
    private IHouseOtherService houseOtherService;

    @Autowired
    private IHouseProfessionService houseProfessionService;

    @Autowired
    private IRegionService regionService;

    @Autowired
    private IBatchConfService batchConfService;

    @Autowired
    private IIndicatorService indicatorService;

    @Autowired
    private IIndicatorItemService indicatorItemService;

    @Autowired
    private IPersonBatchScoreResultService personBatchScoreResultService;

    @Autowired
    private IAcceptAddressService acceptAddressService;

    @Autowired
    private IAcceptDateConfService acceptDateConfService;

    @Autowired
    private IPersonBatchStatusRecordService personBatchStatusRecordService;

    @Autowired
    private IDictService dictService;

    /**
     * 前往新增用户页面
     *
     * @return
     */
    @RequestMapping(value = "/applicationAdd.html", method = RequestMethod.GET)
    public String toAddIdentityInfo() {
        return "application/applicationAdd.html";
    }

    /**
     * 添加申请人信息
     *
     * @param request
     * @param identityInfoJson
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addIdentityInfo", method = RequestMethod.POST)
    public ResultParam addIdentityInfo(HttpServletRequest request,
            @RequestParam("identityInfoJson") String identityInfoJson, @RequestParam("captcha") String captcha) {
        if (StringUtils.isEmpty(identityInfoJson)) {
            return ResultParam.PARAM_ERROR_RESULT;
        }

        String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
        if (!captcha.equalsIgnoreCase(kaptcha)) {
            return ResultParam.CAPTCHA_ERROR_RESULT;
        }

        try {

            CompanyInfoModel currUser = ShiroUtils.getUserEntity();

            //获取当前批次信息
            BatchConfModel batchConfModel = batchConfService.getBatchInfoByDate(new Date());
            if (batchConfModel == null) {
                return ResultParam.error("当前没有落户批次信息,请根据落户指标时间填写申请人!!");
            }
            //申请人信息
            IdentityInfoModel identityInfoModel = JSON.parseObject(identityInfoJson, IdentityInfoModel.class);
            if (identityInfoModel != null) {
                identityInfoModel.setBatchId(batchConfModel.getId());
                identityInfoModel.setCompanyId(currUser.getId());
                //初始化状态信息
                identityInfoModel.setReservationStatus(Constant.reservationStatus_1);//申请预约状态
                identityInfoModel.setHallStatus(Constant.hallStatus_0);//预约大厅状态
                identityInfoModel.setUnionApproveStatus1(Constant.unionApproveStatus1_0);//公安预审状态
                identityInfoModel.setUnionApproveStatus2(Constant.unionApproveStatus2_0);//人社预审状态
                identityInfoModel.setPoliceApproveStatus(Constant.policeApproveStatus_1);//公安前置预审状态
                identityInfoModel.setRensheAcceptStatus(Constant.rensheAcceptStatus_1);//人社受理状态
                identityInfoModel.setAcceptNumber(StringUtils.EMPTY);
                identityInfoModel.setAcceptAddressId(0);
                identityInfoModel.setAcceptAddress(StringUtils.EMPTY);
                identityInfoModel.setReservationM(0);

                if (identityInfoModel.getHouseMoveModel() != null) {
                    Integer region = identityInfoModel.getHouseMoveModel().getRegion();
                    identityInfoModel.setRegion(region);

                    RegionModel regionModel = regionService.getById(region);
                    if (regionModel != null) {
                        identityInfoModel.setRegionName(regionModel.getName());
                    }
                }
                identityInfoService.insert(identityInfoModel);

                //记录状态日志信息
                DictModel dictModel = dictService
                        .findByAliasAndValue("reservationStatus", Constant.reservationStatus_1);
                if (dictModel != null) {
                    PersonBatchStatusRecordModel recordModel = new PersonBatchStatusRecordModel(identityInfoModel,
                            dictModel, "添加申请人信息成功");
                    personBatchStatusRecordService.insert(recordModel);
                }
            }

            //户籍迁移信息
            HouseMoveModel houseMoveModel = identityInfoModel.getHouseMoveModel();
            if (houseMoveModel != null) {
                houseMoveModel.setIdentityInfoId(identityInfoModel.getId());
                houseMoveService.insert(houseMoveModel);
            }

            //申请人家庭关系
            List<HouseRelationshipModel> houseRelationshipModels = identityInfoModel.getHouseRelationshipModelList();
            if (CollectionUtils.isNotEmpty(houseRelationshipModels)) {
                for (HouseRelationshipModel houseRelationship : houseRelationshipModels) {
                    houseRelationship.setIdentityInfoId(identityInfoModel.getId());
                }
                houseRelationshipService.batchInsert(houseRelationshipModels);
            }

            //申请人其他信息
            HouseOtherModel houseOtherModel = identityInfoModel.getHouseOtherModel();
            if (houseOtherModel != null) {
                houseOtherModel.setIdentityInfoId(identityInfoModel.getId());
                houseOtherService.insert(houseOtherModel);
            }

            //职业资格证书
            HouseProfessionModel houseProfessionModel = identityInfoModel.getHouseProfessionModel();
            if (houseProfessionModel != null) {
                houseProfessionModel.setIdentityInfoId(identityInfoModel.getId());
                houseProfessionService.insert(houseProfessionModel);
            }

            return ResultParam.SUCCESS_RESULT;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultParam.SYSTEM_ERROR_RESULT;
        }
    }

    /**
     * 获取申请人列表信息
     *
     * @param request
     * @param queryStr
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResultParam list(HttpServletRequest request, @RequestParam("queryStr") String queryStr,
            @RequestParam("pageNo") Integer pageNo) {

        try {

            CompanyInfoModel currUser = ShiroUtils.getUserEntity();
            if (currUser == null) {
                return ResultParam.LOGIN_ERROR_RESULT;
            }
            BatchConfModel batchConfModel = batchConfService.getBatchInfoByDate(new Date());
            if (batchConfModel == null) {
                return new ResultParam(ResultParam.SUCCESS_RESULT, Collections.emptyList());
            }

            PageData<IdentityInfoModel> pageData = identityInfoService
                    .findPage(currUser, batchConfModel.getId(), queryStr, pageNo);

            return new ResultParam(ResultParam.SUCCESS_RESULT, pageData);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultParam.SYSTEM_ERROR_RESULT;
        }
    }

    /**
     * 自助测评
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/autoEvaluation/{id}", method = RequestMethod.GET)
    public ModelAndView autoEvaluation(HttpServletRequest request, @PathVariable("id") Integer id) {
        if (id == null) {
            return new ModelAndView("500", "result", ResultParam.PARAM_ERROR_RESULT);
        }

        try {
            CompanyInfoModel currUser = ShiroUtils.getUserEntity();
            IdentityInfoModel identityInfo = identityInfoService.getById(id);
            initIdentityInfoAttrs(identityInfo);

            if ((int) currUser.getId() != identityInfo.getCompanyId()) {
                return new ModelAndView("500", "result", ResultParam.error("自动测评数据异常, 请稍后重试"));
            }

            List<IndicatorModel> indicatorModels = indicatorService.getAllIndicators();
            indicatorService.initIndicatorView(identityInfo, indicatorModels);

            ModelAndView mv = new ModelAndView("evaluation/autoEvaluation.html");

            mv.addObject("indicatorModels", indicatorModels);
            mv.addObject("identityInfo", identityInfo);
            return mv;
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("500", "result", ResultParam.SYSTEM_ERROR_RESULT);
        }
    }

    /**
     * 自助评测信息
     *
     * @param request
     * @param evaluationViewStr
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/autoEvaluation", method = RequestMethod.POST)
    public ResultParam autoEvaluation(HttpServletRequest request,
            @RequestParam("evaluationView") String evaluationViewStr) {
        if (StringUtils.isEmpty(evaluationViewStr)) {
            return ResultParam.PARAM_ERROR_RESULT;
        }

        Date currDate = new Date();

        /**
         * 1.年龄,2.受教育程度,3.专业技术、职业技能水平,4.社会保险,5.住房公积金,6.住房,7.在津连续居住年限,8.职业（工种）,
         * 9.落户地区,10.纳税,11.婚姻情况,12.知识产权,13.奖项和荣誉称号,14.退役军人,15.守法诚信
         **/

        try {
            EvaluationView evaluationView = JSON.parseObject(evaluationViewStr, EvaluationView.class);
            Integer identityInfoId = evaluationView.getIdentityInfoId();
            IdentityInfoModel identityInfoModel = identityInfoService.getById(identityInfoId);
            initIdentityInfoAttrs(identityInfoModel);

            List<PersonBatchScoreResultModel> toAddScoreResults = new ArrayList<PersonBatchScoreResultModel>();
            //1.年龄
            PersonBatchScoreResultModel ageResult = new PersonBatchScoreResultModel(1,
                    evaluationView.getAgeItemScore());
            toAddScoreResults.add(ageResult);
            //2.受教育程度
            PersonBatchScoreResultModel educationResult = new PersonBatchScoreResultModel(2,
                    evaluationView.getEducationItemScore());
            toAddScoreResults.add(educationResult);
            //3.专业技术职业技能
            PersonBatchScoreResultModel skillResult = new PersonBatchScoreResultModel(3,
                    evaluationView.getSkillItemScore());
            toAddScoreResults.add(skillResult);
            //4.社会保险
            //5.住房公积金
            //6.住房
            //7.在津连续居住年限
            //8.职业（工种)
            PersonBatchScoreResultModel workTypeResult = new PersonBatchScoreResultModel(8,
                    evaluationView.getWorkTypeItemScore());
            toAddScoreResults.add(workTypeResult);
            //9.落户地区
            //10.纳税
            //11.婚姻情况
            //12.知识产权
            //13.奖项和荣誉称号
            PersonBatchScoreResultModel awardsResult = new PersonBatchScoreResultModel(13,
                    evaluationView.getAwardsItemScore());
            toAddScoreResults.add(awardsResult);
            //14.退役军人
            PersonBatchScoreResultModel soldierResult = new PersonBatchScoreResultModel(14,
                    evaluationView.getSoldierItemScore());
            toAddScoreResults.add(soldierResult);
            //15.守法诚信

            //初始化分数结果信息
            if (CollectionUtils.isNotEmpty(toAddScoreResults)) {
                for (PersonBatchScoreResultModel scoreResult : toAddScoreResults) {
                    scoreResult.setBatchId(identityInfoModel.getBatchId());
                    scoreResult.setPersonId(identityInfoModel.getId());
                    scoreResult.setPersonName(identityInfoModel.getName());
                    scoreResult.setPersonIdNum(identityInfoModel.getIdNumber());
                    scoreResult.setCtime(currDate);
                }

                personBatchScoreResultService.batchInsert(toAddScoreResults);
            }

            //自助测评通过
            if (true) {
                IdentityInfoModel updateIdentityInfo = new IdentityInfoModel();
                updateIdentityInfo.setId(identityInfoId);
                updateIdentityInfo.setReservationStatus(Constant.reservationStatus_6);
                identityInfoService.update(updateIdentityInfo);
                //记录状态日志信息
                DictModel dictModel = dictService
                        .findByAliasAndValue("reservationStatus", Constant.reservationStatus_6);
                if (dictModel != null) {
                    PersonBatchStatusRecordModel recordModel = new PersonBatchStatusRecordModel(identityInfoModel,
                            dictModel, "自助测评通过");
                    personBatchStatusRecordService.insert(recordModel);
                }
            }

            return ResultParam.SUCCESS_RESULT;
        } catch (Exception e) {
            e.printStackTrace();

            return ResultParam.SYSTEM_ERROR_RESULT;
        }

    }

    /**
     * 预约地点
     *
     * @param request
     * @param acceptAddressId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/reserveLocation", method = RequestMethod.POST)
    public ResultParam reserveLocation(HttpServletRequest request,
            @RequestParam("acceptAddressId") Integer acceptAddressId,
            @RequestParam("identityInfoId") Integer identityInfoId, @RequestParam("captcha") String captcha) {
        if (acceptAddressId == null || identityInfoId == null) {
            return ResultParam.PARAM_ERROR_RESULT;
        }

        String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
        if (!captcha.equalsIgnoreCase(kaptcha)) {
            return ResultParam.CAPTCHA_ERROR_RESULT;
        }

        try {
            AcceptAddressModel acceptAddressModel = acceptAddressService.getById(acceptAddressId);
            if (acceptAddressModel == null) {
                return ResultParam.error("受理地点选择异常,请重新选择");
            }

            IdentityInfoModel identityInfoModel = identityInfoService.getById(identityInfoId);

            //更新预约地点
            IdentityInfoModel updateIdentityInfo = new IdentityInfoModel();
            updateIdentityInfo.setId(identityInfoId);
            updateIdentityInfo.setAcceptAddressId(acceptAddressId);
            updateIdentityInfo.setAcceptAddress(acceptAddressModel.getAddress());
            updateIdentityInfo.setReservationStatus(Constant.reservationStatus_8);
            updateIdentityInfo.setUnionApproveStatus1(Constant.unionApproveStatus1_1);
            updateIdentityInfo.setUnionApproveStatus2(Constant.unionApproveStatus2_1);

            identityInfoService.update(updateIdentityInfo);

            List<Integer> values = new ArrayList<Integer>();
            values.add(Constant.reservationStatus_7);
            values.add(Constant.reservationStatus_8);
            //记录状态日志信息
            Map<Integer, DictModel> dictMap = dictService.findByAliasAndValues("reservationStatus", values);
            if (MapUtils.isNotEmpty(dictMap)) {
                PersonBatchStatusRecordModel recordModel = new PersonBatchStatusRecordModel(identityInfoModel,
                        dictMap.get(Constant.reservationStatus_7), "预约地点成功");
                PersonBatchStatusRecordModel recordModel1 = new PersonBatchStatusRecordModel(identityInfoModel,
                        dictMap.get(Constant.reservationStatus_8), "变更联合预审状态");
                List<PersonBatchStatusRecordModel> recordModels = new ArrayList<PersonBatchStatusRecordModel>(2);
                recordModels.add(recordModel);
                recordModels.add(recordModel1);

                personBatchStatusRecordService.batchInsert(recordModels);
            }

            return ResultParam.SUCCESS_RESULT;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultParam.SYSTEM_ERROR_RESULT;
        }
    }

    /**
     * 预约时间
     *
     * @param request
     * @param reservaionDateId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/reserveDate", method = RequestMethod.POST)
    public ResultParam reserveDate(HttpServletRequest request,
            @RequestParam("reservaionDateId") Integer reservaionDateId,
            @RequestParam("identityInfoId") Integer identityInfoId, @RequestParam("reservaionM") Integer reservaionM,
            @RequestParam("captcha") String captcha) {
        if (reservaionDateId == null || identityInfoId == null) {
            return ResultParam.PARAM_ERROR_RESULT;
        }

        String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
        if (!captcha.equalsIgnoreCase(kaptcha)) {
            return ResultParam.CAPTCHA_ERROR_RESULT;
        }

        try {
            AcceptDateConfModel acceptDateConf = acceptDateConfService.getById(reservaionDateId);

            IdentityInfoModel identityInfoModel = identityInfoService.getById(identityInfoId);

            if (acceptDateConf == null) {
                return ResultParam.error("受理时间选择异常,请重新选择");
            }

            int count = 0;
            //更新预约名额
            if (reservaionM == 1) {
                if (acceptDateConf.getAmRemainingCount() <= 0) {
                    return ResultParam.error("预约失败。名额已被占用,请选择其他日期!!");
                }
                count = acceptDateConfService.amSubtractionOne(reservaionDateId);
            } else if (reservaionM == 2) {
                if (acceptDateConf.getPmRemainingCount() <= 0) {
                    return ResultParam.error("预约失败。名额已被占用,请选择其他日期!!");
                }
                count = acceptDateConfService.pmSubtractionOne(reservaionDateId);
            }

            if (count == 0) {
                return ResultParam.error("预约失败, 请重新预约!!");
            }

            //更新预约地点
            IdentityInfoModel updateIdentityInfo = new IdentityInfoModel();
            updateIdentityInfo.setId(identityInfoId);
            updateIdentityInfo.setReservationDate(acceptDateConf.getAcceptDate());
            updateIdentityInfo.setReservationM(reservaionM);
            updateIdentityInfo.setReservationStatus(Constant.reservationStatus_11);
            String acceptNumber = identityInfoService.generAcceptNumber(identityInfoModel);
            updateIdentityInfo.setAcceptNumber(acceptNumber);
            updateIdentityInfo.setPoliceApproveStatus(Constant.policeApproveStatus_1);

            identityInfoService.update(updateIdentityInfo);

            //记录状态日志信息
            DictModel dictModel = dictService.findByAliasAndValue("reservationStatus", Constant.reservationStatus_11);
            if (dictModel != null) {
                PersonBatchStatusRecordModel recordModel = new PersonBatchStatusRecordModel(identityInfoModel,
                        dictModel, "添加申请人信息成功");
                personBatchStatusRecordService.insert(recordModel);
            }

            return ResultParam.SUCCESS_RESULT;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultParam.SYSTEM_ERROR_RESULT;
        }
    }

    /**
     * 初始化申请人关联子表信息信息
     *
     * @param identityInfo
     */
    private void initIdentityInfoAttrs(IdentityInfoModel identityInfo) {
        if (identityInfo == null) {
            return;
        }
        Integer id = identityInfo.getId();
        identityInfo.setHouseMoveModel(houseMoveService.getByIdentityInfoId(id));
        identityInfo.setHouseRelationshipModelList(houseRelationshipService.getByIdentityInfoId(id));
        identityInfo.setHouseOtherModel(houseOtherService.getByIdentityInfoId(id));
        identityInfo.setHouseProfessionModel(houseProfessionService.getByIdentityInfoId(id));
    }
}
