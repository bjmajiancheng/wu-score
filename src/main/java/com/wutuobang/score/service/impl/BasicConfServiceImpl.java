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
import com.wutuobang.score.model.BasicConfModel;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

@Service("basicConfService")
public class BasicConfServiceImpl implements IBasicConfService{
	@Autowired
	private IBasicConfDao basicConfDao;

	public int insert(BasicConfModel basicConf) {
		if(basicConf == null) {
			return 0;
		}
		return basicConfDao.insert(basicConf);
	}
	
	public int update(BasicConfModel basicConf) {
		if(basicConf == null) {
			return 0;
		}
		return basicConfDao.update(basicConf);
	}
	
	public BasicConfModel getById(Integer id) {
		if(id == null) {
			return null;
		}
		return basicConfDao.getById(id);
	}

	public int removeById(Integer id) {
		if(id == null) {
			return 0;
		}
		return basicConfDao.delete(id);
	}	

	public List<BasicConfModel> find(Map<String, Object> param) {
		return basicConfDao.find(param);
	}

	/**
	 * 获取最后一个配置信息
	 *
	 * @return
	 */
	public BasicConfModel findLastConf() {
		return basicConfDao.findLastConf();
	}
	
}
