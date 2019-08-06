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
 * @author davdian
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

	private String cultureDegree;//"文化程度"

	private Date ctime;//"创建时间"

	//2019-1-9添加卫健委信息

	private String formerName;//"曾用名"

	private Integer sex;//"性别：1、男；2、女"

	private String spouse_HJAddress;//"配偶户籍地详细地址"

	private String spouse_LivingAddress;//"配偶现居住地详细地址"

	private String policyAttribute;//"政策属性"

	private String medical_number;//"出生医学证明_编号"

	private String medical_authority;//"出生医学证明_签证机构"

	private String isAdopt;//"收养子女1、是；2、否"

	private String birthplace;//"出生地"

	private java.sql.Date approval_time;//"审批时间"

	private String approval_number;//"审批证明编号"

	private String approval_companyName;//"审批单位名称"

	private String approval_rules;//"审批条例适用"

	private String  approval_index; //第几任 选择项 1- 6

	private  String approval_spouse;//范围 妻子/丈夫

	private String approval_which;//"与第几任妻子/丈夫所生"

	private String approval_custody;//"抚养权归属"

	private String isNaturalChild;// 申请人本人亲生子女,1：是，2：否；

	public String getIsNaturalChild() {
		return isNaturalChild;
	}

	public void setIsNaturalChild(String isNaturalChild) {
		this.isNaturalChild = isNaturalChild;
	}

	//2019-1-9添加卫健委信息END

	//2019-1-17增加字段

	private Integer marriageStatus;  //配偶婚姻状况 0.请选择 7.初婚 8.复婚 9.再婚

	public Integer getMarriageStatus() {
		return marriageStatus;
	}

	public void setMarriageStatus(Integer marriageStatus) {
		this.marriageStatus = marriageStatus;
	}

	public String getStringMarriageStatus() {
		if (this.marriageStatus == null) {
			return "";
		} else {
			switch (this.marriageStatus) {
				case 0:
					return "";
				case 7:
					return "初婚";
				case 8:
					return "复婚";
				case 9:
					return "再婚";
				default:
					return "";
			}
		}
	}

	//2019-1-17增加字段End

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

	public void setCultureDegree(String cultureDegree) {
		this.cultureDegree = cultureDegree;
	}

	public String getCultureDegree() {
		return this.cultureDegree;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Date getCtime() {
		return this.ctime;
	}

	public String getFormerName() {
		return formerName;
	}

	public void setFormerName(String formerName) {
		this.formerName = formerName;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getSpouse_HJAddress() {
		return spouse_HJAddress;
	}

	public void setSpouse_HJAddress(String spouse_HJAddress) {
		this.spouse_HJAddress = spouse_HJAddress;
	}

	public String getSpouse_LivingAddress() {
		return spouse_LivingAddress;
	}

	public void setSpouse_LivingAddress(String spouse_LivingAddress) {
		this.spouse_LivingAddress = spouse_LivingAddress;
	}

	public String getPolicyAttribute() {
		return policyAttribute;
	}

	public void setPolicyAttribute(String policyAttribute) {
		this.policyAttribute = policyAttribute;
	}

	public String getMedical_number() {
		return medical_number;
	}

	public void setMedical_number(String medical_number) {
		this.medical_number = medical_number;
	}

	public String getMedical_authority() {
		return medical_authority;
	}

	public void setMedical_authority(String medical_authority) {
		this.medical_authority = medical_authority;
	}

	public String getIsAdopt() {
		return isAdopt;
	}

	public void setIsAdopt(String isAdopt) {
		this.isAdopt = isAdopt;
	}

	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	public java.sql.Date getApproval_time() {
		return approval_time;
	}

	public void setApproval_time(java.sql.Date approval_time) {
		this.approval_time = approval_time;
	}

	public String getApproval_number() {
		return approval_number;
	}

	public void setApproval_number(String approval_number) {
		this.approval_number = approval_number;
	}

	public String getApproval_companyName() {
		return approval_companyName;
	}

	public void setApproval_companyName(String approval_companyName) {
		this.approval_companyName = approval_companyName;
	}

	public String getApproval_rules() {
		return approval_rules;
	}

	public void setApproval_rules(String approval_rules) {
		this.approval_rules = approval_rules;
	}

	public String getApproval_which() {
		return approval_which;
	}

	public void setApproval_which(String approval_which) {
		this.approval_which = approval_which;
	}

	public String getApproval_custody() {
		return approval_custody;
	}

	public void setApproval_custody(String approval_custody) {
		this.approval_custody = approval_custody;
	}

	public Map<String, Object> getParam() {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("", "");
		return param;
	}

	public String[] getApproval_rulesArray() {
		if (this.approval_rules != null && !"".equals(this.approval_rules)) {
			int firstIndex = this.approval_rules.indexOf("省（市/自治区）人口与计划生育条例, 第");
			int secondIndex = this.approval_rules.indexOf("条第");
			int thirdIndex = this.approval_rules.indexOf("款第");
			int fourthIndex = this.approval_rules.indexOf("项");
			return new String[]{this.approval_rules.substring(0, firstIndex),
					this.approval_rules.substring(firstIndex + 20, secondIndex),
					this.approval_rules.substring(secondIndex + 2, thirdIndex),
					this.approval_rules.substring(thirdIndex + 2, fourthIndex)};
		} else {
			return new String[]{"", "", "", ""};
		}

	}

	public String getApproval_index() {
		return approval_index;
	}

	public void setApproval_index(String approval_index) {
		this.approval_index = approval_index;
	}

	public String getApproval_spouse() {
		return approval_spouse;
	}

	public void setApproval_spouse(String approval_spouse) {
		this.approval_spouse = approval_spouse;
	}
}

