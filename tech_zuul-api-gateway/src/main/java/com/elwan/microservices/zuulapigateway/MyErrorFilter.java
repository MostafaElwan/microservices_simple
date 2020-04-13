package com.elwan.microservices.zuulapigateway;

import com.netflix.zuul.ZuulFilter;

public class MyErrorFilter extends ZuulFilter {
 
  @Override
  public String filterType() {
    return "error";
  }
 
  @Override
  public int filterOrder() {
    return 1;
  }
 
  @Override
  public boolean shouldFilter() {
    return true;
  }
 
  @Override
  public Object run() {
   System.out.println("Inside Route Filter");
 
    return null;
  }
}
