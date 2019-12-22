package com.dksy.seciruty.validate.code.sms;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultSmsCodeSender implements SmsCodeSender{
    @Override
    public void send(String mobile, String code) {
        log.info("向手机号：{}，发送验证码为：{}",mobile,code);
    }
}
