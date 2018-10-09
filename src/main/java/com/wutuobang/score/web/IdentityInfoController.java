package com.wutuobang.score.web;

import com.alibaba.fastjson.JSON;
import com.google.code.kaptcha.Constants;
import com.wutuobang.common.client.ShardJedisClient;
import com.wutuobang.common.constant.CacheConstant;
import com.wutuobang.common.constant.CommonConstant;
import com.wutuobang.common.message.SmsUtil;
import com.wutuobang.common.utils.DateUtil;
import com.wutuobang.common.utils.PageData;
import com.wutuobang.common.utils.ResultParam;
import com.wutuobang.score.biz.IdentityInfoBiz;
import com.wutuobang.score.constant.Constant;
import com.wutuobang.score.model.*;
import com.wutuobang.score.service.*;
import com.wutuobang.score.util.FreeMarkerUtil;
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
import javax.servlet.http.HttpServletResponse;
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

    @Autowired
    private CommonConstant commonConstant;

    @Autowired
    private ICompanyInfoService companyInfoService;

    @Autowired
    private ShardJedisClient jedisClient;

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

            List<IndicatorModel> finalIndicators = new ArrayList<IndicatorModel>();
            if (CollectionUtils.isNotEmpty(indicatorModels)) {
                for (IndicatorModel indicatorModel : indicatorModels) {
                    if (indicatorModel.getIndexNum() != 16) {
                        finalIndicators.add(indicatorModel);
                    }

                }
            }

            mv.addObject("indicatorModels", finalIndicators);
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

            HouseOtherModel houseOtherModel = houseOtherService.getByIdentityInfoId(indicatorView.getIdentityInfoId());

            if (identityInfoModel.getReservationStatus() == 6) {
                return ResultParam.error("您自助测评已通过, 不能再次测评!!");
            }

            if (identityInfoModel.getAutoTestNum() == 0) {
                return ResultParam.error("您本期已没有自助评测机会,请下期再进行评测!!");
            }

            //自助评测信息
            boolean evaluationFlag = identityInfoBiz.autoEvaluation(indicatorView, basicConfModel, currUser);
            if (evaluationFlag) {
                if (currUser != null && StringUtils.isNotEmpty(currUser.getOperatorMobile())) {
                    SmsUtil.send(currUser.getOperatorMobile(),
                            String.format(commonConstant.autoevaluationpassMessage, currUser.getOperator()));
                }

                if (houseOtherModel != null && StringUtils.isNotEmpty(houseOtherModel.getSelfPhone())) {
                    SmsUtil.send(houseOtherModel.getSelfPhone(),
                            String.format(commonConstant.autoevaluationpassMessage, identityInfoModel.getName()));
                }

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
                    if (selfTestResultModel == null) continue;

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

            List<IndicatorModel> finalIndicators = new ArrayList<IndicatorModel>();
            if (CollectionUtils.isNotEmpty(indicatorModels)) {
                for (IndicatorModel indicatorModel : indicatorModels) {
                    if (indicatorModel.getIndexNum() != 16) {
                        finalIndicators.add(indicatorModel);
                    }
                }
            }

            mv.addObject("indicatorModels", finalIndicators);
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

            //预约地点后, 设置公安预审过期时间、人社预审过期时间
            Date currZeroTime = DateUtil.getTheDayZeroTime(new Date());
            currZeroTime = DateUtil.addDay(currZeroTime, 8);
            updateIdentityInfo.setUnionApprove1Et(currZeroTime);
            updateIdentityInfo.setUnionApprove2Et(currZeroTime);

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
            //获取当前批次信息
            BatchConfModel batchConfModel = batchConfService.getBatchInfoByDate(new Date());

            if (batchConfModel == null) {
                return ResultParam.error("当前没有落户批次信息,请联系官网确认!!");
            }

            AcceptDateConfModel acceptDateConf = acceptDateConfService.getById(reservaionDateId);

            IdentityInfoModel identityInfoModel = identityInfoService.getById(identityInfoId);

            if (acceptDateConf == null) {
                return ResultParam.error("受理时间选择异常,请重新选择");
            }

            if (identityInfoModel.getReservationTime() <= 0) {
                return ResultParam.error("该申请人已预约多次, 本期不可预约!!");
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
            String acceptNumber = identityInfoService.generAcceptNumber(identityInfoModel, batchConfModel);
            updateIdentityInfo.setAcceptNumber(acceptNumber);
            updateIdentityInfo.setPoliceApproveStatus(Constant.policeApproveStatus_1);
            updateIdentityInfo.setReservationTime(identityInfoModel.getReservationTime() - 1);

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
     * 取消预约
     *
     * @param request
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/cancalReservation", method = RequestMethod.POST)
    public ResultParam cancalReservation(HttpServletRequest request, @RequestParam("id") Integer id) {
        if (id == null) {
            return ResultParam.PARAM_ERROR_RESULT;
        }

        try {
            //申请人信息
            IdentityInfoModel identityInfoModel = identityInfoService.getById(id);

            if (identityInfoModel.getReservationDate() == null) {
                return ResultParam.error("申请人未预约,请确认申请人是否已预约!!");
            }

            Date currDate = new Date();

            Date reservationDate = identityInfoModel.getReservationDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(reservationDate);
            if (identityInfoModel.getReservationM() == 1) {//上午
                calendar.set(Calendar.HOUR_OF_DAY, 0);
            } else if (identityInfoModel.getReservationM() == 2) {//下午
                calendar.set(Calendar.HOUR_OF_DAY, 12);
            }
            calendar.add(Calendar.DAY_OF_MONTH, -1);

            IdentityInfoModel updateIdentityInfo = new IdentityInfoModel();
            updateIdentityInfo.setId(id);

            /*
            2018年10月9日修改，xuegr
            已经通过公安窗口前置审核的申请人，如果取消预约，再进行二次预约的，公安窗口不必再次进行前置审核，公安窗口的一切操作状态保留；
            下面if中的判断条件就是此状态时的参数
             */
            if(identityInfoModel.getReservationStatus()==11 && identityInfoModel.getHallStatus()==3 && identityInfoModel.getPoliceApproveStatus()==3
                    && identityInfoModel.getRensheAcceptStatus()==1){
//                updateIdentityInfo.setReservationStatus(Constant.reservationStatus_10);
//                updateIdentityInfo.setAcceptNumber(StringUtils.EMPTY);
                //updateIdentityInfo.setPoliceApproveStatus(Constant.policeApproveStatus_0);
//                updateIdentityInfo.setReservationM(0);
                updateIdentityInfo.setReservationDateNull(1);//设置预约时间为空
                updateIdentityInfo.setReservationTime(identityInfoModel.getReservationTime());
                updateIdentityInfo.setHallStatus(0);
                updateIdentityInfo.setPoliceApproveStatus(1);
                updateIdentityInfo.setRensheAcceptStatus(0);
            } else{
                updateIdentityInfo.setReservationStatus(Constant.reservationStatus_10);
                updateIdentityInfo.setAcceptNumber(StringUtils.EMPTY);
                //updateIdentityInfo.setPoliceApproveStatus(Constant.policeApproveStatus_0);
                updateIdentityInfo.setReservationM(0);
                updateIdentityInfo.setReservationDateNull(1);
                updateIdentityInfo.setReservationTime(identityInfoModel.getReservationTime());
            }
            /*//可预约状态
            boolean reservationFlag = true;
            //预约日期 比 当前日期 小24小时
            if (currDate.compareTo(calendar.getTime()) >= 0) {
                reservationFlag = false;
                updateIdentityInfo.setReservationTime(updateIdentityInfo.getReservationTime() - 1);
            } *//*else {//预约日期 比 当前日期 大24小时
                updateIdentityInfo.setReservationTime(1);
            }*/

            int count = identityInfoService.update(updateIdentityInfo);

            CompanyInfoModel currUser = ShiroUtils.getUserEntity();
            HouseOtherModel houseOtherModel = houseOtherService.getByIdentityInfoId(identityInfoModel.getId());

            if (currUser != null && StringUtils.isNotEmpty(currUser.getOperatorMobile())) {
                SmsUtil.send(currUser.getOperatorMobile(),
                        String.format(commonConstant.addapplicationOperatorMessage, currUser.getOperator()));
            }

            if (houseOtherModel != null && StringUtils.isNotEmpty(houseOtherModel.getSelfPhone())) {
                SmsUtil.send(houseOtherModel.getSelfPhone(),
                        String.format(commonConstant.addapplicationOperatorMessage, identityInfoModel.getName()));
            }

            //记录状态日志信息
            DictModel dictModel = dictService.findByAliasAndValue("reservationStatus", Constant.reservationStatus_12);

            PersonBatchStatusRecordModel recordModel = new PersonBatchStatusRecordModel(identityInfoModel, dictModel,
                    "申请人已取消预约。");
            personBatchStatusRecordService.insert(recordModel);

            return ResultParam.ok("取消预约成功, " + (updateIdentityInfo.getReservationTime() == 1 ?
                    "此申请人本期还可以预约一次!!" :
                    "此申请人本期不可再预约!!"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultParam.SYSTEM_ERROR_RESULT;
        }
    }

    /**
     * 验证自助测评
     *
     * @param request
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/validateAutoTest/{id}", method = RequestMethod.POST)
    public ResultParam vdalidateAutoTest(HttpServletRequest request, @PathVariable("id") Integer id) {
        if (id == null) {
            return ResultParam.PARAM_ERROR_RESULT;
        }

        try {

            IdentityInfoModel identityInfoModel = identityInfoService.getById(id);
            if (identityInfoModel.getReservationStatus() == 6) {
                return ResultParam.error("您自助测评已通过, 不能再次测评!!");
            }

            return ResultParam.SUCCESS_RESULT;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultParam.SYSTEM_ERROR_RESULT;
        }
    }

    /**
     * 打印预约凭证
     *
     * @param request
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/printReservation/{id}.html", method = RequestMethod.GET)
    public ResultParam printReservation(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("id") Integer id) {
        if (id == null) {
            return ResultParam.PARAM_ERROR_RESULT;
        }

        try {
            IdentityInfoModel identityInfo = identityInfoService.getById(id);

            if (identityInfo == null) {
                return ResultParam.PARAM_ERROR_RESULT;
            }

            if (identityInfo.getAcceptNumber() == null) {
                identityInfo.setAcceptNumber("");
            }

            Map<String, Object> data = new HashMap<String, Object>();
            data.put("identityInfo", identityInfo);
            CompanyInfoModel companyInfoModel = companyInfoService.getById(identityInfo.getCompanyId());
            if (companyInfoModel != null) {
                identityInfo.setCompanyName(companyInfoModel.getCompanyName());
            } else {
                identityInfo.setCompanyName("");
            }

            if (StringUtils.isEmpty(identityInfo.getAcceptNumber())) {
                identityInfo.setAcceptNumber(
                        "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
            }

            //自定义属性
            Map<String, Object> customData = new HashMap<String, Object>();
            Date ctime = identityInfo.getCtime();
            if (ctime == null) {
                customData.put("addYear", "");
                customData.put("addMonth", "");
                customData.put("addDay", "");
            } else {
                customData.put("addYear", String.valueOf(DateUtil.getYear(ctime)));
                customData.put("addMonth", DateUtil.getMonth(ctime) + 1);
                customData.put("addDay", DateUtil.getDay(ctime));
            }

            Date reservationDate = identityInfo.getReservationDate();
            if (reservationDate == null) {
                customData.put("reserveYear", "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
                customData.put("reserveMonth", "&nbsp;&nbsp;&nbsp;&nbsp;");
                customData.put("reserveDay", "&nbsp;&nbsp;&nbsp;&nbsp;");
            } else {
                customData.put("reserveYear", String.valueOf(DateUtil.getYear(reservationDate)));
                customData.put("reserveMonth", DateUtil.getMonth(reservationDate) + 1);
                customData.put("reserveDay", DateUtil.getDay(reservationDate));
            }

            Date currDate = new Date();
            customData.put("currYear", String.valueOf(DateUtil.getYear(currDate)));
            customData.put("currMonth", DateUtil.getMonth(currDate) + 1);
            customData.put("currDay", DateUtil.getDay(currDate));

            if(identityInfo.getAcceptAddressId() == null) {
                identityInfo.setAcceptAddressId(0);
            }
            if (identityInfo.getAcceptAddressId() == 1) {
                customData.put("reservLocation", "市级行政许可中心");
            } else if (identityInfo.getAcceptAddressId() == 2) {
                customData.put("reservLocation", "滨海新区行政服务中心");
            } else {
                customData.put("reservLocation", "");
            }

            data.put("customData", customData);
            String content = FreeMarkerUtil.getWriter("website_reservation.ftl", data);

            return new ResultParam(ResultParam.SUCCESS_RESULT, content);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultParam.SYSTEM_ERROR_RESULT;
        }
    }

    /**
     * 获取随迁信息
     *
     * @param request
     * @param response
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getFollowUpInfo/{id}.html", method = RequestMethod.GET)
    public ResultParam getFollowUpInfo(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("id") Integer id) {
        if (id == null) {
            return ResultParam.PARAM_ERROR_RESULT;
        }

        try {
            List<HouseRelationshipModel> houseRelationships = houseRelationshipService.getByIdentityInfoId(id);

            return new ResultParam(ResultParam.SUCCESS_RESULT, houseRelationships);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultParam.SYSTEM_ERROR_RESULT;
        }
    }

    /**
     * 更新随迁信息
     *
     * @param request
     * @param response
     * @param houseRelationshipsJson
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateHouseRelationships", method = RequestMethod.POST)
    public ResultParam updateHouseRelationships(HttpServletRequest request, HttpServletResponse response,
            @RequestParam("houseRelationshipsJson") String houseRelationshipsJson) {
        if (StringUtils.isEmpty(houseRelationshipsJson)) {
            return ResultParam.error("暂无随迁人员信息, 无法修改!!");
        }

        try {
            List<HouseRelationshipModel> houseRelationships = JSON
                    .parseArray(houseRelationshipsJson, HouseRelationshipModel.class);

            if (CollectionUtils.isEmpty(houseRelationships)) {
                return ResultParam.error("暂无随迁人员信息, 无法修改!!");
            }

            for (HouseRelationshipModel houseRelationship : houseRelationships) {
                houseRelationshipService.update(houseRelationship);
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
