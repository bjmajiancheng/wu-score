package com.wutuobang.score.service.impl;

import com.wutuobang.common.constant.CommonConstant;
import com.wutuobang.score.dao.IPbScoreRecordDao;
import com.wutuobang.score.model.BatchConfModel;
import com.wutuobang.score.model.PbScoreRecordModel;
import com.wutuobang.score.service.IPbScoreRecordService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rui15 on 2018/12/7.
 */
@Service("pbScoreRecordService")
public class PbScoreRecordServiceImpl implements IPbScoreRecordService {

    @Autowired
    private IPbScoreRecordDao pbScoreRecordDao;

    @Override
    public List<PbScoreRecordModel> getByPersonId(Integer id) {
        return pbScoreRecordDao.getByPersonId(id);
    }

    @Override
    public List<PbScoreRecordModel> getPublicList(Integer batch_id) {
        return pbScoreRecordDao.getPublicList(batch_id);
    }

    @Override
    public List<PbScoreRecordModel> findPublicPage(Integer batch_id, Integer indicatorType, Integer indicatorValue, Integer pageNo, Double scoreValue) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("batch_id",batch_id);
        param.put("indicatorType",indicatorType);
        param.put("indicatorValue", indicatorValue);
        param.put("scoreValue", scoreValue);
//        int pageNo = 1;
        List<PbScoreRecordModel> list = pbScoreRecordDao.findPublicPage(param, new RowBounds((pageNo - 1) * CommonConstant.PAGE_SIZE, CommonConstant.PAGE_SIZE));
        return  list;
    }

    @Override
    public int findPublicPageCount(BatchConfModel bc) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("batch_id",bc.getId());
        param.put("indicatorType",bc.getIndicatorType());
        param.put("indicatorValue", bc.getIndicatorValue());
        param.put("scoreValue",bc.getScoreValue());

        int count = 0;
        if (bc.getIndicatorType() == 0){
            count = bc.getIndicatorValue();
        }else{
            count = pbScoreRecordDao.findPublicPageCount(param);
        }
        return count;
    }

    @Override
    public List<PbScoreRecordModel> findOnePbScoreRecord(String id_number,Integer batch_id) {
        List<PbScoreRecordModel> list = pbScoreRecordDao.findOnePbScoreRecord(id_number, batch_id);
        return  list;
    }
}
