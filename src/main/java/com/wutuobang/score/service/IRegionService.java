/*
 * 北京果敢时代科技有限公司
 * 北京市朝阳区望京SOHO T3 B座1607
 * 邮编：100022
 * 网址：www.davdian.com
 */

package com.wutuobang.score.service;

import com.wutuobang.score.model.RegionModel;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

public interface IRegionService{

	public int insert(RegionModel region);

	public int update(RegionModel region);

	public RegionModel getById(Integer value);

	public int removeById(Integer value);

	public List<RegionModel> find(Map<String, Object> param);

	/**
	 * 根据父地区id获取地区集合信息
	 *
	 * @param regionId
	 * @return
     */
	public List<RegionModel> getByParendId(Integer regionId);

	/**
	 * 根据id集合获取地区信息
	 *
	 * @param ids
	 * @return
     */
	public List<RegionModel> getByIds(List<Integer> ids);

	/**
	 * 根据id集合获取地区Map信息
	 *
	 * @param ids
	 * @return
     */
	public Map<Integer, RegionModel> getMapByIds(List<Integer> ids);
}
