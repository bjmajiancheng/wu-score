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
public class MaterialInfoModel implements Serializable {
	private static final long serialVersionUID = 1L;

	//columns START
	private Integer id;//"id:hidden"

	private String name;//"材料名:text"

	private String note;//"备注:textarea"

	private Date createTime;//"创建时间:skip"

	private String addUser;//"创建用户:skip"

	private Integer titleId;//"材料标题ID"

	private String title;//"材料标题"

	private String templateImg;//"模板图片"
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
		
	public void setNote(String note) {
		this.note = note;
	}

	public String getNote() {
		return this.note;
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
		
	public void setTitleId(Integer titleId) {
		this.titleId = titleId;
	}

	public Integer getTitleId() {
		return this.titleId;
	}
		
	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

	public String getTemplateImg() {
		return templateImg;
	}

	public void setTemplateImg(String templateImg) {
		this.templateImg = templateImg;
	}
}

