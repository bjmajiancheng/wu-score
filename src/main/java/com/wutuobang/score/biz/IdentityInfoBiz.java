package com.wutuobang.score.biz;

import com.alibaba.fastjson.JSON;
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
            CompanyInfoModel currUser) throws Exception {

        Date currDate = new Date();
        if (identityInfoModel != null) {
            identityInfoModel.setBatchId(batchConfModel.getId());
            identityInfoModel.setCompanyId(currUser.getId());
            //初始化状态信息
            identityInfoModel.setReservationStatus(Constant.reservationStatus_1);//申请预约状态
            identityInfoModel.setHallStatus(Constant.hallStatus_0);//预约大厅状态
            identityInfoModel.setUnionApproveStatus1(Constant.unionApproveStatus1_0);//公安预审状态
            identityInfoModel.setUnionApproveStatus2(Constant.unionApproveStatus2_0);//人社预审状态
            identityInfoModel.setPoliceApproveStatus(Constant.policeApproveStatus_0);//公安前置预审状态
            identityInfoModel.setRensheAcceptStatus(Constant.rensheAcceptStatus_0);//人社受理状态:默认值
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

            identityInfoService.insert(identityInfoModel);

            //记录状态日志信息
            DictModel dictModel = dictService.findByAliasAndValue("reservationStatus", Constant.reservationStatus_1);
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
            if (houseMoveModel.getSonNumber() == null) {
                houseMoveModel.setSonNumber(0);
            }
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
            if (houseProfessionModel.getProfessionType() == 1 || houseProfessionModel.getProfessionType() == 2) {
                houseProfessionModel.setJobLevel(0);
                houseProfessionModel.setJobType(0);
            }

            if (houseProfessionModel.getProfessionType() == 1 || houseProfessionModel.getProfessionType() == 3) {
                houseProfessionModel.setJobTitleLevel(0);
                houseProfessionModel.setJobPosition(StringUtils.EMPTY);
                houseProfessionModel.setIssuingAuthority(StringUtils.EMPTY);
                houseProfessionModel.setIssuingDate(StringUtils.EMPTY);
                houseProfessionModel.setCertificateCode(StringUtils.EMPTY);
            }
            houseProfessionService.insert(houseProfessionModel);
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
            if (houseProfessionModel.getProfessionType() == 1 || houseProfessionModel.getProfessionType() == 2) {
                houseProfessionModel.setJobLevel(0);
                houseProfessionModel.setJobType(0);
            }

            if (houseProfessionModel.getProfessionType() == 1 || houseProfessionModel.getProfessionType() == 3) {
                houseProfessionModel.setJobTitleLevel(0);
                houseProfessionModel.setJobPosition(StringUtils.EMPTY);
                houseProfessionModel.setIssuingAuthority(StringUtils.EMPTY);
                houseProfessionModel.setIssuingDate(StringUtils.EMPTY);
                houseProfessionModel.setCertificateCode(StringUtils.EMPTY);
            }
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

                        int pensionScore = 0;
                        if (indicatorView.getPensionMonth() >= 60) {
                            pensionScore = indicatorView.getPensionMonth() / 2;
                        } else {
                            pensionScore = indicatorView.getPensionMonth() / 3;
                        }
                        int medicalScore = 0;
                        if (indicatorView.getMedicalMonth() >= 60) {
                            medicalScore = indicatorView.getMedicalMonth() / 4;
                        } else {
                            medicalScore = indicatorView.getMedicalMonth() / 6;
                        }
                        int unemploymentScore = 0;
                        if (indicatorView.getUnemploymentMonth() >= 60) {
                            unemploymentScore = indicatorView.getUnemploymentMonth() / 4;
                        } else {
                            unemploymentScore = indicatorView.getUnemploymentMonth() / 6;
                        }
                        int workInjuryScore = 0;
                        if (indicatorView.getWorkInjuryMonth() >= 60) {
                            workInjuryScore = indicatorView.getWorkInjuryMonth() / 4;
                        } else {
                            workInjuryScore = indicatorView.getWorkInjuryMonth() / 6;
                        }
                        int fertilityScore = 0;
                        if (indicatorView.getFertilityMonth() >= 60) {
                            fertilityScore = indicatorView.getFertilityMonth() / 4;
                        } else {
                            fertilityScore = indicatorView.getFertilityMonth() / 6;
                        }
                        int totalScore =
                                pensionScore + medicalScore + unemploymentScore + workInjuryScore + fertilityScore;

                        indicatorItemView.setScoreValue(new BigDecimal(totalScore));

                        monthMap.put("pensionMonth", new Integer[] { indicatorView.getPensionMonth(), pensionScore });
                        monthMap.put("medicalMonth", new Integer[] { indicatorView.getMedicalMonth(), medicalScore });
                        monthMap.put("unemploymentMonth",
                                new Integer[] { indicatorView.getUnemploymentMonth(), unemploymentScore });
                        monthMap.put("workInjuryMonth",
                                new Integer[] { indicatorView.getWorkInjuryMonth(), workInjuryScore });
                        monthMap.put("fertilityMonth",
                                new Integer[] { indicatorView.getFertilityMonth(), fertilityScore });
                        indicatorItemView.setScoreDetail(JSON.toJSONString(monthMap));
                    } else if (indicatorItemView.getIndexNum() == 5) {
                        //参加住房公积金的每年积2分。
                        int fundScore = indicatorView.getFundMonth() / 6;
                        indicatorItemView.setScoreValue(new BigDecimal(fundScore));
                        monthMap.put("fundMonth", new Integer[] { indicatorView.getFundMonth(), fundScore });
                        indicatorItemView.setScoreDetail(JSON.toJSONString(monthMap));
                    } else if (indicatorItemView.getIndexNum() == 7) {
                        int liveYearScore = indicatorView.getLiveYear() * 6;
                        indicatorItemView.setScoreValue(new BigDecimal(liveYearScore));
                        monthMap.put("liveYear", new Integer[] { indicatorView.getLiveYear(), liveYearScore });
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
