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
public class ScorePeriodModel implements Serializable {
	private static final long serialVersionUID = 1L;

	//columns START
	private Integer id;//"主键ID"

	private Integer scorePeriodId;//"积分指标ID"

	private String datetime;//"日期（分上午和下午，上午为0点，下午为12点）"

	private Integer totalNumber;//"总人数"

	private Integer reservationNumber;//"已预约人数"

	private Integer location;//"地点，1、市级行政许可中心，2、滨海新区行政服务中心"

	private Date ctime;//"创建时间"

	//columns END
		
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}
		
	public void setScorePeriodId(Integer scorePeriodId) {
		this.scorePeriodId = scorePeriodId;
	}

	public Integer getScorePeriodId() {
		return this.scorePeriodId;
	}
		
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getDatetime() {
		return this.datetime;
	}
		
	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}

	public Integer getTotalNumber() {
		return this.totalNumber;
	}
		
	public void setReservationNumber(Integer reservationNumber) {
		this.reservationNumber = reservationNumber;
	}

	public Integer getReservationNumber() {
		return this.reservationNumber;
	}
		
	public void setLocation(Integer location) {
		this.location = location;
	}

	public Integer getLocation() {
		return this.location;
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

