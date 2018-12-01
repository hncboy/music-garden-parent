package com.hncboy.controller.interceptor;

import com.hncboy.utils.JSONResult;
import com.hncboy.utils.JsonUtils;
import com.hncboy.utils.RedisOperator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: hncboy
 * Date: 2018/12/1
 * Time: 15:20
 */
public class MiniInterceptor implements HandlerInterceptor {

    private static final String USER_REDIS_SESSION = "user-redis-session";

    @Autowired
    public RedisOperator redis;

    /**
     * 拦截请求，在controller调用之前
     *
     * @param request
     * @param response
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
         /*
          返回false：请求被拦截，返回
          返回true：请求ok，可以继续执行，放行
         */
        String userId = request.getHeader("headerUserId");
        String userToken = request.getHeader("headerUserToken");
        if (StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(userToken)) {
            String uniqueToken = redis.get(USER_REDIS_SESSION + ":" + userId);
            if (StringUtils.isEmpty(uniqueToken) && StringUtils.isBlank(uniqueToken)) {
                System.out.println("请登录...");
                returnErrorResponse(response, JSONResult.errorTokenMsg("请登录..."));
                return false;
            } else {
                if (!uniqueToken.equals(userToken)) {
                    System.out.println("账号被挤出...");
                    returnErrorResponse(response, JSONResult.errorTokenMsg("账号被挤出..."));
                    return false;
                }
            }
        } else {
            System.out.println("请登录...");
            returnErrorResponse(response, JSONResult.errorTokenMsg("请登录..."));
            return false;
        }
        return true;
    }

    private void returnErrorResponse(HttpServletResponse response, JSONResult result)
            throws IOException {
        response.setContentType("text/json");
        response.setCharacterEncoding("utf-8");
        try (OutputStream out = response.getOutputStream()) {
            out.write(JsonUtils.objectToJson(result).getBytes("utf-8"));
            out.flush();
        }
    }

    /**
     * 请求controller之后，渲染视图之前
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) {

    }

    /**
     * 请求controller之后，视图渲染之后
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

    }
}
