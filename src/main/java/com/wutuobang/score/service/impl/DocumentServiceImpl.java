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
import com.wutuobang.score.model.CommonQuestionModel;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.wutuobang.score.model.DocumentModel;

import java.util.*;

import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author davdian
 * @version 1.0
 * @since 1.0
 */

@Service("documentService")
public class DocumentServiceImpl implements IDocumentService {

    @Autowired
    private IDocumentDao documentDao;

    public int insert(DocumentModel document) {
        if (document == null) {
            return 0;
        }
        return documentDao.insert(document);
    }

    public int update(DocumentModel document) {
        if (document == null) {
            return 0;
        }
        return documentDao.update(document);
    }

    public DocumentModel getById(Integer id) {
        if (id == null) {
            return null;
        }
        return documentDao.getById(id);
    }

    public int removeById(Integer id) {
        if (id == null) {
            return 0;
        }
        return documentDao.delete(id);
    }

    public List<DocumentModel> find(Map<String, Object> param) {
        return documentDao.find(param);
    }

    /**
     * 获取常用文件下载分页信息
     *
     * @param pageNo
     * @return
     */
    public PageData<DocumentModel> findPage(Integer pageNo) {
        Map<String, Object> param = new HashMap<String, Object>();

        int pageCount = this.documentDao.findPageCount(param);
        if (pageCount == 0) {
            return new PageData<DocumentModel>();
        }

        List<DocumentModel> documents = this.documentDao
                .findPage(param, new RowBounds((pageNo - 1) * CommonConstant.PAGE_SIZE, CommonConstant.PAGE_SIZE));

        PageData<DocumentModel> pageData = new PageData<DocumentModel>();
        pageData.setData(documents);
        pageData.setRecordsTotal(pageCount);

        return pageData;

    }

}
