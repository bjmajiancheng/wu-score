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
import com.wutuobang.score.model.IdentityPeriodFileModel;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

@Service("identityPeriodFileService")
public class IdentityPeriodFileServiceImpl implements IIdentityPeriodFileService{
	@Autowired
	private IIdentityPeriodFileDao identityPeriodFileDao;

	public int insert(IdentityPeriodFileModel identityPeriodFile) {
		if(identityPeriodFile == null) {
			return 0;
		}
		return identityPeriodFileDao.insert(identityPeriodFile);
	}
	
	public int update(IdentityPeriodFileModel identityPeriodFile) {
		if(identityPeriodFile == null) {
			return 0;
		}
		return identityPeriodFileDao.update(identityPeriodFile);
	}
	
	public IdentityPeriodFileModel getById(Integer id) {
		if(id == null) {
			return null;
		}
		return identityPeriodFileDao.getById(id);
	}

	public int removeById(Integer id) {
		if(id == null) {
			return 0;
		}
		return identityPeriodFileDao.delete(id);
	}	

	public List<IdentityPeriodFileModel> find(Map<String, Object> param) {
		return identityPeriodFileDao.find(param);
	}
	
}
