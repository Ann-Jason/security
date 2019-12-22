package com.dksy.seciruty.validate.code;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidateCode {

    private String code;

    private LocalDateTime expireTime;

    /**
     * 通过 传入过期时间间隔秒数 构建
     * @param code
     * @param expireTime
     */
    public ValidateCode(String code, int expireTime) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireTime);
    }

    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
