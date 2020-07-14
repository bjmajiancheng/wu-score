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

    //2019-1-9添加卫健委信息

    private String formerName;//"曾用名"

    private String pregnantPromise;//本人或配偶目前 0:请选择（没啥用）；1.承诺 2.不承诺 已怀孕_周

    private String pregnantWeek;//本人或配偶目前(不)承诺 已怀孕_周

    private String thirdPregnantPromise;//本人或配偶 0:请选择（没啥用）； 1.承诺 2.不承诺目前未处于政策外第三个及以上子女怀孕期间

    private Integer saveStatus;//"申请预约状态"

    //2019-1-9添加卫健委信息END

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

    private Integer materialStatus;//"材料送达补正状态"

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

    private Integer reservationTime;//"剩余可预约次数"

    private Date preApprove;//预审时间

    private Date unionApprove1Et;//"公安预审过期时间"

    private Date unionApprove2Et;//"人社预审过期时间"

    private String rejectReason;//"不通过原因"
    //columns END

    //自定义属性 START
    private HouseMoveModel houseMoveModel;//户籍迁移信息

    private List<HouseRelationshipModel> houseRelationshipModelList;//申请人家庭关系信息

    private HouseOtherModel houseOtherModel;//申请人其他信息

    private HouseProfessionModel houseProfessionModel;//职业资格证书信息

    private String reservaionDateStr;//预约日期

    private Integer reservationDateNull;//设置预约时间为空

    private String companyName;//公司名称
    //自定义属性 END

    /*
    2020年1月8日，
     */
    private Integer is201826Doc;//"是否按照“津发改社会〔2018〕26号”文件计算"
    private Integer contractCertificate; // 购买住房的合同/产权证，1：合同；2：产权证
    private Integer contractOrCertificate; // 1：合同-住建委；2：产权证-规自局；选择按照积分的方式

    private String rentHouseAddress; // 租赁房屋地址
    private String rentIdNumber; // 租赁登记备案证明编号
    private String   rentHouseStartDate; // 租赁备案起始日
    private String   rentHouseEndDate; // 租赁合同终止日


    private String ourHouse; // 是否有自有住房
    private String ourBuyHouse; // 是否于2019年12月31日之前购买住房
    private String housingArea; // 住房所在区
    private String houseAddress; // 住房详细坐落
    private String houseUse; // 房屋设计用途

    private String   houseOurDate; // 不动产权证取得日期
    private String houseOurNumber; // 不动产权登记号
    private String houseProperty; // 房屋产权情况
    private String   housePactDate; // 购房合同签署日期
    private String housePactNumber; // 购房合同编号
    private String rightProperty;// 持有

    private Integer istoreview;//"申请人是否申请复核过；1：是；2：否
    private Date toreviewtime;//"申请人是否申请复的时间"
    private String cancelReason;// 申请人的复核理由

    public Integer getIstoreview() {
        return istoreview;
    }

    public void setIstoreview(Integer istoreview) {
        this.istoreview = istoreview;
    }

    public Date getToreviewtime() {
        return toreviewtime;
    }

    public void setToreviewtime(Date toreviewtime) {
        this.toreviewtime = toreviewtime;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public Integer getSaveStatus() {
        return saveStatus;
    }

    public void setSaveStatus(Integer saveStatus) {
        this.saveStatus = saveStatus;
    }

    public Integer getContractCertificate() {
        return contractCertificate;
    }

    public void setContractCertificate(Integer contractCertificate) {
        this.contractCertificate = contractCertificate;
    }

    public String getRightProperty() {
        return rightProperty;
    }

    public void setRightProperty(String rightProperty) {
        this.rightProperty = rightProperty;
    }

    public Integer getIs201826Doc() {
        return is201826Doc;
    }

    public void setIs201826Doc(Integer is201826Doc) {
        this.is201826Doc = is201826Doc;
    }

    public Integer getContractOrCertificate() {
        return contractOrCertificate;
    }

    public void setContractOrCertificate(Integer contractOrCertificate) {
        this.contractOrCertificate = contractOrCertificate;
    }

    public String getRentHouseAddress() {
        return rentHouseAddress;
    }

    public void setRentHouseAddress(String rentHouseAddress) {
        this.rentHouseAddress = rentHouseAddress;
    }

    public String getRentIdNumber() {
        return rentIdNumber;
    }

    public void setRentIdNumber(String rentIdNumber) {
        this.rentIdNumber = rentIdNumber;
    }

    public String getRentHouseStartDate() {
        return rentHouseStartDate;
    }

    public void setRentHouseStartDate(String rentHouseStartDate) {
        this.rentHouseStartDate = rentHouseStartDate;
    }

    public String getRentHouseEndDate() {
        return rentHouseEndDate;
    }

    public void setRentHouseEndDate(String rentHouseEndDate) {
        this.rentHouseEndDate = rentHouseEndDate;
    }

    public String getOurHouse() {
        return ourHouse;
    }

    public void setOurHouse(String ourHouse) {
        this.ourHouse = ourHouse;
    }

    public String getOurBuyHouse() {
        return ourBuyHouse;
    }

    public void setOurBuyHouse(String ourBuyHouse) {
        this.ourBuyHouse = ourBuyHouse;
    }

    public String getHousingArea() {
        return housingArea;
    }

    public void setHousingArea(String housingArea) {
        this.housingArea = housingArea;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public String getHouseUse() {
        return houseUse;
    }

    public void setHouseUse(String houseUse) {
        this.houseUse = houseUse;
    }

    public String getHouseOurDate() {
        return houseOurDate;
    }

    public void setHouseOurDate(String houseOurDate) {
        this.houseOurDate = houseOurDate;
    }

    public String getHouseOurNumber() {
        return houseOurNumber;
    }

    public void setHouseOurNumber(String houseOurNumber) {
        this.houseOurNumber = houseOurNumber;
    }

    public String getHouseProperty() {
        return houseProperty;
    }

    public void setHouseProperty(String houseProperty) {
        this.houseProperty = houseProperty;
    }

    public String getHousePactDate() {
        return housePactDate;
    }

    public void setHousePactDate(String housePactDate) {
        this.housePactDate = housePactDate;
    }

    public String getHousePactNumber() {
        return housePactNumber;
    }

    public void setHousePactNumber(String housePactNumber) {
        this.housePactNumber = housePactNumber;
    }

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
        name = name.replace(" ","");
        this.name = name;
    }

    public String getFormerName() {
        return formerName;
    }

    public void setFormerName(String formerName) {
        this.formerName = formerName;
    }

    public String getPregnantPromise() {
        return pregnantPromise;
    }

    public void setPregnantPromise(String pregnantPromise) {
        this.pregnantPromise = pregnantPromise;
    }

    public String getPregnantWeek() {
        return pregnantWeek;
    }

    public void setPregnantWeek(String pregnantWeek) {
        this.pregnantWeek = pregnantWeek;
    }

    public String getThirdPregnantPromise() {
        return thirdPregnantPromise;
    }

    public void setThirdPregnantPromise(String thirdPregnantPromise) {
        this.thirdPregnantPromise = thirdPregnantPromise;
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

    public Integer getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(Integer reservationTime) {
        this.reservationTime = reservationTime;
    }

    public Date getUnionApprove1Et() {
        return unionApprove1Et;
    }

    public void setUnionApprove1Et(Date unionApprove1Et) {
        this.unionApprove1Et = unionApprove1Et;
    }

    public Date getUnionApprove2Et() {
        return unionApprove2Et;
    }

    public void setUnionApprove2Et(Date unionApprove2Et) {
        this.unionApprove2Et = unionApprove2Et;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
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

    public Integer weijianwei;
    public Integer gongjijin;
    public Integer zhujianwei;
    public Integer guiziju;
    public Integer tuiyijunren;
    public Integer jiaowei;
    public Integer minzheng;
    public Integer renshe;
    public Integer gongan;

    public Integer getWeijianwei() {
        return weijianwei;
    }

    public void setWeijianwei(Integer weijianwei) {
        this.weijianwei = weijianwei;
    }

    public Integer getGongjijin() {
        return gongjijin;
    }

    public void setGongjijin(Integer gongjijin) {
        this.gongjijin = gongjijin;
    }

    public Integer getZhujianwei() {
        return zhujianwei;
    }

    public void setZhujianwei(Integer zhujianwei) {
        this.zhujianwei = zhujianwei;
    }

    public Integer getGuiziju() {
        return guiziju;
    }

    public void setGuiziju(Integer guiziju) {
        this.guiziju = guiziju;
    }

    public Integer getTuiyijunren() {
        return tuiyijunren;
    }

    public void setTuiyijunren(Integer tuiyijunren) {
        this.tuiyijunren = tuiyijunren;
    }

    public Integer getJiaowei() {
        return jiaowei;
    }

    public void setJiaowei(Integer jiaowei) {
        this.jiaowei = jiaowei;
    }

    public Integer getMinzheng() {
        return minzheng;
    }

    public void setMinzheng(Integer minzheng) {
        this.minzheng = minzheng;
    }

    public Integer getRenshe() {
        return renshe;
    }

    public void setRenshe(Integer renshe) {
        this.renshe = renshe;
    }

    public Integer getGongan() {
        return gongan;
    }

    public void setGongan(Integer gongan) {
        this.gongan = gongan;
    }

    public Integer getReservationDateNull() {
        return reservationDateNull;
    }

    public void setReservationDateNull(Integer reservationDateNull) {
        this.reservationDateNull = reservationDateNull;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getMaterialStatus() {
        return materialStatus;
    }

    public void setMaterialStatus(Integer materialStatus) {
        this.materialStatus = materialStatus;
    }

    public Date getPreApprove() {
        return preApprove;
    }

    public void setPreApprove(Date preApprove) {
        this.preApprove = preApprove;
    }
}

