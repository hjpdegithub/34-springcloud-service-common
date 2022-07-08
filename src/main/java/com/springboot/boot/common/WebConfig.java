package com.springboot.boot.common;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.springboot.boot.utils.ValueDesensitizeFilter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lizh
 * @date 2021/12/11
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

	/**
	 * 增加图片转换器
	 * @param converters
	 */
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new BufferedImageHttpMessageConverter());
	}

	/**
	 * json配置
	 * @return
	 */
	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters() {
		//1.先定义一个convert转换消息的对象
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
		fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
		//处理中文乱码问题(不然出现中文乱码)
		List<MediaType> fastMediaTypes = new ArrayList<>();
		fastMediaTypes.add(MediaType.APPLICATION_JSON);
		fastConverter.setSupportedMediaTypes(fastMediaTypes);
		//3.在convert中添加配置信息

        fastJsonConfig.setSerializeFilters(new ValueDesensitizeFilter());//添加自己写的拦截器
        fastConverter.setFastJsonConfig(fastJsonConfig);
		HttpMessageConverter<?> converter = fastConverter;

		return new HttpMessageConverters(converter);
	}

	/**
	 * 异步线程池
	 * @return
	 */
	/*@Bean
	public ThreadPoolTaskExecutor mvcTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(5);
		executor.setQueueCapacity(30);
		executor.setMaxPoolSize(15);
		return executor;
	}*/

	/**
	 * 异步线程池
	 @param configurer
	 */
	/*@Override
	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		configurer.setTaskExecutor(mvcTaskExecutor());
		configurer.setDefaultTimeout(60000L);
	}*/

}
