package com.wutuobang.score.service.impl;

import com.wutuobang.score.dao.IFakeRecordDao;
import com.wutuobang.score.model.FakeRecordModel;
import com.wutuobang.score.service.IFakeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by rui15 on 2019/3/11.
 */
@Service("fakeRecordService")
public class FakeRecordServiceImpl  implements IFakeRecordService {


    @Autowired

    private IFakeRecordDao fakeRecordDao;

    @Override
    public FakeRecordModel getByIdNumber(String IdNumber) {
        return fakeRecordDao.getByIdNumber(IdNumber);
    }
}
