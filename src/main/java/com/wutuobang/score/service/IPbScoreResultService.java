/*
 * 北京果敢时代科技有限公司
 * 北京市朝阳区望京SOHO T3 B座1607
 * 邮编：100022
 * 网址：www.davdian.com
 */

package com.wutuobang.score.service;

import com.wutuobang.score.model.PbScoreResultModel;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

public interface IPbScoreResultService{

	public int insert(PbScoreResultModel pbScoreResult);

	public int update(PbScoreResultModel pbScoreResult);

	public PbScoreResultModel getById(Integer value);

	public int removeById(Integer value);

	public List<PbScoreResultModel> find(Map<String, Object> param);

	/**
	 * 根据申请人id查询信息
	 *
	 * @param personId
	 * @return
     */
	public List<PbScoreResultModel> getByPersonId(Integer personId);

	/**
	 * 获取当前批次名单公示信息
	 *
	 * @param batchId
	 * @return
     */
	public List<PbScoreResultModel> findCurrBatch(Integer batchId, Integer pageNo);

	public List<PbScoreResultModel> findCurrBatch(Integer batchId);

	public PbScoreResultModel getByPersonIdNum(Integer batchId,String personIdNum);
}
