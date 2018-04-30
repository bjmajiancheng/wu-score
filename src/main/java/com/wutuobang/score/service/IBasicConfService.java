/*
 * 北京果敢时代科技有限公司
 * 北京市朝阳区望京SOHO T3 B座1607
 * 邮编：100022
 * 网址：www.davdian.com
 */

package com.wutuobang.score.service;

import com.wutuobang.score.model.BasicConfModel;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

public interface IBasicConfService{

	public int insert(BasicConfModel basicConf);

	public int update(BasicConfModel basicConf);

	public BasicConfModel getById(Integer value);

	public int removeById(Integer value);

	public List<BasicConfModel> find(Map<String, Object> param);

	/**
	 * 获取最后一个配置信息
	 *
	 * @return
     */
	public BasicConfModel findLastConf();
}
