package com.dksy.seciruty.validate.code.sms;



public interface SmsCodeSender {

    void send(String mobile, String code);
}
