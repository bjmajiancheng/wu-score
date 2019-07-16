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
public class HouseOtherModel implements Serializable {
	private static final long serialVersionUID = 1L;

	//columns START
	private Integer id;//"主键ID"

	private Integer identityInfoId;//"申请人身份信息id"

	private Integer applicantType;//"申请人类型，1、企业员工；2、投资者；3、个体工商户"

	private Integer politicalStatus;//"政治面貌，1、中共党员；2、中共预备党员；3、共青团员"

	private Integer soldierMeritorious;//"军人立功，0、无；1、一等功；2、二等功；3、三等功"

	private Integer cultureDegree;//"文化程度"

	private Integer degree;//"学位"

	private String profession;//"专业"

	private String companyName;//"单位名称"

	private String companyAddress;//"单位地址"

	/*
	2019年7月16日添加字段：申请人办公地址
	 */
	private String applyOfficeAddress;// 申请人办公地址

	private String jobTitle;//"当前工作岗位"

	private String jobContent;//"主要从事工作内容"

	private String companyPhone;//"单位电话"

	private String selfPhone;//"本人电话"

	private String applicationDate;//"居住证申领日期"

	private Integer socialSecurityPay;//"是否缴纳社保, 1、是；2、否"

	private Integer providentFund;//"是否参加住房公积金, 1、是；2、否"

	private Integer taxes;//"纳税情况, 1、是；2、否"

	private Integer detention;//"拘留情况, 1、是；2、否"

	private Integer penalty;//"获刑情况, 1、是；2、否"

	private Integer awardsTitle;//"奖项荣誉称号,1、拥有有效的中国发明专利；2、获得党中央、国务院授予的奖项和荣誉称号；3、获得省（自治区、直辖市）党委、政府或中央和国家机关部委等授予的奖项和荣誉称号；4、获得省（自治区、直辖市）党委、政府或中央和国家机关部委等授予的劳动模范或先进工作者荣誉称号，并享受省部级劳动模范或先进工作者待遇"

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
		
	public void setApplicantType(Integer applicantType) {
		this.applicantType = applicantType;
	}

	public Integer getApplicantType() {
		return this.applicantType;
	}
		
	public void setPoliticalStatus(Integer politicalStatus) {
		this.politicalStatus = politicalStatus;
	}

	public Integer getPoliticalStatus() {
		return this.politicalStatus;
	}
		
	public void setSoldierMeritorious(Integer soldierMeritorious) {
		this.soldierMeritorious = soldierMeritorious;
	}

	public Integer getSoldierMeritorious() {
		return this.soldierMeritorious;
	}
		
	public void setCultureDegree(Integer cultureDegree) {
		this.cultureDegree = cultureDegree;
	}

	public Integer getCultureDegree() {
		return this.cultureDegree;
	}
		
	public void setDegree(Integer degree) {
		this.degree = degree;
	}

	public Integer getDegree() {
		return this.degree;
	}
		
	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getProfession() {
		return this.profession;
	}
		
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyName() {
		return this.companyName;
	}
		
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getCompanyAddress() {
		return this.companyAddress;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getJobContent() {
		return jobContent;
	}

	public void setJobContent(String jobContent) {
		this.jobContent = jobContent;
	}

	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	public String getCompanyPhone() {
		return this.companyPhone;
	}
		
	public void setSelfPhone(String selfPhone) {
		this.selfPhone = selfPhone;
	}

	public String getSelfPhone() {
		return this.selfPhone;
	}
		
	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}

	public String getApplicationDate() {
		return this.applicationDate;
	}
		
	public void setSocialSecurityPay(Integer socialSecurityPay) {
		this.socialSecurityPay = socialSecurityPay;
	}

	public Integer getSocialSecurityPay() {
		return this.socialSecurityPay;
	}
		
	public void setProvidentFund(Integer providentFund) {
		this.providentFund = providentFund;
	}

	public Integer getProvidentFund() {
		return this.providentFund;
	}
		
	public void setTaxes(Integer taxes) {
		this.taxes = taxes;
	}

	public Integer getTaxes() {
		return this.taxes;
	}
		
	public void setDetention(Integer detention) {
		this.detention = detention;
	}

	public Integer getDetention() {
		return this.detention;
	}
		
	public void setPenalty(Integer penalty) {
		this.penalty = penalty;
	}

	public Integer getPenalty() {
		return this.penalty;
	}
		
	public void setAwardsTitle(Integer awardsTitle) {
		this.awardsTitle = awardsTitle;
	}

	public Integer getAwardsTitle() {
		return this.awardsTitle;
	}
		
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Date getCtime() {
		return this.ctime;
	}

	public String getApplyOfficeAddress() {
		return applyOfficeAddress;
	}

	public void setApplyOfficeAddress(String applyOfficeAddress) {
		this.applyOfficeAddress = applyOfficeAddress;
	}

	public Map<String, Object> getParam() {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("", "");
		return param;
	}
}

