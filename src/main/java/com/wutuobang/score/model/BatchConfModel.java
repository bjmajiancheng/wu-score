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
public class BatchConfModel implements Serializable {
	private static final long serialVersionUID = 1L;

	//columns START
	private Integer id;//"id"

	private String batchName;//"批次名称：2018年1期"

	private String batchNumber;//"批次号：201811"

	private Date applyBegin;//"在线申请开始日期"

	private Date applyEnd;//"在线申请结束日期"

	private Date acceptBegin;//"受理开始日期"

	private Date acceptEnd;//"受理结束日期"

	private Date publishBegin;//"发布开始日期"

	private Date publishEnd;//"发布结束日期"

	private Date noticeBegin;//"公示开始日期"

	private Date noticeEnd;//"公示结束日期"

	private Date selfScoreEnd;//"公示结束日期"

	private Integer acceptAddressId;//"受理地点"

	private Integer indicatorType;//"指标方式 0:总人数选取 1:分数线选取"

	private Integer indicatorValue;//"指标值"

	private Integer acceptUserCount;//"设置受理人数"

	private Integer status;//"当前状态"

	private Integer process;//"当前进程"

	/*
	添加4个字段值
	* 1、关闭单位注册、填写申请人信息、自助测评、申请预审功能的时间；
     * 2、打开单位注册、填写申请人信息、自助测评、申请预审功能的时间；
     * 3、关闭申请人登录功能的时间；
     * 4、打开申请人登录功能的时间；
	 */
	private Date closeFunctionTime;
	private Date openFunctionTime;
	private Date closeLoginTime;
	private Date openLoginTime;

	/*
	2019年1月4日
	 */
	private Integer scoreValue;//"指标值"


	//columns END
		
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}
		
	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public String getBatchName() {
		return this.batchName;
	}
		
	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public String getBatchNumber() {
		return this.batchNumber;
	}
		
	public void setApplyBegin(Date applyBegin) {
		this.applyBegin = applyBegin;
	}

	public Date getApplyBegin() {
		return this.applyBegin;
	}
		
	public void setApplyEnd(Date applyEnd) {
		this.applyEnd = applyEnd;
	}

	public Date getApplyEnd() {
		return this.applyEnd;
	}
		
	public void setAcceptBegin(Date acceptBegin) {
		this.acceptBegin = acceptBegin;
	}

	public Date getAcceptBegin() {
		return this.acceptBegin;
	}
		
	public void setAcceptEnd(Date acceptEnd) {
		this.acceptEnd = acceptEnd;
	}

	public Date getAcceptEnd() {
		return this.acceptEnd;
	}
		
	public void setPublishBegin(Date publishBegin) {
		this.publishBegin = publishBegin;
	}

	public Date getPublishBegin() {
		return this.publishBegin;
	}
		
	public void setPublishEnd(Date publishEnd) {
		this.publishEnd = publishEnd;
	}

	public Date getPublishEnd() {
		return this.publishEnd;
	}
		
	public void setNoticeBegin(Date noticeBegin) {
		this.noticeBegin = noticeBegin;
	}

	public Date getNoticeBegin() {
		return this.noticeBegin;
	}
		
	public void setNoticeEnd(Date noticeEnd) {
		this.noticeEnd = noticeEnd;
	}

	public Date getNoticeEnd() {
		return this.noticeEnd;
	}
		
	public void setSelfScoreEnd(Date selfScoreEnd) {
		this.selfScoreEnd = selfScoreEnd;
	}

	public Date getSelfScoreEnd() {
		return this.selfScoreEnd;
	}
		
	public void setAcceptAddressId(Integer acceptAddressId) {
		this.acceptAddressId = acceptAddressId;
	}

	public Integer getAcceptAddressId() {
		return this.acceptAddressId;
	}
		
	public void setIndicatorType(Integer indicatorType) {
		this.indicatorType = indicatorType;
	}

	public Integer getIndicatorType() {
		return this.indicatorType;
	}
		
	public void setIndicatorValue(Integer indicatorValue) {
		this.indicatorValue = indicatorValue;
	}

	public Integer getIndicatorValue() {
		return this.indicatorValue;
	}
		
	public void setAcceptUserCount(Integer acceptUserCount) {
		this.acceptUserCount = acceptUserCount;
	}

	public Integer getAcceptUserCount() {
		return this.acceptUserCount;
	}
		
	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}
		
	public void setProcess(Integer process) {
		this.process = process;
	}

	public Integer getProcess() {
		return this.process;
	}

	public void setCloseFunctionTime(Date closeFunctionTime) {
		this.closeFunctionTime = closeFunctionTime;
	}

	public void setOpenFunctionTime(Date openFunctionTime) {
		this.openFunctionTime = openFunctionTime;
	}

	public void setCloseLoginTime(Date closeLoginTime) {
		this.closeLoginTime = closeLoginTime;
	}

	public void setOpenLoginTime(Date openLoginTime) {
		this.openLoginTime = openLoginTime;
	}

	public Date getCloseFunctionTime() {
		return closeFunctionTime;
	}

	public Date getOpenFunctionTime() {
		return openFunctionTime;
	}

	public Date getCloseLoginTime() {
		return closeLoginTime;
	}

	public Date getOpenLoginTime() {
		return openLoginTime;
	}

	public Integer getScoreValue() {
		return scoreValue;
	}

	public void setScoreValue(Integer scoreValue) {
		this.scoreValue = scoreValue;
	}

	public Map<String, Object> getParam() {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("", "");
		return param;
	}
}

