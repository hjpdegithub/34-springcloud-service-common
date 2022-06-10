
package com.springboot.boot.common.page;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 可排序查询参数对象
 *
 * @author mq
 * @since 2021-12-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BasePageOrderParam extends BasePageParam {
	private static final long serialVersionUID = 57714391204790143L;

	private List<OrderItem> pageSorts;

	public void defaultPageSort(OrderItem orderItem) {
		this.defaultPageSorts(Arrays.asList(orderItem));
	}

	public void defaultPageSorts(List<OrderItem> pageSorts) {
		if (CollectionUtils.isEmpty(pageSorts)) {
			return;
		}
		this.pageSorts = pageSorts;
	}

}
