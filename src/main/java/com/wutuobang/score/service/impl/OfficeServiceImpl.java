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
import com.wutuobang.score.model.OfficeModel;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

@Service("officeService")
public class OfficeServiceImpl implements IOfficeService{
	@Autowired
	private IOfficeDao officeDao;

	public int insert(OfficeModel office) {
		if(office == null) {
			return 0;
		}
		return officeDao.insert(office);
	}
	
	public int update(OfficeModel office) {
		if(office == null) {
			return 0;
		}
		return officeDao.update(office);
	}
	
	public OfficeModel getById(Integer id) {
		if(id == null) {
			return null;
		}
		return officeDao.getById(id);
	}

	public int removeById(Integer id) {
		if(id == null) {
			return 0;
		}
		return officeDao.delete(id);
	}	

	public List<OfficeModel> find(Map<String, Object> param) {
		return officeDao.find(param);
	}

	/**
	 * 根据父id获取办公机关信息
	 *
	 * @param parentId
	 * @return
	 */
	public List<OfficeModel> getByParentId(Integer parentId) {
		if(parentId == null) {
			return Collections.emptyList();
		}

		return this.find(Collections.singletonMap("parentId", (Object)parentId));
	}
	
}
