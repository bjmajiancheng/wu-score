package com.wutuobang.score.biz;

import com.alibaba.fastjson.JSON;
import com.wutuobang.common.constant.CommonConstant;
import com.wutuobang.common.message.SmsUtil;
import com.wutuobang.common.service.IAttachmentService;
import com.wutuobang.common.utils.DateUtil;
import com.wutuobang.common.utils.ResultParam;
import com.wutuobang.score.constant.Constant;
import com.wutuobang.score.model.*;
import com.wutuobang.score.service.*;
import com.wutuobang.score.view.IndicatorItemView;
import com.wutuobang.score.view.IndicatorView;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by majiancheng on 2018/5/12.
 */
@Component("identityInfoBiz")
public class IdentityInfoBiz {

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
    private IIndicatorService indicatorService;

    @Autowired
    private IPersonBatchScoreResultService personBatchScoreResultService;

    @Autowired
    private IPersonBatchStatusRecordService personBatchStatusRecordService;

    @Autowired
    private IDictService dictService;

    @Autowired
    private IIndicatorSelfTestResultService indicatorSelfTestResultService;

    @Autowired
    private CommonConstant commonConstant;

    @Autowired
    private IOnlinePersonMaterialService onlinePersonMaterialService;

    @Autowired
    private IAttachmentService attachmentService;

    @Autowired
    private ICompanyInfoService companyInfoService;

    /**
     * 新增申请人信息
     *
     * @param identityInfoModel
     * @param batchConfModel
     * @param currUser
     * @return
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public boolean addIdentityInfo(IdentityInfoModel identityInfoModel, BatchConfModel batchConfModel,
                                   CompanyInfoModel currUser, Integer attachment_id_card_positive, Integer attachment_id_card_opposite) throws Exception {

        Date currDate = new Date();
        CompanyInfoModel company = companyInfoService.getById(currUser.getId());
        if (identityInfoModel != null) {
            identityInfoModel.setBatchId(batchConfModel.getId());
            identityInfoModel.setCompanyId(currUser.getId());
            /*
            2019年12月25日，将注册时的申请人预约地点，复制给申请人
             */
            identityInfoModel.setAcceptAddressId(Integer.parseInt(company.getOperatorMobile2()));
            //初始化状态信息
            identityInfoModel.setReservationStatus(Constant.reservationStatus_1);//申请预约状态
            identityInfoModel.setHallStatus(Constant.hallStatus_0);//预约大厅状态
            identityInfoModel.setUnionApproveStatus1(Constant.unionApproveStatus1_0);//公安预审状态
            identityInfoModel.setUnionApproveStatus2(Constant.unionApproveStatus2_0);//人社预审状态
            identityInfoModel.setPoliceApproveStatus(Constant.policeApproveStatus_0);//公安前置预审状态
            identityInfoModel.setRensheAcceptStatus(Constant.rensheAcceptStatus_0);//人社受理状态:默认值
            identityInfoModel.setCancelStatus(0);//取消状态:默认值
            identityInfoModel.setCtime(currDate);
            identityInfoModel.setUtime(currDate);
            identityInfoModel.setReservationTime(2);

            if (identityInfoModel.getHouseMoveModel() != null) {
                Integer region = identityInfoModel.getHouseMoveModel().getRegion();
                identityInfoModel.setRegion(region);

                RegionModel regionModel = regionService.getById(region);
                if (regionModel != null) {
                    identityInfoModel.setRegionName(regionModel.getName());
                }
            }

            /**
             * 2019年10月23日，判断年龄字段是否存在并正确
             */
            String str =identityInfoModel.getIdNumber();
            int year = Integer.parseInt(str.substring(6,10));
            int month = Integer.parseInt(str.substring(10,12));
            int day = Integer.parseInt(str.substring(12,14));

            // 录入信息那天的数据
            int year2 = DateUtil.getYear(new Date());
            int month2 = DateUtil.getMonth(new Date())+1;
            int day2 = DateUtil.getDay(new Date());

            if (month2>month){
                identityInfoModel.setAge(year2-year);
            } else if (month2==month && day2>day){
                identityInfoModel.setAge(year2-year);
            } else{
                identityInfoModel.setAge(year2-year-1);
            }

