
package com.springboot.boot.common.page;

import cn.hutool.core.util.StrUtil;
import com.springboot.boot.common.utils.PropertyColumnUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 排序列映射
 *
 * @author mq
 * @since 2021-12-14
 **/
@Data
@Accessors(chain = true)
public class OrderMapping {

	private boolean underLineMode;

	public OrderMapping() {

	}

	public OrderMapping(boolean underLineMode) {
		this.underLineMode = underLineMode;
	}

	private Map<String, String> map = new ConcurrentHashMap<>();

	public OrderMapping mapping(String property, String column) {
		map.put(property, column);
		return this;
	}

	public OrderMapping mapping(String property, String tablePrefix, String column) {
		if (StrUtil.isNotBlank(tablePrefix)) {
			column = tablePrefix + "." + column;
		}
		map.put(property, column);
		return this;
	}

	public OrderMapping mapping(String property, Class<?> clazz) {
		String column = PropertyColumnUtil.getColumn(clazz, property);
		map.put(property, column);
		return this;
	}

	public OrderMapping mapping(String property, String tablePrefix, Class<?> clazz) {
		String column = PropertyColumnUtil.getColumn(clazz, property);
		mapping(property, tablePrefix, column);
		return this;
	}

	public String getMappingColumn(String property) {
		if (StrUtil.isBlank(property)) {
			return null;
		}
		return map.get(property);
	}

	public void filterOrderItems(List<OrderItem> orderItems) {
		if (CollectionUtils.isEmpty(orderItems)) {
			return;
		}
		// 如果集合不为空，则按照PropertyColumnUtil映射
		if (!map.isEmpty()) {
			orderItems.forEach(item -> {
				item.setColumn(this.getMappingColumn(item.getColumn()));
			});
		} else if (underLineMode) {
			// 如果开启下划线模式，自动转换成下划线
			orderItems.forEach(item -> {
				String column = item.getColumn();
				if (StrUtil.isNotBlank(column)) {
					// 驼峰转换成下划线
					//item.setColumn(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, column));
				}
			});
		}
	}

}