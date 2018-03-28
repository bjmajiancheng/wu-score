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
public class IdentityPeriodReviewModel implements Serializable {
	private static final long serialVersionUID = 1L;

	//columns START
	private Integer id;//"主键ID"

	private Integer applyType;//"申请类型，1、申请取消资格，2、申请重新打分"

	private Integer applyReason;//"申请原因1、违反国家和本地剩余政策，2、申请居住证期间使用虚假材料"

	private String applyRemark;//"说明"

	private Integer applyPersonId;//"申请人"

	private Integer applyTime;//"申请时间"

	private Integer reviewStatus;//"审核状态，1、申请中，2、审核通过"

	private Integer reviewPersonId;//"审核人Id"

	private Integer reviewDept;//"审核人部门"

	private String reviewRemark;//"审核说明"

	private Integer reviewTime;//"审核时间"

	private Date ctime;//"创建时间"

	//columns END
		
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}
		
	public void setApplyType(Integer applyType) {
		this.applyType = applyType;
	}

	public Integer getApplyType() {
		return this.applyType;
	}
		
	public void setApplyReason(Integer applyReason) {
		this.applyReason = applyReason;
	}

	public Integer getApplyReason() {
		return this.applyReason;
	}
		
	public void setApplyRemark(String applyRemark) {
		this.applyRemark = applyRemark;
	}

	public String getApplyRemark() {
		return this.applyRemark;
	}
		
	public void setApplyPersonId(Integer applyPersonId) {
		this.applyPersonId = applyPersonId;
	}

	public Integer getApplyPersonId() {
		return this.applyPersonId;
	}
		
	public void setApplyTime(Integer applyTime) {
		this.applyTime = applyTime;
	}

	public Integer getApplyTime() {
		return this.applyTime;
	}
		
	public void setReviewStatus(Integer reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	public Integer getReviewStatus() {
		return this.reviewStatus;
	}
		
	public void setReviewPersonId(Integer reviewPersonId) {
		this.reviewPersonId = reviewPersonId;
	}

	public Integer getReviewPersonId() {
		return this.reviewPersonId;
	}
		
	public void setReviewDept(Integer reviewDept) {
		this.reviewDept = reviewDept;
	}

	public Integer getReviewDept() {
		return this.reviewDept;
	}
		
	public void setReviewRemark(String reviewRemark) {
		this.reviewRemark = reviewRemark;
	}

	public String getReviewRemark() {
		return this.reviewRemark;
	}
		
	public void setReviewTime(Integer reviewTime) {
		this.reviewTime = reviewTime;
	}

	public Integer getReviewTime() {
		return this.reviewTime;
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

