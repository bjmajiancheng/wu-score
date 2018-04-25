package com.wutuobang.score.view;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by majiancheng on 2018/4/25.
 */
public class EvaluationView implements Serializable {
    private static final long serialVersionUID = 4850382210040870513L;

    private Integer identityInfoId;//申请人Id

    private BigDecimal ageItemScore;//年龄

    private BigDecimal educationItemScore;//受教育程度

    private BigDecimal skillItemScore;//专业技术职业技能

    private Integer pensionMonth;//基本养老保险

    private Integer medicalMonth;//基本医疗保险

    private Integer unemploymentMonth;//基本失业保险

    private Integer workInjuryMonth;//基本工伤保险

    private Integer fertilityMonth;//基本生育保险

    private Integer fundMonth;//公积金

    private Integer house;//住房

    private BigDecimal workTypeItemScore;//职业工种

    private Integer taxes;//纳税

    private Integer marriage;//婚姻状况

    private BigDecimal awardsItemScore;//奖项及荣誉称号

    private Integer workYear;//工作年限

    private BigDecimal soldierItemScore;//退役军人

    public Integer getIdentityInfoId() {
        return identityInfoId;
    }

    public void setIdentityInfoId(Integer identityInfoId) {
        this.identityInfoId = identityInfoId;
    }

    public BigDecimal getAgeItemScore() {
        return ageItemScore;
    }

    public void setAgeItemScore(BigDecimal ageItemScore) {
        this.ageItemScore = ageItemScore;
    }

    public BigDecimal getEducationItemScore() {
        return educationItemScore;
    }

    public void setEducationItemScore(BigDecimal educationItemScore) {
        this.educationItemScore = educationItemScore;
    }

    public BigDecimal getSkillItemScore() {
        return skillItemScore;
    }

    public void setSkillItemScore(BigDecimal skillItemScore) {
        this.skillItemScore = skillItemScore;
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

    public Integer getHouse() {
        return house;
    }

    public void setHouse(Integer house) {
        this.house = house;
    }

    public BigDecimal getWorkTypeItemScore() {
        return workTypeItemScore;
    }

    public void setWorkTypeItemScore(BigDecimal workTypeItemScore) {
        this.workTypeItemScore = workTypeItemScore;
    }

    public Integer getTaxes() {
        return taxes;
    }

    public void setTaxes(Integer taxes) {
        this.taxes = taxes;
    }

    public Integer getMarriage() {
        return marriage;
    }

    public void setMarriage(Integer marriage) {
        this.marriage = marriage;
    }

    public BigDecimal getAwardsItemScore() {
        return awardsItemScore;
    }

    public void setAwardsItemScore(BigDecimal awardsItemScore) {
        this.awardsItemScore = awardsItemScore;
    }

    public Integer getWorkYear() {
        return workYear;
    }

    public void setWorkYear(Integer workYear) {
        this.workYear = workYear;
    }

    public BigDecimal getSoldierItemScore() {
        return soldierItemScore;
    }

    public void setSoldierItemScore(BigDecimal soldierItemScore) {
        this.soldierItemScore = soldierItemScore;
    }
}
