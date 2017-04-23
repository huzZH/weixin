package com.wechat.common;

import java.security.MessageDigest;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.web.util.GlobalConstants;

/**
 * ClasName  JSSDK_Config
 * @Description ��jsapi_ticket����ǩ��У��
 * @author ������
 *
 */
public class JSSDK_Config {
	private static Logger logger = Logger.getLogger(JSSDK_Config.class);
	public static Map<String,String> chcek_Sign(String url) throws Exception {
		String noncestr = create_nonceStr(); //�����
		String jsapi_ticket = GlobalConstants.getInterfaceUrl("jsapi_ticket"); //jsapi_ticket
		String timestamp = create_timestamp();  //ʱ���
		String  str = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + noncestr
	                + "&timestamp=" + timestamp  + "&url=" + url;
		System.out.println(str);
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		md.reset();
		md.update(str.getBytes("UTF-8"));
		String signature = byteToHex(md.digest());
		Map<String, String> jssdk = new HashMap<String, String>();
		jssdk.put("appid", GlobalConstants.getInterfaceUrl("appid"));
		jssdk.put("noncestr", noncestr);
		jssdk.put("timestamp", timestamp);
		jssdk.put("signature", signature);
		logger.info("����ɹ�������ã�" + jssdk.toString());
		return jssdk;
	}
	/**
	 * ��������ַ���
	 * @return
	 */
	public static String create_nonceStr() {
		return UUID.randomUUID().toString();
	}
	public static String create_timestamp() {
		return Long.toString(System.currentTimeMillis()/1000);
	}
	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}
}
