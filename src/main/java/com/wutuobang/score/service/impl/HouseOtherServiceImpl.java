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
import com.wutuobang.score.model.HouseOtherModel;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

@Service("houseOtherService")
public class HouseOtherServiceImpl implements IHouseOtherService{
	@Autowired
	private IHouseOtherDao houseOtherDao;

	public int insert(HouseOtherModel houseOther) {
		if(houseOther == null) {
			return 0;
		}
		return houseOtherDao.insert(houseOther);
	}
	
	public int update(HouseOtherModel houseOther) {
		if(houseOther == null) {
			return 0;
		}
		return houseOtherDao.update(houseOther);
	}
	
	public HouseOtherModel getById(Integer id) {
		if(id == null) {
			return null;
		}
		return houseOtherDao.getById(id);
	}

	public int removeById(Integer id) {
		if(id == null) {
			return 0;
		}
		return houseOtherDao.delete(id);
	}	

	public List<HouseOtherModel> find(Map<String, Object> param) {
		return houseOtherDao.find(param);
	}

	/**
	 * 获取其他信息
	 *
	 * @param identityInfoId
	 * @return
	 */
	public HouseOtherModel getByIdentityInfoId(Integer identityInfoId) {
		if(identityInfoId == null) {
			return null;
		}

		List<HouseOtherModel> houseOtherModels = this.find(Collections.singletonMap("identityInfoId", (Object)identityInfoId));
		if(CollectionUtils.isEmpty(houseOtherModels)) {
			return null;
		}

		return houseOtherModels.get(0);
	}
	
}
