/*
 * 北京果敢时代科技有限公司
 * 北京市朝阳区望京SOHO T3 B座1607
 * 邮编：100022
 * 网址：www.davdian.com
 */

package com.wutuobang.score.service;

import com.wutuobang.score.model.DictModel;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

public interface IDictService{

	public int insert(DictModel dict);

	public int update(DictModel dict);

	public DictModel getById(Integer value);

	public int removeById(Integer value);

	public List<DictModel> find(Map<String, Object> param);

	/**
	 * 根据字典名称集合获取所有字典集合信息
	 *
	 * @param aliasKeys
	 * @return
     */
	public List<DictModel> findByAliasKeys(List<String> aliasKeys);

	/**
	 * 根据字典名称集合获取所有字典Map信息
	 *
	 * @param aliasKeys
	 * @return
     */
	public Map<String, List<DictModel>> findMapByAliasKeys(List<String> aliasKeys);
}
