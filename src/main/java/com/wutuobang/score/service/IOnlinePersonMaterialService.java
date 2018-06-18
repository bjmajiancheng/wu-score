/*
 * 北京果敢时代科技有限公司
 * 北京市朝阳区望京SOHO T3 B座1607
 * 邮编：100022
 * 网址：www.davdian.com
 */

package com.wutuobang.score.service;

import com.wutuobang.score.model.OnlinePersonMaterialModel;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

public interface IOnlinePersonMaterialService{

	public int insert(OnlinePersonMaterialModel onlinePersonMaterial);

	public int update(OnlinePersonMaterialModel onlinePersonMaterial);

	public OnlinePersonMaterialModel getById(Integer value);

	public int removeById(Integer value);

	public List<OnlinePersonMaterialModel> find(Map<String, Object> param);

	/**
	 * 根据申请人id获取上传资料信息
	 *
	 * @param personId
	 * @return
     */
	public List<OnlinePersonMaterialModel> getByPersonId(Integer personId);

	/**
	 * 批量新增申请人信息
	 *
	 * @param onlinePersonMaterialModels
	 * @return
     */
	public int batchInsert(List<OnlinePersonMaterialModel> onlinePersonMaterialModels);

	/**
	 * 根据申请人删除上传资料信息
	 *
	 * @param personId
	 * @return
     */
	public int deleteByPersonId(int personId);

	/**
	 * 根据id集合删除用户上传资料
	 *
	 * @param ids
	 * @return
     */
	public int delByIds(List<Integer> ids);
}
