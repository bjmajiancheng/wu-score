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
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */
public class ReservationInfoModel implements Serializable {
	private static final long serialVersionUID = 1L;

	//columns START
	private Integer id;//"主键ID"

	private Integer periodId;//"期限ID"

	private Integer identityInfoId;//"申请人身份信息id"

	private String registerCode;//"登记编号"

	private Integer reservaionLocation;//"预约地点1、市级行政许可中心，2、滨海新区行政服务中心"

	private Integer reservaionDate;//"预约日期(区分上午，下午)"

	private Integer status;//"状态，1、信息保存，2、测评未通过，3、测评通过，4、待审核，5、审核未通过，6、审核通过，7、补充上传"

	private Integer websiteReviewType;//"网上预审类型：1、通过、2、补件、3、当期不合格"

	private Integer websiteReviewTime;//"网上预审时间"

	private String reserveNumber;//"预约号码"

	private Integer policeReviewType;//"公安审核类型，1、审核通过；2、审核不通过"

	private Integer policeReviewTime;//"公安审核时间"

	private Integer preReviewStatus;//"前置预审状态；1、审核通过，2、审核不通过"

	private Integer preReviewTime;//"前置预审时间"

	private Integer formalAcceptTime;//"人社正式受理时间"

	private Integer fileSendStatus;//"材料送达状态1、已送达，2、未送达"

	private Integer socoreStatus;//"打分状态，1、未打分，2、已打分"

	private Date ctime;//"创建时间"

	//columns END
		
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}
		
	public void setPeriodId(Integer periodId) {
		this.periodId = periodId;
	}

	public Integer getPeriodId() {
		return this.periodId;
	}
		
	public void setIdentityInfoId(Integer identityInfoId) {
		this.identityInfoId = identityInfoId;
	}

	public Integer getIdentityInfoId() {
		return this.identityInfoId;
	}
		
	public void setRegisterCode(String registerCode) {
		this.registerCode = registerCode;
	}

	public String getRegisterCode() {
		return this.registerCode;
	}
		
	public void setReservaionLocation(Integer reservaionLocation) {
		this.reservaionLocation = reservaionLocation;
	}

	public Integer getReservaionLocation() {
		return this.reservaionLocation;
	}
		
	public void setReservaionDate(Integer reservaionDate) {
		this.reservaionDate = reservaionDate;
	}

	public Integer getReservaionDate() {
		return this.reservaionDate;
	}
		
	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}
		
	public void setWebsiteReviewType(Integer websiteReviewType) {
		this.websiteReviewType = websiteReviewType;
	}

	public Integer getWebsiteReviewType() {
		return this.websiteReviewType;
	}
		
	public void setWebsiteReviewTime(Integer websiteReviewTime) {
		this.websiteReviewTime = websiteReviewTime;
	}

	public Integer getWebsiteReviewTime() {
		return this.websiteReviewTime;
	}
		
	public void setReserveNumber(String reserveNumber) {
		this.reserveNumber = reserveNumber;
	}

	public String getReserveNumber() {
		return this.reserveNumber;
	}
		
	public void setPoliceReviewType(Integer policeReviewType) {
		this.policeReviewType = policeReviewType;
	}

	public Integer getPoliceReviewType() {
		return this.policeReviewType;
	}
		
	public void setPoliceReviewTime(Integer policeReviewTime) {
		this.policeReviewTime = policeReviewTime;
	}

	public Integer getPoliceReviewTime() {
		return this.policeReviewTime;
	}
		
	public void setPreReviewStatus(Integer preReviewStatus) {
		this.preReviewStatus = preReviewStatus;
	}

	public Integer getPreReviewStatus() {
		return this.preReviewStatus;
	}
		
	public void setPreReviewTime(Integer preReviewTime) {
		this.preReviewTime = preReviewTime;
	}

	public Integer getPreReviewTime() {
		return this.preReviewTime;
	}
		
	public void setFormalAcceptTime(Integer formalAcceptTime) {
		this.formalAcceptTime = formalAcceptTime;
	}

	public Integer getFormalAcceptTime() {
		return this.formalAcceptTime;
	}
		
	public void setFileSendStatus(Integer fileSendStatus) {
		this.fileSendStatus = fileSendStatus;
	}

	public Integer getFileSendStatus() {
		return this.fileSendStatus;
	}
		
	public void setSocoreStatus(Integer socoreStatus) {
		this.socoreStatus = socoreStatus;
	}

	public Integer getSocoreStatus() {
		return this.socoreStatus;
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

