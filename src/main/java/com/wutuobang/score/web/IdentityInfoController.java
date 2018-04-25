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

                if (identityInfoModel.getHouseMoveModel() != null) {
                    Integer region = identityInfoModel.getHouseMoveModel().getRegion();
                    identityInfoModel.setRegion(region);

                    RegionModel regionModel = regionService.getById(region);
                    if (regionModel != null) {
                        identityInfoModel.setRegionName(regionModel.getName());
                    }
                }
                identityInfoService.insert(identityInfoModel);
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
    @RequestMapping(value = "/autoTest/{id}", method = RequestMethod.GET)
    public ModelAndView autoTest(HttpServletRequest request, @PathVariable("id") Integer id) {
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

            IndicatorView indicatorView = indicatorService.initIndicatorView(identityInfo, indicatorModels);

            ModelAndView mv = new ModelAndView("evaluation/autoEvaluation.html");
            mv.addObject("indicatorView", indicatorView);
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
    @RequestMapping(value = "/autoEvaluation", method = RequestMethod.POST)
    public ResultParam autoEvaluation(HttpServletRequest request,
            @RequestParam("evaluationView") String evaluationViewStr) {
        if (StringUtils.isEmpty(evaluationViewStr)) {
            return ResultParam.PARAM_ERROR_RESULT;
        }

        try {
            EvaluationView evaluationView = JSON.parseObject(evaluationViewStr, EvaluationView.class);
            List<PersonBatchScoreResultModel> toAddScoreResults = new ArrayList<PersonBatchScoreResultModel>();



            personBatchScoreResultService.batchInsert(toAddScoreResults);
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
