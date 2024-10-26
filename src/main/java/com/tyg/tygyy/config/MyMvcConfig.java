package com.tyg.tygyy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("file:D://upload//");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        访问首页跳转
        registry.addViewController("/").setViewName("login");
        registry.addViewController("login.html").setViewName("login");
//        跳转注册页面
        registry.addViewController("register.html").setViewName("register");
        registry.addViewController("register2.html").setViewName("register2");
//        跳转找回密码页面
        registry.addViewController("findpwd.html").setViewName("findpwd");
        registry.addViewController("setpwd.html").setViewName("setpwd");
//        跳转注册
        registry.addViewController("toReg").setViewName("register");
        registry.addViewController("toReg2").setViewName("register2");
//        跳转登录
        registry.addViewController("toLogin").setViewName("login");
//        跳转找回密码
        registry.addViewController("tofindpwd").setViewName("findpwd");
//        跳转首页
        registry.addViewController("homepage").setViewName("home/homepage");
        registry.addViewController("homepage.html").setViewName("home/homepage");
        registry.addViewController("echart.html").setViewName("son/echart");
    }

//    拦截器配置
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        设置公开的资源
        registry.addInterceptor(new com.tyg.tygyy.config.LoginHandlerInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/login.html","/","/register.html","/js/**",
                        "/css/**","/fonts/**","/img/**","/layui/**",
                        "/ajaxUsername","/loginUser","/toReg","/toReg2","/toLogin","/registerUser","/registerUser2","/upload",
                        "/ajaxMailCode","/tofindpwd","/findPwd","/findpwd.html","/setpwd.html",
                        "/setPwd","error/404.html","index/*");
    }

}
