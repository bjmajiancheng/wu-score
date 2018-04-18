/*
 * 北京果敢时代科技有限公司
 * 北京市朝阳区望京SOHO T3 B座1607
 * 邮编：100022
 * 网址：www.davdian.com
 */

package com.wutuobang.score.service;

import com.wutuobang.common.utils.PageData;
import com.wutuobang.score.model.IdentityInfoModel;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

public interface IIdentityInfoService{

	public int insert(IdentityInfoModel identityInfo);

	public int update(IdentityInfoModel identityInfo);

	public IdentityInfoModel getById(Integer value);

	public int removeById(Integer value);

	public List<IdentityInfoModel> find(Map<String, Object> param);

	/**
	 * 获取分页数据信息
	 *
	 * @param queryStr
	 * @return
     */
	public PageData<IdentityInfoModel> findPage(String queryStr, Integer pageNo);
}
