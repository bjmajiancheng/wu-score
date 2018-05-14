/*
 * 北京果敢时代科技有限公司
 * 北京市朝阳区望京SOHO T3 B座1607
 * 邮编：100022
 * 网址：www.davdian.com
 */

package com.wutuobang.score.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.wutuobang.score.model.IndicatorSelfTestResultModel;

import java.util.*;

import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author davdian
 * @version 1.0
 * @since 1.0
 */

@Service("indicatorSelfTestResultService")
public class IndicatorSelfTestResultServiceImpl implements IIndicatorSelfTestResultService {

    @Autowired
    private IIndicatorSelfTestResultDao indicatorSelfTestResultDao;

    public int insert(IndicatorSelfTestResultModel indicatorSelfTestResult) {
        if (indicatorSelfTestResult == null) {
            return 0;
        }
        return indicatorSelfTestResultDao.insert(indicatorSelfTestResult);
    }

    public int update(IndicatorSelfTestResultModel indicatorSelfTestResult) {
        if (indicatorSelfTestResult == null) {
            return 0;
        }
        return indicatorSelfTestResultDao.update(indicatorSelfTestResult);
    }

    public IndicatorSelfTestResultModel getById(Integer id) {
        if (id == null) {
            return null;
        }
        return indicatorSelfTestResultDao.getById(id);
    }

    public int removeById(Integer id) {
        if (id == null) {
            return 0;
        }
        return indicatorSelfTestResultDao.delete(id);
    }

    public List<IndicatorSelfTestResultModel> find(Map<String, Object> param) {
        return indicatorSelfTestResultDao.find(param);
    }

    /**
     * 批量新增用户自测信息结果
     *
     * @param selfTestResults
     * @return
     */
    public int batchInsert(List<IndicatorSelfTestResultModel> selfTestResults) {
        if (CollectionUtils.isEmpty(selfTestResults)) {
            return 0;
        }

        return indicatorSelfTestResultDao.batchInsert(selfTestResults);
    }

    /**
     * 根据申请人id获取自测Map信息
     *
     * @param identityInfoId
     * @return
     */
    public Map<Integer, IndicatorSelfTestResultModel> findMapByIdentityInfoId(Integer identityInfoId) {
        if (identityInfoId == null) {
            return Collections.emptyMap();
        }

        List<IndicatorSelfTestResultModel> selfTestResultModels = this
                .find(Collections.singletonMap("personId", (Object) identityInfoId));
        if (CollectionUtils.isEmpty(selfTestResultModels)) {
            return Collections.emptyMap();
        }

        Map<Integer, IndicatorSelfTestResultModel> selfTestResultMap = new HashMap<Integer, IndicatorSelfTestResultModel>();
        for (IndicatorSelfTestResultModel selfTestResult : selfTestResultModels) {
            selfTestResultMap.put(selfTestResult.getIndicatorId(), selfTestResult);
        }

        return selfTestResultMap;
    }

}
