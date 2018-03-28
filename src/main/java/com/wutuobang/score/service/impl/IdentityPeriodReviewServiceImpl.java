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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.wutuobang.score.model.IdentityPeriodReviewModel;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

@Service("identityPeriodReviewService")
public class IdentityPeriodReviewServiceImpl implements IIdentityPeriodReviewService{
	@Autowired
	private IIdentityPeriodReviewDao identityPeriodReviewDao;

	public int insert(IdentityPeriodReviewModel identityPeriodReview) {
		if(identityPeriodReview == null) {
			return 0;
		}
		return identityPeriodReviewDao.insert(identityPeriodReview);
	}
	
	public int update(IdentityPeriodReviewModel identityPeriodReview) {
		if(identityPeriodReview == null) {
			return 0;
		}
		return identityPeriodReviewDao.update(identityPeriodReview);
	}
	
	public IdentityPeriodReviewModel getById(Integer id) {
		if(id == null) {
			return null;
		}
		return identityPeriodReviewDao.getById(id);
	}

	public int removeById(Integer id) {
		if(id == null) {
			return 0;
		}
		return identityPeriodReviewDao.delete(id);
	}	

	public List<IdentityPeriodReviewModel> find(Map<String, Object> param) {
		return identityPeriodReviewDao.find(param);
	}
	
}
