package com.wutuobang.score;

import com.wutuobang.common.utils.ResultParam;
import com.wutuobang.score.constant.Constant;
import com.wutuobang.score.model.*;
import com.wutuobang.score.service.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private IBasicConfService basicConfService;

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
            identityInfoModel.setScore(BigDecimal.ZERO);

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
}
