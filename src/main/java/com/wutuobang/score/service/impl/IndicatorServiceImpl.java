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
import com.wutuobang.score.model.IndicatorModel;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

@Service("indicatorService")
public class IndicatorServiceImpl implements IIndicatorService{
	@Autowired
	private IIndicatorDao indicatorDao;

	public int insert(IndicatorModel indicator) {
		if(indicator == null) {
			return 0;
		}
		return indicatorDao.insert(indicator);
	}
	
	public int update(IndicatorModel indicator) {
		if(indicator == null) {
			return 0;
		}
		return indicatorDao.update(indicator);
	}
	
	public IndicatorModel getById(Integer id) {
		if(id == null) {
			return null;
		}
		return indicatorDao.getById(id);
	}

	public int removeById(Integer id) {
		if(id == null) {
			return 0;
		}
		return indicatorDao.delete(id);
	}	

	public List<IndicatorModel> find(Map<String, Object> param) {
		return indicatorDao.find(param);
	}
	
}
