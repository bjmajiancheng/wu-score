/*
 * 北京果敢时代科技有限公司
 * 北京市朝阳区望京SOHO T3 B座1607
 * 邮编：100022
 * 网址：www.davdian.com
 */

package com.wutuobang.score.model;

import com.wutuobang.common.utils.DateStyle;
import com.wutuobang.common.utils.DateUtil;
import org.apache.commons.lang.StringUtils;

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
public class AcceptDateConfModel implements Serializable {

    private static final long serialVersionUID = 1L;

    //columns START
    private Integer id;//"id"

    private Integer batchId;//"批次ID"

    private Integer addressId;//"地点ID"

    private Date acceptDate;//"受理日期"

    private String weekDay;//"周几"

    private Integer amUserCount;//"上午发放人数"

    private Integer pmUserCount;//"下午发放人数"

    private Integer amRemainingCount;//"上午剩余人数"

    private Integer pmRemainingCount;//"下午剩余人数"
    //columns END

    //自定义属性START
    private String acceptDateStr;//受理日期字符串
    //自定义属性END

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

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getAddressId() {
        return this.addressId;
    }

    public void setAcceptDate(Date acceptDate) {
        this.acceptDate = acceptDate;
    }

    public Date getAcceptDate() {
        return this.acceptDate;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public String getWeekDay() {
        return this.weekDay;
    }

    public void setAmUserCount(Integer amUserCount) {
        this.amUserCount = amUserCount;
    }

    public Integer getAmUserCount() {
        return this.amUserCount;
    }

    public void setPmUserCount(Integer pmUserCount) {
        this.pmUserCount = pmUserCount;
    }

    public Integer getPmUserCount() {
        return this.pmUserCount;
    }

    public void setAmRemainingCount(Integer amRemainingCount) {
        this.amRemainingCount = amRemainingCount;
    }

    public Integer getAmRemainingCount() {
        return this.amRemainingCount;
    }

    public void setPmRemainingCount(Integer pmRemainingCount) {
        this.pmRemainingCount = pmRemainingCount;
    }

    public Integer getPmRemainingCount() {
        return this.pmRemainingCount;
    }

    public String getAcceptDateStr() {
        if (StringUtils.isEmpty(acceptDateStr) && this.acceptDate != null) {
            this.acceptDateStr = DateUtil.DateToString(this.acceptDate, DateStyle.YYYY_MM_DD);
        }
        return acceptDateStr;
    }

    public void setAcceptDateStr(String acceptDateStr) {
        this.acceptDateStr = acceptDateStr;
    }
}

