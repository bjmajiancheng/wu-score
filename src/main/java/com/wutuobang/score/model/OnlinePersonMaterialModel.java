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
public class OnlinePersonMaterialModel implements Serializable {
	private static final long serialVersionUID = 1L;

	//columns START
	private Integer id;//"id"

	private Integer personId;//"申请人ID"

	private Integer batchId;//"批次ID"

	private Integer materialInfoId;//"材料信息Id"

	private Integer materialId;//"材料ID"

	private String materialName;//"材料名称"

	private String materialUri;//"材料uri"

	private Integer status;//"材料状态"

	private Date ctime;//"创建时间"
	//columns END
		
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}
		
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public Integer getPersonId() {
		return this.personId;
	}
		
	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}

	public Integer getBatchId() {
		return this.batchId;
	}

	public Integer getMaterialInfoId() {
		return materialInfoId;
	}

	public void setMaterialInfoId(Integer materialInfoId) {
		this.materialInfoId = materialInfoId;
	}

	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
	}

	public Integer getMaterialId() {
		return this.materialId;
	}
		
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getMaterialName() {
		return this.materialName;
	}
		
	public void setMaterialUri(String materialUri) {
		this.materialUri = materialUri;
	}

	public String getMaterialUri() {
		return this.materialUri;
	}
		
	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}
		
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Date getCtime() {
		return this.ctime;
	}
}

