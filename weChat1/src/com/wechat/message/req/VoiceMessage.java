package com.wechat.message.req;
/**
 * @Description 语音消息类
 * @author 黄中正
 *
 */
public class VoiceMessage {
	private String MediaId; //媒体Id
	private String Format;  //语音格式
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getFormat() {
		return Format;
	}
	public void setFormat(String format) {
		Format = format;
	}
	
	
}
