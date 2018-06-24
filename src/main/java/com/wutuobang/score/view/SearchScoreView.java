package com.wutuobang.score.view;

import java.io.Serializable;

/**
 * Created by majiancheng on 2018/6/22.
 */
public class SearchScoreView implements Serializable{

    private static final long serialVersionUID = 6088244360639661787L;

    private String acceptNumber;//登记编码

    private String userName;//用户名称

    private String idCardNumber;//身份证号

    private String companyName;//公司名称

    private Integer pageNo;//当前页数

    public String getAcceptNumber() {
        return acceptNumber;
    }

    public void setAcceptNumber(String acceptNumber) {
        this.acceptNumber = acceptNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }
}
