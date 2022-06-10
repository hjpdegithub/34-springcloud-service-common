package com.springboot.boot.common;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lizh
 */
@RestController
public class ErrorPagesController implements ErrorController {

	private static final String ERROR_PATH = "/error";

	@RequestMapping(value = ERROR_PATH)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public String error() {
		return null;
	}

	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}
}
