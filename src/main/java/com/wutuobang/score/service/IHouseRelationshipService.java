/*
 * 北京果敢时代科技有限公司
 * 北京市朝阳区望京SOHO T3 B座1607
 * 邮编：100022
 * 网址：www.davdian.com
 */

package com.wutuobang.score.service;

import com.wutuobang.score.model.HouseRelationshipModel;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

public interface IHouseRelationshipService{

	public int insert(HouseRelationshipModel houseRelationship);

	public int update(HouseRelationshipModel houseRelationship);

	public HouseRelationshipModel getById(Integer value);

	public int removeById(Integer value);

	public List<HouseRelationshipModel> find(Map<String, Object> param);

	/**
	 * 批量添加家庭关系信息集合
	 *
	 * @param houseRelationships
	 * @return
     */
	public int batchInsert(List<HouseRelationshipModel> houseRelationships);

	/**
	 * 获取家庭关系集合
	 *
	 * @return
     */
	public List<HouseRelationshipModel> getByIdentityInfoId(Integer identityInfoId);
}
