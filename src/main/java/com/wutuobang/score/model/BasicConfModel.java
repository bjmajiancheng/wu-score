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
public class BasicConfModel implements Serializable {
	private static final long serialVersionUID = 1L;

	//columns START
	private Integer id;//"id:hidden"

	private BigDecimal selfTestScoreLine;//"自测合格分数"

	private Integer selfTestLimit;//"自测评次数限制"

	private Integer appointmentLimit;//"预约限制次数"

	private Integer companyEditLimit;//"经办人信息修改次数限制"

	private Integer publishDay;//"发布天数"

	private Integer noticeDay;//"公示天数"

	//columns END
		
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}
		
	public void setSelfTestScoreLine(BigDecimal selfTestScoreLine) {
		this.selfTestScoreLine = selfTestScoreLine;
	}

	public BigDecimal getSelfTestScoreLine() {
		return this.selfTestScoreLine;
	}
		
	public void setSelfTestLimit(Integer selfTestLimit) {
		this.selfTestLimit = selfTestLimit;
	}

	public Integer getSelfTestLimit() {
		return this.selfTestLimit;
	}
		
	public void setAppointmentLimit(Integer appointmentLimit) {
		this.appointmentLimit = appointmentLimit;
	}

	public Integer getAppointmentLimit() {
		return this.appointmentLimit;
	}
		
	public void setCompanyEditLimit(Integer companyEditLimit) {
		this.companyEditLimit = companyEditLimit;
	}

	public Integer getCompanyEditLimit() {
		return this.companyEditLimit;
	}
		
	public void setPublishDay(Integer publishDay) {
		this.publishDay = publishDay;
	}

	public Integer getPublishDay() {
		return this.publishDay;
	}
		
	public void setNoticeDay(Integer noticeDay) {
		this.noticeDay = noticeDay;
	}

	public Integer getNoticeDay() {
		return this.noticeDay;
	}

	public Map<String, Object> getParam() {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("", "");
		return param;
	}
}

