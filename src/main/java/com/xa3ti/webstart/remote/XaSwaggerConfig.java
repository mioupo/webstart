package com.xa3ti.webstart.remote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;


import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import com.wordnik.swagger.model.ApiInfo;


public class XaSwaggerConfig {
	
	@Autowired
	private SpringSwaggerConfig springSwaggerConfig;

	/**
	 * Required to autowire SpringSwaggerConfig
	 */
	
	public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
		this.springSwaggerConfig = springSwaggerConfig;
	}

	/**
	 * Every SwaggerSpringMvcPlugin bean is picked up by the swagger-mvc
	 * framework - allowing for multiple swagger groups i.e. same code base
	 * multiple swagger resource listings.
	 */
	@Bean
	public SwaggerSpringMvcPlugin customImplementation() {
	      return new SwaggerSpringMvcPlugin(this.springSwaggerConfig).apiInfo(apiInfo()).includePatterns(".mobile.*");
	}

	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo("项目文档",
				"接口描述", "3ti Xian'an",
				"wbcao@3ti.us", "",
				"");
		return apiInfo;
	}
}
