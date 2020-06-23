/*
 * 北京果敢时代科技有限公司
 * 北京市朝阳区望京SOHO T3 B座1607
 * 邮编：100022
 * 网址：www.davdian.com
 */

package com.wutuobang.score.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.wutuobang.common.constant.CommonConstant;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.wutuobang.score.model.PbScoreResultModel;

import java.util.*;

import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author davdian
 * @version 1.0
 * @since 1.0
 */

@Service("pbScoreResultService")
public class PbScoreResultServiceImpl implements IPbScoreResultService {

    @Autowired
    private IPbScoreResultDao pbScoreResultDao;

    public int insert(PbScoreResultModel pbScoreResult) {
        if (pbScoreResult == null) {
            return 0;
        }
        return pbScoreResultDao.insert(pbScoreResult);
    }

    public int update(PbScoreResultModel pbScoreResult) {
        if (pbScoreResult == null) {
            return 0;
        }
        return pbScoreResultDao.update(pbScoreResult);
    }

    public PbScoreResultModel getById(Integer id) {
        if (id == null) {
            return null;
        }
        return pbScoreResultDao.getById(id);
    }

    public int removeById(Integer id) {
        if (id == null) {
            return 0;
        }
        return pbScoreResultDao.delete(id);
    }

    public List<PbScoreResultModel> find(Map<String, Object> param) {
        return pbScoreResultDao.find(param);
    }

    /**
     * 根据申请人id查询信息
     *
     * @param personId
     * @return
     */
    public List<PbScoreResultModel> getByPersonId(Integer personId) {
        if (personId == null) {
            return Collections.emptyList();
        }

        return this.find(Collections.singletonMap("personId", (Object) personId));
    }

    /**
     * 获取当前批次名单公示信息
     *
     * @param batchId
     * @return
     */
    public List<PbScoreResultModel> findCurrBatch(Integer batchId,Integer pageNo) {
        if(batchId == null) {
            return Collections.emptyList();
        }

        return pbScoreResultDao.findCurrBatch(batchId, new RowBounds((pageNo - 1) * CommonConstant.PAGE_SIZE, CommonConstant.PAGE_SIZE));
    }
    public List<PbScoreResultModel> findCurrBatch(Integer batchId) {
        if(batchId == null) {
            return Collections.emptyList();
        }
        Integer pageNo = 1;

        return pbScoreResultDao.findCurrBatch(batchId, new RowBounds((pageNo - 1) * CommonConstant.PAGE_SIZE, CommonConstant.PAGE_SIZE));
    }

    @Override
    public PbScoreResultModel getByPersonIdNum(Integer batchId,String personIdNum) {
        if (personIdNum == null) {
            return null;
        }
        return pbScoreResultDao.getByPersonIdNum(batchId,personIdNum);
    }

    @Override
    public int getCount(Integer batchId) {
        return pbScoreResultDao.getCount(batchId);
    }

}
