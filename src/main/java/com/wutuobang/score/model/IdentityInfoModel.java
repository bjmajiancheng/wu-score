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
public class IdentityInfoModel implements Serializable {
	private static final long serialVersionUID = 1L;

	//columns START
	private Integer id;//"主键ID"

	private String idNumber;//"身份证号"

	private Integer companyId;//"单位ID"

	private String idCardPositive;//"身份证正面图片"

	private String idCardOpposite;//"身份证反面图片"

	private String name;//"姓名"

	private Integer sex;//"性别：1、男；2、女"

	private String birthday;//"出生日期"

	private Integer age;//"年龄"

	private String nation;//"民族"

	private Integer region;//"拟落户地区"

	private Date ctime;//"创建时间"

	private Date utime;//"更新时间"

	//columns END
		
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}
		
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getIdNumber() {
		return this.idNumber;
	}
		
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getCompanyId() {
		return this.companyId;
	}
		
	public void setIdCardPositive(String idCardPositive) {
		this.idCardPositive = idCardPositive;
	}

	public String getIdCardPositive() {
		return this.idCardPositive;
	}
		
	public void setIdCardOpposite(String idCardOpposite) {
		this.idCardOpposite = idCardOpposite;
	}

	public String getIdCardOpposite() {
		return this.idCardOpposite;
	}
		
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
		
	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getSex() {
		return this.sex;
	}
		
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getBirthday() {
		return this.birthday;
	}
		
	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getAge() {
		return this.age;
	}
		
	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getNation() {
		return this.nation;
	}
		
	public void setRegion(Integer region) {
		this.region = region;
	}

	public Integer getRegion() {
		return this.region;
	}
		
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Date getCtime() {
		return this.ctime;
	}
		
	public void setUtime(Date utime) {
		this.utime = utime;
	}

	public Date getUtime() {
		return this.utime;
	}

	public Map<String, Object> getParam() {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("", "");
		return param;
	}
}

