package com.tensquare.manager.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yeming
 * @date 2019/9/3 22:07
 */
@Component
public class ManagerFilter extends ZuulFilter {
    @Autowired
    JwtUtil jwtUtil;

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
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        if (request.getMethod() == "options"){
            return null;
        }
        if (request.getRequestURI().indexOf("login") > 0){
            return null;
        }

        //获取请求头
        String header = request.getHeader("Authorization");
        if (!StringUtils.isEmpty(header) && header.startsWith("Bearer_")){
            String token = header.substring(7);

            try {
                Claims claims = jwtUtil.parseJWT(token);
                if (claims !=null){
                    String roles = (String) claims.get("roles");
                    if (!StringUtils.isEmpty(roles) && "admin".equals(roles)){
                        currentContext.addZuulRequestHeader("Authorization",header);
                        return null;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                currentContext.setSendZuulResponse(false);
            }
        }
        currentContext.setSendZuulResponse(false);
        currentContext.setResponseStatusCode(403);
        currentContext.setResponseBody("权限不足");
        currentContext.getResponse().setContentType("text/html;charset=utf-8");
        return null;
    }
}
