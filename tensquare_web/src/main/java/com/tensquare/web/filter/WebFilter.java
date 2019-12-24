package com.tensquare.web.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.netflix.zuul.http.ZuulServlet;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yeming
 * @date 2019/9/1 22:53
 */
@Component
public class WebFilter extends ZuulFilter{
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("经过了网关过滤器");
        //获取request上下文对象
        RequestContext currentContext = RequestContext.getCurrentContext();
        //获取request域
        HttpServletRequest request = currentContext.getRequest();
        //获取请求头信息
        String header = request.getHeader("Authorization");
        if (!StringUtils.isEmpty(header)){
            currentContext.addZuulRequestHeader("Authorization",header);
        }
        return null;
    }
}
