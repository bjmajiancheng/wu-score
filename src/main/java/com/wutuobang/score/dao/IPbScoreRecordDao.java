package com.wutuobang.score.dao;

import com.wutuobang.score.model.PbScoreRecordModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**
 * Created by rui15 on 2018/12/7.
 */
public interface IPbScoreRecordDao {
    /*
    根据申请人ID查询得到20个打分项
     */
    public List<PbScoreRecordModel> getByPersonId(@Param("id") Integer id);

    public List<PbScoreRecordModel> getPublicList(@Param("batch_id") Integer batch_id);

    public List<PbScoreRecordModel> findPublicPage(Map<String, Object> param, RowBounds rowBounds);

    /*
    2018年12月16日 获取名单公示页面的总记录数量
     */
    public int findPublicPageCount(Map<String,Object> param);

    /*
    查询一条数据
     */
    public List<PbScoreRecordModel> findOnePbScoreRecord(@Param("id_number") String id_number, @Param("batch_id") Integer batch_id);
}
