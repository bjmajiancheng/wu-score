/*
 * 北京果敢时代科技有限公司
 * 北京市朝阳区望京SOHO T3 B座1607
 * 邮编：100022
 * 网址：www.davdian.com
 */

package com.wutuobang.score.service;

import com.wutuobang.score.model.OfficeModel;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

public interface IOfficeService{

	public int insert(OfficeModel office);

	public int update(OfficeModel office);

	public OfficeModel getById(Integer value);

	public int removeById(Integer value);

	public List<OfficeModel> find(Map<String, Object> param);

	/**
	 * 根据父id获取办公机关信息
	 *
	 * @param parentId
	 * @return
     */
	public List<OfficeModel> getByParentId(Integer parentId);
}
