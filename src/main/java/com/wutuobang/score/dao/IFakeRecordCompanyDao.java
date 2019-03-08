package com.wutuobang.score.dao;

import com.wutuobang.score.model.FakeRecordCompanyModel;

import java.util.List;
import java.util.Map;

/**
 * Created by rui15 on 2019/3/1.
 */
public interface IFakeRecordCompanyDao {

    public Integer find(String sco);

    public Integer findFakeRecordCompany(String name);

}
