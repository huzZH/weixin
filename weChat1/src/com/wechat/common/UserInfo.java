package com.wechat.common;

import java.io.InputStream;
import java.net.URI;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;
import com.wechat.bean.User;
import com.wechat.util.HttpUtil;

public class UserInfo {
	
	public static User getUserInfo(String regUrl, Map<String, String> params) throws Exception{
		InputStream inputStream = null;
		HttpGet request = new HttpGet();
		User user = null;
		try {
			String url = HttpUtil.buildUrl(regUrl, params);
			System.out.println(url);
			HttpClient client = new DefaultHttpClient();
			request.setHeader("Accept-Encoding", "gzip");
			request.setURI(new URI(url));
			HttpResponse response = client.execute(request);
			inputStream = response.getEntity().getContent();
			String result = HttpUtil.getJsonStringFromGZIP(inputStream);
			System.out.println(result);
			Gson json = new Gson();
			user = json.fromJson(result, User.class);
			if(user.getSubscribe() != 0) {
				System.out.println(user.toString());
			}
		} finally {
			if(inputStream != null) {
				inputStream.close();
				inputStream = null;
			}
			request.releaseConnection();
		}
		return user;
	}
}
