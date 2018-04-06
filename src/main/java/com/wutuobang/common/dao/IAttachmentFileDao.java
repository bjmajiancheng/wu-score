/*
 * 北京果敢时代科技有限公司
 * 北京市朝阳区望京SOHO T3 B座1607
 * 邮编：100022
 * 网址：www.davdian.com
 */

package com.wutuobang.common.dao;
import com.wutuobang.common.model.AttachmentFileModel;
import org.apache.ibatis.annotations.Param;
import java.util.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

public interface IAttachmentFileDao {

	public int insert(AttachmentFileModel attachmentFile);

	public int update(AttachmentFileModel attachmentFile);

	public AttachmentFileModel getById(@Param("id") Integer id);

	public int delete(@Param("id") Integer id);
	
	public List<AttachmentFileModel> find(Map<String, Object> param);
	
}
