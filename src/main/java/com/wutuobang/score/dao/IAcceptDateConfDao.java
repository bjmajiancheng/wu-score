/*
 * 北京果敢时代科技有限公司
 * 北京市朝阳区望京SOHO T3 B座1607
 * 邮编：100022
 * 网址：www.davdian.com
 */

package com.wutuobang.score.dao;
import com.wutuobang.score.model.AcceptDateConfModel;
import org.apache.ibatis.annotations.Param;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

public interface IAcceptDateConfDao {

	public int insert(AcceptDateConfModel acceptDateConf);

	public int update(AcceptDateConfModel acceptDateConf);

	public AcceptDateConfModel getById(@Param("id") Integer id);

	public int delete(@Param("id") Integer id);
	
	public List<AcceptDateConfModel> find(Map<String, Object> param);

	/**
	 * 减上午名额
	 *
	 * @param id
	 * @return
	 */
	public int amSubtractionOne(@Param("id") Integer id);

	/**
	 * 减上午名额
	 *
	 * @param id
	 * @return
	 */
	public int pmSubtractionOne(@Param("id") Integer id);

	public AcceptDateConfModel getByBatchidAndAddressidAndAcceptdate(@Param("batch_id") Integer batch_id, @Param("address_id") Integer address_id, @Param("dateStr") String dateStr);

	
}
