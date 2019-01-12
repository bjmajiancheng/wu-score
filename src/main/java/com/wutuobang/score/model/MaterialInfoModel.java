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

	private Integer isUpload;//"是否需要上传"

	private String category;//一级分类  1：必传材料；2：人力社保局材料；3:公安局材料；

	private String categoryRenshe;//二级分类 21：高级技工学校高级班相关材料；22：专业技术人员职业资格证书相关材料；23：技能职业资格证书相关材料；24：奖项及荣誉称号相关材料；25：其他；
	//columns END

	//自定义属性START
	private OnlinePersonMaterialModel onlinePersonMaterial;
	//自定义属性END

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

	public Integer getIsUpload() {
		return isUpload;
	}

	public void setIsUpload(Integer isUpload) {
		this.isUpload = isUpload;
	}

	public OnlinePersonMaterialModel getOnlinePersonMaterial() {
		return onlinePersonMaterial;
	}

	public void setOnlinePersonMaterial(OnlinePersonMaterialModel onlinePersonMaterial) {
		this.onlinePersonMaterial = onlinePersonMaterial;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategoryRenshe() {
		return categoryRenshe;
	}

	public void setCategoryRenshe(String categoryRenshe) {
		this.categoryRenshe = categoryRenshe;
	}
}

