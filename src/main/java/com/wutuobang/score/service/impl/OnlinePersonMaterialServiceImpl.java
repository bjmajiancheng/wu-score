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
import com.wutuobang.score.model.OnlinePersonMaterialModel;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

@Service("onlinePersonMaterialService")
public class OnlinePersonMaterialServiceImpl implements IOnlinePersonMaterialService{
	@Autowired
	private IOnlinePersonMaterialDao onlinePersonMaterialDao;

	public int insert(OnlinePersonMaterialModel onlinePersonMaterial) {
		if(onlinePersonMaterial == null) {
			return 0;
		}
		return onlinePersonMaterialDao.insert(onlinePersonMaterial);
	}
	
	public int update(OnlinePersonMaterialModel onlinePersonMaterial) {
		if(onlinePersonMaterial == null) {
			return 0;
		}
		return onlinePersonMaterialDao.update(onlinePersonMaterial);
	}
	
	public OnlinePersonMaterialModel getById(Integer id) {
		if(id == null) {
			return null;
		}
		return onlinePersonMaterialDao.getById(id);
	}

	public int removeById(Integer id) {
		if(id == null) {
			return 0;
		}
		return onlinePersonMaterialDao.delete(id);
	}	

	public List<OnlinePersonMaterialModel> find(Map<String, Object> param) {
		return onlinePersonMaterialDao.find(param);
	}

	/**
	 * 根据申请人id获取上传资料信息
	 *
	 * @param personId
	 * @return
	 */
	public List<OnlinePersonMaterialModel> getByPersonId(Integer personId) {
		if(personId == null) {
			return Collections.emptyList();
		}

		return this.find(Collections.singletonMap("personId", (Object)personId));
	}

	/**
	 * 批量新增申请人信息
	 *
	 * @param onlinePersonMaterialModels
	 * @return
	 */
	public int batchInsert(List<OnlinePersonMaterialModel> onlinePersonMaterialModels) {
		if(CollectionUtils.isEmpty(onlinePersonMaterialModels)) {
			return 0;
		}

		return onlinePersonMaterialDao.batchInsert(onlinePersonMaterialModels);
	}
	
}
