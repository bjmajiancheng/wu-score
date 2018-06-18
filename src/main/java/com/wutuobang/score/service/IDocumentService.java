/*
 * 北京果敢时代科技有限公司
 * 北京市朝阳区望京SOHO T3 B座1607
 * 邮编：100022
 * 网址：www.davdian.com
 */

package com.wutuobang.score.service;

import com.wutuobang.common.utils.PageData;
import com.wutuobang.score.model.DocumentModel;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

public interface IDocumentService{

	public int insert(DocumentModel document);

	public int update(DocumentModel document);

	public DocumentModel getById(Integer value);

	public int removeById(Integer value);

	public List<DocumentModel> find(Map<String, Object> param);

	/**
	 * 获取常用文件下载分页信息
	 *
	 * @param pageNo
	 * @return
     */
	public PageData<DocumentModel> findPage(Integer pageNo);
}
