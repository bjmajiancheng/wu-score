/*
 * 北京果敢时代科技有限公司
 * 北京市朝阳区望京SOHO T3 B座1607
 * 邮编：100022
 * 网址：www.davdian.com
 */

package com.wutuobang.score.service;

import com.wutuobang.common.utils.PageData;
import com.wutuobang.score.model.SystemNoticeModel;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

public interface ISystemNoticeService{

	public int insert(SystemNoticeModel systemNotice);

	public int update(SystemNoticeModel systemNotice);

	public SystemNoticeModel getById(Integer value);

	public int removeById(Integer value);

	public List<SystemNoticeModel> find(Map<String, Object> param);

	/**
	 * 获取分页数据信息
	 *
	 * @param type
	 * @return
     */
	public PageData<SystemNoticeModel> findPage(Integer type, Integer pageNo);

	/**
	 * 获取最新一条重要通知信息
	 *
	 * @return
     */
	public SystemNoticeModel getLastSystemNotice();
}
