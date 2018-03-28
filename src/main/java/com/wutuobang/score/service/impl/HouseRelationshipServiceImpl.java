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
import com.wutuobang.score.model.HouseRelationshipModel;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

@Service("houseRelationshipService")
public class HouseRelationshipServiceImpl implements IHouseRelationshipService{
	@Autowired
	private IHouseRelationshipDao houseRelationshipDao;

	public int insert(HouseRelationshipModel houseRelationship) {
		if(houseRelationship == null) {
			return 0;
		}
		return houseRelationshipDao.insert(houseRelationship);
	}
	
	public int update(HouseRelationshipModel houseRelationship) {
		if(houseRelationship == null) {
			return 0;
		}
		return houseRelationshipDao.update(houseRelationship);
	}
	
	public HouseRelationshipModel getById(Integer id) {
		if(id == null) {
			return null;
		}
		return houseRelationshipDao.getById(id);
	}

	public int removeById(Integer id) {
		if(id == null) {
			return 0;
		}
		return houseRelationshipDao.delete(id);
	}	

	public List<HouseRelationshipModel> find(Map<String, Object> param) {
		return houseRelationshipDao.find(param);
	}
	
}
