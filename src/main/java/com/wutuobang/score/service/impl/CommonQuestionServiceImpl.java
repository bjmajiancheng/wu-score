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
import com.wutuobang.score.model.CommonQuestionModel;

import java.util.*;

import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author davdian
 * @version 1.0
 * @since 1.0
 */

@Service("commonQuestionService")
public class CommonQuestionServiceImpl implements ICommonQuestionService {

    @Autowired
    private ICommonQuestionDao commonQuestionDao;

    public int insert(CommonQuestionModel commonQuestion) {
        if (commonQuestion == null) {
            return 0;
        }
        return commonQuestionDao.insert(commonQuestion);
    }

    public int update(CommonQuestionModel commonQuestion) {
        if (commonQuestion == null) {
            return 0;
        }
        return commonQuestionDao.update(commonQuestion);
    }

    public CommonQuestionModel getById(Integer id) {
        if (id == null) {
            return null;
        }
        return commonQuestionDao.getById(id);
    }

    public int removeById(Integer id) {
        if (id == null) {
            return 0;
        }
        return commonQuestionDao.delete(id);
    }

    public List<CommonQuestionModel> find(Map<String, Object> param) {
        return commonQuestionDao.find(param);
    }

    /**
     * 获取常见问题分页数据
     *
     * @param pageNo
     * @return
     */
    public PageData<CommonQuestionModel> findPage(Integer pageNo) {
        Map<String, Object> param = new HashMap<String, Object>();
        /*param.put("start", (pageNo - 1) * CommonConstant.PAGE_SIZE);
        param.put("pageSize", CommonConstant.PAGE_SIZE);*/
        int pageCount = this.commonQuestionDao.findPageCount(param);

        if (pageCount == 0) {
            return new PageData<CommonQuestionModel>();
        }

        List<CommonQuestionModel> commonQuestions = this.commonQuestionDao.findPage(param, new RowBounds((pageNo - 1) * CommonConstant.PAGE_SIZE, CommonConstant.PAGE_SIZE));

        PageData<CommonQuestionModel> pageData = new PageData<CommonQuestionModel>();
        pageData.setData(commonQuestions);
        pageData.setRecordsTotal(pageCount);

        return pageData;
    }

}
