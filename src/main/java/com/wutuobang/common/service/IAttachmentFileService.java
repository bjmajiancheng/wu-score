/*
 * 北京果敢时代科技有限公司
 * 北京市朝阳区望京SOHO T3 B座1607
 * 邮编：100022
 * 网址：www.davdian.com
 */

package com.wutuobang.common.service;

import com.wutuobang.common.model.AttachmentFileModel;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

public interface IAttachmentFileService{

	public int insert(AttachmentFileModel attachmentFile);

	public int update(AttachmentFileModel attachmentFile);

	public AttachmentFileModel getById(Integer value);

	public int removeById(Integer value);

	public List<AttachmentFileModel> find(Map<String, Object> param);
}
