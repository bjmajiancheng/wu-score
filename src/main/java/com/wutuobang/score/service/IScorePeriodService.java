/*
 * 北京果敢时代科技有限公司
 * 北京市朝阳区望京SOHO T3 B座1607
 * 邮编：100022
 * 网址：www.davdian.com
 */

package com.wutuobang.score.service;

import com.wutuobang.score.model.ScorePeriodModel;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

public interface IScorePeriodService{

	public int insert(ScorePeriodModel scorePeriod);

	public int update(ScorePeriodModel scorePeriod);

	public ScorePeriodModel getById(Integer value);

	public int removeById(Integer value);

	public List<ScorePeriodModel> find(Map<String, Object> param);
}
