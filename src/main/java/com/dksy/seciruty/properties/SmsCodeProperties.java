/**
 * 
 */
package com.dksy.seciruty.properties;

import lombok.Data;

/**
 * @author zhailiang
 *
 */
@Data
public class SmsCodeProperties {
	
	private int length = 6;
	private int expireIn = 60;
	
	private String url;
}
