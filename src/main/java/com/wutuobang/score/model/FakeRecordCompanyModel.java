package com.wutuobang.score.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by rui15 on 2019/3/1.
 */
public class FakeRecordCompanyModel  implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;//"id"

    private String companyName;//公司名字

    private String companyUsid;//社会统一信用代码

    private Integer batchId;

    private Date recordDate;

    private Date finishDate;//惩罚截止时间

    private String addUser;

    private String fakeContent;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyUsid() {
        return companyUsid;
    }

    public void setCompanyUsid(String companyUsid) {
        this.companyUsid = companyUsid;
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public String getAddUser() {
        return addUser;
    }

    public void setAddUser(String addUser) {
        this.addUser = addUser;
    }

    public String getFakeContent() {
        return fakeContent;
    }

    public void setFakeContent(String fakeContent) {
        this.fakeContent = fakeContent;
    }
}
