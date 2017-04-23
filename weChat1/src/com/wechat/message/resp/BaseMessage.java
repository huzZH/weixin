package com.wechat.message.resp;
/**
 * ClassName BaseMessage
 * @Descriptoion 返回消息体基本类
 * @author 黄中正
 *
 */
public class BaseMessage {
	private String ToUserName;    //接收方微信账号(一个OpenID)
	private String FromUserName;  //开发者微信号
	private Long CreateTime;      //消息创建时间
	private String MsgType;       //返回消息类型
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
