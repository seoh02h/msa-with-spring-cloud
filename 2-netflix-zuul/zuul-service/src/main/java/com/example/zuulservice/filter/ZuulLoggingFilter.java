package com.example.zuulservice.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ZuulLoggingFilter extends ZuulFilter {

//  Logger logger = LoggerFactory.getLogger(ZuulLoggingFilter.class); -> @Sl4j 로 대체

  @Override
  public Object run() throws ZuulException {
    // 실행 동작

    log.info("********* printing logs : ");

    RequestContext ctx = RequestContext.getCurrentContext();
    HttpServletRequest request = ctx.getRequest();
    log.info("******* " + request.getRequestURI());

    log.info("********* printing logs : ");

    return null;
  }

  @Override
  public String filterType() {
    return "pre"; // 사전필터
  }

  @Override
  public int filterOrder() {
    return 1; // 필터가 여러 개 있을 경우 순서
  }

  @Override
  public boolean shouldFilter() {
    return true; // 필터를 사용하겠다.
  }


}
