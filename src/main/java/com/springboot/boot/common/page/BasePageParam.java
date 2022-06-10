
package com.springboot.boot.common.page;

import com.springboot.boot.common.constant.CommonConstant;
import lombok.Data;

import java.io.Serializable;

/**
 * 查询参数
 *
 * @author mq
 * @since 2021-12-14
 */
@Data
public abstract class BasePageParam implements Serializable {
	private static final long serialVersionUID = -3263921252635611410L;

	private Long pageNum = CommonConstant.DEFAULT_PAGE_INDEX;

	private Long pageSize = CommonConstant.DEFAULT_PAGE_SIZE;

	private String keyword;

	public void setPageNum(Long pageNum) {
		if (pageNum == null || pageNum <= 0) {
			this.pageNum = CommonConstant.DEFAULT_PAGE_INDEX;
		} else {
			this.pageNum = pageNum;
		}
	}

	public void setPageSize(Long pageSize) {
		if (pageSize == null || pageSize <= 0) {
			this.pageSize = CommonConstant.DEFAULT_PAGE_SIZE;
		} else {
			this.pageSize = pageSize;
		}
	}

}
