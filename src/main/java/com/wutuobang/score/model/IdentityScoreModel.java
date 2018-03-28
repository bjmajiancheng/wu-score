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
public class IdentityScoreModel implements Serializable {
	private static final long serialVersionUID = 1L;

	//columns START
	private Integer id;//"主键ID"

	private Integer identityPeriodId;//"申请人员信息Id"

	private Integer questionNo;//"指标序号"

	private Integer questionId;//"指标问题编号"

	private BigDecimal score;//"获取分数"

	private Integer answerId;//"所选答案Id"

	private String reviewRemark;//"审核打分说明"

	private Integer reviewStatus;//"审核状态；1、通过，2、审核不通过，3、补件"

	private Integer reviewReasonId;//"审核不通过原因Id"

	private Date ctime;//"创建时间"

	//columns END
		
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}
		
	public void setIdentityPeriodId(Integer identityPeriodId) {
		this.identityPeriodId = identityPeriodId;
	}

	public Integer getIdentityPeriodId() {
		return this.identityPeriodId;
	}
		
	public void setQuestionNo(Integer questionNo) {
		this.questionNo = questionNo;
	}

	public Integer getQuestionNo() {
		return this.questionNo;
	}
		
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public Integer getQuestionId() {
		return this.questionId;
	}
		
	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public BigDecimal getScore() {
		return this.score;
	}
		
	public void setAnswerId(Integer answerId) {
		this.answerId = answerId;
	}

	public Integer getAnswerId() {
		return this.answerId;
	}
		
	public void setReviewRemark(String reviewRemark) {
		this.reviewRemark = reviewRemark;
	}

	public String getReviewRemark() {
		return this.reviewRemark;
	}
		
	public void setReviewStatus(Integer reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	public Integer getReviewStatus() {
		return this.reviewStatus;
	}
		
	public void setReviewReasonId(Integer reviewReasonId) {
		this.reviewReasonId = reviewReasonId;
	}

	public Integer getReviewReasonId() {
		return this.reviewReasonId;
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

