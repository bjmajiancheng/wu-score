/*
 * 北京果敢时代科技有限公司
 * 北京市朝阳区望京SOHO T3 B座1607
 * 邮编：100022
 * 网址：www.davdian.com
 */

package com.wutuobang.common.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.wutuobang.common.dao.IAttachmentFileDao;
import com.wutuobang.common.service.IAttachmentFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.wutuobang.common.model.AttachmentFileModel;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

@Service("attachmentFileService")
public class AttachmentFileServiceImpl implements IAttachmentFileService {
	@Autowired
	private IAttachmentFileDao attachmentFileDao;

	public int insert(AttachmentFileModel attachmentFile) {
		if(attachmentFile == null) {
			return 0;
		}
		return attachmentFileDao.insert(attachmentFile);
	}
	
	public int update(AttachmentFileModel attachmentFile) {
		if(attachmentFile == null) {
			return 0;
		}
		return attachmentFileDao.update(attachmentFile);
	}
	
	public AttachmentFileModel getById(Integer id) {
		if(id == null) {
			return null;
		}
		return attachmentFileDao.getById(id);
	}

	public int removeById(Integer id) {
		if(id == null) {
			return 0;
		}
		return attachmentFileDao.delete(id);
	}	

	public List<AttachmentFileModel> find(Map<String, Object> param) {
		return attachmentFileDao.find(param);
	}
	
}
