/*
 * 北京果敢时代科技有限公司
 * 北京市朝阳区望京SOHO T3 B座1607
 * 邮编：100022
 * 网址：www.davdian.com
 */

package com.wutuobang.score.service;

import com.wutuobang.score.model.HouseProfessionModel;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

public interface IHouseProfessionService{

	public int insert(HouseProfessionModel houseProfession);

	public int update(HouseProfessionModel houseProfession);

	public HouseProfessionModel getById(Integer value);

	public int removeById(Integer value);

	public List<HouseProfessionModel> find(Map<String, Object> param);

	/**
	 * 获取专业信息
	 *
	 * @param identityInfoId
	 * @return
     */
	public HouseProfessionModel getByIdentityInfoId(Integer identityInfoId);
}
