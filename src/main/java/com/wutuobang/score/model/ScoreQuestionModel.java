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
public class ScoreQuestionModel implements Serializable {
	private static final long serialVersionUID = 1L;

	//columns START
	private Integer id;//"主键ID"

	private String name;//"指标名称"

	private String deptIds;//"相关部门可以为多个，中间以逗号分隔"

	private Integer scoreType;//"加分方式；1、独立加分，2、取部门打分最高分，3、累计加分参加社会保险按险种计分，每年最高积12分。4、两部门同时给10分，此项才能计10分，否则计0分，5、5打分未无时，累计减分； 5打分未取消资格时，发起取消资格流程"

	private Date ctime;//"创建时间"

	//columns END
		
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}
		
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
		
	public void setDeptIds(String deptIds) {
		this.deptIds = deptIds;
	}

	public String getDeptIds() {
		return this.deptIds;
	}
		
	public void setScoreType(Integer scoreType) {
		this.scoreType = scoreType;
	}

	public Integer getScoreType() {
		return this.scoreType;
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

