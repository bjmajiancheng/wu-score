/*
 * 北京果敢时代科技有限公司
 * 北京市朝阳区望京SOHO T3 B座1607
 * 邮编：100022
 * 网址：www.davdian.com
 */

package com.wutuobang.score.service.impl;

import com.wutuobang.score.dao.IBatchConfDao;
import com.wutuobang.score.dao.IFakeRecordCompanyDao;
import com.wutuobang.score.model.BatchConfModel;
import com.wutuobang.score.model.FakeRecordCompanyModel;
import com.wutuobang.score.service.IBatchConfService;
import com.wutuobang.score.service.IFakeRecordCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author davdian
 * @version 1.0
 * @since 1.0
 */

@Service("fakeRecordCompanyService")
public class FakeRecordCompanyServiceImpl implements IFakeRecordCompanyService {

    @Autowired
    private IFakeRecordCompanyDao fakeRecordCompanyDao;

    public Integer find(String sco) {
        return fakeRecordCompanyDao.find(sco);
    }

    public Integer findFakeRecordCompany(String name) {
        return fakeRecordCompanyDao.findFakeRecordCompany(name);
    }

}
