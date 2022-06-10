package com.springboot.boot.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lizh
 * @date 2021/12/11
 */
@RestController
@Slf4j
public class IndexController {

	@GetMapping(value = "/")
	public String index() {
		return "ok";
	}

}
