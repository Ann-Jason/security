package com.dksy.seciruty.controller;

import com.dksy.seciruty.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@Slf4j
public class SecurityController {

    @Autowired
    private SecurityProperties securityProperties;

    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    /**
     * 配置security登录方法 ，控制跳转
     * html 直接跳转  不是的话按ajax处理，返回状态码和错误信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/authentication/require")
    public String requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {

        SavedRequest savedRequest = requestCache.getRequest(request, response);

        if (savedRequest != null) {
            String redirectUrl = savedRequest.getRedirectUrl();
//            log.info("访问路径：{}",redirectUrl);
            // 以html 结尾的，跳转所配置的登录页面
            if (StringUtils.endsWithIgnoreCase(redirectUrl,".html")){
                redirectStrategy.sendRedirect(request,response, securityProperties.getBrowser().getLoginPage());
            }
        }
        return "请引导用户登录";

    }
}
