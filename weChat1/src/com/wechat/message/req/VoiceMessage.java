package com.wechat.message.req;
/**
 * @Description ������Ϣ��
 * @author ������
 *
 */
public class VoiceMessage {
	private String MediaId; //ý��Id
	private String Format;  //������ʽ
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
