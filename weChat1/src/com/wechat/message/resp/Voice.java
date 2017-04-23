package com.wechat.message.resp;
/**
 * ClassName VoiceMessage
 * @Description 返回语音消息类
 * @author 黄中正
 *
 */
public class Voice {
	//通过素材管理接口上传多媒体文件,得到的ID
	private String MediaId;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	
}
