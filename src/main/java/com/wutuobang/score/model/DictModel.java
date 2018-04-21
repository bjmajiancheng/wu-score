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
public class DictModel implements Serializable {
	private static final long serialVersionUID = 1L;

	//columns START
	private Integer id;//"ID"

	private String alias;//"字典别名英文"

	private String aliasName;//"字典别名"

	private String text;//"文本"

	private Integer value;//"值"

	private Integer sort;//"排序值"

	//columns END
		
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}
		
	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getAlias() {
		return this.alias;
	}
		
	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public String getAliasName() {
		return this.aliasName;
	}
		
	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return this.text;
	}
		
	public void setValue(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return this.value;
	}
		
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getSort() {
		return this.sort;
	}
}

