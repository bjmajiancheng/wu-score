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
import com.wutuobang.score.model.IndicatorItemModel;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

@Service("indicatorItemService")
public class IndicatorItemServiceImpl implements IIndicatorItemService{
	@Autowired
	private IIndicatorItemDao indicatorItemDao;

	public int insert(IndicatorItemModel indicatorItem) {
		if(indicatorItem == null) {
			return 0;
		}
		return indicatorItemDao.insert(indicatorItem);
	}
	
	public int update(IndicatorItemModel indicatorItem) {
		if(indicatorItem == null) {
			return 0;
		}
		return indicatorItemDao.update(indicatorItem);
	}
	
	public IndicatorItemModel getById(Integer id) {
		if(id == null) {
			return null;
		}
		return indicatorItemDao.getById(id);
	}

	public int removeById(Integer id) {
		if(id == null) {
			return 0;
		}
		return indicatorItemDao.delete(id);
	}	

	public List<IndicatorItemModel> find(Map<String, Object> param) {
		return indicatorItemDao.find(param);
	}
	
}
