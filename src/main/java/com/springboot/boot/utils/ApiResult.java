
package com.springboot.boot.utils;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * REST API 返回结果
 * </p>
 *
 */
@Data
@Slf4j
@Accessors(chain = true)
@Builder
@AllArgsConstructor
public class ApiResult<T> implements Serializable {

	private static final long serialVersionUID = 8004487252556526569L;

	/**
	 * 响应码
	 */
	private int code;

	/**
	 * 响应消息
	 */
	private String msg;

	/**
	 * 响应数据
	 */
	private T data;

	public ApiResult() {
	}

	public static ApiResult<Boolean> result(boolean flag) {
		if (flag) {
			return success();
		}
		return error();
	}

	public static ApiResult<Boolean> result(ApiCode apiCode) {
		return result(apiCode, null);
	}

	public static <T> ApiResult<T> result(ApiCode apiCode, T data) {
		return result(apiCode, null, data,0L);
	}

	public static <T> ApiResult<T> result(ApiCode apiCode, T data,Long total) {
		return result(apiCode, null, data,total);
	}

	public static <T> ApiResult<T> result(ApiCode apiCode, String message, T data,Long total) {
		String apiMessage = apiCode.getMessage();
		if (StrUtil.isBlank(message)) {
			message = apiMessage;
		}
		return (ApiResult<T>) ApiResult.builder().code(apiCode.getCode()).msg(message).data(data).build();
	}

	public static ApiResult<Boolean> success() {
		return success(null);
	}

	public static <T> ApiResult<T> success(T data) {
		return result(ApiCode.SUCCESS, data);
	}

	public static <T> ApiResult<T> success(T data,Long total) {
		return result(ApiCode.SUCCESS, data,total);
	}

	public static <T> ApiResult<T> success(T data, String message) {
		return result(ApiCode.SUCCESS, message, data,0L);
	}

	public static ApiResult<Map<String, Object>> successMap(String key, Object value) {
		Map<String, Object> map = new HashMap<>(1);
		map.put(key, value);
		return success(map);
	}

	public static ApiResult<Boolean> error(ApiCode apiCode) {
		return result(apiCode, null);
	}

	public static ApiResult<String> error(String message) {
		return result(ApiCode.FAIL, message, null,0L);

	}

	public static <T> ApiResult<T> error(ApiCode apiCode, T data) {
		if (ApiCode.SUCCESS == apiCode) {
			throw new RuntimeException("失败结果状态码不能为" + ApiCode.SUCCESS.getCode());
		}
		return result(apiCode, data);

	}

	public static ApiResult<String> error(Integer errorCode, String message) {
		return new ApiResult<String>().setCode(errorCode).setMsg(message);
	}

	public static ApiResult<Map<String, Object>> error(String key, Object value) {
		Map<String, Object> map = new HashMap<>(1);
		map.put(key, value);
		return result(ApiCode.FAIL, map);
	}

	public static ApiResult<Boolean> error() {
		return error(ApiCode.FAIL);
	}
}