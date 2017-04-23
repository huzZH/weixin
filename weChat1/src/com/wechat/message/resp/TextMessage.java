package com.wechat.message.resp;
/**
 * ClassName TextMessage
 * @Description 返回文本消息类
 * @author 黄中正
 *
 */
public class TextMessage extends BaseMessage{
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
	
}
