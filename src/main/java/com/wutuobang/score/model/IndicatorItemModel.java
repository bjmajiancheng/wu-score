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
public class IndicatorItemModel implements Serializable {
	private static final long serialVersionUID = 1L;

	//columns START
	private Integer id;//"id"

	private Integer indicatorId;//"指标信息ID"

	private String content;//"选项内容"

	private Integer score;//"分数"

	private String scoreFunction;//"分数计算"

	private Date createTime;//"创建时间"

	private String addUser;//"创建用户"

	//columns END
		
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}
		
	public void setIndicatorId(Integer indicatorId) {
		this.indicatorId = indicatorId;
	}

	public Integer getIndicatorId() {
		return this.indicatorId;
	}
		
	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return this.content;
	}
		
	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getScore() {
		return this.score;
	}
		
	public void setScoreFunction(String scoreFunction) {
		this.scoreFunction = scoreFunction;
	}

	public String getScoreFunction() {
		return this.scoreFunction;
	}
		
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}
		
	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}

	public String getAddUser() {
		return this.addUser;
	}

	public Map<String, Object> getParam() {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("", "");
		return param;
	}
}

