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
import com.wutuobang.score.model.PersonBatchStatusRecordModel;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

@Service("personBatchStatusRecordService")
public class PersonBatchStatusRecordServiceImpl implements IPersonBatchStatusRecordService{
	@Autowired
	private IPersonBatchStatusRecordDao personBatchStatusRecordDao;

	public int insert(PersonBatchStatusRecordModel personBatchStatusRecord) {
		if(personBatchStatusRecord == null) {
			return 0;
		}
		return personBatchStatusRecordDao.insert(personBatchStatusRecord);
	}
	
	public int update(PersonBatchStatusRecordModel personBatchStatusRecord) {
		if(personBatchStatusRecord == null) {
			return 0;
		}
		return personBatchStatusRecordDao.update(personBatchStatusRecord);
	}
	
	public PersonBatchStatusRecordModel getById(Integer id) {
		if(id == null) {
			return null;
		}
		return personBatchStatusRecordDao.getById(id);
	}

	public int removeById(Integer id) {
		if(id == null) {
			return 0;
		}
		return personBatchStatusRecordDao.delete(id);
	}	

	public List<PersonBatchStatusRecordModel> find(Map<String, Object> param) {
		return personBatchStatusRecordDao.find(param);
	}

	/**
	 * 批量插入修改状态日志信息
	 *
	 * @param personBatchStatusRecords
	 * @return
	 */
	public int batchInsert(List<PersonBatchStatusRecordModel> personBatchStatusRecords) {
		if(CollectionUtils.isEmpty(personBatchStatusRecords)) {
			return 0;
		}

		return personBatchStatusRecordDao.batchInsert(personBatchStatusRecords);
	}
	
}
