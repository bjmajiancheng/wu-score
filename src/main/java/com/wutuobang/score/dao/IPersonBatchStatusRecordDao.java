/*
 * 北京果敢时代科技有限公司
 * 北京市朝阳区望京SOHO T3 B座1607
 * 邮编：100022
 * 网址：www.davdian.com
 */

package com.wutuobang.score.dao;

import com.wutuobang.score.model.PersonBatchStatusRecordModel;
import org.apache.ibatis.annotations.Param;

import java.util.*;

import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author davdian
 * @version 1.0
 * @since 1.0
 */

public interface IPersonBatchStatusRecordDao {

    public int insert(PersonBatchStatusRecordModel personBatchStatusRecord);

    public int update(PersonBatchStatusRecordModel personBatchStatusRecord);

    public PersonBatchStatusRecordModel getById(@Param("id") Integer id);

    public int delete(@Param("id") Integer id);

    public List<PersonBatchStatusRecordModel> find(Map<String, Object> param);

    /**
     * 批量插入修改状态日志信息
     *
     * @param personBatchStatusRecords
     * @return
     */
    public int batchInsert(@Param("personBatchStatusRecords") List<PersonBatchStatusRecordModel> personBatchStatusRecords);

}
