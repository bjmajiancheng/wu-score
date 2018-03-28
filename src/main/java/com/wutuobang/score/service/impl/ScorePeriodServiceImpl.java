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
import com.wutuobang.score.model.ScorePeriodModel;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

@Service("scorePeriodService")
public class ScorePeriodServiceImpl implements IScorePeriodService{
	@Autowired
	private IScorePeriodDao scorePeriodDao;

	public int insert(ScorePeriodModel scorePeriod) {
		if(scorePeriod == null) {
			return 0;
		}
		return scorePeriodDao.insert(scorePeriod);
	}
	
	public int update(ScorePeriodModel scorePeriod) {
		if(scorePeriod == null) {
			return 0;
		}
		return scorePeriodDao.update(scorePeriod);
	}
	
	public ScorePeriodModel getById(Integer id) {
		if(id == null) {
			return null;
		}
		return scorePeriodDao.getById(id);
	}

	public int removeById(Integer id) {
		if(id == null) {
			return 0;
		}
		return scorePeriodDao.delete(id);
	}	

	public List<ScorePeriodModel> find(Map<String, Object> param) {
		return scorePeriodDao.find(param);
	}
	
}
