package com.wutuobang.score.view;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by majiancheng on 2018/4/23.
 */
public class IndicatorItemView implements Serializable {

    private static final long serialVersionUID = -4465677247267401674L;

    private Integer indicatorId;//指标ID

    private String indicatorName;//指标名称

    private Integer indexNum;//用户选择指标选项Num

    private Integer indicatorItemId;//用户选择选项ID

    private BigDecimal scoreValue;//得分

    public Integer getIndicatorId() {
        return indicatorId;
    }

    public void setIndicatorId(Integer indicatorId) {
        this.indicatorId = indicatorId;
    }

    public String getIndicatorName() {
        return indicatorName;
    }

    public void setIndicatorName(String indicatorName) {
        this.indicatorName = indicatorName;
    }

    public Integer getIndexNum() {
        return indexNum;
    }

    public void setIndexNum(Integer indexNum) {
        this.indexNum = indexNum;
    }

    public Integer getIndicatorItemId() {
        return indicatorItemId;
    }

    public void setIndicatorItemId(Integer indicatorItemId) {
        this.indicatorItemId = indicatorItemId;
    }

    public BigDecimal getScoreValue() {
        return scoreValue;
    }

    public void setScoreValue(BigDecimal scoreValue) {
        this.scoreValue = scoreValue;
    }
}
