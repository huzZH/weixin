package com.web.start;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

import com.web.util.GlobalConstants;

public class InterfaceUrlInit {
	public synchronized static void init() {
		ClassLoader load = Thread.currentThread().getContextClassLoader();
		Properties prop = new Properties();
		if(GlobalConstants.interfaceUrlProperties == null) {
			GlobalConstants.interfaceUrlProperties = new Properties();
		}
		InputStream inputStream = null;
		try {
			inputStream = load.getResourceAsStream("interface_url.properties");
			prop.load(inputStream);
			for(Object key : prop.keySet()) {
				GlobalConstants.interfaceUrlProperties.put(key, prop.get(key));
			}

			prop = new Properties();
			inputStream = load.getResourceAsStream("wechat.properties");
			prop.load(inputStream);
			for(Object key :prop.keySet()) {
				GlobalConstants.interfaceUrlProperties.put(key, prop.get(key));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
}
