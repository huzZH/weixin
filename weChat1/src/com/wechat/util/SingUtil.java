package com.wechat.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * ClassName: SingUtil
 * @Description: ����У���๤��
 * @author ������
 *
 */
public class SingUtil {
	
	private static String token = "weixin";
	
	public static Boolean checkSingature(String signature, String timestamp, String nonce) {
		
		String[] arr = new String[] { token, timestamp, nonce };  
        // ��token��timestamp��nonce�������������ֵ�������   
        Arrays.sort(arr);  
        StringBuilder content = new StringBuilder();  
        for (int i = 0; i < arr.length; i++) {  
            content.append(arr[i]);  
        }  
        MessageDigest md = null;  
        String tmpStr = null;  
  
        try {  
            md = MessageDigest.getInstance("SHA-1");  
            // �����������ַ���ƴ�ӳ�һ���ַ�������sha1����   ,�������
            byte[] digest = md.digest(content.toString().getBytes());  
            //������ת����16�����ַ�����ʽ
   //       tmpStr = byteToStr(digest);  
            tmpStr = byteToHexStr(digest);
            System.out.println("tmpStr:  " + tmpStr);
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        }  
  
        content = null;  
        // ��sha1���ܺ���ַ�������signature�Աȣ���ʶ��������Դ��΢��   
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;  
    }  
  
    /** 
     * ���ֽ�����ת��Ϊʮ�������ַ��� 
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
     * ���ֽ�ת��Ϊʮ�������ַ��� 
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
     * @Description ���ַ�����ת����ʮ�������ַ�����
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
