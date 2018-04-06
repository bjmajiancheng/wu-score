/*
 * 北京果敢时代科技有限公司
 * 北京市朝阳区望京SOHO T3 B座1607
 * 邮编：100022
 * 网址：www.davdian.com
 */

package com.wutuobang.common.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */
public class AttachmentFileModel implements Serializable {
	private static final long serialVersionUID = 1L;

	//columns START
	private Integer id;//"主键ID"

	private String fileName;//"文件名"

	private String filePath;//"文件路径"

	private String downloadPath;//"下载路径"

	private Integer isSystem;//"系统文件 1：是，0：否"

	private Date ctime;//"创建时间"
	//columns END

	//自定义属性信息START
	/** 系统文件:是 */
	public static final int IS_SYSTEM_YES = 1;
	/** 系统文件:否 */
	public static final int IS_SYSTEM_NO = 0;
	//自定义属性信息END
		
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}
		
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return this.fileName;
	}
		
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFilePath() {
		return this.filePath;
	}
		
	public void setDownloadPath(String downloadPath) {
		this.downloadPath = downloadPath;
	}

	public String getDownloadPath() {
		return this.downloadPath;
	}
		
	public void setIsSystem(Integer isSystem) {
		this.isSystem = isSystem;
	}

	public Integer getIsSystem() {
		return this.isSystem;
	}
		
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Date getCtime() {
		return this.ctime;
	}

	public Map<String, Object> getParam() {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("", "");
		return param;
	}
}

