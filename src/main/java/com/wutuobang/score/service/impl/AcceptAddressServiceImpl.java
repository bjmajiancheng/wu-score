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
import com.wutuobang.score.model.AcceptAddressModel;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

@Service("acceptAddressService")
public class AcceptAddressServiceImpl implements IAcceptAddressService{
	@Autowired
	private IAcceptAddressDao acceptAddressDao;

	public int insert(AcceptAddressModel acceptAddress) {
		if(acceptAddress == null) {
			return 0;
		}
		return acceptAddressDao.insert(acceptAddress);
	}
	
	public int update(AcceptAddressModel acceptAddress) {
		if(acceptAddress == null) {
			return 0;
		}
		return acceptAddressDao.update(acceptAddress);
	}
	
	public AcceptAddressModel getById(Integer id) {
		if(id == null) {
			return null;
		}
		return acceptAddressDao.getById(id);
	}

	public int removeById(Integer id) {
		if(id == null) {
			return 0;
		}
		return acceptAddressDao.delete(id);
	}	

	public List<AcceptAddressModel> find(Map<String, Object> param) {
		return acceptAddressDao.find(param);
	}
	
}
