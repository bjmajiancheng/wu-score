/*
 * 北京果敢时代科技有限公司
 * 北京市朝阳区望京SOHO T3 B座1607
 * 邮编：100022
 * 网址：www.davdian.com
 */

package com.wutuobang.score.service;

import com.wutuobang.score.model.ScoreAnswerModel;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

public interface IScoreAnswerService{

	public int insert(ScoreAnswerModel scoreAnswer);

	public int update(ScoreAnswerModel scoreAnswer);

	public ScoreAnswerModel getById(Integer value);

	public int removeById(Integer value);

	public List<ScoreAnswerModel> find(Map<String, Object> param);
}
