package com.wutuobang.score.web;

import com.alibaba.fastjson.JSON;
import com.google.code.kaptcha.Constants;
import com.wutuobang.common.utils.PageData;
import com.wutuobang.common.utils.ResultParam;
import com.wutuobang.score.biz.IdentityInfoBiz;
import com.wutuobang.score.constant.Constant;
import com.wutuobang.score.model.*;
import com.wutuobang.score.service.*;
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
    private IAcceptAddressService acceptAddressService;

    @Autowired
    private IAcceptDateConfService acceptDateConfService;

    @Autowired
    private IPersonBatchStatusRecordService personBatchStatusRecordService;

    @Autowired
    private IDictService dictService;

    @Autowired
    private IBasicConfService basicConfService;

    @Autowired
    private IdentityInfoBiz identityInfoBiz;

    @Autowired
    private IIndicatorSelfTestResultService indicatorSelfTestResultService;

    /**
     * 前往新增用户页面
     *
     * @return
     */
    @RequestMapping(value = "/applicationAdd.html", method = RequestMethod.GET)
    public ModelAndView toAddIdentityInfo() {
        ModelAndView mv = new ModelAndView("application/applicationAdd.html");

        mv.addObject("addFlag", true);
        return mv;
    }

    /**
     * 前往申请人列表页面
     *
     * @return
     */
    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public String index() {
        return "index.html";
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

            BasicConfModel basicConfModel = basicConfService.findLastConf();
            if (basicConfModel == null) {
                return ResultParam.error("当前没有基本设置信息, 请联系管理员配置基本设置信息!!");
            }

            //申请人信息
            IdentityInfoModel identityInfoModel = JSON.parseObject(identityInfoJson, IdentityInfoModel.class);

            Map<String, Object> param = new HashMap<String, Object>();
            param.put("batchId", batchConfModel.getId());
            param.put("idNumber", identityInfoModel.getIdNumber());
            List<IdentityInfoModel> identityInfoModels = identityInfoService.find(param);
            if (CollectionUtils.isNotEmpty(identityInfoModels)) {
                return ResultParam.error("本批次申请人身份证号重复, 请填写其他申请人!!");
            }

            identityInfoModel.setAutoTestNum(basicConfModel.getSelfTestLimit());
            boolean addFlag = identityInfoBiz.addIdentityInfo(identityInfoModel, batchConfModel, currUser);

            return ResultParam.SUCCESS_RESULT;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultParam.SYSTEM_ERROR_RESULT;
        }
    }

    /**
     * 更新申请人信息
     *
     * @param request
     * @param identityInfoJson
     * @param captcha
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateIdentityInfo", method = RequestMethod.POST)
    public ResultParam updateIdentityInfo(HttpServletRequest request,
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
                return ResultParam.error("当前没有落户批次信息,请根据落户指标时间再修改申请人!!");
            }

            //申请人信息
            IdentityInfoModel identityInfoModel = JSON.parseObject(identityInfoJson, IdentityInfoModel.class);

            Map<String, Object> param = new HashMap<String, Object>();
            param.put("batchId", batchConfModel.getId());
            param.put("idNumber", identityInfoModel.getIdNumber());
            param.put("notId", identityInfoModel.getId());
            List<IdentityInfoModel> identityInfoModels = identityInfoService.find(param);
            if (CollectionUtils.isNotEmpty(identityInfoModels)) {
                return ResultParam.error("本批次申请人身份证号重复, 请填写其他申请人!!");
            }

            boolean updateFlag = identityInfoBiz.updateIdentityInfo(identityInfoModel, batchConfModel, currUser);

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
    @RequestMapping(value = "/autoEvaluation/{id}.html", method = RequestMethod.GET)
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
     * 自助评测接口
     *
     * @param request
     * @param indicatorViewStr
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/autoEvaluation", method = RequestMethod.POST)
    public ResultParam autoEvaluation(HttpServletRequest request,
            @RequestParam("indicatorView") String indicatorViewStr, @RequestParam("captcha") String captcha) {
        if (StringUtils.isEmpty(indicatorViewStr)) {
            return ResultParam.PARAM_ERROR_RESULT;
        }

        String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
        if (!captcha.equalsIgnoreCase(kaptcha)) {
            return ResultParam.CAPTCHA_ERROR_RESULT;
        }

        //基本设置信息
        BasicConfModel basicConfModel = basicConfService.findLastConf();
        if (basicConfModel == null) {
            return ResultParam.error("请联系管理员配置测评信息!!");
        }

        try {
            CompanyInfoModel currUser = ShiroUtils.getUserEntity();

            IndicatorView indicatorView = JSON.parseObject(indicatorViewStr, IndicatorView.class);

            IdentityInfoModel identityInfoModel = identityInfoService.getById(indicatorView.getIdentityInfoId());
            if (identityInfoModel.getAutoTestNum() == 0) {
                return ResultParam.error("您本期已没有自助评测机会,请下期再进行评测!!");
            }

            //自助评测信息
            boolean evaluationFlag = identityInfoBiz.autoEvaluation(indicatorView, basicConfModel, currUser);
            if (evaluationFlag) {
                return new ResultParam(ResultParam.SUCCESS, "您已通过测评!!");
            } else {
                return new ResultParam(ResultParam.SUCCESS,
                        "您未通过测评, 您还有" + (identityInfoModel.getAutoTestNum() - 1) + "次测评机会!!");
            }
        } catch (Exception e) {
            e.printStackTrace();

            return ResultParam.SYSTEM_ERROR_RESULT;
        }

    }

    /**
     * 查看评测信息
     *
     * @param request
     * @param identityInfoId
     * @return
     */
    @RequestMapping("/evaluationView/{identityInfoId}.html")
    public ModelAndView evaluationView(HttpServletRequest request,
            @PathVariable("identityInfoId") Integer identityInfoId) {
        ModelAndView mv = new ModelAndView("evaluation/evaluationView.html");
        if (identityInfoId == null) {
            return new ModelAndView("500", "result", ResultParam.PARAM_ERROR_RESULT);
        }

        try {

            IdentityInfoModel identityInfo = identityInfoService.getById(identityInfoId);
            initIdentityInfoAttrs(identityInfo);

            Map<Integer, IndicatorSelfTestResultModel> selfTestResultMap = indicatorSelfTestResultService
                    .findMapByIdentityInfoId(identityInfoId);

            List<IndicatorModel> indicatorModels = indicatorService.getAllIndicators();

            //初始化预览自助评测信息
            if (CollectionUtils.isNotEmpty(indicatorModels)) {
                for (IndicatorModel indicatorModel : indicatorModels) {
                    IndicatorSelfTestResultModel selfTestResultModel = selfTestResultMap
                            .get((int) indicatorModel.getId());

                    //文本框值
                    if (selfTestResultModel.getItemId() == 0) {
                        String scoreDetail = selfTestResultModel.getScoreDetail();
                        if (StringUtils.isNotEmpty(scoreDetail)) {
                            indicatorModel.setScoreDetailMap(JSON.parseObject(scoreDetail, Map.class));
                        }
                        continue;
                    }

                    List<IndicatorItemModel> indicatorItems = indicatorModel.getIndicatorItems();
                    if (CollectionUtils.isNotEmpty(indicatorItems)) {
                        for (IndicatorItemModel indicatorItem : indicatorItems) {
                            if ((int) indicatorItem.getId() == selfTestResultModel.getItemId()) {
                                indicatorItem.setChecked(1);
                                indicatorModel.setDisabled(1);
                                break;
                            }
                        }
                    }
                }
            }

            mv.addObject("indicatorModels", indicatorModels);
            mv.addObject("identityInfo", identityInfo);

            return mv;
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("500", "result", ResultParam.PARAM_ERROR_RESULT);
        }
    }

    /**
     * 跳转到修改申请人信息页面
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/editIdentityInfo/{id}.html")
    public ModelAndView editIdentityInfo(HttpServletRequest request, @PathVariable("id") Integer id) {
        if (id == null) {
            return new ModelAndView("500", "result", ResultParam.PARAM_ERROR_RESULT);
        }

        ModelAndView mv = new ModelAndView("application/applicationAdd.html");

        try {

            IdentityInfoModel identityInfo = identityInfoService.getById(id);
            initIdentityInfoAttrs(identityInfo);

            mv.addObject("identityInfo", identityInfo);
            mv.addObject("houseMoveModel", identityInfo.getHouseMoveModel());
            mv.addObject("houseRelationshipList", identityInfo.getHouseRelationshipModelList());
            mv.addObject("houseOtherModel", identityInfo.getHouseOtherModel());
            mv.addObject("houseProfessionModel", identityInfo.getHouseProfessionModel());
            mv.addObject("addFlag", false);
            return mv;
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("500", "result", ResultParam.PARAM_ERROR_RESULT);
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
     * 验证身份证号信息
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/validIdNumber", method = RequestMethod.POST)
    public ResultParam validIdNumber(HttpServletRequest request, @RequestParam("idNumber") String idNumber,
            @RequestParam("identityInfoId") Integer identityInfoId) {
        if (StringUtils.isEmpty(idNumber)) {
            return ResultParam.PARAM_ERROR_RESULT;
        }

        try {
            //获取当前批次信息
            BatchConfModel batchConfModel = batchConfService.getBatchInfoByDate(new Date());

            Map<String, Object> param = new HashMap<String, Object>();
            param.put("idNumber", idNumber);
            param.put("batchId", batchConfModel.getId());
            if (identityInfoId != null) {
                param.put("notId", identityInfoId);
            }
            List<IdentityInfoModel> identityInfos = identityInfoService.find(param);

            return new ResultParam(ResultParam.SUCCESS_RESULT,
                    Collections.singletonMap("validFlag", CollectionUtils.isEmpty(identityInfos)));
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
