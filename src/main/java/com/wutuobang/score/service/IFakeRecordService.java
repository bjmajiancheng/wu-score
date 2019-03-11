package com.wutuobang.score.service;

import com.wutuobang.score.model.FakeRecordModel;

/**
 * Created by rui15 on 2019/3/11.
 */
public interface IFakeRecordService {
    public FakeRecordModel getByIdNumber(String IdNumber);
}
