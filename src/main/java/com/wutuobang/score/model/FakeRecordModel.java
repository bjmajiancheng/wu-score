package com.wutuobang.score.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by rui15 on 2019/3/11.
 */
public class FakeRecordModel   implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String userName;

    private String idNumber;

    private Integer companyId;

    private String companyName;

    private String companyCode;

    private String batchCode;

    private String fakeContent;

    private Date recordDate;

    private Date createTime;

    private String addUser;

    private String indicatorRole;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public String getFakeContent() {
        return fakeContent;
    }

    public void setFakeContent(String fakeContent) {
        this.fakeContent = fakeContent;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAddUser() {
        return addUser;
    }

    public void setAddUser(String addUser) {
        this.addUser = addUser;
    }

    public String getIndicatorRole() {
        return indicatorRole;
    }

    public void setIndicatorRole(String indicatorRole) {
        this.indicatorRole = indicatorRole;
    }
}
