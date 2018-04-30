package com.wutuobang.score.view;

import java.io.Serializable;
import java.util.List;

/**
 * Created by majiancheng on 2018/4/23.
 */
public class IndicatorView implements Serializable {
    private static final long serialVersionUID = 4565797266071864534L;

    private Integer identityInfoId;//申请人Id

    private Integer pensionMonth;//基本养老保险(月)

    private Integer medicalMonth;//基本医疗保险(月)

    private Integer unemploymentMonth;//基本失业保险(月)

    private Integer workInjuryMonth;//基本工伤保险(月)

    private Integer fertilityMonth;//基本生育保险(月)

    private Integer fundMonth;//公积金(月)

    private Integer liveYear;//居住年限(年)

    private List<IndicatorItemView> indicatorItemList;//指标选项信息

    public Integer getIdentityInfoId() {
        return identityInfoId;
    }

    public void setIdentityInfoId(Integer identityInfoId) {
        this.identityInfoId = identityInfoId;
    }

    public Integer getPensionMonth() {
        return pensionMonth;
    }

    public void setPensionMonth(Integer pensionMonth) {
        this.pensionMonth = pensionMonth;
    }

    public Integer getMedicalMonth() {
        return medicalMonth;
    }

    public void setMedicalMonth(Integer medicalMonth) {
        this.medicalMonth = medicalMonth;
    }

    public Integer getUnemploymentMonth() {
        return unemploymentMonth;
    }

    public void setUnemploymentMonth(Integer unemploymentMonth) {
        this.unemploymentMonth = unemploymentMonth;
    }

    public Integer getWorkInjuryMonth() {
        return workInjuryMonth;
    }

    public void setWorkInjuryMonth(Integer workInjuryMonth) {
        this.workInjuryMonth = workInjuryMonth;
    }

    public Integer getFertilityMonth() {
        return fertilityMonth;
    }

    public void setFertilityMonth(Integer fertilityMonth) {
        this.fertilityMonth = fertilityMonth;
    }

    public Integer getFundMonth() {
        return fundMonth;
    }

    public void setFundMonth(Integer fundMonth) {
        this.fundMonth = fundMonth;
    }

    public Integer getLiveYear() {
        return liveYear;
    }

    public void setLiveYear(Integer liveYear) {
        this.liveYear = liveYear;
    }

    public List<IndicatorItemView> getIndicatorItemList() {
        return indicatorItemList;
    }

    public void setIndicatorItemList(List<IndicatorItemView> indicatorItemList) {
        this.indicatorItemList = indicatorItemList;
    }
}
