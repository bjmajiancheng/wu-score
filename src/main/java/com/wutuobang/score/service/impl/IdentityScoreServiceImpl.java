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
import com.wutuobang.score.model.IdentityScoreModel;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

@Service("identityScoreService")
public class IdentityScoreServiceImpl implements IIdentityScoreService{
	@Autowired
	private IIdentityScoreDao identityScoreDao;

	public int insert(IdentityScoreModel identityScore) {
		if(identityScore == null) {
			return 0;
		}
		return identityScoreDao.insert(identityScore);
	}
	
	public int update(IdentityScoreModel identityScore) {
		if(identityScore == null) {
			return 0;
		}
		return identityScoreDao.update(identityScore);
	}
	
	public IdentityScoreModel getById(Integer id) {
		if(id == null) {
			return null;
		}
		return identityScoreDao.getById(id);
	}

	public int removeById(Integer id) {
		if(id == null) {
			return 0;
		}
		return identityScoreDao.delete(id);
	}	

	public List<IdentityScoreModel> find(Map<String, Object> param) {
		return identityScoreDao.find(param);
	}
	
}
