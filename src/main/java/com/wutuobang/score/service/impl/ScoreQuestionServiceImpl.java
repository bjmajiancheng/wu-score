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
import com.wutuobang.score.model.ScoreQuestionModel;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

@Service("scoreQuestionService")
public class ScoreQuestionServiceImpl implements IScoreQuestionService{
	@Autowired
	private IScoreQuestionDao scoreQuestionDao;

	public int insert(ScoreQuestionModel scoreQuestion) {
		if(scoreQuestion == null) {
			return 0;
		}
		return scoreQuestionDao.insert(scoreQuestion);
	}
	
	public int update(ScoreQuestionModel scoreQuestion) {
		if(scoreQuestion == null) {
			return 0;
		}
		return scoreQuestionDao.update(scoreQuestion);
	}
	
	public ScoreQuestionModel getById(Integer id) {
		if(id == null) {
			return null;
		}
		return scoreQuestionDao.getById(id);
	}

	public int removeById(Integer id) {
		if(id == null) {
			return 0;
		}
		return scoreQuestionDao.delete(id);
	}	

	public List<ScoreQuestionModel> find(Map<String, Object> param) {
		return scoreQuestionDao.find(param);
	}
	
}
