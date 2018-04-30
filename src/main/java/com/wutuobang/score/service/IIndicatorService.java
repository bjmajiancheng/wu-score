/*
 * 北京果敢时代科技有限公司
 * 北京市朝阳区望京SOHO T3 B座1607
 * 邮编：100022
 * 网址：www.davdian.com
 */

package com.wutuobang.score.service;

import com.wutuobang.score.model.IdentityInfoModel;
import com.wutuobang.score.model.IndicatorModel;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

public interface IIndicatorService{

	public int insert(IndicatorModel indicator);

	public int update(IndicatorModel indicator);

	public IndicatorModel getById(Integer value);

	public int removeById(Integer value);

	public List<IndicatorModel> find(Map<String, Object> param);

	/**
	 * 获取所有指标信息
	 *
	 * @return
     */
	public List<IndicatorModel> getAllIndicators();

	/**
	 * 获取所有指标map信息
	 *
	 * @return
     */
	public Map<Integer, IndicatorModel> getAllMapIndicator();

	/**
	 * 初始化自助评测信息
	 *
	 * @param identityInfo 申请人信息
	 * @param indicatorModels 所有指标信息
     * @return
     */
	public void initIndicatorView(IdentityInfoModel identityInfo, List<IndicatorModel> indicatorModels);
}
