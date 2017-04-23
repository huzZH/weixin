package com.wechat;

import com.wechat.common.Code;

/**
 * ClassName: Message
 * @Description: ������Ϣ�ظ�
 * @author ������
 */
public class Message {
	private int code;
	private String msg;
	private Object data;
	public Message() {
		
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	public Message(int code,String msg) {
		this.code=code;
		this.msg=msg;
	}

	public Message(int code,String msg,Object data) {
		this.code=code;
		this.msg=msg;
		this.data=data;
	}
	

	/**
	 * ���سɹ���Ϣ
	 * @param content ����
	 * @return �ɹ���Ϣ
	 */
	public static Message success(String content, Object data) {
		return new Message(Code.SUCCESS, content, data);
	}
	
	/**
	 * ���سɹ���Ϣ
	 * @param content ����
	 * @return �ɹ���Ϣ
	 */
	public static Message success(String content) {
		return new Message(Code.SUCCESS, content);
	}
	
	/**
	 * ���سɹ���Ϣ
	 * @param content ����
	 * @return �ɹ���Ϣ
	 */
	public static Message success(Object data) {
		return new Message(Code.SUCCESS, "�����ɹ�",data);
	}
	
	/**
	 * ���سɹ���Ϣ
	 * @param content ����
	 * @return �ɹ���Ϣ
	 */
	public static Message success() {
		return new Message(Code.SUCCESS, "�����ɹ�");
	}
	
	/**
	 * ����ʧ����Ϣ
	 * @param content ����
	 * @return �ɹ���Ϣ
	 */
	public static Message error(int code,String content, Object data) {
		return new Message(code, content, data);
	}
	/**
	 * ����ʧ����Ϣ
	 * @param content ����
	 * @return �ɹ���Ϣ
	 */
	public static Message error(String content, Object data) {
		return new Message(Code.FAIL, content, data);
	}
	
	/**
	 * ����ʧ����Ϣ
	 * @param content ����
	 * @return �ɹ���Ϣ
	 */
	public static Message error(String content) {
		return new Message(Code.FAIL, content);
	}
	
	/**
	 * ����ʧ����Ϣ
	 * @param content ����
	 * @return �ɹ���Ϣ
	 */
	public static Message error() {
		return new Message(Code.FAIL, "����ʧ��");
	}

}
