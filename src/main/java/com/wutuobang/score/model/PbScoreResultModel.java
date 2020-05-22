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
public class PbScoreResultModel implements Serializable {
	private static final long serialVersionUID = 1L;

	//columns START
	private Integer id;//"自增主键"

	private Integer batchId;//"spu商品id"

	private Integer indicatorId;//"指标id"

	private String indicatorName;//"指标名称"

	private Integer indicatorItemId;//"指标选项id"

	private Integer personId;//"申请人id"

	private String personName;//"申请人姓名"

	private String personIdNum;//"申请人身份证"

	private Integer scoreValue;//"分数值"

	private String scoreDetail;//"分数详情"

	private Date ctime;//"新建日期"

	//columns END
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}
		
	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}

	public Integer getBatchId() {
		return this.batchId;
	}
		
	public void setIndicatorId(Integer indicatorId) {
		this.indicatorId = indicatorId;
	}

	public Integer getIndicatorId() {
		return this.indicatorId;
	}
		
	public void setIndicatorName(String indicatorName) {
		this.indicatorName = indicatorName;
	}

	public String getIndicatorName() {
		return this.indicatorName;
	}
		
	public void setIndicatorItemId(Integer indicatorItemId) {
		this.indicatorItemId = indicatorItemId;
	}

	public Integer getIndicatorItemId() {
		return this.indicatorItemId;
	}
		
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public Integer getPersonId() {
		return this.personId;
	}
		
	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPersonName() {
		return this.personName;
	}
		
	public void setPersonIdNum(String personIdNum) {
		this.personIdNum = personIdNum;
	}

	public String getPersonIdNum() {
		return this.personIdNum;
	}
		
	public void setScoreValue(Integer scoreValue) {
		this.scoreValue = scoreValue;
	}

	public Integer getScoreValue() {
		return this.scoreValue;
	}
		
	public void setScoreDetail(String scoreDetail) {
		this.scoreDetail = scoreDetail;
	}

	public String getScoreDetail() {
		return this.scoreDetail;
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

