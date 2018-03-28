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
import com.wutuobang.score.model.SystemNoticeModel;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

@Service("systemNoticeService")
public class SystemNoticeServiceImpl implements ISystemNoticeService{
	@Autowired
	private ISystemNoticeDao systemNoticeDao;

	public int insert(SystemNoticeModel systemNotice) {
		if(systemNotice == null) {
			return 0;
		}
		return systemNoticeDao.insert(systemNotice);
	}
	
	public int update(SystemNoticeModel systemNotice) {
		if(systemNotice == null) {
			return 0;
		}
		return systemNoticeDao.update(systemNotice);
	}
	
	public SystemNoticeModel getById(Integer id) {
		if(id == null) {
			return null;
		}
		return systemNoticeDao.getById(id);
	}

	public int removeById(Integer id) {
		if(id == null) {
			return 0;
		}
		return systemNoticeDao.delete(id);
	}	

	public List<SystemNoticeModel> find(Map<String, Object> param) {
		return systemNoticeDao.find(param);
	}
	
}