            /*
            2019年10月24日，补丁性别，出问题的概率为5千分之一，在此修正下
             */
            if (identityInfoModel.getSex()==null){
                String sex = str.substring(str.length()-2,str.length()-1);
                if (Integer.parseInt(sex)%2!=0){
                    identityInfoModel.setSex(1);
                } else {
                    identityInfoModel.setSex(2);
                }
            }

            identityInfoService.insert(identityInfoModel);

            //将身份证照片放到上传材料中
            //正面照片
            String idCardPositiveUrl = identityInfoModel.getIdCardPositive();
            if (StringUtils.isNotEmpty(idCardPositiveUrl)) {
                OnlinePersonMaterialModel onlinePersonMaterialModelPositive = new OnlinePersonMaterialModel();
                onlinePersonMaterialModelPositive.setCtime(new Date());
                onlinePersonMaterialModelPositive.setMaterialUri(idCardPositiveUrl);
                onlinePersonMaterialModelPositive.setBatchId(batchConfModel.getId());
                onlinePersonMaterialModelPositive.setPersonId(identityInfoModel.getId());
                onlinePersonMaterialModelPositive.setMaterialInfoId(1057);
                onlinePersonMaterialModelPositive.setStatus(0);
                onlinePersonMaterialModelPositive.setMaterialId(attachment_id_card_positive);
                onlinePersonMaterialModelPositive.setMaterialName(attachmentService.getById(attachment_id_card_positive).getAttachmentName());
                onlinePersonMaterialService.insert(onlinePersonMaterialModelPositive);
            }
            //反面照片
            String idCardOppositeUrl = identityInfoModel.getIdCardOpposite();
            if (StringUtils.isNotEmpty(idCardOppositeUrl)) {
                OnlinePersonMaterialModel onlinePersonMaterialModelOpposite = new OnlinePersonMaterialModel();
                onlinePersonMaterialModelOpposite.setCtime(new Date());
                onlinePersonMaterialModelOpposite.setMaterialUri(idCardOppositeUrl);
                onlinePersonMaterialModelOpposite.setBatchId(batchConfModel.getId());
                onlinePersonMaterialModelOpposite.setPersonId(identityInfoModel.getId());
                onlinePersonMaterialModelOpposite.setMaterialInfoId(1058);
                onlinePersonMaterialModelOpposite.setStatus(0);
                onlinePersonMaterialModelOpposite.setMaterialId(attachment_id_card_opposite);
                onlinePersonMaterialModelOpposite.setMaterialName(attachmentService.getById(attachment_id_card_opposite).getAttachmentName());
                onlinePersonMaterialService.insert(onlinePersonMaterialModelOpposite);
            }

            //记录状态日志信息
            DictModel dictModel = dictService.findByAliasAndValue("reservationStatus", Constant.reservationStatus_1);
            if (dictModel != null) {
                PersonBatchStatusRecordModel recordModel = new PersonBatchStatusRecordModel(identityInfoModel,
                        dictModel, "添加申请人信息成功");
                personBatchStatusRecordService.insert(recordModel);
            }

