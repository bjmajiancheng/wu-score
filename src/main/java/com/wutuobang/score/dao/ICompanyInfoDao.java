/*
 * 北京果敢时代科技有限公司
 * 北京市朝阳区望京SOHO T3 B座1607
 * 邮编：100022
 * 网址：www.davdian.com
 */

package com.wutuobang.score.dao;
import com.wutuobang.score.model.CompanyInfoModel;
import org.apache.ibatis.annotations.Param;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

public interface ICompanyInfoDao {

	public int insert(CompanyInfoModel companyInfo);

	public int update(CompanyInfoModel companyInfo);

	public CompanyInfoModel getById(@Param("id") Integer id);

	public int delete(@Param("id") Integer id);
	
	public List<CompanyInfoModel> find(Map<String, Object> param);
	
}
