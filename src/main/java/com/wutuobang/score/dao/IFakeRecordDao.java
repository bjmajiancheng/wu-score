package com.wutuobang.score.dao;

import com.wutuobang.score.model.FakeRecordModel;

/**
 * Created by rui15 on 2019/3/1.
 */
public interface IFakeRecordDao {

    public FakeRecordModel getByIdNumber(String IdNumber);

}
