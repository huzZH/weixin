package com.wechat.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * ClassName: SingUtil
 * @Description: 请求校验类工具
 * @author 黄中正
 *
 */
public class SingUtil {
	
	private static String token = "weixin";
	
	public static Boolean checkSingature(String signature, String timestamp, String nonce) {
		
		String[] arr = new String[] { token, timestamp, nonce };  
        // 将token、timestamp、nonce三个参数进行字典序排序   
        Arrays.sort(arr);  
        StringBuilder content = new StringBuilder();  
        for (int i = 0; i < arr.length; i++) {  
            content.append(arr[i]);  
        }  
        MessageDigest md = null;  
        String tmpStr = null;  
  
        try {  
            md = MessageDigest.getInstance("SHA-1");  
            // 将三个参数字符串拼接成一个字符串进行sha1加密   ,获得密文
            byte[] digest = md.digest(content.toString().getBytes());  
            //将密文转换成16进制字符串形式
   //       tmpStr = byteToStr(digest);  
            tmpStr = byteToHexStr(digest);
            System.out.println("tmpStr:  " + tmpStr);
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        }  
  
        content = null;  
        // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信   
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;  
    }  
  
    /** 
     * 将字节数组转换为十六进制字符串 
     * @param byteArray 
     * @return 
     */  
    private static String byteToStr(byte[] byteArray) {  
        String strDigest = "";  
        for (int i = 0; i < byteArray.length; i++) {  
            strDigest += byteToHexStr(byteArray[i]);  
        }  
        return strDigest;  
    }  
  
    /** 
     * 将字节转换为十六进制字符串 
     * @param mByte 
     * @return 
     */  
    private static String byteToHexStr(byte mByte) {  
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };  
        char[] tempArr = new char[2];  
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];  
        tempArr[1] = Digit[mByte & 0X0F];  
        String s = new String(tempArr);  
        return s;  
    }  
    
    /**
     * @Description 将字符数组转换成十六进制字符数组
     * @param bytes
     * @return
     */
    public static String byteToHexStr(byte[] bytes) {
    	StringBuilder build = new StringBuilder();
    	for(int i = 0; i < bytes.length; i++) {
    		String hexStr = Integer.toHexString(bytes[i] & 0xFF);
    		if(hexStr.length() < 2) {
    			build.append(0);
    		}
    		build.append(hexStr);
    	}
    	return build.toString().toUpperCase();
    }
}  
