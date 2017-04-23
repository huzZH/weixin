package com.wechat.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.web.util.GlobalConstants;
import com.wechat.util.HttpUtil;

public class WeChatTask {
	
	public void getToken() throws Exception {
		Map map = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("grant_type", "client_credential");
		params.put("appid", GlobalConstants.getInterfaceUrl("appid"));
		params.put("secret", GlobalConstants.getInterfaceUrl("AppSecret"));
		String regUrl = GlobalConstants.getInterfaceUrl("tokenUrl");
		String result = HttpUtil.sendGet(regUrl, params);
		Gson json = new Gson();
		map = json.fromJson(result, Map.class);
		String access_token = (String) map.get("access_token");
		GlobalConstants.interfaceUrlProperties.put("access_token", access_token);
		
		Map<String, String> jsMap = new HashMap<String, String>();
		jsMap.put("access_token", access_token);
		jsMap.put("type", "jsapi");
		String jsTicket = HttpUtil.sendGet(GlobalConstants.getInterfaceUrl("ticketUrl"), jsMap);
		map = json.fromJson(jsTicket, Map.class);
		String jsapi_ticket = (String) map.get("ticket");
		System.out.println("jsapi_ticketÖµÎª£º   " + jsapi_ticket);
		GlobalConstants.interfaceUrlProperties.put("jsapi_ticket", jsapi_ticket);

		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+
				"tokenÎª=============================="+access_token);
	}
}
