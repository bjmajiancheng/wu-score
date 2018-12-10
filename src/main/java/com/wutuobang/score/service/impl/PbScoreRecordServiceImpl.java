package com.wutuobang.score.service.impl;

import com.wutuobang.score.dao.IPbScoreRecordDao;
import com.wutuobang.score.model.PbScoreRecordModel;
import com.wutuobang.score.service.IPbScoreRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
