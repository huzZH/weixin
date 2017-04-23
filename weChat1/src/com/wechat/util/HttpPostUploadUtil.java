package com.wechat.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.activation.MimetypesFileTypeMap;

import com.google.gson.Gson;
import com.web.util.GlobalConstants;

public class HttpPostUploadUtil {
	
	public static  String urlStr;
	public HttpPostUploadUtil() {
		// TODO Auto-generated constructor stub
		urlStr = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=" 
					+ GlobalConstants.getInterfaceUrl("access_token") + "&type=image";
	}
	
	public String formUpload(Map<String, String> textMap,  
            Map<String, String> fileMap) {  
        String res = "";  
        HttpURLConnection conn = null;  
        String BOUNDARY = "--------" + new SimpleDateFormat("yyyymmddHHmmssSS").format(new Date()); //boundary就是request头和上传文件内容的分隔符  
        try {  
            URL url = new URL(urlStr);  
            conn = (HttpURLConnection) url.openConnection();  
            conn.setConnectTimeout(5000);  
            conn.setReadTimeout(30000);  
            conn.setDoOutput(true);  
            conn.setDoInput(true);  
            conn.setUseCaches(false);  
            conn.setRequestMethod("POST");  
            conn.setRequestProperty("Connection", "Keep-Alive");  
            conn  
                    .setRequestProperty("User-Agent",  
                            "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");  
            conn.setRequestProperty("Content-Type",  
                    "multipart/form-data; boundary=" + BOUNDARY);  
  
            OutputStream out = new DataOutputStream(conn.getOutputStream());  
            // text  
            if (textMap != null) {  
                StringBuffer strBuf = new StringBuffer();  
                Iterator<?> iter = textMap.entrySet().iterator();  
                while (iter.hasNext()) {  
                    Map.Entry entry = (Map.Entry) iter.next();  
                    String inputName = (String) entry.getKey();  
                    String inputValue = (String) entry.getValue();  
                    if (inputValue == null) {  
                        continue;  
                    }  
                    strBuf.append("\r\n").append("--").append(BOUNDARY).append(  
                            "\r\n");  
                    strBuf.append("Content-Disposition: form-data; name=\""  
                            + inputName + "\"\r\n\r\n");  
                    strBuf.append(inputValue);  
                }  
                out.write(strBuf.toString().getBytes());  
            }  
  
            // file  
            if (fileMap != null) {  
                Iterator<?> iter = fileMap.entrySet().iterator();  
                while (iter.hasNext()) {  
                    Map.Entry entry = (Map.Entry) iter.next();  
                    String inputName = (String) entry.getKey();  
                    String inputValue = (String) entry.getValue();  
                    if (inputValue == null) {  
                        continue;  
                    }  
                    File file = new File(inputValue);  
                    String filename = file.getName();  
                    String contentType = new MimetypesFileTypeMap()  
                            .getContentType(file);  
                    if (filename.endsWith(".jpg")) {  
                        contentType = "image/jpg";  
                    }  
                    if (contentType == null || contentType.equals("")) {  
                        contentType = "application/octet-stream";  
                    }  
  
                    StringBuffer strBuf = new StringBuffer();  
                    strBuf.append("\r\n").append("--").append(BOUNDARY).append(  
                            "\r\n");  
                    strBuf.append("Content-Disposition: form-data; name=\""  
                            + inputName + "\"; filename=\"" + filename  
                            + "\"\r\n");  
                    strBuf.append("Content-Type:" + contentType + "\r\n\r\n");  
  
                    out.write(strBuf.toString().getBytes());  
  
                    DataInputStream in = new DataInputStream(  
                            new FileInputStream(file));  
                    int bytes = 0;  
                    byte[] bufferOut = new byte[1024];  
                    while ((bytes = in.read(bufferOut)) != -1) {  
                        out.write(bufferOut, 0, bytes);  
                    }  
                    in.close();  
                }  
            }  
  
            byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();  
            out.write(endData);  
            out.flush();  
            out.close();  
  
            // 读取返回数据  
            StringBuffer strBuf = new StringBuffer();  
            BufferedReader reader = new BufferedReader(new InputStreamReader(  
                    conn.getInputStream()));  
            String line = null;  
            while ((line = reader.readLine()) != null) {  
                strBuf.append(line).append("\n");  
            }  
            res = strBuf.toString();  
            reader.close();  
            reader = null;  
        } catch (Exception e) {  
            System.out.println("发送POST请求出错。" + urlStr);  
            e.printStackTrace();  
        } finally {  
            if (conn != null) {  
                conn.disconnect();  
                conn = null;  
            }  
        }  
        return res;  
    }  
	
	public static String upload(File file) throws Exception {
		String result = null;
		String STR = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=" + GlobalConstants.getInterfaceUrl("access_token");
		//设置边界，boundary是http协议里的分隔符
		String BOUNDARY = "--------" + new SimpleDateFormat("yyyymmddHHmmssSS").format(new Date());
		HttpURLConnection con = null;
		try {
			
			String fileName = file.getName();
			long fileLength = file.length();
			String type = "image";
			
			
			URL url = new URL(STR);
			con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setConnectTimeout(5000);
			con.setReadTimeout(30000);
			con.setRequestMethod("POST");
			con.setUseCaches(false);
			//设置请求头信息
			con.setRequestProperty("Connection", "Keep-Alive");
			con.setRequestProperty("Charset", "UTF-8");
			con.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + BOUNDARY);
			//请求正文信息
			StringBuilder build = new StringBuilder();
			build.append("--");//必须多两道线，HTTP协议规定
			build.append(BOUNDARY);
			build.append("\r\n");
			build.append("Content-Disposition: form-data;name=\"type\" \r\n\r\n");
			build.append(type + "\r\n");
			
			
			build.append("--");
			build.append(BOUNDARY);
			build.append("\r\n");
			build.append("Content-Disposition: form-data;name=\"media\";filename=\""  
                    + fileName + "\";filelength=\"" + fileLength + "\" \r\n");
			build.append("Content-Type:application/octet-stream\r\n\r\n");
			System.out.println(build.toString());
			//获得输出流
			OutputStream out = new DataOutputStream(con.getOutputStream());
			//输出表头
			out.write(build.toString().getBytes("UTF-8"));
			//文件正文部分
			//将文件以 流的形式推送到url中
			InputStream inputStream = new DataInputStream(new FileInputStream(file));
			int length = 0;
			byte[] fileByte = new byte[1024];
			while((length = inputStream.read(fileByte)) != -1) {
				out.write(fileByte, 0, length);
			}
			inputStream.close();
			
			//结尾部分
			byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线  
			out.write(foot);
			out.flush();
			out.close();
			
			
			// 定义BufferedReader输入流来读取URL的响应  
			StringBuffer strBuff = new StringBuffer();
			BufferedReader read = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line = null;
			while((line = read.readLine()) != null) {
				strBuff.append(line);
			}
			System.out.println(strBuff.toString());
			Gson json = new Gson();
			Map map = json.fromJson(strBuff.toString(), Map.class);
			if(map != null){
				result = (String) map.get("media_id");
				System.out.println("上传图片的mediaId： " + result);
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(con != null) {
				con.disconnect();
				con = null;
			}
		}
		return result;
	}
	
	
}
