package com.miles.seed.base.vo;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaginationVO<T> implements java.io.Serializable {
	private static final long serialVersionUID = -6435578206412325595L;
	
	/** 最大页数 **/
	private Integer maxPage = 0;
	/** 当前页数 **/
	private Integer pageNum = 1;
	/** 默认每页显示多少记录 **/
	public final static  Integer DEFAULT_PAGE_SIZE = 10;
	/** 每页显示多少记录 **/
	private Integer pageSize = DEFAULT_PAGE_SIZE;
	/** 总记录数 **/
	private Integer total = 0;

	private List<T> items = Collections.emptyList();
	
	public PaginationVO() {}

	public PaginationVO(Integer pageNum, Integer pageSize, Integer total, List<T> items) {
		super();
		this.pageNum = pageNum == null ? 0 : pageNum;
		this.pageSize = pageSize == null ? 0 : pageSize;
		this.total = total == null ? 0 : total;
		this.items = items;
	}

	public Integer getMaxPage() {
		if(pageSize == 0) pageSize = DEFAULT_PAGE_SIZE; 
		return total/pageSize+((total%pageSize)>0?1:0);
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotal() {
		if(items != null && items.size() > total) total =  items.size();
		return total;
	}

	public void setTotal(Integer  total) {
		this.total = total;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer  pageNum) {
		this.pageNum = pageNum;
	}

	public void setMaxPage(Integer  maxPage) {
		this.maxPage = maxPage;
	}
	
	@Override
	public String toString() {
		return "{maxPage:" + maxPage + ",pageNum:" + pageNum + ",pageSize:" + pageSize + ",total:" + total + ",items:" + items.toString() + "}";
	}

	public Map<String, Object> convertToJson() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("maxPage", maxPage);
		result.put("pageNum", pageNum);
		result.put("pageSize", pageSize);
		result.put("total", total);
		result.put("items", items);
		return result;
	}
}
