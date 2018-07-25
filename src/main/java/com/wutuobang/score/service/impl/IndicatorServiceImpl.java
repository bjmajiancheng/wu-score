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

import com.wutuobang.common.utils.NumberUtil;
import com.wutuobang.score.model.*;
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
        List<IndicatorModel> indicatorModels = indicatorDao.getAllIndicators();

        Map<Integer, List<IndicatorItemModel>> indicatorItemMap = indicatorItemService.getAllMapItemInfo();
        if (CollectionUtils.isNotEmpty(indicatorModels)) {
            for (IndicatorModel indicatorModel : indicatorModels) {
                List<IndicatorItemModel> indicatorItems = indicatorItemMap.get(indicatorModel.getId());
                indicatorModel.setIndicatorItems(indicatorItems);
            }
        }
        return indicatorModels;
    }

    /**
     * 获取所有指标map信息
     *
     * @return
     */
    public Map<Integer, IndicatorModel> getAllMapIndicator() {
        List<IndicatorModel> indicatorModels = this.getAllIndicators();
        if (CollectionUtils.isEmpty(indicatorModels)) {
            return Collections.emptyMap();
        }

        Map<Integer, IndicatorModel> indicatorModelMap = new HashMap<Integer, IndicatorModel>(indicatorModels.size());
        for (IndicatorModel indicatorModel : indicatorModels) {
            indicatorModelMap.put(indicatorModel.getId(), indicatorModel);
        }

        return indicatorModelMap;
    }

    /**
     * 初始化自助评测信息
     *
     * @param identityInfo    申请人信息
     * @param indicatorModels 所有指标信息
     * @return
     */
    public void initIndicatorView(IdentityInfoModel identityInfo, List<IndicatorModel> indicatorModels) {

        /** 申请人信息 */
        HouseOtherModel houseOtherModel = identityInfo.getHouseOtherModel();
        HouseProfessionModel houseProfession = identityInfo.getHouseProfessionModel();
        HouseMoveModel houseMoveModel = identityInfo.getHouseMoveModel();

        /**
         * 1.年龄,2.受教育程度,3.专业技术、职业技能水平,4.社会保险,5.住房公积金,6.住房,7.在津连续居住年限,8.职业（工种）,
         * 9.落户地区,10.纳税,11.婚姻情况,12.知识产权,13.奖项和荣誉称号,14.退役军人,15.守法诚信
         **/
        if (CollectionUtils.isNotEmpty(indicatorModels)) {
            for (IndicatorModel indicatorModel : indicatorModels) {
                List<IndicatorItemModel> indicatorItems = indicatorModel.getIndicatorItems();

                Map<String, IndicatorItemModel> indicatorItemMap = new HashMap<String, IndicatorItemModel>();
                if (CollectionUtils.isNotEmpty(indicatorItems)) {
                    for (IndicatorItemModel indicatorItem : indicatorItems) {
                        indicatorItemMap.put(indicatorItem.getContent(), indicatorItem);
                    }
                }

                switch (indicatorModel.getIndexNum()) {
                    case 1:
                        Integer age = identityInfo.getAge();
                        for (IndicatorItemModel indicatorItem : indicatorItems) {
                            String[] ageArr = indicatorItem.getScoreFunction().split("-");

                            int minAge = NumberUtil.getInteger(ageArr[0]);
                            int maxAge = NumberUtil.getInteger(ageArr[1]);
                            if (age >= minAge && age <= maxAge) {
                                indicatorItem.setChecked(1);
                                indicatorModel.setDisabled(1);
                                break;
                            }
                        }

                        break;
                    case 2:
                        int cultureDegree = houseOtherModel.getCultureDegree();

                        for (IndicatorItemModel indicatorItem : indicatorItems) {
                            if (indicatorItem.getId() == cultureDegree) {
                                indicatorItem.setChecked(1);
                                indicatorModel.setDisabled(1);
                                break;
                            }
                        }
                        break;
                    case 3:
                        int jobLevel = houseProfession.getJobLevel();

                        for (IndicatorItemModel indicatorItem : indicatorItems) {
                            if (indicatorItem.getId() == jobLevel) {
                                indicatorItem.setChecked(1);
                                indicatorModel.setDisabled(1);
                                break;
                            }

                            if (jobLevel == 0 && indicatorItem.getScore() == 0) {
                                indicatorItem.setChecked(1);
                                indicatorModel.setDisabled(1);
                                break;
                            }
                        }

                        List<IndicatorItemModel> finalIndicatorItems = new ArrayList<IndicatorItemModel>();
                        List<IndicatorItemModel> tmpIndicatorItems = new ArrayList<IndicatorItemModel>();

                        for (IndicatorItemModel indicatorItem : indicatorItems) {
                            if (indicatorItem.getContent().indexOf("具有") > -1) {
                                finalIndicatorItems.add(indicatorItem);
                            } else {
                                tmpIndicatorItems.add(indicatorItem);
                            }
                        }
                        finalIndicatorItems.addAll(tmpIndicatorItems);
                        indicatorModel.setIndicatorItems(finalIndicatorItems);

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
                        int jobType = houseProfession.getJobType();
                        int professionType = houseProfession.getProfessionType();

                        if (professionType == 1 || professionType == 2) {
                            IndicatorItemModel indicatorItem = indicatorItemMap.get("无");
                            if (indicatorItem != null) {
                                indicatorItem.setChecked(1);
                                indicatorModel.setDisabled(1);
                            }
                        } else {
                            for (IndicatorItemModel indicatorItem : indicatorItems) {
                                if (indicatorItem.getId() == jobType) {
                                    indicatorItem.setChecked(1);
                                    indicatorModel.setDisabled(1);
                                    break;
                                }

                                if (jobType == 0 && indicatorItem.getScore() == 0) {
                                    indicatorItem.setChecked(1);
                                    indicatorModel.setDisabled(1);
                                    break;
                                }
                            }
                        }
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
                        int awardsTitle = houseOtherModel.getAwardsTitle();

                        for (IndicatorItemModel indicatorItem : indicatorItems) {
                            if (indicatorItem.getId() == awardsTitle) {
                                indicatorItem.setChecked(1);
                                indicatorModel.setDisabled(1);
                                break;
                            }
                        }
                        break;
                    case 14:
                        int soldierMeritorious = houseOtherModel.getSoldierMeritorious();

                        for (IndicatorItemModel indicatorItem : indicatorItems) {
                            if (indicatorItem.getId() == soldierMeritorious) {
                                indicatorItem.setChecked(1);
                                indicatorModel.setDisabled(1);
                                break;
                            }
                        }
                        break;
                    case 15:
                        int region = houseMoveModel.getRegion();
                        if (region == 33) {
                            IndicatorItemModel indicatorItem = indicatorItemMap.get("申请落户滨海新区");
                            if (indicatorItem != null) {
                                indicatorItem.setChecked(1);
                            }
                        } else if (region == 31 || region == 32 || region == 34 || region == 35 || region == 36) {
                            IndicatorItemModel indicatorItem = indicatorItemMap.get("申请落户武清区、宝坻区、静海区、宁河区、蓟州区");
                            if (indicatorItem != null) {
                                indicatorItem.setChecked(1);
                            }
                        } else {
                            IndicatorItemModel indicatorItem = indicatorItemMap.get("申请落户其他地区");
                            if (indicatorItem != null) {
                                indicatorItem.setChecked(1);
                            }
                        }
                        indicatorModel.setDisabled(1);
                        break;
                }
            }
        }
    }

}
