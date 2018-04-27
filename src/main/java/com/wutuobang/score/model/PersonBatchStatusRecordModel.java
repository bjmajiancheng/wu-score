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
public class PersonBatchStatusRecordModel implements Serializable {

    private static final long serialVersionUID = 1L;

    //columns START
    private Integer id;//"id"

    private Integer batchId;//"批次ID"

    private Integer personId;//"申请人ID"

    private String personIdNumber;//"申请人身份证号码"

    private String statusDictAlias;//"状态字典alias"

    private String statusTypeDesc;//"状态类型"

    private Integer statusInt;//"状态值"

    private String statusStr;//"状态文本"

    private Date statusTime;//"状态生成时间"

    private String statusReason;//"状态原因"
    //columns END

    public PersonBatchStatusRecordModel() {
    }

    public PersonBatchStatusRecordModel(IdentityInfoModel identityInfoModel, DictModel dictModel, String statusReason) {
        this.batchId = identityInfoModel.getBatchId();
        this.personId = identityInfoModel.getId();
        this.personIdNumber = identityInfoModel.getIdNumber();
        this.statusDictAlias = dictModel.getAlias();
        this.statusTypeDesc = dictModel.getAliasName();
        this.statusInt = dictModel.getValue();
        this.statusStr = dictModel.getText();
        this.statusTime = new Date();
        this.statusReason = statusReason;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public Integer getBatchId() {
        return this.batchId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Integer getPersonId() {
        return this.personId;
    }

    public void setPersonIdNumber(String personIdNumber) {
        this.personIdNumber = personIdNumber;
    }

    public String getPersonIdNumber() {
        return this.personIdNumber;
    }

    public void setStatusDictAlias(String statusDictAlias) {
        this.statusDictAlias = statusDictAlias;
    }

    public String getStatusDictAlias() {
        return this.statusDictAlias;
    }

    public void setStatusTypeDesc(String statusTypeDesc) {
        this.statusTypeDesc = statusTypeDesc;
    }

    public String getStatusTypeDesc() {
        return this.statusTypeDesc;
    }

    public void setStatusInt(Integer statusInt) {
        this.statusInt = statusInt;
    }

    public Integer getStatusInt() {
        return this.statusInt;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public String getStatusStr() {
        return this.statusStr;
    }

    public void setStatusTime(Date statusTime) {
        this.statusTime = statusTime;
    }

    public Date getStatusTime() {
        return this.statusTime;
    }

    public void setStatusReason(String statusReason) {
        this.statusReason = statusReason;
    }

    public String getStatusReason() {
        return this.statusReason;
    }

    public Map<String, Object> getParam() {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("", "");
        return param;
    }
}

