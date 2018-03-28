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
public class CompanyInfoModel implements Serializable {
	private static final long serialVersionUID = 1L;

	//columns START
	private Integer id;//"主键ID"

	private String userName;//"用户名"

	private String password;//"用户密码"

	private String companyName;//"企业名称"

	private Integer companyType;//"单位类型，1、企事业单位；2、个体工商户"

	private String societyCode;//"统一社会信用代码"

	private String companyMobile;//"单位联系电话"

	private String operator;//"经办人姓名"

	private String operatorMobile;//"经办人联系手机"

	private String operatorAddress;//"经办人联系地址"

	private String remark;//"备注说明"

	private Date ctime;//"创建时间"

	//columns END
		
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}
		
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return this.userName;
	}
		
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}
		
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyName() {
		return this.companyName;
	}
		
	public void setCompanyType(Integer companyType) {
		this.companyType = companyType;
	}

	public Integer getCompanyType() {
		return this.companyType;
	}
		
	public void setSocietyCode(String societyCode) {
		this.societyCode = societyCode;
	}

	public String getSocietyCode() {
		return this.societyCode;
	}
		
	public void setCompanyMobile(String companyMobile) {
		this.companyMobile = companyMobile;
	}

	public String getCompanyMobile() {
		return this.companyMobile;
	}
		
	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperator() {
		return this.operator;
	}
		
	public void setOperatorMobile(String operatorMobile) {
		this.operatorMobile = operatorMobile;
	}

	public String getOperatorMobile() {
		return this.operatorMobile;
	}
		
	public void setOperatorAddress(String operatorAddress) {
		this.operatorAddress = operatorAddress;
	}

	public String getOperatorAddress() {
		return this.operatorAddress;
	}
		
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return this.remark;
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

