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
public class SystemNoticeModel implements Serializable {
	private static final long serialVersionUID = 1L;

	//columns START
	private Integer id;//"主键ID"

	private String title;//"标题"

	private String content;//"内容"

	private Integer type;//"类型，1、相关政策，2、办理指南；2、重要通知"

	private String author;//"发文机关或作者"

	private Date ctime;//"创建时间"

	//columns END
		
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}
		
	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}
		
	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return this.content;
	}
		
	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getType() {
		return this.type;
	}
		
	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthor() {
		return this.author;
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

