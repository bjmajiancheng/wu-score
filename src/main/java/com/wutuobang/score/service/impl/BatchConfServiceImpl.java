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

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.wutuobang.score.model.BatchConfModel;

import java.util.*;

import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author davdian
 * @version 1.0
 * @since 1.0
 */

@Service("batchConfService")
public class BatchConfServiceImpl implements IBatchConfService {

    @Autowired
    private IBatchConfDao batchConfDao;

    public int insert(BatchConfModel batchConf) {
        if (batchConf == null) {
            return 0;
        }
        return batchConfDao.insert(batchConf);
    }

    public int update(BatchConfModel batchConf) {
        if (batchConf == null) {
            return 0;
        }
        return batchConfDao.update(batchConf);
    }

    public BatchConfModel getById(Integer id) {
        if (id == null) {
            return null;
        }
        return batchConfDao.getById(id);
    }

    public int removeById(Integer id) {
        if (id == null) {
            return 0;
        }
        return batchConfDao.delete(id);
    }

    public List<BatchConfModel> find(Map<String, Object> param) {
        return batchConfDao.find(param);
    }

    /**
     * 根据时间获取批次信息
     *
     * @return
     */
    public BatchConfModel getBatchInfoByDate(Date date) {
        if (date == null) {
            date = new Date();
        }

        BatchConfModel batchConfModel = this.batchConfDao.getBatchInfoByDate(date);

        return batchConfModel;
    }

}
