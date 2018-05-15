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
import java.util.List;
import java.util.Map;

/**
 * @author davdian
 * @version 1.0
 * @since 1.0
 */
public class IdentityInfoModel implements Serializable {

    private static final long serialVersionUID = 1L;

    //columns START
    private Integer id;//"主键ID"

    private String idNumber;//"身份证号"

    private Integer batchId;//"批次ID"

    private Integer companyId;//"单位ID"

    private String idCardPositive;//"身份证正面图片"

    private String idCardOpposite;//"身份证反面图片"

    private String name;//"姓名"

    private Integer sex;//"性别：1、男；2、女"

    private String birthday;//"出生日期"

    private Integer age;//"年龄"

    private String nation;//"民族"

    private Integer region;//"拟落户地区"

    private String regionName;//"拟落户地区名称"

    private Integer reservationStatus;//"申请预约状态"

    private Integer hallStatus;//"预约大厅状态"

    private Integer unionApproveStatus1;//"公安预审状态"

    private Integer unionApproveStatus2;//"人社预审状态"

    private Integer policeApproveStatus;//"公安前置预审状态"

    private Integer rensheAcceptStatus;//"人社受理状态"

    private Integer cancelStatus;//"资格取消状态"

    private String acceptNumber;//"受理编号"

    private Integer acceptAddressId;//"受理地点1、市级行政许可中心，2、滨海新区行政服务中心"

    private String acceptAddress;//"受理地点"

    private Date ctime;//"创建时间"

    private Date utime;//"更新时间"

    private Integer resultStatus;//"核算状态 0未核算 1已核算"

    private Date reservationDate;//"预约日期"

    private Integer reservationM;//"上午，下午"

    private BigDecimal score;//自测分数

    private Integer autoTestNum;//"自助评测限制次数"
    //columns END

    //自定义属性 START
    private HouseMoveModel houseMoveModel;//户籍迁移信息

    private List<HouseRelationshipModel> houseRelationshipModelList;//申请人家庭关系信息

    private HouseOtherModel houseOtherModel;//申请人其他信息

    private HouseProfessionModel houseProfessionModel;//职业资格证书信息

    private String reservaionDateStr;//预约日期
    //自定义属性 END

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getIdCardPositive() {
        return idCardPositive;
    }

    public void setIdCardPositive(String idCardPositive) {
        this.idCardPositive = idCardPositive;
    }

    public String getIdCardOpposite() {
        return idCardOpposite;
    }

    public void setIdCardOpposite(String idCardOpposite) {
        this.idCardOpposite = idCardOpposite;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public Integer getRegion() {
        return region;
    }

    public void setRegion(Integer region) {
        this.region = region;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Integer getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(Integer reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public Integer getHallStatus() {
        return hallStatus;
    }

    public void setHallStatus(Integer hallStatus) {
        this.hallStatus = hallStatus;
    }

    public Integer getUnionApproveStatus1() {
        return unionApproveStatus1;
    }

    public void setUnionApproveStatus1(Integer unionApproveStatus1) {
        this.unionApproveStatus1 = unionApproveStatus1;
    }

    public Integer getUnionApproveStatus2() {
        return unionApproveStatus2;
    }

    public void setUnionApproveStatus2(Integer unionApproveStatus2) {
        this.unionApproveStatus2 = unionApproveStatus2;
    }

    public Integer getPoliceApproveStatus() {
        return policeApproveStatus;
    }

    public void setPoliceApproveStatus(Integer policeApproveStatus) {
        this.policeApproveStatus = policeApproveStatus;
    }

    public Integer getRensheAcceptStatus() {
        return rensheAcceptStatus;
    }

    public void setRensheAcceptStatus(Integer rensheAcceptStatus) {
        this.rensheAcceptStatus = rensheAcceptStatus;
    }

    public Integer getCancelStatus() {
        return cancelStatus;
    }

    public void setCancelStatus(Integer cancelStatus) {
        this.cancelStatus = cancelStatus;
    }

    public String getAcceptNumber() {
        return acceptNumber;
    }

    public void setAcceptNumber(String acceptNumber) {
        this.acceptNumber = acceptNumber;
    }

    public Integer getAcceptAddressId() {
        return acceptAddressId;
    }

    public void setAcceptAddressId(Integer acceptAddressId) {
        this.acceptAddressId = acceptAddressId;
    }

    public String getAcceptAddress() {
        return acceptAddress;
    }

    public void setAcceptAddress(String acceptAddress) {
        this.acceptAddress = acceptAddress;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getUtime() {
        return utime;
    }

    public void setUtime(Date utime) {
        this.utime = utime;
    }

    public Integer getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(Integer resultStatus) {
        this.resultStatus = resultStatus;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Integer getReservationM() {
        return reservationM;
    }

    public void setReservationM(Integer reservationM) {
        this.reservationM = reservationM;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public Integer getAutoTestNum() {
        return autoTestNum;
    }

    public void setAutoTestNum(Integer autoTestNum) {
        this.autoTestNum = autoTestNum;
    }

    public HouseMoveModel getHouseMoveModel() {
        return houseMoveModel;
    }

    public void setHouseMoveModel(HouseMoveModel houseMoveModel) {
        this.houseMoveModel = houseMoveModel;
    }

    public List<HouseRelationshipModel> getHouseRelationshipModelList() {
        return houseRelationshipModelList;
    }

    public void setHouseRelationshipModelList(List<HouseRelationshipModel> houseRelationshipModelList) {
        this.houseRelationshipModelList = houseRelationshipModelList;
    }

    public HouseOtherModel getHouseOtherModel() {
        return houseOtherModel;
    }

    public void setHouseOtherModel(HouseOtherModel houseOtherModel) {
        this.houseOtherModel = houseOtherModel;
    }

    public HouseProfessionModel getHouseProfessionModel() {
        return houseProfessionModel;
    }

    public void setHouseProfessionModel(HouseProfessionModel houseProfessionModel) {
        this.houseProfessionModel = houseProfessionModel;
    }

    public String getReservaionDateStr() {
        if (StringUtils.isEmpty(reservaionDateStr)) {
            if (this.reservationDate != null) {
                this.reservaionDateStr = DateUtil.DateToString(this.reservationDate, DateStyle.YYYY_MM_DD);
            }
        }
        return reservaionDateStr;
    }

    public void setReservaionDateStr(String reservaionDateStr) {
        this.reservaionDateStr = reservaionDateStr;
    }

    public String getSexStr() {
        if (this.getSex() == null) {
            return "";
        }

        return this.getSex() == 1 ? "男" : "女";
    }
}

