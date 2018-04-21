/*
 * 北京果敢时代科技有限公司
 * 北京市朝阳区望京SOHO T3 B座1607
 * 邮编：100022
 * 网址：www.davdian.com
 */

package com.wutuobang.common.service.impl;

import java.util.List;

import com.wutuobang.common.model.AttachmentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import com.wutuobang.common.dao.*;
import com.wutuobang.common.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

@Service("dattachmentService")
public class AttachmentServiceImpl implements IAttachmentService {
	@Autowired
	private IAttachmentDao dattachmentDao;

	public int insert(AttachmentModel dattachment) {
		if(dattachment == null) {
			return 0;
		}
		return dattachmentDao.insert(dattachment);
	}
	
	public int update(AttachmentModel dattachment) {
		if(dattachment == null) {
			return 0;
		}
		return dattachmentDao.update(dattachment);
	}
	
	public AttachmentModel getById(Integer id) {
		if(id == null) {
			return null;
		}
		return dattachmentDao.getById(id);
	}

	public int removeById(Integer id) {
		if(id == null) {
			return 0;
		}
		return dattachmentDao.delete(id);
	}	

	public List<AttachmentModel> find(Map<String, Object> param) {
		return dattachmentDao.find(param);
	}
	
}