            //户籍迁移信息
            HouseMoveModel houseMoveModel = identityInfoModel.getHouseMoveModel();
            if (houseMoveModel != null) {
                houseMoveModel.setIdentityInfoId(identityInfoModel.getId());
                if (houseMoveModel.getSonNumber() == null) {
                    houseMoveModel.setSonNumber(0);
                }
                Map<Integer,Integer> map = new HashMap<Integer,Integer>();
                map.put(1	,21);
                map.put(2	,22);
                map.put(3	,23);
                map.put(4	,24);
                map.put(5	,25);
                map.put(6	,26);
                map.put(10	,27);
                map.put(11	,28);
                map.put(12	,29);
                map.put(13	,30);
                map.put(14	,31);
                map.put(15	,32);
                map.put(16	,34);
                map.put(17	,35);
                map.put(18	,36);
                map.put(20	,33);
                houseMoveModel.setRegion(map.get(Integer.parseInt(houseMoveModel.getRegisteredOffice())));
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
                if (houseProfessionModel.getProfessionType() == 1) {
                    houseProfessionModel.setJobLevel(0);
                    houseProfessionModel.setJobType(0);

                    houseProfessionModel.setJobTitleLevel(0);
                    houseProfessionModel.setJobPosition(StringUtils.EMPTY);
                    houseProfessionModel.setIssuingAuthority(StringUtils.EMPTY);
                    houseProfessionModel.setIssuingDate(StringUtils.EMPTY);
                    houseProfessionModel.setCertificateCode(StringUtils.EMPTY);
                    houseProfessionModel.setProfessionTitle(0);
                    houseProfessionModel.setJobName(0);
                }
                if (houseProfessionModel.getProfessionType() == 2) {
                    houseProfessionModel.setJobLevel(0);
                    houseProfessionModel.setJobType(0);
                    houseProfessionModel.setJobName(0);
                }

                if (houseProfessionModel.getProfessionType() == 3) {
                    houseProfessionModel.setJobTitleLevel(0);
                    houseProfessionModel.setJobPosition(StringUtils.EMPTY);
                    houseProfessionModel.setProfessionTitle(0);
                }
                houseProfessionService.insert(houseProfessionModel);
            }

            if (StringUtils.isNotEmpty(currUser.getOperatorMobile())) {
                SmsUtil.send(currUser.getOperatorMobile(),
                        String.format(commonConstant.addapplicationApplicantMessage, currUser.getOperator()));
            }

