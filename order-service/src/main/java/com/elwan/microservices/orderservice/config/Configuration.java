package com.elwan.microservices.orderservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("")
@RefreshScope
public class Configuration {

	@Value("${app.title}")
	private String appTitle;
	
	@Value("${rows.per.page}")
	private int rowsPerPage;
	
	@Value("${context.path.order}")
	private String contextPath;

	public String getAppTitle() {
		return appTitle;
	}

	public void setAppTitle(String appName) {
		this.appTitle = appName;
	}

	public int getRowsPerPage() {
		return rowsPerPage;
	}

	public void setRowsPerPage(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}

	public String getContextPath() {
		return contextPath;
	}
	
	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}
}
