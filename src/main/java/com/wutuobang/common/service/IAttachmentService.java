/*
 * 北京果敢时代科技有限公司
 * 北京市朝阳区望京SOHO T3 B座1607
 * 邮编：100022
 * 网址：www.davdian.com
 */

package com.wutuobang.common.service;

import com.wutuobang.common.model.AttachmentModel;
import java.util.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

public interface IAttachmentService {

	public int insert(AttachmentModel dattachment);

	public int update(AttachmentModel dattachment);

	public AttachmentModel getById(Integer value);

	public int removeById(Integer value);

	public List<AttachmentModel> find(Map<String, Object> param);
}
