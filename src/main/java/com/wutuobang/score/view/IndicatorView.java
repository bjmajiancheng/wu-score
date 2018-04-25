package com.wutuobang.score.view;

import java.io.Serializable;

/**
 * Created by majiancheng on 2018/4/23.
 */
public class IndicatorView implements Serializable {
    private static final long serialVersionUID = 4565797266071864534L;

    private IndicatorItemView ageItem;//年龄

    private IndicatorItemView educationItem;//受教育程度

    private IndicatorItemView skillItem;//专业技术职业技能

    private IndicatorItemView socialItem;//社保公积金

    private IndicatorItemView houseItem;//住房

    private IndicatorItemView workTypeItem;//职业工种

    private IndicatorItemView settledRegionItem;//落户地区

    private IndicatorItemView taxItem;//纳税

    private IndicatorItemView marriageItem;//婚姻状况

    private IndicatorItemView awardsItem;//奖项及荣誉称号

    private IndicatorItemView jobYearsItem;//工作年限

    private IndicatorItemView soldierItem;//退役军人

    public IndicatorItemView getAgeItem() {
        return ageItem;
    }

    public void setAgeItem(IndicatorItemView ageItem) {
        this.ageItem = ageItem;
    }

    public IndicatorItemView getEducationItem() {
        return educationItem;
    }

    public void setEducationItem(IndicatorItemView educationItem) {
        this.educationItem = educationItem;
    }

    public IndicatorItemView getSkillItem() {
        return skillItem;
    }

    public void setSkillItem(IndicatorItemView skillItem) {
        this.skillItem = skillItem;
    }

    public IndicatorItemView getSocialItem() {
        return socialItem;
    }

    public void setSocialItem(IndicatorItemView socialItem) {
        this.socialItem = socialItem;
    }

    public IndicatorItemView getHouseItem() {
        return houseItem;
    }

    public void setHouseItem(IndicatorItemView houseItem) {
        this.houseItem = houseItem;
    }

    public IndicatorItemView getWorkTypeItem() {
        return workTypeItem;
    }

    public void setWorkTypeItem(IndicatorItemView workTypeItem) {
        this.workTypeItem = workTypeItem;
    }

    public IndicatorItemView getSettledRegionItem() {
        return settledRegionItem;
    }

    public void setSettledRegionItem(IndicatorItemView settledRegionItem) {
        this.settledRegionItem = settledRegionItem;
    }

    public IndicatorItemView getTaxItem() {
        return taxItem;
    }

    public void setTaxItem(IndicatorItemView taxItem) {
        this.taxItem = taxItem;
    }

    public IndicatorItemView getMarriageItem() {
        return marriageItem;
    }

    public void setMarriageItem(IndicatorItemView marriageItem) {
        this.marriageItem = marriageItem;
    }

    public IndicatorItemView getAwardsItem() {
        return awardsItem;
    }

    public void setAwardsItem(IndicatorItemView awardsItem) {
        this.awardsItem = awardsItem;
    }

    public IndicatorItemView getJobYearsItem() {
        return jobYearsItem;
    }

    public void setJobYearsItem(IndicatorItemView jobYearsItem) {
        this.jobYearsItem = jobYearsItem;
    }

    public IndicatorItemView getSoldierItem() {
        return soldierItem;
    }

    public void setSoldierItem(IndicatorItemView soldierItem) {
        this.soldierItem = soldierItem;
    }
}
