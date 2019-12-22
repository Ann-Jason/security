package com.dksy.seciruty.authentication.mobile;

import com.dksy.seciruty.authentication.MyAuthenticationFailHandler;
import com.dksy.seciruty.authentication.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class SmsCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    /**
     * 登陆失败处理
     */
    @Autowired
    private MyAuthenticationFailHandler failHandler;
    /**
     *  登陆成功处理
     */
    @Autowired
    private MyAuthenticationSuccessHandler successHandler;
    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
        smsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        smsCodeAuthenticationFilter.setAuthenticationFailureHandler(failHandler);
        smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(successHandler);

        SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
        smsCodeAuthenticationProvider.setUserDetailsService(userDetailsService);

        http.authenticationProvider(smsCodeAuthenticationProvider)
                .addFilterAfter(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
