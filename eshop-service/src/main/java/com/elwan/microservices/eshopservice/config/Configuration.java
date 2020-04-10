package com.elwan.microservices.eshopservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties("")
@RefreshScope
public class Configuration {

	@Value("${app.title}")
	private String appTitle;
	
	@Value("${context.path.eshop}")
	private String contextPath;
	
	@Value("${context.path.product}")
	private String productContextPath;
	
	@Value("${context.path.customer}")
	private String customerContextPath;
	
	@Value("${context.path.order}")
	private String orderContextPath;
	
	@Value("${product.rows.per.page}")
	private int productRowsPerPage;

}
