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
public class HouseProfessionModel implements Serializable {
	private static final long serialVersionUID = 1L;

	//columns START
	private Integer id;//"主键ID"

	private Integer identityInfoId;//"申请人身份信息id"

	private Integer professionType;//"职业资格项，1、无；2、具有职称；3、具有职业资格"

	private Integer professionTitle;//专业名称

	private Integer jobTitleLevel;//"职称级别，1、初级职称；2、中级职称；3、高级职称"

	private String jobPosition;//"职位"

	private String issuingAuthority;//"发证机关"

	private String issuingDate;//"发证日期"

	private String certificateCode;//"证书编号"

	private Integer jobLevel;//"职业资格级别,1、高级技师；2、技师；3、高级工；4、中级工；5、初级工"

	private Integer jobName;//职业名称

	private Integer jobType;//"工种"

	private Date ctime;//"创建时间"

	private String awardsName;//"奖项名称"
	private String awardsOffical;//"授奖机关"
	private String awardsDate;//"获奖日期"
	private String graduateSchool;//"毕业院校"
	private String professional;//"专    业"
	private String office;//"发证机关"
	private String officeDate;//"发证日期"
	private String jobTypeName;//"取得相对应的高级技能证书工种名称"
	private String jobTypeDate;//"取得相对应的高级技能证书发证日期"

	//columns END


	public String getAwardsName() {
		return awardsName;
	}

	public void setAwardsName(String awardsName) {
		this.awardsName = awardsName;
	}

	public String getAwardsOffical() {
		return awardsOffical;
	}

	public void setAwardsOffical(String awardsOffical) {
		this.awardsOffical = awardsOffical;
	}

	public String getAwardsDate() {
		return awardsDate;
	}

	public void setAwardsDate(String awardsDate) {
		this.awardsDate = awardsDate;
	}

	public String getGraduateSchool() {
		return graduateSchool;
	}

	public void setGraduateSchool(String graduateSchool) {
		this.graduateSchool = graduateSchool;
	}

	public String getProfessional() {
		return professional;
	}

	public void setProfessional(String professional) {
		this.professional = professional;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getOfficeDate() {
		return officeDate;
	}

	public void setOfficeDate(String officeDate) {
		this.officeDate = officeDate;
	}

	public String getJobTypeName() {
		return jobTypeName;
	}

	public void setJobTypeName(String jobTypeName) {
		this.jobTypeName = jobTypeName;
	}

	public String getJobTypeDate() {
		return jobTypeDate;
	}

	public void setJobTypeDate(String jobTypeDate) {
		this.jobTypeDate = jobTypeDate;
	}

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
		
	public void setProfessionType(Integer professionType) {
		this.professionType = professionType;
	}

	public Integer getProfessionType() {
		return this.professionType;
	}

	public Integer getProfessionTitle() {
		return professionTitle;
	}

	public void setProfessionTitle(Integer professionTitle) {
		this.professionTitle = professionTitle;
	}

	public void setJobTitleLevel(Integer jobTitleLevel) {
		this.jobTitleLevel = jobTitleLevel;
	}

	public Integer getJobTitleLevel() {
		return this.jobTitleLevel;
	}
		
	public void setJobPosition(String jobPosition) {
		this.jobPosition = jobPosition;
	}

	public String getJobPosition() {
		return this.jobPosition;
	}
		
	public void setIssuingAuthority(String issuingAuthority) {
		this.issuingAuthority = issuingAuthority;
	}

	public String getIssuingAuthority() {
		return this.issuingAuthority;
	}
		
	public void setIssuingDate(String issuingDate) {
		this.issuingDate = issuingDate;
	}

	public String getIssuingDate() {
		return this.issuingDate;
	}
		
	public void setCertificateCode(String certificateCode) {
		this.certificateCode = certificateCode;
	}

	public String getCertificateCode() {
		return this.certificateCode;
	}
		
	public void setJobLevel(Integer jobLevel) {
		this.jobLevel = jobLevel;
	}

	public Integer getJobName() {
		return jobName;
	}

	public void setJobName(Integer jobName) {
		this.jobName = jobName;
	}

	public Integer getJobLevel() {
		return this.jobLevel;
	}
		
	public void setJobType(Integer jobType) {
		this.jobType = jobType;
	}

	public Integer getJobType() {
		return this.jobType;
	}
		
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Date getCtime() {
		return this.ctime;
	}
}

