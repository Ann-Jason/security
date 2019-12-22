package com.dksy.seciruty.config;

import com.dksy.seciruty.authentication.MyAuthenticationFailHandler;
import com.dksy.seciruty.authentication.MyAuthenticationSuccessHandler;
import com.dksy.seciruty.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.dksy.seciruty.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
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
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;


    @Bean
    public PersistentTokenRepository tokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        //首次使用创建表，第二次务必关闭，否则报错，已存在相同表名字
        //tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //验证码过滤器放在用户名密码之前
//        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
        //http.httpBasic()  alert提示框登录
        //http 表单登录      所有授权请求 必须 认证  注意登录页面需要直接被访问   登录表单请求路径
        http.formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")
                .successHandler(successHandler)
                .failureHandler(failHandler)
                .and().apply(validateCodeSecurityConfig)
                .and().apply(smsCodeAuthenticationSecurityConfig)
                .and().rememberMe().tokenRepository(tokenRepository())
                    .tokenValiditySeconds(3600)
                    .userDetailsService(userDetailsService)
                .and().authorizeRequests()
                    .antMatchers("/code/*","/authentication/require","/imooc-signIn.html","/demo-signIn.html").permitAll()
                    .anyRequest()
                    .authenticated().and().csrf().disable();
    }

    @Bean
    public PasswordEncoder encoder(){
        return  new BCryptPasswordEncoder();
    }
}
