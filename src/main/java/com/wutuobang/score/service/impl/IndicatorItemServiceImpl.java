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
import com.wutuobang.score.model.IndicatorItemModel;

import java.util.*;

import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author davdian
 * @version 1.0
 * @since 1.0
 */

@Service("indicatorItemService")
public class IndicatorItemServiceImpl implements IIndicatorItemService {

    @Autowired
    private IIndicatorItemDao indicatorItemDao;

    public int insert(IndicatorItemModel indicatorItem) {
        if (indicatorItem == null) {
            return 0;
        }
        return indicatorItemDao.insert(indicatorItem);
    }

    public int update(IndicatorItemModel indicatorItem) {
        if (indicatorItem == null) {
            return 0;
        }
        return indicatorItemDao.update(indicatorItem);
    }

    public IndicatorItemModel getById(Integer id) {
        if (id == null) {
            return null;
        }
        return indicatorItemDao.getById(id);
    }

    public int removeById(Integer id) {
        if (id == null) {
            return 0;
        }
        return indicatorItemDao.delete(id);
    }

    public List<IndicatorItemModel> find(Map<String, Object> param) {
        return indicatorItemDao.find(param);
    }

    /**
     * 获取所有指标选项信息
     *
     * @return
     */
    public Map<Integer, List<IndicatorItemModel>> getAllMapItemInfo() {
        List<IndicatorItemModel> indicatorItems = this.find(new HashMap<String, Object>());
        if (CollectionUtils.isEmpty(indicatorItems)) {
            return Collections.emptyMap();
        }

        Map<Integer, List<IndicatorItemModel>> indicatorItemMap = new HashMap<Integer, List<IndicatorItemModel>>();
        for (IndicatorItemModel indicatorItem : indicatorItems) {
            int indicatorId = indicatorItem.getIndicatorId();
            List<IndicatorItemModel> tempItems = indicatorItemMap.get(indicatorId);
            if(CollectionUtils.isEmpty(tempItems)) {
                tempItems = new ArrayList<IndicatorItemModel>();
            }
            tempItems.add(indicatorItem);
            indicatorItemMap.put(indicatorId, tempItems);
        }

        return indicatorItemMap;
    }

}
