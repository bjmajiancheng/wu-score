/*
 * 北京果敢时代科技有限公司
 * 北京市朝阳区望京SOHO T3 B座1607
 * 邮编：100022
 * 网址：www.davdian.com
 */

package com.wutuobang.score.dao;

import com.wutuobang.score.model.HouseRelationshipModel;
import org.apache.ibatis.annotations.Param;

import java.util.*;

import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author davdian
 * @version 1.0
 * @since 1.0
 */

public interface IHouseRelationshipDao {

    public int insert(HouseRelationshipModel houseRelationship);

    public int update(HouseRelationshipModel houseRelationship);

    public HouseRelationshipModel getById(@Param("id") Integer id);

    public int delete(@Param("id") Integer id);

    public List<HouseRelationshipModel> find(Map<String, Object> param);

    /**
     * 批量添加家庭关系信息集合
     *
     * @param houseRelationships
     * @return
     */
    public int batchInsert(@Param("houseRelationships") List<HouseRelationshipModel> houseRelationships);

    /**
     * 删除申请人家庭关系信息
     *
     * @param identityInfoId
     * @return
     */
    public int delByIdentityInfoId(@Param("identityInfoId") Integer identityInfoId);

    /**
     * 根据id集合删除家庭关系信息
     *
     * @param ids
     * @return
     */
    public int delByIds(@Param("ids") List<Integer> ids);

}
