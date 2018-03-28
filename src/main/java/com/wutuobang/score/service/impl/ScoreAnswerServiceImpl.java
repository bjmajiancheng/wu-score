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
import com.wutuobang.score.model.ScoreAnswerModel;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

@Service("scoreAnswerService")
public class ScoreAnswerServiceImpl implements IScoreAnswerService{
	@Autowired
	private IScoreAnswerDao scoreAnswerDao;

	public int insert(ScoreAnswerModel scoreAnswer) {
		if(scoreAnswer == null) {
			return 0;
		}
		return scoreAnswerDao.insert(scoreAnswer);
	}
	
	public int update(ScoreAnswerModel scoreAnswer) {
		if(scoreAnswer == null) {
			return 0;
		}
		return scoreAnswerDao.update(scoreAnswer);
	}
	
	public ScoreAnswerModel getById(Integer id) {
		if(id == null) {
			return null;
		}
		return scoreAnswerDao.getById(id);
	}

	public int removeById(Integer id) {
		if(id == null) {
			return 0;
		}
		return scoreAnswerDao.delete(id);
	}	

	public List<ScoreAnswerModel> find(Map<String, Object> param) {
		return scoreAnswerDao.find(param);
	}
	
}
