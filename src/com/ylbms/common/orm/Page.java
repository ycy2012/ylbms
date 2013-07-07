/**
 * Copyright (c) 2005-2010 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * $Id: Page.java 1183 2010-08-28 08:05:49Z calvinxiu $
 */
package com.ylbms.common.orm;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Lists;

/**
 * 与具体ORM实现无关的分页参数及查询结果封装.
 * 
 * 注意所有序号从1开始.
 * 
 * @param <T>
 *            Page中记录的类型.
 * 
 * @author calvin
 * @author JackLiang 二〇一三年六月十八日 13:26:36
 */
public class Page<T> {
	// -- 公共变量 --//
	public static final String ASC = "asc";
	public static final String DESC = "desc";
	public static final int NON_PAGE = 0;

	private static final int PAGE_SIZE = 20;

	// -- 分页参数 --//
	protected int pageNum = 1;
	protected int numPerPage = -1;
	protected String orderBy = null;
	protected String order = null;
	protected boolean autoCount = true;

	// -- 返回结果 --//
	protected List<T> result = Lists.newArrayList();

	protected long totalCount = -1;

	// -- 构造函数 --//
	public Page() {
		this.numPerPage = PAGE_SIZE;
	}

	public Page(int numPerPage) {
		this.numPerPage = PAGE_SIZE;
	}

	// -- 分页参数访问函数 --//
	/**
	 * 获得当前页的页号,序号从1开始,默认为1.
	 */
	public int getPageNum() {
		return pageNum;
	}

	/**
	 * 设置当前页的页号,序号从1开始,低于1时自动调整为1.
	 */

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
		if (pageNum < 1) {
			this.pageNum = 1;
		}
	}

	/**
	 * 返回Page对象自身的setPageNo函数,可用于连续设置。
	 */
	public Page<T> pageNo(final int thePageNo) {
		setPageNum(thePageNo);
		return this;
	}

	/**
	 * 获得每页的记录数量, 默认为-1.
	 */

	public int getNumPerPage() {
		return numPerPage;
	}

	/**
	 * 设置每页的记录数量.
	 */

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	/**
	 * 返回Page对象自身的setPageSize函数,可用于连续设置。
	 */
	public Page<T> pageSize(final int thePageSize) {
		setNumPerPage(thePageSize);
		return this;
	}

	/**
	 * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从1开始.
	 */
	public int getFirst() {
		return ((pageNum - 1) * numPerPage) + 1;
	}

	/**
	 * 获得排序字段,无默认值. 多个排序字段时用','分隔.
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * 设置排序字段,多个排序字段时用','分隔.
	 */
	public void setOrderBy(final String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * 返回Page对象自身的setOrderBy函数,可用于连续设置。
	 */
	public Page<T> orderBy(final String theOrderBy) {
		setOrderBy(theOrderBy);
		return this;
	}

	/**
	 * 获得排序方向, 无默认值.
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * 设置排序方式向.
	 * 
	 * @param order
	 *            可选值为desc或asc,多个排序字段时用','分隔.
	 */
	public void setOrder(final String order) {
		String lowcaseOrder = StringUtils.lowerCase(order);

		// 检查order字符串的合法值
		String[] orders = StringUtils.split(lowcaseOrder, ',');
		for (String orderStr : orders) {
			if (!StringUtils.equals(DESC, orderStr)
					&& !StringUtils.equals(ASC, orderStr)) {
				throw new IllegalArgumentException("排序方向" + orderStr + "不是合法值");
			}
		}

		this.order = lowcaseOrder;
	}

	/**
	 * 返回Page对象自身的setOrder函数,可用于连续设置。
	 */
	public Page<T> order(final String theOrder) {
		setOrder(theOrder);
		return this;
	}

	/**
	 * 是否已设置排序字段,无默认值.
	 */
	public boolean isOrderBySetted() {
		return (StringUtils.isNotBlank(orderBy) && StringUtils
				.isNotBlank(order));
	}

	/**
	 * 获得查询对象时是否先自动执行count查询获取总记录数, 默认为false.
	 */
	public boolean isAutoCount() {
		return autoCount;
	}

	/**
	 * 设置查询对象时是否自动先执行count查询获取总记录数.
	 */
	public void setAutoCount(final boolean autoCount) {
		this.autoCount = autoCount;
	}

	/**
	 * 返回Page对象自身的setAutoCount函数,可用于连续设置。
	 */
	public Page<T> autoCount(final boolean theAutoCount) {
		setAutoCount(theAutoCount);
		return this;
	}

	// -- 访问查询结果函数 --//

	/**
	 * 获得页内的记录列表.
	 */
	public List<T> getResult() {
		return result;
	}

	/**
	 * 设置页内的记录列表.
	 */
	public void setResult(final List<T> result) {
		this.result = result;
	}

	/**
	 * 获得总记录数, 默认值为-1.
	 */
	public long getTotalCount() {
		return totalCount;
	}

	/**
	 * 设置总记录数.
	 */
	public void setTotalCount(final long totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * 根据pageSize与totalCount计算总页数, 默认值为-1.
	 */
	public long getTotalPages() {
		if (totalCount < 0) {
			return -1;
		}

		long count = totalCount / numPerPage;
		if (totalCount % numPerPage > 0) {
			count++;
		}
		return count;
	}

	/**
	 * 是否还有下一页.
	 */
	public boolean isHasNext() {
		return (pageNum + 1 <= getTotalPages());
	}

	/**
	 * 取得下页的页号, 序号从1开始. 当前页为尾页时仍返回尾页序号.
	 */
	public int getNextPage() {
		if (isHasNext()) {
			return pageNum + 1;
		} else {
			return pageNum;
		}
	}

	/**
	 * 是否还有上一页.
	 */
	public boolean isHasPre() {
		return (pageNum - 1 >= 1);
	}

	/**
	 * 取得上页的页号, 序号从1开始. 当前页为首页时返回首页序号.
	 */
	public int getPrePage() {
		if (isHasPre()) {
			return pageNum - 1;
		} else {
			return pageNum;
		}
	}

	public boolean isDisabled() {
		return this.pageNum == -1;
	}
}
