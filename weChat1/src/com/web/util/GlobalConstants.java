package com.web.util;

import java.util.Properties;

public class GlobalConstants {

	public static Properties interfaceUrlProperties;

/**
 * 
 * @Description: TODO
 * @param @param key
 * @param @return   
 * @author »ÆÖÐÕý
 */
	public static String getInterfaceUrl(String key) {
		return (String) interfaceUrlProperties.get(key);
	}
	
		
	
}
