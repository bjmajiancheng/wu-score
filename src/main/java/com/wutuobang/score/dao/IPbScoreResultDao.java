/*
 * 北京果敢时代科技有限公司
 * 北京市朝阳区望京SOHO T3 B座1607
 * 邮编：100022
 * 网址：www.davdian.com
 */

package com.wutuobang.score.dao;
import com.wutuobang.score.model.PbScoreResultModel;
import org.apache.ibatis.annotations.Param;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;
import org.apache.ibatis.session.RowBounds;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

public interface IPbScoreResultDao {

	public int insert(PbScoreResultModel pbScoreResult);

	public int update(PbScoreResultModel pbScoreResult);

	public PbScoreResultModel getById(@Param("id") Integer id);

	public int delete(@Param("id") Integer id);
	
	public List<PbScoreResultModel> find(Map<String, Object> param);

	/**
	 * 获取当前批次名单公示信息
	 *
	 * @param batchId
	 * @return
	 */
	public List<PbScoreResultModel> findCurrBatch(@Param("batchId") Integer batchId, RowBounds rowBounds);

	public PbScoreResultModel getByPersonIdNum(@Param("batchId") Integer batchId,@Param("personIdNum") String  personIdNum);

	public int getCount(@Param("batchId") Integer batchId);
	
}
