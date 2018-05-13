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
public class PersonBatchScoreResultModel implements Serializable {

    private static final long serialVersionUID = 1L;

    //columns START
    private Integer id;//"id"

    private Integer batchId;//"批次ID"

    private Integer indicatorId;//"指标ID"

    private String indicatorName;//"指标名称"

    private Integer indicatorItemId;//"选择选项ID"

    private Integer personId;//"申请人ID"

    private String personName;//"申请人"

    private String personIdNum;//"申请人身份证"

    private BigDecimal scoreValue;//"分数"

    private String scoreDetail;//"打分说明"

    private Date ctime;//"创建时间"
    //columns END

    public PersonBatchScoreResultModel() {
    }

    public PersonBatchScoreResultModel(Integer indicatorId, String indicatorName, BigDecimal scoreValue) {
        this.indicatorId = indicatorId;
        this.indicatorName = indicatorName;
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

    public void setIndicatorId(Integer indicatorId) {
        this.indicatorId = indicatorId;
    }

    public Integer getIndicatorId() {
        return this.indicatorId;
    }

    public void setIndicatorName(String indicatorName) {
        this.indicatorName = indicatorName;
    }

    public String getIndicatorName() {
        return this.indicatorName;
    }

    public Integer getIndicatorItemId() {
        return indicatorItemId;
    }

    public void setIndicatorItemId(Integer indicatorItemId) {
        this.indicatorItemId = indicatorItemId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Integer getPersonId() {
        return this.personId;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonName() {
        return this.personName;
    }

    public void setPersonIdNum(String personIdNum) {
        this.personIdNum = personIdNum;
    }

    public String getPersonIdNum() {
        return this.personIdNum;
    }

    public void setScoreValue(BigDecimal scoreValue) {
        this.scoreValue = scoreValue;
    }

    public BigDecimal getScoreValue() {
        return this.scoreValue;
    }

    public void setScoreDetail(String scoreDetail) {
        this.scoreDetail = scoreDetail;
    }

    public String getScoreDetail() {
        return this.scoreDetail;
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

