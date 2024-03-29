package com.dksy.seciruty.validate.code.sms;

import com.dksy.seciruty.properties.SecurityProperties;
import com.dksy.seciruty.validate.code.ValidateCode;
import com.dksy.seciruty.validate.code.ValidateCodeGenerator;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

@Component("smsValidateCodeGenerator")
@Data
public class SmsCodeGenerator implements ValidateCodeGenerator {
    @Autowired
    private SecurityProperties  securityProperties;

    @Override
    public ValidateCode generate(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new ValidateCode(code,securityProperties.getCode().getSms().getExpireIn());
    }
}
