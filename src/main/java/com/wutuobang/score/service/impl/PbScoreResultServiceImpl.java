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
import com.wutuobang.score.model.PbScoreResultModel;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

@Service("pbScoreResultService")
public class PbScoreResultServiceImpl implements IPbScoreResultService{
	@Autowired
	private IPbScoreResultDao pbScoreResultDao;

	public int insert(PbScoreResultModel pbScoreResult) {
		if(pbScoreResult == null) {
			return 0;
		}
		return pbScoreResultDao.insert(pbScoreResult);
	}
	
	public int update(PbScoreResultModel pbScoreResult) {
		if(pbScoreResult == null) {
			return 0;
		}
		return pbScoreResultDao.update(pbScoreResult);
	}
	
	public PbScoreResultModel getById(Integer id) {
		if(id == null) {
			return null;
		}
		return pbScoreResultDao.getById(id);
	}

	public int removeById(Integer id) {
		if(id == null) {
			return 0;
		}
		return pbScoreResultDao.delete(id);
	}	

	public List<PbScoreResultModel> find(Map<String, Object> param) {
		return pbScoreResultDao.find(param);
	}
	
}
