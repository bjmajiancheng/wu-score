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

import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.wutuobang.score.model.PersonBatchScoreResultModel;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

@Service("personBatchScoreResultService")
public class PersonBatchScoreResultServiceImpl implements IPersonBatchScoreResultService{
	@Autowired
	private IPersonBatchScoreResultDao personBatchScoreResultDao;

	public int insert(PersonBatchScoreResultModel personBatchScoreResult) {
		if(personBatchScoreResult == null) {
			return 0;
		}
		return personBatchScoreResultDao.insert(personBatchScoreResult);
	}
	
	public int update(PersonBatchScoreResultModel personBatchScoreResult) {
		if(personBatchScoreResult == null) {
			return 0;
		}
		return personBatchScoreResultDao.update(personBatchScoreResult);
	}
	
	public PersonBatchScoreResultModel getById(Integer id) {
		if(id == null) {
			return null;
		}
		return personBatchScoreResultDao.getById(id);
	}

	public int removeById(Integer id) {
		if(id == null) {
			return 0;
		}
		return personBatchScoreResultDao.delete(id);
	}	

	public List<PersonBatchScoreResultModel> find(Map<String, Object> param) {
		return personBatchScoreResultDao.find(param);
	}

	/**
	 * 申请人评分详细信息
	 *
	 * @param personBatchScoreResults
	 * @return
	 */
	public int batchInsert(List<PersonBatchScoreResultModel> personBatchScoreResults) {
		if(CollectionUtils.isEmpty(personBatchScoreResults)) {
			return 0;
		}

		return personBatchScoreResultDao.batchInsert(personBatchScoreResults);
	}
	
}
