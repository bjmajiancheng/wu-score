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
public class AcceptAddressModel implements Serializable {
	private static final long serialVersionUID = 1L;

	//columns START
	private Integer id;//"id:hidden"

	private String address;//"地址:text"

	private String addressDetail;//"详细地址:textarea"

	//columns END
		
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}
		
	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return this.address;
	}
		
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getAddressDetail() {
		return this.addressDetail;
	}

	public Map<String, Object> getParam() {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("", "");
		return param;
	}
}

