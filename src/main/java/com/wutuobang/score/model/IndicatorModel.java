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
public class IndicatorModel implements Serializable {
	private static final long serialVersionUID = 1L;

	//columns START
	private Integer id;//"id"

	private String category;//"类别"

	private Integer indexNum;//"序号"

	private String name;//"指标"

	private String note;//"备注"

	private Integer itemType;//"选项类型  0：单选题，1：填空题"

	private Integer scoreRule;//"打分类型  0：单一部门打分，1：取最高分，2：同分则给分，3：累加计分"

	private Date createTime;//"创建时间"

	private String addUser;//"创建用户"

	//columns END
		
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}
		
	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategory() {
		return this.category;
	}
		
	public void setIndexNum(Integer indexNum) {
		this.indexNum = indexNum;
	}

	public Integer getIndexNum() {
		return this.indexNum;
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
		
	public void setItemType(Integer itemType) {
		this.itemType = itemType;
	}

	public Integer getItemType() {
		return this.itemType;
	}
		
	public void setScoreRule(Integer scoreRule) {
		this.scoreRule = scoreRule;
	}

	public Integer getScoreRule() {
		return this.scoreRule;
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

