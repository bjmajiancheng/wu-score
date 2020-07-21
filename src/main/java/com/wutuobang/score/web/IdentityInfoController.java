package com.wutuobang.score.web;

import com.alibaba.fastjson.JSON;
import com.google.code.kaptcha.Constants;
import com.wutuobang.common.client.ShardJedisClient;
import com.wutuobang.common.constant.CacheConstant;
import com.wutuobang.common.constant.CommonConstant;
import com.wutuobang.common.message.SmsUtil;
import com.wutuobang.common.model.AttachmentFileModel;
import com.wutuobang.common.model.AttachmentModel;
import com.wutuobang.common.service.IAttachmentService;
import com.wutuobang.common.utils.DateUtil;
import com.wutuobang.common.utils.PageData;
import com.wutuobang.common.utils.RandomCodeUtil;
import com.wutuobang.common.utils.ResultParam;
import com.wutuobang.score.biz.IdentityInfoBiz;
import com.wutuobang.score.constant.Constant;
import com.wutuobang.score.model.*;
import com.wutuobang.score.service.*;
import com.wutuobang.score.util.FreeMarkerUtil;
import com.wutuobang.score.view.IndicatorView;
import com.wutuobang.shiro.utils.ShiroUtils;
import freemarker.template.SimpleDate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
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

    @Autowired
    private IOnlinePersonMaterialService onlinePersonMaterialService;

    @Autowired
    private IAttachmentService attachmentService;

    @Autowired
    private IPbScoreRecordService pbScoreRecordService;

    private static final Logger LOGGER = LoggerFactory.getLogger(IdentityInfoController.class);

    /**
     * 前往新增用户页面
     *
     * @return
     */
    @RequestMapping(value = "/applicationAdd.html", method = RequestMethod.GET)
    public ModelAndView toAddIdentityInfo() {
        ModelAndView mv = new ModelAndView("application/applicationAdd.html");
        if (StringUtils.isEmpty(companyInfoService.getById(ShiroUtils.getUserEntity().getId()).getBusinessLicenseSrc())) {
            mv.setViewName("company/companyEdit.html");
        } else {
            mv.addObject("addFlag", true);
        }

        return mv;
    }

    /**
     * 前往申请人列表页面
     *
     * @return
     */
    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public String index() {
        if (StringUtils.isEmpty(companyInfoService.getById(ShiroUtils.getUserEntity().getId()).getBusinessLicenseSrc())) {
            return "company/companyEdit.html";
        } else {
            return "index.html";
        }
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
    public ResultParam addIdentityInfo(
            HttpServletRequest request,
            @RequestParam("identityInfoJson") String identityInfoJson,
            @RequestParam("captcha") String captcha,
            @RequestParam(value = "attachment_id_card_positive")
                    Integer attachment_id_card_positive,
            @RequestParam(value = "attachment_id_card_opposite")
                    Integer attachment_id_card_opposite) {
        if (StringUtils.isEmpty(identityInfoJson)) {
            return ResultParam.PARAM_ERROR_RESULT;
        }
        //String Agent = request.getHeader("User-Agent");
        //StringTokenizer st = new StringTokenizer(Agent,";");
        //st.nextToken();
//得到用户的浏览器名
        //String userbrowser = st.nextToken();
//得到用户的操作系统名
        //String useros = st.nextToken();

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String str = sdf.format(date);
        //System.out.println(str + "第一次保存申请人信息浏览器信息，操作系统："+userbrowser+"--"+useros);
        System.out.println(str + "第一次保存申请人信息："+identityInfoJson);
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
            if (identityInfoModel.getThirdPregnantPromise()==null || identityInfoModel.getThirdPregnantPromise()==""){
                return ResultParam.error("第5页，本人及配偶承诺目前未政策外生育（或收养）第三个及以上子女，未处于政策外怀孕第三个及以上子女 没有勾选！");
            }

            Map<String, Object> param = new HashMap<>();
            param.put("batchId", batchConfModel.getId());
            param.put("idNumber", identityInfoModel.getIdNumber());
            List<IdentityInfoModel> identityInfoModels = identityInfoService.find(param);
            if (CollectionUtils.isNotEmpty(identityInfoModels)) {
                return ResultParam.error("本批次申请人身份证号重复, 请填写其他申请人!!");
            }

            identityInfoModel.setAutoTestNum(basicConfModel.getSelfTestLimit());
            boolean addFlag = identityInfoBiz.addIdentityInfo(identityInfoModel, batchConfModel, currUser, attachment_id_card_positive, attachment_id_card_opposite);
            if (!addFlag){
                return ResultParam.error("请用电脑端谷歌浏览器进行信息录入");
            }

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
    public ResultParam updateIdentityInfo(
            HttpServletRequest request,
            @RequestParam("identityInfoJson") String identityInfoJson,
            @RequestParam("captcha") String captcha,
            @RequestParam(value = "attachment_id_card_positive")
                    Integer attachment_id_card_positive,
            @RequestParam(value = "attachment_id_card_opposite")
                    Integer attachment_id_card_opposite) {
        if (StringUtils.isEmpty(identityInfoJson)) {
            return ResultParam.PARAM_ERROR_RESULT;
        }

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String str = sdf.format(date);
        System.out.println(str + ":更新申请人信息："+identityInfoJson);
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

            IdentityInfoModel infoModelDatebase = identityInfoService.getById(identityInfoModel.getId());
            if (infoModelDatebase != null && infoModelDatebase.getReservationStatus() > 6) {
                return ResultParam.error("您当前状态不能修改申请人信息");
            }


            //修改身份证照片上传材料
            List<OnlinePersonMaterialModel> onlinePersonMaterialModelList =
                    onlinePersonMaterialService.getByPersonId(identityInfoModel.getId());
            for (OnlinePersonMaterialModel o : onlinePersonMaterialModelList) {
                if (o.getMaterialInfoId() == 1057) {
                    //正面照片
                    AttachmentModel attachmentModel = attachmentService.getById(attachment_id_card_positive);
                    if (attachmentModel != null && !Objects.equals(attachmentModel.getAttachmentUrl(), identityInfoModel.getIdCardPositive())) {
                        o.setCtime(new Date());
                        o.setMaterialUri(attachmentModel.getAttachmentUrl());
                        o.setMaterialId(attachment_id_card_positive);
                        o.setMaterialName(attachmentModel.getAttachmentName());
                        onlinePersonMaterialService.update(o);
                    }
                } else if (o.getMaterialInfoId() == 1058) {
                    //反面照片
                    AttachmentModel attachmentModel = attachmentService.getById(attachment_id_card_opposite);
                    if (attachmentModel != null && !Objects.equals(attachmentModel.getAttachmentUrl(), identityInfoModel.getIdCardOpposite())) {
                        o.setCtime(new Date());
                        o.setMaterialUri(attachmentModel.getAttachmentUrl());
                        o.setMaterialId(attachment_id_card_opposite);
                        o.setMaterialName(attachmentModel.getAttachmentName());
                        onlinePersonMaterialService.update(o);
                    }

                }
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

            for (IdentityInfoModel identityInfoModel : pageData.getData()){
                if (Integer.parseInt(identityInfoModel.getThirdPregnantPromise())==0 && identityInfoModel.getReservationStatus()>6){
                    identityInfoModel.setSaveStatus(1);// 补录出现的状态设为1
                }
                //用户已上传的材料信息
                List<OnlinePersonMaterialModel> onlinePersonMaterials = onlinePersonMaterialService.getByPersonId_1(identityInfoModel.getId());
                identityInfoModel.setAge(onlinePersonMaterials.size()); /// 占用年龄的字段来表示有多少需要补正的材料
                List<PbScoreRecordModel> list = pbScoreRecordService.getByPersonId(identityInfoModel.getId());
                for (PbScoreRecordModel pbScoreRecordModel : list){

//                    3	    人社
//                    4	    市公安局
//                    5	    市民政局
//                    6	    市教委
//                    9	    市住建委
//                    10	住房公积金中心
//                    12	市卫健委
//                    1050	退役军人事务局
//                    1060	规自局

                    if (pbScoreRecordModel!=null){
                        switch (pbScoreRecordModel.getOp_role_id()){
                            case 3:
                                identityInfoModel.setRenshe(pbScoreRecordModel.getStatus());
                                break;
                            case 4:
                                identityInfoModel.setGongan(pbScoreRecordModel.getStatus());
                                break;
                            case 5:
                                identityInfoModel.setMinzheng(pbScoreRecordModel.getStatus());
                                break;
                            case 6:
                                identityInfoModel.setJiaowei(pbScoreRecordModel.getStatus());
                                break;
                            case 9:
                                identityInfoModel.setZhujianwei(pbScoreRecordModel.getStatus());
                                break;
                            case 10:
                                identityInfoModel.setGongjijin(pbScoreRecordModel.getStatus());
                                break;
                            case 12:
                                identityInfoModel.setWeijianwei(pbScoreRecordModel.getStatus());
                                break;
                            case 1050:
                                identityInfoModel.setTuiyijunren(pbScoreRecordModel.getStatus());
                                break;
                            case 1060:
                                identityInfoModel.setGuiziju(pbScoreRecordModel.getStatus());
                        }
                    }

                }

            }

            AcceptDateConfModel acceptDateConf_shiqu = acceptDateConfService.getByBatchidAndAddressidAndAcceptdate(batchConfModel.getId(), 1,DateUtil.getDate(new Date()));
            AcceptDateConfModel acceptDateConf_binhai = acceptDateConfService.getByBatchidAndAddressidAndAcceptdate(batchConfModel.getId(), 2,DateUtil.getDate(new Date()));
            int count1 = 0;
            int count2 = 0;
            if (acceptDateConf_shiqu != null){
                count1 = acceptDateConf_shiqu.getAmRemainingCount() + acceptDateConf_shiqu.getPmRemainingCount();
            }
            if (acceptDateConf_binhai != null){
                count2 = acceptDateConf_binhai.getAmRemainingCount() + acceptDateConf_binhai.getPmRemainingCount();
            }
            pageData.setCountmsg("市区："+count1+"；滨海新区："+ count2);

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
                    /*
                    输入项：租赁住房月数，租赁积分=租赁月份数×6/12

                    页面3中：如选择“按照“津发改社会〔2018〕26号”文件计算”
                    显示“在天津市有本人产权住房或者与配偶、父母、子女共有产权住房（40分）。”积分40分。24
                    住房所在区“武清区/宝坻区/宁河区/静海区/蓟州区”15分，“滨海新区”20分。              25

                    页面3中：如选择“不按照“津发改社会〔2018〕26号”文件计算”
                    输入项：购买住房月数，住房积分=有效住房持有月份数×住房所在区域年积分分值/12
                    区域分值：“和平区/河东区/河西区/南开区/河北区/红桥区”10分；“东丽区/西青区/津南区/北辰区”11分；“武清区/宝坻区/宁河区/静海区/蓟州区”12分；“滨海新区”15分。
                     */
                    if (identityInfo.getIs201826Doc()==null){
                        if (indicatorModel.getIndexNum() != 16 && indicatorModel.getIndexNum() != 24 && indicatorModel.getIndexNum() != 25 && indicatorModel.getIndexNum() != 26 && indicatorModel.getIndexNum() != 27) {
                            finalIndicators.add(indicatorModel);
                        }
                    }
                    if (identityInfo.getIs201826Doc()!=null && identityInfo.getIs201826Doc() == 2){
                        if (indicatorModel.getIndexNum() != 16 && indicatorModel.getIndexNum() != 24 && indicatorModel.getIndexNum() != 25) {
                            finalIndicators.add(indicatorModel);
                        }
                    }
                    if (identityInfo.getIs201826Doc()!=null && identityInfo.getIs201826Doc() == 1){
                        if (indicatorModel.getIndexNum() != 16 && indicatorModel.getIndexNum() != 26 && indicatorModel.getIndexNum() != 27) {
                            finalIndicators.add(indicatorModel);
                        }
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
//                    SmsUtil.send(currUser.getOperatorMobile(),
//                            String.format(commonConstant.autoevaluationpassMessage, currUser.getOperator()));
                }

                if (houseOtherModel != null && StringUtils.isNotEmpty(houseOtherModel.getSelfPhone())) {
//                    SmsUtil.send(houseOtherModel.getSelfPhone(),
//                            String.format(commonConstant.autoevaluationpassMessage, identityInfoModel.getName()));
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
            /*
            2020年1月16日
            “增加租赁备案信息”
            若申请人点击了上面的按钮“增加租赁备案信息”，则要回显显示内容
             */
            List<HouseMoveModel> houseInformationList = new ArrayList<HouseMoveModel>();
            if (identityInfo.getHouseMoveModel()!=null && identityInfo.getHouseMoveModel().getRentHouseAddress() != null){
                HouseMoveModel houseMoveModel = identityInfo.getHouseMoveModel();

                String[] renIdNumberArr = houseMoveModel.getRentIdNumber().replaceAll("\"","").replaceAll("\\[","").replaceAll("\\]","").split(",");
                String[] strRentHouseAddressArr = houseMoveModel.getRentHouseAddress().replaceAll("\"","").replaceAll("\\[","").replaceAll("\\]","").split(",");
                String[] strRenHouseStartDateArr = houseMoveModel.getRentHouseStartDate().replaceAll("\"","").replaceAll("\\[","").replaceAll("\\]","").split(",");
                String[] strRentHouseEndDateArr = houseMoveModel.getRentHouseEndDate().replaceAll("\"","").replaceAll("\\[","").replaceAll("\\]","").split(",");

                for (int i=0; i<strRentHouseAddressArr.length; i++){
                    HouseMoveModel hv = new HouseMoveModel();
                    hv.setRentIdNumber(renIdNumberArr[i]);
                    hv.setRentHouseAddress(strRentHouseAddressArr[i]);
                    hv.setRentHouseStartDate(strRenHouseStartDateArr[i]);
                    hv.setRentHouseEndDate(strRentHouseEndDateArr[i]);

                    houseInformationList.add(hv);
                }

            }
            List<HouseRelationshipModel> houseRelationshipList = identityInfo.getHouseRelationshipModelList();
            List<HouseRelationshipModel> zinvInfoList = new ArrayList<HouseRelationshipModel>(); // 保存第一页的子女信息的4个字段
            for (HouseRelationshipModel houseRelationshipModel : houseRelationshipList) {
                if ("配偶".equals(houseRelationshipModel.getRelationship())) {
                    mv.addObject("spouseHouseRelationshipModel", houseRelationshipModel);
                }else{
                    HouseRelationshipModel hrm = new HouseRelationshipModel();
                    hrm.setName(houseRelationshipModel.getName());// 姓名
                    hrm.setIdNumber(houseRelationshipModel.getIdNumber());
                    hrm.setIsChinese(houseRelationshipModel.getIsChinese());// 是否为中国国籍
                    hrm.setIsRemove(houseRelationshipModel.getIsRemove());
                    zinvInfoList.add(hrm);
                }
            }
            // 处理第一页的子女个人信息的4个字段


            mv.addObject("zinvInfoList", zinvInfoList);
            mv.addObject("houseInformationList", houseInformationList);
            mv.addObject("houseRelationshipList", houseRelationshipList);
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
     * 跳转到查看申请人信息页面,不可修改
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/selectIdentityInfo/{id}.html")
    public ModelAndView selectIdentityInfo(HttpServletRequest request, @PathVariable("id") Integer id) {
        if (id == null) {
            return new ModelAndView("500", "result", ResultParam.PARAM_ERROR_RESULT);
        }

        ModelAndView mv = new ModelAndView("application/applicationAdd.html");

        try {

            IdentityInfoModel identityInfo = identityInfoService.getById(id);
            initIdentityInfoAttrs(identityInfo);

            mv.addObject("identityInfo", identityInfo);
            mv.addObject("houseMoveModel", identityInfo.getHouseMoveModel());
            List<HouseRelationshipModel> houseRelationshipList = identityInfo.getHouseRelationshipModelList();
            for (HouseRelationshipModel houseRelationshipModel : houseRelationshipList) {
                if ("配偶".equals(houseRelationshipModel.getRelationship())) {
                    mv.addObject("spouseHouseRelationshipModel", houseRelationshipModel);
                }
            }
            mv.addObject("houseRelationshipList", houseRelationshipList);
            mv.addObject("houseOtherModel", identityInfo.getHouseOtherModel());
            mv.addObject("houseProfessionModel", identityInfo.getHouseProfessionModel());
            mv.addObject("addFlag", false);
            mv.addObject("selectFlag", true);
            return mv;
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("500", "result", ResultParam.PARAM_ERROR_RESULT);
        }

    }


    @ResponseBody
    @RequestMapping(value = "/testAll", method = RequestMethod.POST)
    public ResultParam testAll(HttpServletRequest request,
                                       @RequestParam("identityInfoId") Integer identityInfoId) {

        try {

            IdentityInfoModel identityInfoModel = identityInfoService.getById(identityInfoId);
            HouseOtherModel houseOtherModel = houseOtherService.getByIdentityInfoId(identityInfoModel.getId());
            HouseProfessionModel houseProfessionModel = houseProfessionService.getByIdentityInfoId(identityInfoModel.getId());
            HouseMoveModel houseMoveModel = houseMoveService.getByIdentityInfoId(identityInfoModel.getId());
            if(houseMoveModel==null){
                return ResultParam.error("请先点击编辑按钮，完善信息后，操作下一步。");
            }
            if (houseOtherModel==null || houseProfessionModel==null){
                return ResultParam.error("请先点击编辑按钮，完善信息后，操作下一步。");
            }
            if (Integer.parseInt(identityInfoModel.getThirdPregnantPromise())==0){
                ResultParam resultParam = new ResultParam();
                resultParam.setCode(1005);
                resultParam.setMessage("第5页，本人及配偶承诺目前未政策外生育（或收养）第三个及以上子女，未处于政策外怀孕第三个及以上子女 没有勾选！请 编辑 完善个人信息！");
                return resultParam;
            }

            return ResultParam.SUCCESS_RESULT;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultParam.SYSTEM_ERROR_RESULT;
        }
    }


    /**
     * 2020年4月18日
     * 补充申请人没有填写的一个字段
     * @param request
     * @param identityInfoId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveIdentityInfo", method = RequestMethod.POST)
    public ResultParam saveIdentityInfo(HttpServletRequest request,
                               @RequestParam("identityInfoId") Integer identityInfoId,
                               @RequestParam("thirdPregnantPromiseValue") Integer thirdPregnantPromiseValue) {

        try {

            IdentityInfoModel identityInfoModel = identityInfoService.getById(identityInfoId);
            identityInfoModel.setThirdPregnantPromise(thirdPregnantPromiseValue.toString());
            identityInfoService.update(identityInfoModel);

            IdentityInfoModel identityInfoModel2 = identityInfoService.getById(identityInfoId);

            if (Integer.parseInt(identityInfoModel2.getThirdPregnantPromise())>0){
                return ResultParam.SUCCESS_RESULT;
            }else {
                return ResultParam.error("您的补录操作失败，请再次补录");
            }



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

            if (identityInfoModel.getReservationStatus() != 6) {
                return ResultParam.error("当前状态不可预约");
            }

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
            updateIdentityInfo.setPreApprove(new Date());
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
            //            String acceptNumber = "102018200405";
            updateIdentityInfo.setAcceptNumber(acceptNumber);
            if (identityInfoModel.getPoliceApproveStatus() == 3) {
                updateIdentityInfo.setPoliceApproveStatus(Constant.policeApproveStatus_3);
            } else {
                updateIdentityInfo.setPoliceApproveStatus(Constant.policeApproveStatus_1);
            }

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


    /*
    2019年12月26日
    1、申请审核后，数据在后台公安前置审核“待审核”显示
    2、给申请人发短信申请审核成功
     */
    @ResponseBody
    @RequestMapping(value = "/examineAndVerify", method = RequestMethod.POST)
    public ResultParam examineAndVerify(HttpServletRequest request, @RequestParam("id") Integer id){
        if (id == null) {
            return ResultParam.PARAM_ERROR_RESULT;
        }
        BatchConfModel batchConfModel = batchConfService.getBatchInfoByDate(new Date());
        if (batchConfModel == null) {
            return ResultParam.error("当前没有落户批次信息,请联系官网确认!!");
        }

        IdentityInfoModel identityInfoModel = identityInfoService.getById(id);

        /**
         * 2020年4月10日
         * 若申请人没有上传图片资料，那么就不允许审核
         */
        //用户已上传的材料信息
        boolean flag = true;
        int count = 0;
        int[] intArr = new int[]{20,1068};
        List<OnlinePersonMaterialModel> onlinePersonMaterials = onlinePersonMaterialService.getByPersonId(identityInfoModel.getId());
        for(OnlinePersonMaterialModel onlinePersonMaterialModel : onlinePersonMaterials){
            for (int a : intArr){
                if (a==onlinePersonMaterialModel.getMaterialInfoId()){
                    count++;
                }
            }
        }
        if(count<2){
            return ResultParam.error("请先点击“材料上传”按钮上传图片材料");
        }


        /*
        2019年12月27日
        根据申请人所在的预约地点，先确认当天是否还有预约名额
            市政务办每天有260个名额（上午130，下午130），滨海新区每天有160个名额（上午80个，下午80个）
        若有名额，就允许申请人预约，否则不能预约；
         */

        AcceptDateConfModel acceptDateConf = acceptDateConfService.getByBatchidAndAddressidAndAcceptdate(identityInfoModel.getBatchId(), identityInfoModel.getAcceptAddressId(),DateUtil.getDate(new Date()));
        if(acceptDateConf == null){
            return ResultParam.error("当天没有审核名额");
        }
        // 当天剩余的预约名额 leftoverCount
        Integer leftoverCount = acceptDateConf.getAmRemainingCount()+acceptDateConf.getPmRemainingCount();
        if (leftoverCount<=0){
            // 不可以预约
            return ResultParam.error("当天的审核名额已经用完");
        }else{
            // 可以预约
            if (acceptDateConf.getAmRemainingCount()>0){
                acceptDateConf.setAmRemainingCount(acceptDateConf.getAmRemainingCount()-1);
            }else if(acceptDateConf.getAmRemainingCount()==0 && acceptDateConf.getPmRemainingCount()>0){
                acceptDateConf.setPmRemainingCount(acceptDateConf.getPmRemainingCount()-1);
            }
        }
        identityInfoModel.setReservationStatus(11); // 申请审核状态
        identityInfoModel.setHallStatus(0);
        identityInfoModel.setUnionApproveStatus1(2);
        identityInfoModel.setUnionApproveStatus2(2);
        identityInfoModel.setPoliceApproveStatus(1);// 公安前置审核“待审核”
        identityInfoModel.setRensheAcceptStatus(1);// 人社受理“待审核”
        identityInfoModel.setReservationDate(new Date()); // 原来存储预约日期，现在存储申请审核的日期
        String acceptNumber = identityInfoService.generAcceptNumber(identityInfoModel, batchConfModel);
        identityInfoModel.setAcceptNumber(acceptNumber);
        identityInfoService.update(identityInfoModel);

        //记录状态日志信息
        DictModel dictModel = dictService.findByAliasAndValue("reservationStatus", Constant.reservationStatus_11);
        if (dictModel != null) {
            PersonBatchStatusRecordModel recordModel = new PersonBatchStatusRecordModel(identityInfoModel,
                    dictModel, "添加申请人信息成功");
            recordModel.setStatusTypeDesc("申请人申请审核");
            recordModel.setStatusStr("申请成功");
            recordModel.setStatusTime(new Date());
            recordModel.setStatusReason("申请审核包含预约地点、预约时间两个节点，系统升级改造");
            personBatchStatusRecordService.insert(recordModel);
        }
        return ResultParam.ok("申请审核成功！");
    }

    /*
    根据申请人id 删除申请人信息
     */
    @ResponseBody
    @RequestMapping(value = "/deleteIdentityInfo", method = RequestMethod.POST)
    public ResultParam deleteIdentityInfo(HttpServletRequest request, @RequestParam("id") Integer id){
        if (id == null) {
            return ResultParam.PARAM_ERROR_RESULT;
        }
        BatchConfModel batchConfModel = batchConfService.getBatchInfoByDate(new Date());
        if (batchConfModel == null) {
            return ResultParam.error("当前没有落户批次信息,请联系官网确认!!");
        }

        IdentityInfoModel identityInfoModel = identityInfoService.getById(id);

        identityInfoService.removeById(id);
//        houseMoveService.removeById(id);
//        houseOtherService.removeById(id);
//        houseProfessionService.removeById(id);
//        houseRelationshipService.removeById(id);

        //记录状态日志信息
        DictModel dictModel = dictService.findByAliasAndValue("reservationStatus", Constant.reservationStatus_11);
        if (dictModel != null) {
            PersonBatchStatusRecordModel recordModel = new PersonBatchStatusRecordModel(identityInfoModel,
                    dictModel, "删除申请人信息成功");
            recordModel.setStatusTypeDesc("删除申请人信息");
            recordModel.setStatusStr("删除成功");
            recordModel.setStatusTime(new Date());
            recordModel.setStatusReason("申请人自己删除");
            personBatchStatusRecordService.insert(recordModel);
        }
        return ResultParam.ok("删除成功！");
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
            //            updateIdentityInfo.setReservationStatus(Constant.reservationStatus_10);
            //            updateIdentityInfo.setAcceptNumber(StringUtils.EMPTY);
            //            //updateIdentityInfo.setPoliceApproveStatus(Constant.policeApproveStatus_0);
            //            updateIdentityInfo.setReservationM(0);
            //            updateIdentityInfo.setReservationDateNull(1);
            //            updateIdentityInfo.setReservationTime(identityInfoModel.getReservationTime());
             /*
            2018年10月9日修改，xuegr
            已经通过公安窗口前置审核的申请人，如果取消预约，再进行二次预约的，公安窗口不必再次进行前置审核，公安窗口的一切操作状态保留；
            下面if中的判断条件就是此状态时的参数
             */
            if (identityInfoModel.getReservationStatus() == 11 && identityInfoModel.getHallStatus() == 3 && identityInfoModel.getPoliceApproveStatus() == 3
                    && identityInfoModel.getRensheAcceptStatus() == 1) {
                updateIdentityInfo.setReservationStatus(Constant.reservationStatus_10);
                //                updateIdentityInfo.setAcceptNumber(StringUtils.EMPTY);
                //updateIdentityInfo.setPoliceApproveStatus(Constant.policeApproveStatus_0);
                updateIdentityInfo.setReservationM(0);//1:上午，2：下午；0表示无
                updateIdentityInfo.setReservationDateNull(1);//设置预约时间为空
                updateIdentityInfo.setReservationTime(identityInfoModel.getReservationTime());
            } else {
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
            if (identityInfoModel.getReservationStatus() >= 11) {
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

            if (identityInfo.getAcceptAddressId() == null) {
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

    /*
    判断是否在关闭功能的时间段内，若是返回成功，失败返回0即可
     */
    @ResponseBody
    @RequestMapping(value = "/sys/getCloseOrOpenFunTime", method = RequestMethod.POST)
    public ResultParam getCloseOrOpenFunTime() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("status", 1);//
        List<BatchConfModel> batchConfs = batchConfService.find(params);
        //由于条件为1 的查询结果结果不是预期的一条，所以挑选了一个状态为1 的记录
        Date closeFunctionTime = new Date();
        Date openFunctionTime = new Date();
        for (BatchConfModel batchConf : batchConfs) {
            if (batchConf.getStatus() == 1) {
                closeFunctionTime = batchConf.getCloseFunctionTime();
                openFunctionTime = batchConf.getOpenFunctionTime();
            }
        }
        Date closeRegisterTime = new Date();
        Date openRegisterTime = new Date();
        for (BatchConfModel batchConf : batchConfs) {
            if (batchConf.getStatus() == 1) {
                closeRegisterTime = batchConf.getCloseRegisterTime();
                openRegisterTime = batchConf.getOpenRegisterTime();
            }
        }

        if(System.currentTimeMillis()>closeRegisterTime.getTime()){
            ResultParam resultParam = new ResultParam();
            resultParam.setMessage("积分注册、申请审核阶段已停止，请关注重要通知页面内容");
            resultParam.setCode(11);
            resultParam.setData(null);
            return resultParam;
        }
        if (closeFunctionTime.getTime() < System.currentTimeMillis() && System.currentTimeMillis() < openFunctionTime.getTime()) {
            return ResultParam.cloOrOpen();
        } else {
            return ResultParam.ok();
        }
    }


    /*
    获取是否关闭了单位、个人信息的注册
     */
    @ResponseBody
    @RequestMapping(value = "/sys/getCloseRegisterTime", method = RequestMethod.POST)
    public ResultParam getCloseRegisterTime() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("status", 1);//
        List<BatchConfModel> batchConfs = batchConfService.find(params);
        //由于条件为1 的查询结果结果不是预期的一条，所以挑选了一个状态为1 的记录
        Date closeRegisterTime = new Date();
        Date openRegisterTime = new Date();
        for (BatchConfModel batchConf : batchConfs) {
            if (batchConf.getStatus() == 1) {
                closeRegisterTime = batchConf.getCloseRegisterTime();
                openRegisterTime = batchConf.getOpenRegisterTime();
            }
        }

        if(System.currentTimeMillis()>closeRegisterTime.getTime()){
            ResultParam resultParam = new ResultParam();
            resultParam.setMessage("积分注册阶段已停止，请关注重要通知页面内容");
            resultParam.setCode(11);
            resultParam.setData(null);
            return resultParam;
        } else {
            return ResultParam.ok();
        }
    }

    /*
    2019年10月24日，关闭申请人的预约交件时间
     */
    @ResponseBody
    @RequestMapping(value = "/sys/getCloseOrder", method = RequestMethod.POST)
    public ResultParam getCloseOrder(){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("status", 1);//
        List<BatchConfModel> batchConfs = batchConfService.find(params);
        //由于条件为1 的查询结果结果不是预期的一条，所以挑选了一个状态为1 的记录
        Date closeOrder = new Date();
        for (BatchConfModel batchConf : batchConfs) {
            if (batchConf.getStatus() == 1) {
                closeOrder = batchConf.getCloseOrder();
            }
        }

        if (System.currentTimeMillis()>closeOrder.getTime()){
            return ResultParam.closeOrderMsg();
        }else {
            return ResultParam.ok();
        }

    }

    @ResponseBody
    @RequestMapping(value ="/reviewPhoneCode" ,method = RequestMethod.POST)
    public ResultParam reviewPhoneCode(@RequestParam("mobilePhone") String mobilePhone){

        try {
            //String randomCode = "1234";
            //SmsUtil.send("15863150206",String.format("系统提示：您的验证码为：%s，有效期为5分钟，请勿向他人提供收到的信息。", randomCode));
            String randomCode = RandomCodeUtil.generRandomCode(4);
            jedisClient.setex(String.format(com.wutuobang.score.constant.CacheConstant.RETRIEVE_PASSWD_CACHE_KEY, mobilePhone), randomCode, 5 * 60);
            //发送短信
            SmsUtil.send(mobilePhone,String.format("系统提示：您的验证码为：%s，有效期为5分钟，请勿向他人提供收到的信息。", randomCode));

            LOGGER.info("用户验证码:{}", randomCode);

            ResultParam resultParam = new ResultParam();
            String mobileStr =  ":" + mobilePhone.substring(0,3)+"****"+mobilePhone.substring(7);
            resultParam.setData(mobileStr);
            resultParam.setMessage("验证码发送成功!!");
            return resultParam;
        }catch (Exception e){
            e.printStackTrace();
            return ResultParam.SYSTEM_ERROR_RESULT;
        }

    }

    /*
    2020年6月30日
    接收短信验证码
     */
    @ResponseBody
    @RequestMapping(value = "/validReviewPhoneCode", method = RequestMethod.POST)
    public ResultParam validReviewPhoneCode(@RequestParam("mobilePhone") String mobilePhone,@RequestParam("msgCode") String msgCode){

        String randomCode = jedisClient.get(String.format(com.wutuobang.score.constant.CacheConstant.RETRIEVE_PASSWD_CACHE_KEY, mobilePhone));

        //String randomCode = "1234";
        if (randomCode == null) {
            return ResultParam.error("短信验证码已失效, 请重新发送。");
        }

        if (!msgCode.equals(randomCode)) {
            return ResultParam.error("短信验证码不正确, 请重新输入。");
        }

        return ResultParam.SUCCESS_RESULT;

    }

}
