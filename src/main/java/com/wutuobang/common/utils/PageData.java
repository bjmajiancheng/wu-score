/**
 * 
 */
package com.wutuobang.common.utils;

import com.wutuobang.common.constant.CommonConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jcma
 *
 */
public class PageData<T> implements java.io.Serializable{
	private static final long serialVersionUID = -558124060795928389L;
	
	private List<T> data = new ArrayList<T>();
	
	private int recordsTotal = 0;

	private int pageNo = 1;

	private int pageSize = CommonConstant.PAGE_SIZE;

	private int pageCount;

	private String countmsg;

	public String getCountmsg() {
		return countmsg;
	}

	public void setCountmsg(String countmsg) {
		this.countmsg = countmsg;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageCount() {
		int pageCount = recordsTotal / pageSize;
		if (recordsTotal % pageSize != 0) {
			pageCount += 1;
		}
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
}
