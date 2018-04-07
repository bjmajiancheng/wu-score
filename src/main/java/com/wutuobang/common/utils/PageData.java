/**
 * 
 */
package com.wutuobang.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jcma
 *
 */
public class PageData<T> implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -558124060795928389L;
	
	private List<T> data = new ArrayList<T>();
	
	private Integer draw = 0;
	
	private Integer recordsTotal = 0;

	private Integer recordsFiltered;
	
	private String error;

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public Integer getDraw() {
		return draw;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	public Integer getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(Integer recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public Integer getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(Integer recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	

}
