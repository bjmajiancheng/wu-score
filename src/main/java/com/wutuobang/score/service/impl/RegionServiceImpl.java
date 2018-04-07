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
import com.wutuobang.score.model.RegionModel;

import java.util.*;

import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author davdian
 * @version 1.0
 * @since 1.0
 */

@Service("regionService")
public class RegionServiceImpl implements IRegionService {

    @Autowired
    private IRegionDao regionDao;

    public int insert(RegionModel region) {
        if (region == null) {
            return 0;
        }
        return regionDao.insert(region);
    }

    public int update(RegionModel region) {
        if (region == null) {
            return 0;
        }
        return regionDao.update(region);
    }

    public RegionModel getById(Integer id) {
        if (id == null) {
            return null;
        }
        return regionDao.getById(id);
    }

    public int removeById(Integer id) {
        if (id == null) {
            return 0;
        }
        return regionDao.delete(id);
    }

    public List<RegionModel> find(Map<String, Object> param) {
        return regionDao.find(param);
    }

    /**
     * 根据父地区id获取地区集合信息
     *
     * @param regionId
     * @return
     */
    public List<RegionModel> getByParendId(Integer regionId) {
        if (regionId == null) {
            return Collections.emptyList();
        }

        return this.find(Collections.singletonMap("parentId", (Object) regionId));
    }

    /**
     * 根据id集合获取地区信息
     *
     * @param ids
     * @return
     */
    @Override
    public List<RegionModel> getByIds(List<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return this.find(Collections.singletonMap("ids", (Object) ids));
    }

    @Override
    public Map<Integer, RegionModel> getMapByIds(List<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyMap();
        }
        List<RegionModel> regionModels = this.getByIds(ids);

        Map<Integer, RegionModel> regionMap = new HashMap<Integer, RegionModel>();
        for (RegionModel regionModel : regionModels) {
            regionMap.put(regionModel.getId(), regionModel);
        }
        return regionMap;
    }

}
