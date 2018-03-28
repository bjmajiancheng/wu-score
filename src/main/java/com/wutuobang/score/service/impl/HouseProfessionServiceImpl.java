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
import com.wutuobang.score.model.HouseProfessionModel;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

@Service("houseProfessionService")
public class HouseProfessionServiceImpl implements IHouseProfessionService{
	@Autowired
	private IHouseProfessionDao houseProfessionDao;

	public int insert(HouseProfessionModel houseProfession) {
		if(houseProfession == null) {
			return 0;
		}
		return houseProfessionDao.insert(houseProfession);
	}
	
	public int update(HouseProfessionModel houseProfession) {
		if(houseProfession == null) {
			return 0;
		}
		return houseProfessionDao.update(houseProfession);
	}
	
	public HouseProfessionModel getById(Integer id) {
		if(id == null) {
			return null;
		}
		return houseProfessionDao.getById(id);
	}

	public int removeById(Integer id) {
		if(id == null) {
			return 0;
		}
		return houseProfessionDao.delete(id);
	}	

	public List<HouseProfessionModel> find(Map<String, Object> param) {
		return houseProfessionDao.find(param);
	}
	
}
