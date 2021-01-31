package com.elwan.microservices.productservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties("")
@RefreshScope
@Data
public class Configuration {

	@Value("${app.title}")
	private String appTitle;
	
	@Value("${rows.per.page}")
	private int rowsPerPage;
	
	@Value("${context.path.product}")
	private String contextPath;

}
