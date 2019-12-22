package com.dksy.seciruty.validate.code.image;

import com.dksy.seciruty.validate.code.ValidateCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.image.BufferedImage;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageCode extends ValidateCode {

    private BufferedImage image;


    /**
     * 通过 传入过期时间间隔秒数 构建
     * @param image
     * @param code
     * @param expireTime
     */
    public ImageCode(BufferedImage image, String code, int expireTime) {
        super(code,expireTime);
        this.image = image;
    }

}
