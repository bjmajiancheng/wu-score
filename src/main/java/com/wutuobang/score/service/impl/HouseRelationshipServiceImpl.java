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
import com.wutuobang.score.model.HouseRelationshipModel;

import java.util.*;

import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author davdian
 * @version 1.0
 * @since 1.0
 */

@Service("houseRelationshipService")
public class HouseRelationshipServiceImpl implements IHouseRelationshipService {

    @Autowired
    private IHouseRelationshipDao houseRelationshipDao;

    public int insert(HouseRelationshipModel houseRelationship) {
        if (houseRelationship == null) {
            return 0;
        }
        return houseRelationshipDao.insert(houseRelationship);
    }

    public int update(HouseRelationshipModel houseRelationship) {
        if (houseRelationship == null) {
            return 0;
        }
        return houseRelationshipDao.update(houseRelationship);
    }

    public HouseRelationshipModel getById(Integer id) {
        if (id == null) {
            return null;
        }
        return houseRelationshipDao.getById(id);
    }

    public int removeById(Integer id) {
        if (id == null) {
            return 0;
        }
        return houseRelationshipDao.delete(id);
    }

    public List<HouseRelationshipModel> find(Map<String, Object> param) {
        return houseRelationshipDao.find(param);
    }

    /**
     * 批量添加家庭关系信息集合
     *
     * @param houseRelationships
     * @return
     */
    public int batchInsert(List<HouseRelationshipModel> houseRelationships) {
        if (CollectionUtils.isEmpty(houseRelationships)) {
            return 0;
        }

        int count = 0;
        for(HouseRelationshipModel houseRelationship : houseRelationships) {
            if(houseRelationship.getRelationship()!=null && houseRelationship.getRelationship().equals("配偶")){
                houseRelationship.setIsRemove(2);
            }
            if (houseRelationship.getIdNumber() != null){
                count += this.insert(houseRelationship);
            }
        }
        return count;
    }

    /**
     * 获取家庭关系集合
     *
     * @return
     */
    public List<HouseRelationshipModel> getByIdentityInfoId(Integer identityInfoId) {
        if (identityInfoId == null) {
            return Collections.emptyList();
        }

        return this.find(Collections.singletonMap("identityInfoId", (Object) identityInfoId));
    }

    /**
     * 删除申请人家庭关系信息
     *
     * @param identityInfoId
     * @return
     */
    public int delByIdentityInfoId(Integer identityInfoId) {
        return houseRelationshipDao.delByIdentityInfoId(identityInfoId);
    }

    /**
     * 根据id集合 删除家庭关系信息
     *
     * @param ids
     * @return
     */
    public int delByIds(List<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return 0;
        }

        return houseRelationshipDao.delByIds(ids);
    }

}
