/*
 * 北京果敢时代科技有限公司
 * 北京市朝阳区望京SOHO T3 B座1607
 * 邮编：100022
 * 网址：www.davdian.com
 */

package com.wutuobang.score.service;

import com.wutuobang.score.model.AcceptDateConfModel;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

public interface IAcceptDateConfService{

	public int insert(AcceptDateConfModel acceptDateConf);

	public int update(AcceptDateConfModel acceptDateConf);

	public AcceptDateConfModel getById(Integer value);

	public int removeById(Integer value);

	public List<AcceptDateConfModel> find(Map<String, Object> param);

	/**
	 * 减上午名额
	 *
	 * @param id
	 * @return
     */
	public int amSubtractionOne(Integer id);

	/**
	 * 减上午名额
	 *
	 * @param id
	 * @return
	 */
	public int pmSubtractionOne(Integer id);


	/**
	 * 根据批次、申请人的预约地点、预约日期，查询当天的名额情况
	 * @param batch_id
	 * @param address_id
	 * @param dateStr
	 * @return
	 */
	public AcceptDateConfModel getByBatchidAndAddressidAndAcceptdate(Integer batch_id,Integer address_id, String dateStr);
}
