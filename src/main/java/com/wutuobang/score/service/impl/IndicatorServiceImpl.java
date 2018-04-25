/*
 * 北京果敢时代科技有限公司
 * 北京市朝阳区望京SOHO T3 B座1607
 * 邮编：100022
 * 网址：www.davdian.com
 */

package com.wutuobang.score.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.wutuobang.score.constant.EnumConstant;
import com.wutuobang.score.model.*;
import com.wutuobang.score.view.IndicatorItemView;
import com.wutuobang.score.view.IndicatorView;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author davdian
 * @version 1.0
 * @since 1.0
 */

@Service("indicatorService")
public class IndicatorServiceImpl implements IIndicatorService {

    @Autowired
    private IIndicatorDao indicatorDao;

    @Autowired
    private IIndicatorItemService indicatorItemService;

    public int insert(IndicatorModel indicator) {
        if (indicator == null) {
            return 0;
        }
        return indicatorDao.insert(indicator);
    }

    public int update(IndicatorModel indicator) {
        if (indicator == null) {
            return 0;
        }
        return indicatorDao.update(indicator);
    }

    public IndicatorModel getById(Integer id) {
        if (id == null) {
            return null;
        }
        return indicatorDao.getById(id);
    }

    public int removeById(Integer id) {
        if (id == null) {
            return 0;
        }
        return indicatorDao.delete(id);
    }

    public List<IndicatorModel> find(Map<String, Object> param) {
        return indicatorDao.find(param);
    }

    /**
     * 获取所有指标信息
     *
     * @return
     */
    public List<IndicatorModel> getAllIndicators() {
        List<IndicatorModel> indicatorModels = this.find(new HashMap<String, Object>());

        Map<Integer, List<IndicatorItemModel>> indicatorItemMap = indicatorItemService.getAllMapItemInfo();
        if (CollectionUtils.isNotEmpty(indicatorModels)) {
            for (IndicatorModel indicatorModel : indicatorModels) {
                List<IndicatorItemModel> indicatorItems = indicatorItemMap.get(indicatorModel.getId());

                Map<String, IndicatorItemModel> tempItemMap = new HashMap<String, IndicatorItemModel>();
                if (CollectionUtils.isNotEmpty(indicatorItems)) {
                    for (IndicatorItemModel indicatorItem : indicatorItems) {
                        tempItemMap.put(indicatorItem.getContent(), indicatorItem);
                    }
                }

                indicatorModel.setIndicatorItemMap(tempItemMap);
            }
        }
        return indicatorModels;
    }

    /**
     * 初始化自助评测信息
     *
     * @param identityInfo    申请人信息
     * @param indicatorModels 所有指标信息
     * @return
     */
    public IndicatorView initIndicatorView(IdentityInfoModel identityInfo, List<IndicatorModel> indicatorModels) {
        IndicatorView indicatorView = new IndicatorView();

        /** 申请人信息 */
        HouseOtherModel houseOtherModel = identityInfo.getHouseOtherModel();
        HouseProfessionModel houseProfession = identityInfo.getHouseProfessionModel();

        /**
         * 1.年龄,2.受教育程度,3.专业技术、职业技能水平,4.社会保险,5.住房公积金,6.住房,7.在津连续居住年限,8.职业（工种）,
         * 9.落户地区,10.纳税,11.婚姻情况,12.知识产权,13.奖项和荣誉称号,14.退役军人,15.守法诚信
         **/
        if (CollectionUtils.isNotEmpty(indicatorModels)) {
            for (IndicatorModel indicatorModel : indicatorModels) {
                Map<String, IndicatorItemModel> indicatorItemMap = indicatorModel.getIndicatorItemMap();

                switch (indicatorModel.getIndexNum()) {
                    case 1:
                        IndicatorItemView ageItem = null;
                        Integer age = identityInfo.getAge();
                        if (age >= 36 && age <= 45) {
                            ageItem = new IndicatorItemView(indicatorModel.getIndexNum(), 1, "35周岁以下（含35周岁，20分）",
                                    new BigDecimal(20));
                        } else if (age <= 35) {
                            ageItem = new IndicatorItemView(indicatorModel.getIndexNum(), 2, "36至45周岁（10分）",
                                    new BigDecimal(10));
                        } else {
                            ageItem = new IndicatorItemView(indicatorModel.getIndexNum(), 3, "无", new BigDecimal(0));
                        }
                        indicatorView.setAgeItem(ageItem);
                        break;
                    case 2:
                        Integer cultureDegree = houseOtherModel.getCultureDegree();

                        String cultureDegreeText = EnumConstant.cultureDegreeArr[cultureDegree];

                        IndicatorItemModel educationItem = indicatorItemMap.get(cultureDegreeText);
                        IndicatorItemView educationView = new IndicatorItemView(indicatorModel.getIndexNum(),
                                cultureDegree, cultureDegreeText,
                                educationItem == null ? BigDecimal.ZERO : new BigDecimal(educationItem.getScore()));

                        indicatorView.setEducationItem(educationView);
                        break;
                    case 3:
                        Integer jobLevel = houseProfession.getJobLevel();

                        String jobLevelText = EnumConstant.jobLevelArr[jobLevel];

                        IndicatorItemModel jobLevelItem = indicatorItemMap.get(jobLevelText);
                        IndicatorItemView jobLevelView = new IndicatorItemView(indicatorModel.getIndexNum(), jobLevel,
                                jobLevelText,
                                jobLevelItem == null ? BigDecimal.ZERO : new BigDecimal(jobLevelItem.getScore()));

                        indicatorView.setSkillItem(jobLevelView);
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        Integer jobType = houseProfession.getJobType();
                        String jobTyleText = EnumConstant.jobTypeArr[jobType];

                        IndicatorItemModel jobTypeItem = indicatorItemMap.get(jobTyleText);
                        IndicatorItemView jobTypeView = new IndicatorItemView(indicatorModel.getIndexNum(), jobType,
                                jobTyleText,
                                jobTypeItem == null ? BigDecimal.ZERO : new BigDecimal(jobTypeItem.getScore()));

                        indicatorView.setWorkTypeItem(jobTypeView);
                        break;
                    case 9:
                        break;
                    case 10:
                        break;
                    case 11:
                        break;
                    case 12:
                        break;
                    case 13:
                        Integer awardsTitle = houseOtherModel.getAwardsTitle();
                        String awardsTitleText = EnumConstant.awardsArr[awardsTitle];

                        IndicatorItemModel awardsItem = indicatorItemMap.get(awardsTitleText);
                        IndicatorItemView awardsView = new IndicatorItemView(indicatorModel.getIndexNum(), awardsTitle,
                                awardsTitleText,
                                awardsItem == null ? BigDecimal.ZERO : new BigDecimal(awardsItem.getScore()));

                        indicatorView.setAwardsItem(awardsView);
                        break;
                    case 14:
                        Integer soldierMeritorious = houseOtherModel.getSoldierMeritorious();
                        String soldierText = EnumConstant.soldierArr[soldierMeritorious];

                        IndicatorItemModel soldierItem = indicatorItemMap.get(soldierText);
                        IndicatorItemView soldierView = new IndicatorItemView(indicatorModel.getIndexNum(),
                                soldierMeritorious, soldierText,
                                soldierItem == null ? BigDecimal.ZERO : new BigDecimal(soldierItem.getScore()));

                        indicatorView.setSoldierItem(soldierView);
                        break;
                    case 15:
                        break;
                }
            }
        }

        return indicatorView;
    }

}
