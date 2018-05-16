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
import com.wutuobang.score.model.MaterialInfoModel;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

@Service("materialInfoService")
public class MaterialInfoServiceImpl implements IMaterialInfoService{
	@Autowired
	private IMaterialInfoDao materialInfoDao;

	public int insert(MaterialInfoModel materialInfo) {
		if(materialInfo == null) {
			return 0;
		}
		return materialInfoDao.insert(materialInfo);
	}
	
	public int update(MaterialInfoModel materialInfo) {
		if(materialInfo == null) {
			return 0;
		}
		return materialInfoDao.update(materialInfo);
	}
	
	public MaterialInfoModel getById(Integer id) {
		if(id == null) {
			return null;
		}
		return materialInfoDao.getById(id);
	}

	public int removeById(Integer id) {
		if(id == null) {
			return 0;
		}
		return materialInfoDao.delete(id);
	}	

	public List<MaterialInfoModel> find(Map<String, Object> param) {
		return materialInfoDao.find(param);
	}
	
}
