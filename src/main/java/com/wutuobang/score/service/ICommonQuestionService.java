/*
 * 北京果敢时代科技有限公司
 * 北京市朝阳区望京SOHO T3 B座1607
 * 邮编：100022
 * 网址：www.davdian.com
 */

package com.wutuobang.score.service;

import com.wutuobang.common.utils.PageData;
import com.wutuobang.score.model.CommonQuestionModel;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

public interface ICommonQuestionService{

	public int insert(CommonQuestionModel commonQuestion);

	public int update(CommonQuestionModel commonQuestion);

	public CommonQuestionModel getById(Integer value);

	public int removeById(Integer value);

	public List<CommonQuestionModel> find(Map<String, Object> param);

	/**
	 * 获取常见问题分页数据
	 *
	 * @param pageNo
	 * @param pageSize
	 * @return
     */
	public PageData<CommonQuestionModel> findPage(Integer pageNo, Integer pageSize);
}
