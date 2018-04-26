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
import com.wutuobang.score.model.AcceptDateConfModel;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

@Service("acceptDateConfService")
public class AcceptDateConfServiceImpl implements IAcceptDateConfService{
	@Autowired
	private IAcceptDateConfDao acceptDateConfDao;

	public int insert(AcceptDateConfModel acceptDateConf) {
		if(acceptDateConf == null) {
			return 0;
		}
		return acceptDateConfDao.insert(acceptDateConf);
	}
	
	public int update(AcceptDateConfModel acceptDateConf) {
		if(acceptDateConf == null) {
			return 0;
		}
		return acceptDateConfDao.update(acceptDateConf);
	}
	
	public AcceptDateConfModel getById(Integer id) {
		if(id == null) {
			return null;
		}
		return acceptDateConfDao.getById(id);
	}

	public int removeById(Integer id) {
		if(id == null) {
			return 0;
		}
		return acceptDateConfDao.delete(id);
	}	

	public List<AcceptDateConfModel> find(Map<String, Object> param) {
		return acceptDateConfDao.find(param);
	}

	/**
	 * 减上午名额
	 *
	 * @param id
	 * @return
	 */
	public int amSubtractionOne(Integer id) {
		if(id == null) {
			return 0;
		}

		return acceptDateConfDao.amSubtractionOne(id);
	}

	/**
	 * 减上午名额
	 *
	 * @param id
	 * @return
	 */
	public int pmSubtractionOne(Integer id) {
		if(id == null) {
			return 0;
		}

		return acceptDateConfDao.pmSubtractionOne(id);
	}
	
}
