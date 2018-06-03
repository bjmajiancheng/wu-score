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
import com.wutuobang.score.model.CompanyInfoModel;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.wutuobang.score.model.IdentityInfoModel;

import java.util.*;

import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author davdian
 * @version 1.0
 * @since 1.0
 */

@Service("identityInfoService")
public class IdentityInfoServiceImpl implements IIdentityInfoService {

    @Autowired
    private IIdentityInfoDao identityInfoDao;

    public int insert(IdentityInfoModel identityInfo) {
        if (identityInfo == null) {
            return 0;
        }
        return identityInfoDao.insert(identityInfo);
    }

    public int update(IdentityInfoModel identityInfo) {
        if (identityInfo == null) {
            return 0;
        }
        return identityInfoDao.update(identityInfo);
    }

    public IdentityInfoModel getById(Integer id) {
        if (id == null) {
            return null;
        }
        return identityInfoDao.getById(id);
    }

    public int removeById(Integer id) {
        if (id == null) {
            return 0;
        }
        return identityInfoDao.delete(id);
    }

    public List<IdentityInfoModel> find(Map<String, Object> param) {
        return identityInfoDao.find(param);
    }

    /**
     * 获取分页数据信息
     *
     * @param queryStr
     * @return
     */
    public PageData<IdentityInfoModel> findPage(CompanyInfoModel currUser, Integer batchId, String queryStr,
            Integer pageNo) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("companyId", currUser.getId());
        param.put("batchId", batchId);
        if (StringUtils.isNotEmpty(queryStr)) {
            param.put("queryStr", "%" + queryStr + "%");
        }

        /*param.put("start", (pageNo - 1) * CommonConstant.PAGE_SIZE);
        param.put("pageSize", CommonConstant.PAGE_SIZE);*/
        int pageCount = identityInfoDao.findPageCount(param);
        if (pageCount < 0) {
            return new PageData<IdentityInfoModel>();
        }
        param.put("sortColumns", "id ASC");

        List<IdentityInfoModel> identityInfos = identityInfoDao
                .findPage(param, new RowBounds((pageNo - 1) * CommonConstant.PAGE_SIZE, CommonConstant.PAGE_SIZE));

        PageData<IdentityInfoModel> identityInfoPageData = new PageData<IdentityInfoModel>();
        identityInfoPageData.setData(identityInfos);
        identityInfoPageData.setRecordsTotal(pageCount);

        return identityInfoPageData;
    }

    /**
     * 生成受理编号
     *
     * @param identityInfoModel
     * @return
     */
    public String generAcceptNumber(IdentityInfoModel identityInfoModel) {
        if (identityInfoModel == null) {
            return "";
        }

        String acceptNumber =
                "00000000" + String.format("%d%d", identityInfoModel.getBatchId(), identityInfoModel.getId());
        acceptNumber = acceptNumber.substring(acceptNumber.length() - 8, acceptNumber.length());

        return acceptNumber;
    }

}
