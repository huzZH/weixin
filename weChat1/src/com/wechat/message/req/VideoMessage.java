package com.wechat.message.req;
/**
 * ClassName VideoMessage
 * @Description ��Ƶ��Ϣ��
 * @author ������
 *
 */
public class VideoMessage {
	//��Ƶ��Ϣý��ID,���Ե��ö�ý���ļ����ؽӿ���ȡ����
	private String MediaId;
	//��Ƶ��Ϣ����ͼ��ý��id�����Ե��ö�ý���ļ����ؽӿ���ȡ����
	private String ThumbMediaId;
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getThumbMediaId() {
		return ThumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
	
}
