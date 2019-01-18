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
public class HouseMoveModel implements Serializable {
	private static final long serialVersionUID = 1L;

	//columns START
	private Integer id;//"主键ID"

	private Integer identityInfoId;//"申请人身份信息id"

	private Integer moveProvince;//"迁出地区（省）"

	private Integer moveCity;//"迁出地区（市）"

	private Integer moveRegion;//"迁出地区（区）"

	private String moveAddress;//"迁出地地址"

	private String moveRegisteredOffice;//"现户籍登记机关"

	private Integer houseNature;//"现户口性质"

	private Integer settledNature;//"落户性质"

	private String registeredOffice;//"迁入户籍登记机关"

	private Integer registeredRegion;//"登记地区"

	private String address;//"迁入地详细地址"

	private String witness;//"证明人"

	private String witnessPhone;//"证明人电话"

	private String witnessAddress;//"证明人收件地址"

	private Integer region;//"拟落户地区"

	private Integer marriageStatus;//"婚姻状态，1、已婚；2、未婚；3、丧偶；4、离婚"

	private Integer haveSon;//"有无子女，1、有；2、无"

	private Integer sonNumber;//"子女数量"

	private Date ctime;//"创建时间"

	//2019-1-17增加字段

	private String moveNowAddress; //申请人现居住地址

	public String getMoveNowAddress() {
		return moveNowAddress;
	}

	public void setMoveNowAddress(String moveNowAddress) {
		this.moveNowAddress = moveNowAddress;
	}

	//2019-1-17增加字段End

	//columns END

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setIdentityInfoId(Integer identityInfoId) {
		this.identityInfoId = identityInfoId;
	}

	public Integer getIdentityInfoId() {
		return this.identityInfoId;
	}

	public void setMoveProvince(Integer moveProvince) {
		this.moveProvince = moveProvince;
	}

	public Integer getMoveProvince() {
		return this.moveProvince;
	}

	public void setMoveCity(Integer moveCity) {
		this.moveCity = moveCity;
	}

	public Integer getMoveCity() {
		return this.moveCity;
	}

	public void setMoveRegion(Integer moveRegion) {
		this.moveRegion = moveRegion;
	}

	public Integer getMoveRegion() {
		return this.moveRegion;
	}

	public void setMoveAddress(String moveAddress) {
		this.moveAddress = moveAddress;
	}

	public String getMoveAddress() {
		return this.moveAddress;
	}

	public void setMoveRegisteredOffice(String moveRegisteredOffice) {
		this.moveRegisteredOffice = moveRegisteredOffice;
	}

	public String getMoveRegisteredOffice() {
		return this.moveRegisteredOffice;
	}

	public void setHouseNature(Integer houseNature) {
		this.houseNature = houseNature;
	}

	public Integer getHouseNature() {
		return this.houseNature;
	}

	public void setSettledNature(Integer settledNature) {
		this.settledNature = settledNature;
	}

	public Integer getSettledNature() {
		return this.settledNature;
	}

	public void setRegisteredOffice(String registeredOffice) {
		this.registeredOffice = registeredOffice;
	}

	public String getRegisteredOffice() {
		return this.registeredOffice;
	}

	public Integer getRegisteredRegion() {
		return registeredRegion;
	}

	public void setRegisteredRegion(Integer registeredRegion) {
		this.registeredRegion = registeredRegion;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return this.address;
	}

	public void setWitness(String witness) {
		this.witness = witness;
	}

	public String getWitness() {
		return this.witness;
	}

	public void setWitnessPhone(String witnessPhone) {
		this.witnessPhone = witnessPhone;
	}

	public String getWitnessPhone() {
		return this.witnessPhone;
	}

	public void setWitnessAddress(String witnessAddress) {
		this.witnessAddress = witnessAddress;
	}

	public String getWitnessAddress() {
		return this.witnessAddress;
	}

	public void setRegion(Integer region) {
		this.region = region;
	}

	public Integer getRegion() {
		return this.region;
	}

	public void setMarriageStatus(Integer marriageStatus) {
		this.marriageStatus = marriageStatus;
	}

	public Integer getMarriageStatus() {
		return this.marriageStatus;
	}

	public void setHaveSon(Integer haveSon) {
		this.haveSon = haveSon;
	}

	public Integer getHaveSon() {
		return this.haveSon;
	}

	public void setSonNumber(Integer sonNumber) {
		this.sonNumber = sonNumber;
	}

	public Integer getSonNumber() {
		return this.sonNumber;
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

