/*
 * 北京果敢时代科技有限公司
 * 北京市朝阳区望京SOHO T3 B座1607
 * 邮编：100022
 * 网址：www.davdian.com
 */

package com.wutuobang.score.dao;
import com.wutuobang.score.model.SystemNoticeModel;
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

public interface ISystemNoticeDao {

	public int insert(SystemNoticeModel systemNotice);

	public int update(SystemNoticeModel systemNotice);

	public SystemNoticeModel getById(@Param("id") Integer id);

	public int delete(@Param("id") Integer id);
	
	public List<SystemNoticeModel> find(Map<String, Object> param);

	/**
	 * 获取分页总数
	 *
	 * @param param
	 * @return
     */
	public int findPageCount(Map<String, Object> param);

	/**
	 * 获取分页数据
	 *
	 * @param param
	 * @return
     */
	public List<SystemNoticeModel> findPage(Map<String, Object> param, RowBounds rowBounds);

	/**
	 * 获取最新一条重要通知信息
	 *
	 * @return
	 */
	public SystemNoticeModel getLastSystemNotice();
	
}
