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
public class IndicatorSelfTestResultModel implements Serializable {

    private static final long serialVersionUID = 1L;

    //columns START
    private Integer id;//"id"

    private Integer batchId;//"批次ID"

    private Integer personId;//"申请人ID"

    private Integer indicatorId;//"指标ID"

    private Integer itemId;//"用户选择指标选项ID"

    private String itemValue;//"itemValue"

    private BigDecimal scoreValue;//"得分"

    private String scoreDetail;//"打分说明"

    private Date createTime;//"创建时间"

    private String addUser;//"创建用户"
    //columns END

    public IndicatorSelfTestResultModel() {
    }

    public IndicatorSelfTestResultModel(Integer indicatorId, Integer itemId, String itemValue, BigDecimal scoreValue) {
        this.indicatorId = indicatorId;
        this.itemId = itemId;
        this.itemValue = itemValue;
        this.scoreValue = scoreValue;
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

    public void setIndicatorId(Integer indicatorId) {
        this.indicatorId = indicatorId;
    }

    public Integer getIndicatorId() {
        return this.indicatorId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getItemId() {
        return this.itemId;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    public String getItemValue() {
        return this.itemValue;
    }

    public void setScoreValue(BigDecimal scoreValue) {
        this.scoreValue = scoreValue;
    }

    public BigDecimal getScoreValue() {
        return this.scoreValue;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setAddUser(String addUser) {
        this.addUser = addUser;
    }

    public String getAddUser() {
        return this.addUser;
    }

    public String getScoreDetail() {
        return scoreDetail;
    }

    public void setScoreDetail(String scoreDetail) {
        this.scoreDetail = scoreDetail;
    }
}

