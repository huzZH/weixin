package com.wechat.message.req;
/**
 * ClassName BaseMessage
 * @Description ������Ϣ������
 * @author ������
 *
 */
public class BaseMessage {
	
	private String ToUserName;   //������΢�ź�
	private String FromUserName; //���ͷ�΢�ź�(һ��OpenId)
	private Long CreateTime;	 //����ʱ��
	private String MsgType;      //��Ϣ����(text/image/video/shortvideo/voice/location/link)
	private Long MsgId   ;       //��ϢID
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
	public Long getMsgId() {
		return MsgId;
	}
	public void setMsgId(Long msgId) {
		MsgId = msgId;
	}
	
}
