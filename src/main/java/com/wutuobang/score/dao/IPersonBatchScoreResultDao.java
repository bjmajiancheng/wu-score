/*
 * 北京果敢时代科技有限公司
 * 北京市朝阳区望京SOHO T3 B座1607
 * 邮编：100022
 * 网址：www.davdian.com
 */

package com.wutuobang.score.dao;

import com.wutuobang.score.model.PersonBatchScoreResultModel;
import org.apache.ibatis.annotations.Param;

import java.util.*;

import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author davdian
 * @version 1.0
 * @since 1.0
 */

public interface IPersonBatchScoreResultDao {

    public int insert(PersonBatchScoreResultModel personBatchScoreResult);

    public int update(PersonBatchScoreResultModel personBatchScoreResult);

    public PersonBatchScoreResultModel getById(@Param("id") Integer id);

    public int delete(@Param("id") Integer id);

    public List<PersonBatchScoreResultModel> find(Map<String, Object> param);

    /**
     * 批量添加
     *
     * @param personBatchScoreResults
     * @return
     */
    public int batchInsert(@Param("personBatchScoreResults") List<PersonBatchScoreResultModel> personBatchScoreResults);

}
