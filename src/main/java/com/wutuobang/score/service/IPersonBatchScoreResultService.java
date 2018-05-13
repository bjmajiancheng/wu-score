/*
 * 北京果敢时代科技有限公司
 * 北京市朝阳区望京SOHO T3 B座1607
 * 邮编：100022
 * 网址：www.davdian.com
 */

package com.wutuobang.score.service;

import com.wutuobang.score.model.PersonBatchScoreResultModel;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

public interface IPersonBatchScoreResultService{

	public int insert(PersonBatchScoreResultModel personBatchScoreResult);

	public int update(PersonBatchScoreResultModel personBatchScoreResult);

	public PersonBatchScoreResultModel getById(Integer value);

	public int removeById(Integer value);

	public List<PersonBatchScoreResultModel> find(Map<String, Object> param);

	/**
	 * 申请人评分详细信息
	 *
	 * @param personBatchScoreResults
	 * @return
     */
	public int batchInsert(List<PersonBatchScoreResultModel> personBatchScoreResults);

	/**
	 * 根据申请人获取申请人评测信息
	 *
	 * @param identityInfoId
	 * @return
     */
	public Map<Integer, PersonBatchScoreResultModel> findMapByIdentityInfoId(Integer identityInfoId);
}
