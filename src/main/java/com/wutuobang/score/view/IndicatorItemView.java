package com.wutuobang.score.view;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by majiancheng on 2018/4/23.
 */
public class IndicatorItemView implements Serializable {

    private static final long serialVersionUID = -4465677247267401674L;

    private Integer indicatorId;//指标ID

    private Integer itemId;//用户选择指标选项ID

    private String itemValue;//用户选择选项文本

    private BigDecimal scoreValue;//得分

    public IndicatorItemView() {
    }

    public IndicatorItemView(Integer indicatorId, Integer itemId, String itemValue, BigDecimal scoreValue) {
        this.indicatorId = indicatorId;
        this.itemId = itemId;
        this.itemValue = itemValue;
        this.scoreValue = scoreValue;
    }

    public Integer getIndicatorId() {
        return indicatorId;
    }

    public void setIndicatorId(Integer indicatorId) {
        this.indicatorId = indicatorId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    public BigDecimal getScoreValue() {
        return scoreValue;
    }

    public void setScoreValue(BigDecimal scoreValue) {
        this.scoreValue = scoreValue;
    }
}
