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
        String BOUNDARY = "--------" + new SimpleDateFormat("yyyymmddHHmmssSS").format(new Date()); //boundary����requestͷ���ϴ��ļ����ݵķָ���  
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
  
            // ��ȡ��������  
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
            System.out.println("����POST���������" + urlStr);  
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
		//���ñ߽磬boundary��httpЭ����ķָ���
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
			//��������ͷ��Ϣ
			con.setRequestProperty("Connection", "Keep-Alive");
			con.setRequestProperty("Charset", "UTF-8");
			con.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + BOUNDARY);
			//����������Ϣ
			StringBuilder build = new StringBuilder();
			build.append("--");//����������ߣ�HTTPЭ��涨
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
			//��������
			OutputStream out = new DataOutputStream(con.getOutputStream());
			//�����ͷ
			out.write(build.toString().getBytes("UTF-8"));
			//�ļ����Ĳ���
			//���ļ��� ������ʽ���͵�url��
			InputStream inputStream = new DataInputStream(new FileInputStream(file));
			int length = 0;
			byte[] fileByte = new byte[1024];
			while((length = inputStream.read(fileByte)) != -1) {
				out.write(fileByte, 0, length);
			}
			inputStream.close();
			
			//��β����
			byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// ����������ݷָ���  
			out.write(foot);
			out.flush();
			out.close();
			
			
			// ����BufferedReader����������ȡURL����Ӧ  
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
				System.out.println("�ϴ�ͼƬ��mediaId�� " + result);
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