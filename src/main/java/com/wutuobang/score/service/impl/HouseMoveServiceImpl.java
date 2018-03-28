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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.wutuobang.score.model.HouseMoveModel;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

@Service("houseMoveService")
public class HouseMoveServiceImpl implements IHouseMoveService{
	@Autowired
	private IHouseMoveDao houseMoveDao;

	public int insert(HouseMoveModel houseMove) {
		if(houseMove == null) {
			return 0;
		}
		return houseMoveDao.insert(houseMove);
	}
	
	public int update(HouseMoveModel houseMove) {
		if(houseMove == null) {
			return 0;
		}
		return houseMoveDao.update(houseMove);
	}
	
	public HouseMoveModel getById(Integer id) {
		if(id == null) {
			return null;
		}
		return houseMoveDao.getById(id);
	}

	public int removeById(Integer id) {
		if(id == null) {
			return 0;
		}
		return houseMoveDao.delete(id);
	}	

	public List<HouseMoveModel> find(Map<String, Object> param) {
		return houseMoveDao.find(param);
	}
	
}
