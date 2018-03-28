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
import com.wutuobang.score.model.CommonQuestionModel;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

@Service("commonQuestionService")
public class CommonQuestionServiceImpl implements ICommonQuestionService{
	@Autowired
	private ICommonQuestionDao commonQuestionDao;

	public int insert(CommonQuestionModel commonQuestion) {
		if(commonQuestion == null) {
			return 0;
		}
		return commonQuestionDao.insert(commonQuestion);
	}
	
	public int update(CommonQuestionModel commonQuestion) {
		if(commonQuestion == null) {
			return 0;
		}
		return commonQuestionDao.update(commonQuestion);
	}
	
	public CommonQuestionModel getById(Integer id) {
		if(id == null) {
			return null;
		}
		return commonQuestionDao.getById(id);
	}

	public int removeById(Integer id) {
		if(id == null) {
			return 0;
		}
		return commonQuestionDao.delete(id);
	}	

	public List<CommonQuestionModel> find(Map<String, Object> param) {
		return commonQuestionDao.find(param);
	}
	
}
