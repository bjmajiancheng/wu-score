/*
 * 北京果敢时代科技有限公司
 * 北京市朝阳区望京SOHO T3 B座1607
 * 邮编：100022
 * 网址：www.davdian.com
 */

package com.wutuobang.score.model;

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
public class IdentityPeriodFileModel implements Serializable {
	private static final long serialVersionUID = 1L;

	//columns START
	private Integer id;//"主键ID"

	private Integer periodId;//"期限ID"

	private Integer identityInfoId;//"申请人身份信息id"

	private Integer fileId;//"文件id"

	private Integer isCommit;//"文件是否提交， 1、已提交；2、未提交"

	private Date ctime;//"创建时间"

	//columns END
		
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}
		
	public void setPeriodId(Integer periodId) {
		this.periodId = periodId;
	}

	public Integer getPeriodId() {
		return this.periodId;
	}
		
	public void setIdentityInfoId(Integer identityInfoId) {
		this.identityInfoId = identityInfoId;
	}

	public Integer getIdentityInfoId() {
		return this.identityInfoId;
	}
		
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public Integer getFileId() {
		return this.fileId;
	}
		
	public void setIsCommit(Integer isCommit) {
		this.isCommit = isCommit;
	}

	public Integer getIsCommit() {
		return this.isCommit;
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

