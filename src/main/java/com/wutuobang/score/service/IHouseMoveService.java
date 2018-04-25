/*
 * 北京果敢时代科技有限公司
 * 北京市朝阳区望京SOHO T3 B座1607
 * 邮编：100022
 * 网址：www.davdian.com
 */

package com.wutuobang.score.service;

import com.wutuobang.score.model.HouseMoveModel;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

public interface IHouseMoveService{

	public int insert(HouseMoveModel houseMove);

	public int update(HouseMoveModel houseMove);

	public HouseMoveModel getById(Integer value);

	public int removeById(Integer value);

	public List<HouseMoveModel> find(Map<String, Object> param);

	/**
	 * 根据申请人id获取户籍迁移信息
	 *
	 * @param identityInfoId
	 * @return
     */
	public HouseMoveModel getByIdentityInfoId(Integer identityInfoId);
}
