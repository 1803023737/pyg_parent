package com.cloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class LoginFilter extends ZuulFilter {
    @Override
    public String filterType() {//类型
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {//顺序
        return FilterConstants.PRE_DECORATION_FILTER_ORDER-1;//请求头之前
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //业务逻辑
        //获取参数 判断是否存在  参考其他的过滤器
        //RequestContext 生命周期
        // 路由前到路由结束 返回信息
        RequestContext ctx = RequestContext.getCurrentContext();//获得上下文
        HttpServletRequest request = ctx.getRequest();
        String token_access= request.getParameter("token-access");
        //不存在，拦截
        if (StringUtils.isBlank(token_access)){//apache 工具包
            ctx.setSendZuulResponse(false);//设置false进行拦截
            ctx.setResponseStatusCode(org.springframework.http.HttpStatus.FORBIDDEN.value());//状态码403
        }
        return null;
    }
}
