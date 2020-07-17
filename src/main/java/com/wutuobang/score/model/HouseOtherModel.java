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

	private Integer kaifaquFund; // 2014年以前是否缴存开发区社保中心公积金：是/否

	private Integer taxes;//"纳税情况, 1、是；2、否"

	private Integer detention;//"拘留情况, 1、是；2、否"

	private Integer penalty;//"获刑情况, 1、是；2、否"

	private Integer isAnotherInsurance; //  申请人是否存在同时在异地缴纳社会保险情况，1、是；2、否

	private Integer isApplyRegiste; // 申请人是否在申请单位进行就业登记，1、是；2、否

	private Integer isInsurance;// 申请人是否在2008年以前在开发区有社保缴费记录，1、是；2、否

	private Integer isSpouseInsurance;// 配偶是否在本市缴纳5险且满2年以上，1、是；2、否

	private Integer awardsTitle;//"奖项荣誉称号,1、拥有有效的中国发明专利；2、获得党中央、国务院授予的奖项和荣誉称号；3、获得省（自治区、直辖市）党委、政府或中央和国家机关部委等授予的奖项和荣誉称号；4、获得省（自治区、直辖市）党委、政府或中央和国家机关部委等授予的劳动模范或先进工作者荣誉称号，并享受省部级劳动模范或先进工作者待遇"

	private Date ctime;//"创建时间"

	private String penaltyDate;//"犯罪日期"
	private String penaltyContent;//"犯罪内容"
	private String penaltyResults;//"处罚结果"
	private String socialSecurityType;//"缴纳类型"

	// 人社相关信息
	private Integer dispatch;//"是否为派遣制用工"
	private String industry;//"实际用工单位所属行业"
	private String jobDate;//"自??年??月在此岗位工作"

	private Integer isDispatchLicense; // 是否具有劳务派遣经营许可证 1：是；2：否
	private String  DispatchLicenseNum; // 劳务派遣经营许可证编号
	private Integer isDispatchLicenseDate; // 劳务派遣经营许可证是否在有效期内 1：是；2：否
	private Integer isSignDispatch; // 是否签订派遣协议 1：是；2：否

	public Integer getIsDispatchLicense() {
		return isDispatchLicense;
	}

	public void setIsDispatchLicense(Integer isDispatchLicense) {
		this.isDispatchLicense = isDispatchLicense;
	}

	public String getDispatchLicenseNum() {
		return DispatchLicenseNum;
	}

	public void setDispatchLicenseNum(String dispatchLicenseNum) {
		DispatchLicenseNum = dispatchLicenseNum;
	}

	public Integer getIsDispatchLicenseDate() {
		return isDispatchLicenseDate;
	}

	public void setIsDispatchLicenseDate(Integer isDispatchLicenseDate) {
		this.isDispatchLicenseDate = isDispatchLicenseDate;
	}

	public Integer getIsSignDispatch() {
		return isSignDispatch;
	}

	public void setIsSignDispatch(Integer isSignDispatch) {
		this.isSignDispatch = isSignDispatch;
	}


	//columns END

	public String getPenaltyDate() {
		return penaltyDate;
	}

	public void setPenaltyDate(String penaltyDate) {
		this.penaltyDate = penaltyDate;
	}

	public String getPenaltyContent() {
		return penaltyContent;
	}

	public void setPenaltyContent(String penaltyContent) {
		this.penaltyContent = penaltyContent;
	}

	public String getPenaltyResults() {
		return penaltyResults;
	}

	public void setPenaltyResults(String penaltyResults) {
		this.penaltyResults = penaltyResults;
	}

	public String getSocialSecurityType() {
		return socialSecurityType;
	}

	public void setSocialSecurityType(String socialSecurityType) {
		this.socialSecurityType = socialSecurityType;
	}

	public Integer getDispatch() {
		return dispatch;
	}

	public void setDispatch(Integer dispatch) {
		this.dispatch = dispatch;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getJobDate() {
		return jobDate;
	}

	public void setJobDate(String jobDate) {
		this.jobDate = jobDate;
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

	public Integer getIsAnotherInsurance() {
		return isAnotherInsurance;
	}

	public void setIsAnotherInsurance(Integer isAnotherInsurance) {
		this.isAnotherInsurance = isAnotherInsurance;
	}

	public Integer getIsApplyRegiste() {
		return isApplyRegiste;
	}

	public void setIsApplyRegiste(Integer isApplyRegiste) {
		this.isApplyRegiste = isApplyRegiste;
	}

	public Integer getIsInsurance() {
		return isInsurance;
	}

	public void setIsInsurance(Integer isInsurance) {
		this.isInsurance = isInsurance;
	}

	public Integer getIsSpouseInsurance() {
		return isSpouseInsurance;
	}

	public void setIsSpouseInsurance(Integer isSpouseInsurance) {
		this.isSpouseInsurance = isSpouseInsurance;
	}

	public Integer getKaifaquFund() {
		return kaifaquFund;
	}

	public void setKaifaquFund(Integer kaifaquFund) {
		this.kaifaquFund = kaifaquFund;
	}

	public Map<String, Object> getParam() {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("", "");
		return param;
	}
}

