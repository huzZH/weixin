package com.wechat.message.resp;
/**
 * ClassName BaseMessage
 * @Descriptoion ������Ϣ�������
 * @author ������
 *
 */
public class BaseMessage {
	private String ToUserName;    //���շ�΢���˺�(һ��OpenID)
	private String FromUserName;  //������΢�ź�
	private Long CreateTime;      //��Ϣ����ʱ��
	private String MsgType;       //������Ϣ����
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public Long getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(Long createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	
}
