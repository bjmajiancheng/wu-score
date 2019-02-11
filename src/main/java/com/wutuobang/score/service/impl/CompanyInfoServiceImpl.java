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
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.wutuobang.score.model.CompanyInfoModel;

import java.util.*;

import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author davdian
 * @version 1.0
 * @since 1.0
 */

@Service("companyInfoService")
public class CompanyInfoServiceImpl implements ICompanyInfoService {

    @Autowired
    private ICompanyInfoDao companyInfoDao;

    public int insert(CompanyInfoModel companyInfo) {
        if (companyInfo == null) {
            return 0;
        }
        return companyInfoDao.insert(companyInfo);
    }

    public int insertCompanyEditRecord(CompanyInfoModel companyInfo) {
        if (companyInfo == null) {
            return 0;
        }
        return companyInfoDao.insertCompanyEditRecord(companyInfo);
    }

    public int update(CompanyInfoModel companyInfo) {
        if (companyInfo == null) {
            return 0;
        }
        return companyInfoDao.update(companyInfo);
    }

    public CompanyInfoModel getById(Integer id) {
        if (id == null) {
            return null;
        }
        return companyInfoDao.getById(id);
    }

    public int removeById(Integer id) {
        if (id == null) {
            return 0;
        }
        return companyInfoDao.delete(id);
    }

    public List<CompanyInfoModel> find(Map<String, Object> param) {
        return companyInfoDao.find(param);
    }

    /**
     * 根据用户名获取信息
     *
     * @param userName
     * @return
     */
    public CompanyInfoModel queryByUserName(String userName) {
        if (StringUtils.isEmpty(userName)) {
            return null;
        }

        List<CompanyInfoModel> companyInfos = this.find(Collections.singletonMap("userName", (Object) userName));

        if (CollectionUtils.isNotEmpty(companyInfos)) {
            return companyInfos.get(0);
        }
        return null;
    }

    /**
     * 获取公司map信息
     *
     * @param ids
     * @return
     */
    public Map<Integer, CompanyInfoModel> getMapByIds(List<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyMap();
        }

        List<CompanyInfoModel> companyInfos = this.find(Collections.singletonMap("ids", (Object) ids));
        Map<Integer, CompanyInfoModel> companyInfoMap = new HashMap<Integer, CompanyInfoModel>();

        if (CollectionUtils.isNotEmpty(companyInfos)) {
            for (CompanyInfoModel companyInfo : companyInfos) {
                companyInfoMap.put(companyInfo.getId(), companyInfo);
            }
        }

        return companyInfoMap;
    }

    /**
     * 根据公司名称或统一社会代码查询注册公司
     *
     * @param companyName
     * @param societyCode
     * @return
     */
    public List<CompanyInfoModel> findByCompanyNameOrCode(String companyName, String societyCode) {
        if(StringUtils.isEmpty(companyName) || StringUtils.isEmpty(societyCode)) {
            return Collections.emptyList();
        }

        return companyInfoDao.findByCompanyNameOrCode(companyName, societyCode);
    }

}
