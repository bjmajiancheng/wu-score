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
import com.wutuobang.score.model.ScoreFileModel;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

@Service("scoreFileService")
public class ScoreFileServiceImpl implements IScoreFileService{
	@Autowired
	private IScoreFileDao scoreFileDao;

	public int insert(ScoreFileModel scoreFile) {
		if(scoreFile == null) {
			return 0;
		}
		return scoreFileDao.insert(scoreFile);
	}
	
	public int update(ScoreFileModel scoreFile) {
		if(scoreFile == null) {
			return 0;
		}
		return scoreFileDao.update(scoreFile);
	}
	
	public ScoreFileModel getById(Integer id) {
		if(id == null) {
			return null;
		}
		return scoreFileDao.getById(id);
	}

	public int removeById(Integer id) {
		if(id == null) {
			return 0;
		}
		return scoreFileDao.delete(id);
	}	

	public List<ScoreFileModel> find(Map<String, Object> param) {
		return scoreFileDao.find(param);
	}
	
}
