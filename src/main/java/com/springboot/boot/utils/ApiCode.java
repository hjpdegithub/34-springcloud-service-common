
package com.springboot.boot.utils;

/**
 * <p>
 * REST API 响应码
 * </p>
 *
 */
public enum ApiCode {

	/**
	 * 操作成功
	 **/
	SUCCESS(200, "操作成功"),
	/**
	 * 非法访问
	 **/
	UNAUTHORIZED(401, "非法访问"),
	/**
	 * 没有权限
	 **/
	NOT_PERMISSION(403, "没有权限"),
	/**
	 * 你请求的资源不存在
	 **/
	NOT_FOUND(404, "你请求的资源不存在"),
	/**
	 * 操作失败
	 **/
	FAIL(500, "操作失败"),
	/**
	 * 前端参数为空或者后端判断失败
	 */
	BUSINESS_FAIL(1000,"前端业务失败或判断错误标识"),
	FILE_FAIL(1000,"文件错误"),
	/**
	 * 系统异常
	 **/
	SYSTEM_EXCEPTION(5000, "系统异常"),
	/**
	 * 请求参数校验异常
	 **/
	PARAMETER_EXCEPTION(5001, "请求参数校验异常"),
	/**
	 * 请求参数解析异常
	 **/
	PARAMETER_PARSE_EXCEPTION(5002, "请求参数解析异常"),
	/**
	 * HTTP内容类型异常
	 **/
	HTTP_MEDIA_TYPE_EXCEPTION(5003, "HTTP内容类型异常"),
	/**
	 * 系统处理异常
	 **/
	SPRING_BOOT_PLUS_EXCEPTION(5100, "系统处理异常"),
	/**
	 * 业务处理异常
	 **/
	BUSINESS_EXCEPTION(5101, "业务处理异常"),
	/**
	 * 数据库处理异常
	 **/


	;

	private final int code;
	private final String message;

	ApiCode(final int code, final String message) {
		this.code = code;
		this.message = message;
	}

	public static ApiCode getApiCode(int code) {
		ApiCode[] ecs = ApiCode.values();
		for (ApiCode ec : ecs) {
			if (ec.getCode() == code) {
				return ec;
			}
		}
		return SUCCESS;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
