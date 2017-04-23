package com.wechat.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

/**
 * ClassName  HttpUtil
 * @Description Http���󹤾���
 * @author ������
 *
 */
public class HttpUtil {
	
	public static Double getCount(String regUrl) throws Exception {
		InputStream inputStream = null;
		HttpGet request = new HttpGet();
		try {
			HttpClient client = new DefaultHttpClient();
			request.setHeader("Accept-Encoding", "gzip");
			request.setURI(new URI(regUrl));
			HttpResponse response = client.execute(request);
			inputStream = response.getEntity().getContent();
			String result = getJsonStringFromGZIP(inputStream);
			Gson json = new Gson();
			Map map = json.fromJson(result, Map.class);
			Double count = (Double) map.get("image_count");
			return count;
		} finally{
			if(inputStream != null) {
				inputStream.close();
			}
			request.releaseConnection();
		}
		
	}
	
	
	/**
	 * @Description Get����response
	 * @param regUrl
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String sendGet(String regUrl, Map<String, String> params) throws Exception {
		InputStream inputStream = null;
		HttpGet request = new HttpGet();
		try {
			String url = buildUrl(regUrl, params);
			HttpClient client = new DefaultHttpClient();
			System.out.println("========================="+url);
			request.setHeader("Accept-Encoding", "gzip");
			request.setURI(new URI(url));

			HttpResponse response = client.execute(request);

			inputStream = response.getEntity().getContent();
			String result = getJsonStringFromGZIP(inputStream);
			return result;
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			request.releaseConnection();
		}
	}
	/**
	 * @Description  POST����response
	 * @param regUrl
	 * @param map
	 * @return
	 * @throws Exception 
	 */
	public static String sendPost(String regUrl, Map<String, String> map) throws Exception{
		try{
			Set<String> set = map.keySet();
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			for(String key : set) {
				list.add(new BasicNameValuePair(key, map.get(key)));
			}
			if(list.size() > 0) {
				try{
					HttpClient client = new DefaultHttpClient();
					HttpPost request = new HttpPost(regUrl);
					request.setHeader("Accept-Encoding", HTTP.UTF_8);
					request.setEntity(new UrlEncodedFormEntity(list,HTTP.UTF_8));
					HttpResponse response = client.execute(request);
					InputStream inputStream = response.getEntity().getContent();
					try{
						String result = getJsonStringFromGZIP(inputStream);
					}finally {
						inputStream.close();
					}
				}catch(Exception e) {
					e.printStackTrace();
					throw new Exception("��������ʧ��,��������������ԣ�");
				}
			}else {
				throw new Exception("���������ȫ,�����ԣ�");
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception("δ֪����");
		}
		return null;
	}
	/**
	 * @Description ����post���󲢷���json�ַ���
	 * @param url
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public static String sendPostBuffer(String regUrl, String params)
			throws ClientProtocolException, IOException {
		HttpPost request = new HttpPost(regUrl);

		StringEntity se = new StringEntity(params, HTTP.UTF_8);
		request.setEntity(se);
		// ��������
		
		HttpResponse httpResponse = new DefaultHttpClient().execute(request);
		// �õ�Ӧ����ַ�������Ҳ��һ�� JSON ��ʽ���������
		String retSrc = EntityUtils.toString(httpResponse.getEntity());
		request.releaseConnection();
		return retSrc;
		

	}
	/**
	 * @Description  post��ʽ����xml��Ϣ�������ؽ�� 
	 * @param urlStr
	 * @param xmlInfo
	 * @return
	 */
	public static String sendXMlPost(String urlStr, String xmlInfo) {
		
		/*
		 * URL ����һ��ʵ���������ӣ�Ȼ��Ϳ���ͨ�������������д���ݣ�д��֮��رգ�ͨ����ȡ������������
		 */
		try {
			URL url = new URL(urlStr);
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "text/xml");
			connection.setRequestProperty("Pragma", "no-cache");
			connection.setRequestProperty("Cache-Control", "no-cache");
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
			out.write(new String(xmlInfo.getBytes("utf-8")));
			out.flush();
			out.close();
			BufferedReader read = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String result = "";
			String readLine = read.readLine();
			for(String line = read.readLine(); line != null; line = read.readLine()) {
				result += line;
			}
			return result;  //����������
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "fail";
	}
	/**
	 * @Description  ��ѹ��GZIP
	 * @param inputStream
	 * @return
	 */
	public static String getJsonStringFromGZIP(InputStream is) {
		String jsonString = null;
		try {
			BufferedInputStream bis = new BufferedInputStream(is);
			bis.mark(2);
			// ȡǰ�����ֽ�
			byte[] header = new byte[2];
			int result = bis.read(header);
			// reset����������ʼλ��
			bis.reset();
			// �ж��Ƿ���GZIP��ʽ
			int headerData = getShort(header);
			// Gzip �� ��ǰ�����ֽ��� 0x1f8b
			if (result != -1 && headerData == 0x1f8b) {
				// LogUtil.i("HttpTask", " use GZIPInputStream  ");
				is = new GZIPInputStream(bis);
			} else {
				// LogUtil.d("HttpTask", " not use GZIPInputStream");
				is = bis;
			}
			InputStreamReader reader = new InputStreamReader(is, "utf-8");
			char[] data = new char[100];
			int readSize;
			StringBuffer sb = new StringBuffer();
			while ((readSize = reader.read(data)) > 0) {
				sb.append(data, 0, readSize);
			}
			jsonString = sb.toString();
			bis.close();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonString;
	}
	
	public static int getShort(byte[] data) {
		return (data[0] << 8) | data[1] & 0xFF;
	}
	
	
	/**
	 * ����Get��ʽ��Url
	 * @param regUrl ������Url��ַ
	 * @param params ��ѯ����
	 * @return
	 */
	public static String buildUrl(String regUrl, Map<String, String> params) {
		StringBuilder build = new StringBuilder();
		Set<String> set = params.keySet();
		for(String key : set) {
			build.append(String.format("%s=%s&", key, params.get(key)));
		}
		
		return regUrl + "?" + build.toString();
	}
}
