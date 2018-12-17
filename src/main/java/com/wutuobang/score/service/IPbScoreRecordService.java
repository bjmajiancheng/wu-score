package com.wutuobang.score.service;

import com.wutuobang.score.model.BatchConfModel;
import com.wutuobang.score.model.PbScoreRecordModel;

import java.util.List;

/**
 * Created by rui15 on 2018/12/7.
 */
public interface IPbScoreRecordService {
    /*
    通过申请人ID查找到所有的打分项，每个人都有20项；
     */
    public List<PbScoreRecordModel> getByPersonId(Integer id);

    /*
    2018年12月10日 名单公示
     */
    public List<PbScoreRecordModel> getPublicList(Integer batch_id);


    /*
    2018年12月13日 根据居住证积分的
     */
    public List<PbScoreRecordModel> findPublicPage(Integer batch_id, Integer indicatorType, Integer indicatorValue, Integer pageNo);

    /*
    2018年12月16日
     */
    public int findPublicPageCount(BatchConfModel bc);

    /*
    查询一条数据
     */
    public List<PbScoreRecordModel> findOnePbScoreRecord(String id_number,Integer batch_id);

}
