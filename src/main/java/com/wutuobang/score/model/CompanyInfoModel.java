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
import java.util.Objects;

/**
 * @author davdian
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

    private String operator;//"经办人1姓名"

    private String operator2;//"经办人2姓名" ，2019年12月23日后废弃经办人2的业务，改为经办人1的身份证反面

    private String operatorMobile;//"经办人1联系手机"

    private String operatorMobile2;//"经办人2联系手机"

    private String idCardNumber_1; // 经办人1身份证号

    private String idCardNumber_2; // 经办人2身份证号

    private String operatorAddress;//"经办人联系地址"

    private String remark;//"备注说明"

    private Date createTime;//"创建时间"

    //19-1-15增加

    private Integer status;//"是否修改过经办人信息状态,1为已修改过"

    private String businessLicenseSrc;//营业执照路径

    private Integer recordId;

    private String apprdate;
    private String dom;
    private String entname;
    private String enttype;
    private String estdate;
    private String lerep;
    private String opfrom;
    private String opscope;
    private String opto;
    private String regcap;
    private String regcapcur;
    private String regno;
    private String regorg;
    private String regstate;
    private String orgno;
    private String compform;

    public String getApprdate() {
        return apprdate;
    }

    public void setApprdate(String apprdate) {
        this.apprdate = apprdate;
    }

    public String getDom() {
        return dom;
    }

    public void setDom(String dom) {
        this.dom = dom;
    }

    public String getEntname() {
        return entname;
    }

    public void setEntname(String entname) {
        this.entname = entname;
    }

    public String getEnttype() {
        return enttype;
    }

    public void setEnttype(String enttype) {
        this.enttype = enttype;
    }

    public String getEstdate() {
        return estdate;
    }

    public void setEstdate(String estdate) {
        this.estdate = estdate;
    }

    public String getLerep() {
        return lerep;
    }

    public void setLerep(String lerep) {
        this.lerep = lerep;
    }

    public String getOpfrom() {
        return opfrom;
    }

    public void setOpfrom(String opfrom) {
        this.opfrom = opfrom;
    }

    public String getOpscope() {
        return opscope;
    }

    public void setOpscope(String opscope) {
        this.opscope = opscope;
    }

    public String getOpto() {
        return opto;
    }

    public void setOpto(String opto) {
        this.opto = opto;
    }

    public String getRegcap() {
        return regcap;
    }

    public void setRegcap(String regcap) {
        this.regcap = regcap;
    }

    public String getRegcapcur() {
        return regcapcur;
    }

    public void setRegcapcur(String regcapcur) {
        this.regcapcur = regcapcur;
    }

    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public String getRegorg() {
        return regorg;
    }

    public void setRegorg(String regorg) {
        this.regorg = regorg;
    }

    public String getRegstate() {
        return regstate;
    }

    public void setRegstate(String regstate) {
        this.regstate = regstate;
    }

    public String getOrgno() {
        return orgno;
    }

    public void setOrgno(String orgno) {
        this.orgno = orgno;
    }

    public String getCompform() {
        return compform;
    }

    public void setCompform(String compform) {
        this.compform = compform;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getBusinessLicenseSrc() {
        return businessLicenseSrc;
    }

    public void setBusinessLicenseSrc(String businessLicenseSrc) {
        this.businessLicenseSrc = businessLicenseSrc;
    }

    //19-1-15 END

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

    public String getOperator2() {
        return operator2;
    }

    public void setOperator2(String operator2) {
        this.operator2 = operator2;
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

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setOperatorMobile2(String operatorMobile2) {
        this.operatorMobile2 = operatorMobile2;
    }

    public void setIdCardNumber_1(String idCardNumber_1) {
        this.idCardNumber_1 = idCardNumber_1;
    }

    public void setIdCardNumber_2(String idCardNumber_2) {
        this.idCardNumber_2 = idCardNumber_2;
    }

    public String getOperatorMobile2() {
        return operatorMobile2;
    }

    public String getIdCardNumber_1() {
        return idCardNumber_1;
    }

    public String getIdCardNumber_2() {
        return idCardNumber_2;
    }

    //判断只能修改一次的信息是否修改
    public boolean isOperatorEquals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompanyInfoModel)) return false;
        CompanyInfoModel that = (CompanyInfoModel) o;
        return Objects.equals(companyMobile, that.companyMobile) &&
                Objects.equals(operator, that.operator) &&
                Objects.equals(operator2, that.operator2) &&
                Objects.equals(operatorMobile, that.operatorMobile) &&
                Objects.equals(operatorMobile2, that.operatorMobile2) &&
                Objects.equals(idCardNumber_1, that.idCardNumber_1) &&
                Objects.equals(idCardNumber_2, that.idCardNumber_2) &&
                Objects.equals(operatorAddress, that.operatorAddress);
    }

    /**
     * 修改信息
     * @param changeDate
     */
    public void setChangeDate(CompanyInfoModel changeDate) {
        this.companyMobile = changeDate.companyMobile;
        this.operator = changeDate.operator;
        this.operator2 = changeDate.operator2;
        this.operatorMobile = changeDate.operatorMobile;
        this.operatorMobile2 = changeDate.operatorMobile2;
        this.idCardNumber_1 = changeDate.idCardNumber_1;
        this.idCardNumber_2 = changeDate.idCardNumber_2;
        this.operatorAddress = changeDate.operatorAddress;
        this.businessLicenseSrc = changeDate.businessLicenseSrc;
    }
}

