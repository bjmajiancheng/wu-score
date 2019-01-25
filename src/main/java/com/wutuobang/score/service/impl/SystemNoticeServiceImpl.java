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
import com.wutuobang.common.utils.PageData;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.wutuobang.score.model.SystemNoticeModel;

import java.util.*;

import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author davdian
 * @version 1.0
 * @since 1.0
 */

@Service("systemNoticeService")
public class SystemNoticeServiceImpl implements ISystemNoticeService {

    @Autowired
    private ISystemNoticeDao systemNoticeDao;

    public int insert(SystemNoticeModel systemNotice) {
        if (systemNotice == null) {
            return 0;
        }
        return systemNoticeDao.insert(systemNotice);
    }

    public int update(SystemNoticeModel systemNotice) {
        if (systemNotice == null) {
            return 0;
        }
        return systemNoticeDao.update(systemNotice);
    }

    public SystemNoticeModel getById(Integer id) {
        if (id == null) {
            return null;
        }
        return systemNoticeDao.getById(id);
    }

    public int removeById(Integer id) {
        if (id == null) {
            return 0;
        }
        return systemNoticeDao.delete(id);
    }

    public List<SystemNoticeModel> find(Map<String, Object> param) {
        return systemNoticeDao.find(param);
    }

    /**
     * 获取分页数据信息
     *
     * @param type
     * @return
     */
    @Override
    public PageData<SystemNoticeModel> findPage(Integer type, Integer pageNo) {
        if (type == null) {
            return new PageData<SystemNoticeModel>();
        }

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("type", type);
        /*param.put("start", (pageNo - 1) * CommonConstant.PAGE_SIZE);
        param.put("pageSize", CommonConstant.PAGE_SIZE);*/
        param.put("sortColumns", "id ASC");

        int pageCount = systemNoticeDao.findPageCount(param);
        if (pageCount == 0) {
            return new PageData<SystemNoticeModel>();
        }

        List<SystemNoticeModel> systemNotices = null;
        if (type == 2){
            systemNotices = systemNoticeDao.findPage(param, new RowBounds((pageNo - 1) * 30, 30));
        } else {
            systemNotices = systemNoticeDao.findPage(param, new RowBounds((pageNo - 1) * CommonConstant.PAGE_SIZE, CommonConstant.PAGE_SIZE));
        }

        PageData<SystemNoticeModel> pageData = new PageData<SystemNoticeModel>();
        pageData.setData(systemNotices);
        pageData.setRecordsTotal(pageCount);

        return pageData;
    }

    /**
     * 获取最新一条重要通知信息
     *
     * @return
     */
    public SystemNoticeModel getLastSystemNotice() {
        return systemNoticeDao.getLastSystemNotice();
    }

}
