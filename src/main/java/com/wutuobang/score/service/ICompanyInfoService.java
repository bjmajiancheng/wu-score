/*
 * 北京果敢时代科技有限公司
 * 北京市朝阳区望京SOHO T3 B座1607
 * 邮编：100022
 * 网址：www.davdian.com
 */

package com.wutuobang.score.service;

import com.wutuobang.score.model.CompanyInfoModel;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

public interface ICompanyInfoService{

	public int insert(CompanyInfoModel companyInfo);

	public int update(CompanyInfoModel companyInfo);

	public CompanyInfoModel getById(Integer value);

	public int removeById(Integer value);

	public List<CompanyInfoModel> find(Map<String, Object> param);

	/**
	 * 根据用户名获取信息
	 *
	 * @param userName
	 * @return
     */
	public CompanyInfoModel queryByUserName(String userName);

	/**
	 * 获取公司map信息
	 *
	 * @param ids
	 * @return
     */
	public Map<Integer, CompanyInfoModel> getMapByIds(List<Integer> ids);

	/**
	 * 根据公司名称或统一社会代码查询注册公司
	 *
	 * @param companyName
	 * @param societyCode
     * @return
     */
	public List<CompanyInfoModel> findByCompanyNameOrCode(String companyName, String societyCode);
}
