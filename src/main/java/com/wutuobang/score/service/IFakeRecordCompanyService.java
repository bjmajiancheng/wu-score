package com.wutuobang.score.service;

import com.wutuobang.score.model.BatchConfModel;
import com.wutuobang.score.model.FakeRecordCompanyModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by rui15 on 2019/3/1.
 */
public interface IFakeRecordCompanyService {
    public Integer find(String sco);

    public Integer findFakeRecordCompany(String name);


}
