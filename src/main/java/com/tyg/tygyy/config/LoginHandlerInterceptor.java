package com.tyg.tygyy.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    /**
     * 拦截器
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        获取session中的用户名
       // Object admin = request.getSession().getAttribute("admin");
       // Object user = request.getSession().getAttribute("user");
       // if (admin == null && user==null ){
//            为空就返回登录
         //   request.setAttribute("status","登录已过期");
          //  request.getRequestDispatcher("/login.html").forward(request,response);
            //response.sendRedirect("/toIndex");//重定向防止重复提交哦
            //return false;
       // }else{
            return true;
       // }
    }
}
