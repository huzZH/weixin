package com.wechat.message.req;
/**
 * ClassName BaseMessage
 * @Description 请求消息基本类
 * @author 黄中正
 *
 */
public class BaseMessage {
	
	private String ToUserName;   //开发者微信号
	private String FromUserName; //发送发微信号(一个OpenId)
	private Long CreateTime;	 //创建时间
	private String MsgType;      //消息类型(text/image/video/shortvideo/voice/location/link)
	private Long MsgId   ;       //消息ID
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
