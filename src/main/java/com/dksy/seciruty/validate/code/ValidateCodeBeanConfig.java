package com.dksy.seciruty.validate.code;

import com.dksy.seciruty.properties.SecurityProperties;
import com.dksy.seciruty.validate.code.image.ImageCodeGenerator;
import com.dksy.seciruty.validate.code.sms.DefaultSmsCodeSender;
import com.dksy.seciruty.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 验证码配置项   可供后续功能维护升级
 */
@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 将图片验证码生成器注入spring 容器  ，提供默认实现
     * 当默认实现不满足时，可通过覆盖方式实现升级
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name="imageValidateCodeGenerator")
    public ValidateCodeGenerator imageValidateCodeGenerator(){
        ImageCodeGenerator generator = new ImageCodeGenerator();
        generator.setSecurityProperties(securityProperties);
        return generator;
    }

    /**
     * 同理，提供默认发送手机短信方法  ，如需升级，覆盖即可
     *  或者 name = "smsCodeSender"
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender sender(){
        return new DefaultSmsCodeSender();
    }



}
