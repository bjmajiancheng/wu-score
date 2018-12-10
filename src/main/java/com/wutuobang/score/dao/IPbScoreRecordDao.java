package com.wutuobang.score.dao;

import com.wutuobang.score.model.PbScoreRecordModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by rui15 on 2018/12/7.
 */
public interface IPbScoreRecordDao {
    /*
    根据申请人ID查询得到20个打分项
     */
    public List<PbScoreRecordModel> getByPersonId(@Param("id") Integer id);

    public List<PbScoreRecordModel> getPublicList(@Param("batch_id") Integer batch_id);

}