            if (houseOtherModel != null && StringUtils.isNotEmpty(houseOtherModel.getSelfPhone())) {
                SmsUtil.send(houseOtherModel.getSelfPhone(),
                        String.format(commonConstant.addapplicationApplicantMessage, identityInfoModel.getName()));
            }
        }

        return true;
    }

    /**
     * 更新申请人信息
     *
     * @param identityInfoModel
     * @param batchConfModel
     * @param currUser
     * @return
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public boolean updateIdentityInfo(IdentityInfoModel identityInfoModel, BatchConfModel batchConfModel,
                                      CompanyInfoModel currUser) throws Exception {

        if (identityInfoModel == null) {
            return false;
        }

        if (identityInfoModel.getHouseMoveModel() != null) {
            Integer region = identityInfoModel.getHouseMoveModel().getRegion();
            identityInfoModel.setRegion(region);

            RegionModel regionModel = regionService.getById(region);
            if (regionModel != null) {
                identityInfoModel.setRegionName(regionModel.getName());
            }
        }
        identityInfoModel.setUtime(new Date());
        identityInfoService.update(identityInfoModel);

        //户籍迁移信息
        HouseMoveModel updateHouseMove = identityInfoModel.getHouseMoveModel();
        if (updateHouseMove != null) {
            if (updateHouseMove.getId() != null) {
                Map<Integer,Integer> map = new HashMap<Integer,Integer>();
                map.put(1	,21);
                map.put(2	,22);
                map.put(3	,23);
                map.put(4	,24);
                map.put(5	,25);
                map.put(6	,26);
                map.put(10	,27);
                map.put(11	,28);
                map.put(12	,29);
                map.put(13	,30);
                map.put(14	,31);
                map.put(15	,32);
                map.put(16	,34);
                map.put(17	,35);
                map.put(18	,36);
                map.put(20	,33);
                String aa = updateHouseMove.getRegisteredOffice();
                updateHouseMove.setRegion(map.get(Integer.parseInt(updateHouseMove.getRegisteredOffice())));
                houseMoveService.update(updateHouseMove);
            } else {
                updateHouseMove.setIdentityInfoId(identityInfoModel.getId());
                if (updateHouseMove.getSonNumber() == null) {
                    updateHouseMove.setSonNumber(0);
                }
                houseMoveService.insert(updateHouseMove);
            }
        }

        //申请人家庭关系
        List<HouseRelationshipModel> updateHouseRelations = identityInfoModel.getHouseRelationshipModelList();
        if (CollectionUtils.isEmpty(updateHouseRelations)) {
            houseRelationshipService.delByIdentityInfoId(identityInfoModel.getId());
        } else {
            List<HouseRelationshipModel> houseRelationships = houseRelationshipService
                    .getByIdentityInfoId(identityInfoModel.getId());
            Map<Integer, HouseRelationshipModel> houseRelationshipMap = new HashMap<Integer, HouseRelationshipModel>();
            if (CollectionUtils.isNotEmpty(houseRelationships)) {
                for (HouseRelationshipModel houseRelationship : houseRelationships) {
                    houseRelationshipMap.put(houseRelationship.getId(), houseRelationship);
                }
            }

            List<HouseRelationshipModel> toAddHouseRelationships = new ArrayList<HouseRelationshipModel>();
            for (HouseRelationshipModel updateHouseRelation : updateHouseRelations) {
                if (updateHouseRelation.getId() == null) {
                    updateHouseRelation.setIdentityInfoId(identityInfoModel.getId());
                    toAddHouseRelationships.add(updateHouseRelation);
                } else {
                    houseRelationshipService.update(updateHouseRelation);
                    houseRelationshipMap.remove(updateHouseRelation.getId());
                }
            }

            Set<Integer> delIds = houseRelationshipMap.keySet();
            houseRelationshipService.delByIds(new ArrayList<Integer>(delIds));

            houseRelationshipService.batchInsert(toAddHouseRelationships);
        }

        //申请人其他信息
        HouseOtherModel houseOtherModel = identityInfoModel.getHouseOtherModel();
        if (houseOtherModel != null) {
            if (houseOtherModel.getId() != null) {
                houseOtherService.update(houseOtherModel);
            } else {
                houseOtherModel.setIdentityInfoId(identityInfoModel.getId());
                houseOtherService.insert(houseOtherModel);
            }
        }

        //职业资格证书
        HouseProfessionModel houseProfessionModel = identityInfoModel.getHouseProfessionModel();
        if (houseProfessionModel != null) {
            if (houseProfessionModel.getProfessionType() == 1) {
                houseProfessionModel.setJobLevel(0);
                houseProfessionModel.setJobType(0);
                houseProfessionModel.setJobName(0);

                houseProfessionModel.setJobTitleLevel(0);
                houseProfessionModel.setJobPosition(StringUtils.EMPTY);
                houseProfessionModel.setProfessionTitle(0);

                houseProfessionModel.setIssuingAuthority(StringUtils.EMPTY);
                houseProfessionModel.setIssuingDate(StringUtils.EMPTY);
                houseProfessionModel.setCertificateCode(StringUtils.EMPTY);
            }
            if (houseProfessionModel.getProfessionType() == 2) {
                houseProfessionModel.setJobLevel(0);
                houseProfessionModel.setJobType(0);
                houseProfessionModel.setJobName(0);
            }

            if (houseProfessionModel.getProfessionType() == 3) {
                houseProfessionModel.setJobTitleLevel(0);
                houseProfessionModel.setJobPosition(StringUtils.EMPTY);
                houseProfessionModel.setProfessionTitle(0);
            }

            /*if (houseProfessionModel.getProfessionType() == 1 || houseProfessionModel.getProfessionType() == 2) {
                houseProfessionModel.setJobLevel(0);
                houseProfessionModel.setJobType(0);
            }

            if (houseProfessionModel.getProfessionType() == 1 || houseProfessionModel.getProfessionType() == 3) {
                houseProfessionModel.setJobTitleLevel(0);
                houseProfessionModel.setJobPosition(StringUtils.EMPTY);
                *//*houseProfessionModel.setIssuingAuthority(StringUtils.EMPTY);
                houseProfessionModel.setIssuingDate(StringUtils.EMPTY);
                houseProfessionModel.setCertificateCode(StringUtils.EMPTY);*//*
            }*/
            if (houseProfessionModel.getId() != null) {
                houseProfessionService.update(houseProfessionModel);
            } else {
                houseProfessionModel.setIdentityInfoId(identityInfoModel.getId());
                houseProfessionService.insert(houseProfessionModel);
            }
        }

        return true;
    }

    /**
     * 自助评测
     *
     * @param indicatorView
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public boolean autoEvaluation(IndicatorView indicatorView, BasicConfModel basicConfModel, CompanyInfoModel currUser) throws Exception {
        /**
         * 1.年龄,2.受教育程度,3.专业技术、职业技能水平,4.社会保险,5.住房公积金,6.住房,7.在津连续居住年限,8.职业（工种）,
         * 9.落户地区,10.纳税,11.婚姻情况,12.知识产权,13.奖项和荣誉称号,14.退役军人,15.守法诚信
         **/

        Date currDate = new Date();

        IdentityInfoModel identityInfoModel = identityInfoService.getById(indicatorView.getIdentityInfoId());

        List<IndicatorSelfTestResultModel> toAddScoreResults = new ArrayList<IndicatorSelfTestResultModel>();

        Map<Integer, IndicatorModel> indicatorModelMap = indicatorService.getAllMapIndicator();

        if (CollectionUtils.isNotEmpty(indicatorView.getIndicatorItemList())) {

            /*
            2020年1月9日，获取“住房区域”
            区域分值：
                “和平区/河东区/河西区/南开区/河北区/红桥区”10分；
                “东丽区/西青区/津南区/北辰区”11分；
                “武清区/宝坻区/宁河区/静海区/蓟州区”12分；
                “滨海新区”15分。
             */
            int area = 0;
            for (IndicatorItemView indicatorItemView : indicatorView.getIndicatorItemList()) {
                if(indicatorItemView.getIndexNum() == 27){
                    area = indicatorItemView.getIndicatorItemId();
                }
            }
            //初始化评测信息
            for (IndicatorItemView indicatorItemView : indicatorView.getIndicatorItemList()) {
                IndicatorModel indicatorModel = indicatorModelMap.get((int) indicatorItemView.getIndicatorId());
                if (indicatorModel != null) {
                    Map<String, Object> monthMap = new HashMap<String, Object>();
                    if (indicatorItemView.getIndexNum() == 4) {
                        //参加社会保险按险种计分。缴费不满60个月的，每年最高积12分，
                        //其中：参加基本养老保险（4分），参加基本医疗保险、失业保险、工伤保险、生育保险（各2分）；
                        //缴费满60个月的，自满60个月的下一月起，每年最高积18分，
                        //其中：参加基本养老保险（6分），参加基本医疗保险、失业保险、工伤保险、生育保险（各3分）。

                        /*
                        2019年12月16日
                        最新分数打分规则：社会保险

                        “请输入在津单位正常缴纳社会保险月数”
                        单位正常缴纳基本养老保险    月	a
                        单位正常缴纳基本医疗保险    月	b
                        单位正常缴纳失业保险    月	c
                        单位正常缴纳工伤保险    月	d
                        单位正常缴纳生育保险    月	e
                        计算公式：
                        条件	结果	        条件	结果
                        a>60	40+10/12*(a-60)	a<=60	8/12*a
                        b>60	10+3/12*(b-60)	b<=60	2/12*b
                        c>60	10+3/12*(c-60)	c<=60	2/12*c
                        d>60	10+3/12*(d-60)	d<=60	2/12*d
                        e>60	10+3/12*(e-60)	e<=60	2/12*e
                         */
                        int pensionScore = 0;
                        if (indicatorView.getPensionMonth() > 36) {
                            pensionScore = 40 + (indicatorView.getPensionMonth()-36)*10 / 12;
                        } else {
                            pensionScore = indicatorView.getPensionMonth()*8 / 12;
                        }
                        int medicalScore = 0;
                        if (indicatorView.getMedicalMonth() > 36) {
                            medicalScore = 10 + (indicatorView.getMedicalMonth()-36)*3 / 12;
                        } else {
                            medicalScore = (indicatorView.getMedicalMonth()*2) / 12;
                        }
                        int unemploymentScore = 0;
                        if (indicatorView.getUnemploymentMonth() > 36) {
                            unemploymentScore = 10 + (indicatorView.getUnemploymentMonth()-36)*3 / 12;
                        } else {
                            unemploymentScore = (indicatorView.getUnemploymentMonth()*2) / 12;
                        }
                        int workInjuryScore = 0;
                        if (indicatorView.getWorkInjuryMonth() > 36) {
                            workInjuryScore = 10 + (indicatorView.getWorkInjuryMonth()-36)*3 / 12;
                        } else {
                            workInjuryScore = (indicatorView.getWorkInjuryMonth()*2) / 12;
                        }
                        int fertilityScore = 0;
                        if (indicatorView.getFertilityMonth() > 36) {
                            fertilityScore = 10 + (indicatorView.getFertilityMonth()-36)*3 / 12;
                        } else {
                            fertilityScore = (indicatorView.getFertilityMonth()*2) / 12;
                        }
                        int totalScore =
                                pensionScore + medicalScore + unemploymentScore + workInjuryScore + fertilityScore;

                        indicatorItemView.setScoreValue(new BigDecimal(totalScore));

                        monthMap.put("pensionMonth", new Integer[]{indicatorView.getPensionMonth(), pensionScore});
                        monthMap.put("medicalMonth", new Integer[]{indicatorView.getMedicalMonth(), medicalScore});
                        monthMap.put("unemploymentMonth",
                                new Integer[]{indicatorView.getUnemploymentMonth(), unemploymentScore});
                        monthMap.put("workInjuryMonth",
                                new Integer[]{indicatorView.getWorkInjuryMonth(), workInjuryScore});
                        monthMap.put("fertilityMonth",
                                new Integer[]{indicatorView.getFertilityMonth(), fertilityScore});
                        indicatorItemView.setScoreDetail(JSON.toJSONString(monthMap));
                    } else if (indicatorItemView.getIndexNum() == 5) {
                        //参加住房公积金的每年积2分。
                        /*
                        2019年12月16日，开始新的打分规则
                        3、住房公积金
                        “请输入在津缴纳住房公积金月数”
                        条件	结果	        条件	结果
                        f>60	15+4/12*(f-60)	f<=60	3/12*f
                         */
                        int fundScore = 0;
                        if(indicatorView.getFundMonth()>60){
                            fundScore = (indicatorView.getFundMonth()-60) / 3;
                        }else {
                            fundScore = indicatorView.getFundMonth() / 4;
                        }
                        indicatorItemView.setScoreValue(new BigDecimal(fundScore));
                        monthMap.put("fundMonth", new Integer[]{indicatorView.getFundMonth(), fundScore});
                        indicatorItemView.setScoreDetail(JSON.toJSONString(monthMap));
                    } else if (indicatorItemView.getIndexNum() == 7) {
                        int liveYearScore = indicatorView.getLiveYear() * 6;
                        indicatorItemView.setScoreValue(new BigDecimal(liveYearScore));
                        monthMap.put("liveYear", new Integer[]{indicatorView.getLiveYear(), liveYearScore});
                        indicatorItemView.setScoreDetail(JSON.toJSONString(monthMap));
                    } else if (indicatorItemView.getIndexNum() == 23) { // 租赁住房月数
                        /*
                        2020年1月9日
                        输入项：租赁住房月数，租赁积分=租赁月份数×6/12
                         */
                        int rentHouseMonth = indicatorView.getRentHouseMonth()/2;
                        indicatorItemView.setScoreValue(new BigDecimal(rentHouseMonth));
                        monthMap.put("rentHouseMonth", new Integer[]{indicatorView.getRentHouseMonth(), rentHouseMonth});
                        indicatorItemView.setScoreDetail(JSON.toJSONString(monthMap));
                    } else if (indicatorItemView.getIndexNum() == 26) { // 购买住房月数
                        /*
                        页面3中：如选择“不按照“津发改社会〔2018〕26号”文件计算”
                        输入项：购买住房月数，住房积分=有效住房持有月份数×住房所在区域年积分分值/12

                        区域分值：“和平区/河东区/河西区/南开区/河北区/红桥区”10分；
                                  “东丽区/西青区/津南区/北辰区”11分；
                                  “武清区/宝坻区/宁河区/静海区/蓟州区”12分；
                                  “滨海新区”15分。
                         */
                        int areaScore = 0;
                        switch (area){
                            case 1037:
                                areaScore = 10;
                                break;
                            case 1038:
                                areaScore = 11;
                                break;
                            case 1039:
                                areaScore = 12;
                                break;
                            case 1040:
                                areaScore = 15;
                                break;
                            case 1041:
                                areaScore = 0;
                                break;
                        }
                        int buyHouseMonth = indicatorView.getBuyHouseMonth()*areaScore/12;
                        indicatorItemView.setScoreValue(new BigDecimal(buyHouseMonth));
                        monthMap.put("buyHouseMonth", new Integer[]{indicatorView.getLiveYear(), buyHouseMonth});
                        indicatorItemView.setScoreDetail(JSON.toJSONString(monthMap));
                    } else {
                        List<IndicatorItemModel> indicatorItems = indicatorModel.getIndicatorItems();
                        if (CollectionUtils.isNotEmpty(indicatorItems)) {
                            for (IndicatorItemModel indicatorItemModel : indicatorItems) {
                                if ((int) indicatorItemModel.getId() == indicatorItemView.getIndicatorItemId()) {
                                    indicatorItemView.setScoreValue(new BigDecimal(indicatorItemModel.getScore()));
                                    break;
                                }
                            }
                        }
                    }

                    indicatorItemView.setIndicatorName(indicatorModel.getName());
                }
            }

            for (IndicatorItemView indicatorItemView : indicatorView.getIndicatorItemList()) {
                if (indicatorItemView.getIndicatorItemId() == null) {
                    indicatorItemView.setIndicatorItemId(0);
                }

                IndicatorSelfTestResultModel indicatorSelfTestResult = new IndicatorSelfTestResultModel(
                        indicatorItemView.getIndicatorId(), indicatorItemView.getIndicatorItemId(),
                        indicatorItemView.getIndicatorName(), indicatorItemView.getScoreValue());

                if (StringUtils.isNotEmpty(indicatorItemView.getScoreDetail())) {
                    indicatorSelfTestResult.setScoreDetail(indicatorItemView.getScoreDetail());
                } else {
                    indicatorSelfTestResult.setScoreDetail(StringUtils.EMPTY);
                }

                toAddScoreResults.add(indicatorSelfTestResult);
            }
        }

        BigDecimal totalDecimal = new BigDecimal(0);
        //初始化分数结果信息
        if (CollectionUtils.isNotEmpty(toAddScoreResults)) {
            for (IndicatorSelfTestResultModel selfTestResult : toAddScoreResults) {
                selfTestResult.setBatchId(identityInfoModel.getBatchId());
                selfTestResult.setPersonId(identityInfoModel.getId());
                selfTestResult.setCreateTime(currDate);
                selfTestResult.setAddUser(String.valueOf(currUser.getId()));

                if (selfTestResult.getScoreValue() == null) {
                    selfTestResult.setScoreValue(BigDecimal.ZERO);
                }
                totalDecimal = totalDecimal.add(selfTestResult.getScoreValue());
            }

            indicatorSelfTestResultService.batchInsert(toAddScoreResults);
        }

        IdentityInfoModel updateIdentityInfo = new IdentityInfoModel();
        updateIdentityInfo.setId(indicatorView.getIdentityInfoId());
        updateIdentityInfo.setAutoTestNum(1);
        //自助测评通过
        if (totalDecimal.compareTo(basicConfModel.getSelfTestScoreLine()) >= 0) {
            updateIdentityInfo.setReservationStatus(Constant.reservationStatus_6);
            updateIdentityInfo.setScore(totalDecimal);
            identityInfoService.update(updateIdentityInfo);
            //记录状态日志信息
            DictModel dictModel = dictService.findByAliasAndValue("reservationStatus", Constant.reservationStatus_6);
            if (dictModel != null) {
                PersonBatchStatusRecordModel recordModel = new PersonBatchStatusRecordModel(identityInfoModel,
                        dictModel, "自助测评通过, 测评分数为:" + totalDecimal.toString());
                personBatchStatusRecordService.insert(recordModel);
            }
            return true;
        } else {
            updateIdentityInfo.setReservationStatus(Constant.reservationStatus_5);
            identityInfoService.update(updateIdentityInfo);

            //记录状态日志信息
            DictModel dictModel = dictService.findByAliasAndValue("reservationStatus", Constant.reservationStatus_5);
            if (dictModel != null) {
                PersonBatchStatusRecordModel recordModel = new PersonBatchStatusRecordModel(identityInfoModel,
                        dictModel, "自助测评未通过, 测评分数为:" + totalDecimal.toString());
                personBatchStatusRecordService.insert(recordModel);
            }
            return false;
        }
    }
}
