/*
 * 北京果敢时代科技有限公司
 * 北京市朝阳区望京SOHO T3 B座1607
 * 邮编：100022
 * 网址：www.davdian.com
 */

package com.wutuobang.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */
public class AttachmentModel implements Serializable {
	private static final long serialVersionUID = 1L;

	//columns START
	private Integer attachmentId;//"附件id"

	private String attachmentName;//"附件名称"

	private Integer attachmentType;//"附件类型:0:图片 1:文档 2:其他"

	private String attachmentSuffix;//"附件后缀"

	private String attachmentPath;//"附件文件路径"

	private String attachmentUrl;//"附件文件路径"

	private Long attachmentSize;//"附件文件大小"

	private String uploadLoginName;//"上传附件用户"

	//columns END
		
	public void setAttachmentId(Integer attachmentId) {
		this.attachmentId = attachmentId;
	}

	public Integer getAttachmentId() {
		return this.attachmentId;
	}
		
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public String getAttachmentName() {
		return this.attachmentName;
	}
		
	public void setAttachmentType(Integer attachmentType) {
		this.attachmentType = attachmentType;
	}

	public Integer getAttachmentType() {
		return this.attachmentType;
	}
		
	public void setAttachmentSuffix(String attachmentSuffix) {
		this.attachmentSuffix = attachmentSuffix;
	}

	public String getAttachmentSuffix() {
		return this.attachmentSuffix;
	}
		
	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}

	public String getAttachmentPath() {
		return this.attachmentPath;
	}
		
	public void setAttachmentUrl(String attachmentUrl) {
		this.attachmentUrl = attachmentUrl;
	}

	public String getAttachmentUrl() {
		return this.attachmentUrl;
	}
		
	public void setAttachmentSize(Long attachmentSize) {
		this.attachmentSize = attachmentSize;
	}

	public Long getAttachmentSize() {
		return this.attachmentSize;
	}
		
	public void setUploadLoginName(String uploadLoginName) {
		this.uploadLoginName = uploadLoginName;
	}

	public String getUploadLoginName() {
		return this.uploadLoginName;
	}

	public Map<String, Object> getParam() {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("", "");
		return param;
	}
}

