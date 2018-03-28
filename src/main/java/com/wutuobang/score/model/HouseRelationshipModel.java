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
public class HouseRelationshipModel implements Serializable {
	private static final long serialVersionUID = 1L;

	//columns START
	private Integer id;//"主键ID"

	private Integer identityInfoId;//"申请人身份信息id"

	private String relationship;//"与本人关系"

	private String name;//"姓名"

	private String idNumber;//"身份证号"

	private Integer isRemove;//"是否随迁，1、是；2、否"

	private Integer cultureDegree;//"文化程度"

	private Date ctime;//"创建时间"

	//columns END
		
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}
		
	public void setIdentityInfoId(Integer identityInfoId) {
		this.identityInfoId = identityInfoId;
	}

	public Integer getIdentityInfoId() {
		return this.identityInfoId;
	}
		
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getRelationship() {
		return this.relationship;
	}
		
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
		
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getIdNumber() {
		return this.idNumber;
	}
		
	public void setIsRemove(Integer isRemove) {
		this.isRemove = isRemove;
	}

	public Integer getIsRemove() {
		return this.isRemove;
	}
		
	public void setCultureDegree(Integer cultureDegree) {
		this.cultureDegree = cultureDegree;
	}

	public Integer getCultureDegree() {
		return this.cultureDegree;
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

