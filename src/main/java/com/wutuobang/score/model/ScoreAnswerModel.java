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
public class ScoreAnswerModel implements Serializable {
	private static final long serialVersionUID = 1L;

	//columns START
	private Integer id;//"主键ID"

	private Integer scoreQuestionId;//"打分事项id"

	private String content;//"事项内容"

	private Integer score;//"事项分数"

	private Date ctime;//"创建时间"

	//columns END
		
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}
		
	public void setScoreQuestionId(Integer scoreQuestionId) {
		this.scoreQuestionId = scoreQuestionId;
	}

	public Integer getScoreQuestionId() {
		return this.scoreQuestionId;
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

