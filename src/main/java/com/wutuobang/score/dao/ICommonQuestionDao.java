/*
 * 北京果敢时代科技有限公司
 * 北京市朝阳区望京SOHO T3 B座1607
 * 邮编：100022
 * 网址：www.davdian.com
 */

package com.wutuobang.score.dao;
import com.wutuobang.score.model.CommonQuestionModel;
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

public interface ICommonQuestionDao {

	public int insert(CommonQuestionModel commonQuestion);

	public int update(CommonQuestionModel commonQuestion);

	public CommonQuestionModel getById(@Param("id") Integer id);

	public int delete(@Param("id") Integer id);
	
	public List<CommonQuestionModel> find(Map<String, Object> param);

	/**
	 * 获取分页总数
	 *
	 * @param param
	 * @return
     */
	public int findPageCount(Map<String, Object> param);

	/**
	 * 获取分页数据信息
	 *
	 * @param param
	 * @return
     */
	public List<CommonQuestionModel> findPage(Map<String, Object> param, RowBounds rowBounds);
	
}
