package com.wechat.message.req;
/**
 * ClassName ImageMessage
 * @Description ͼƬ��Ϣ��
 * @author ������
 *
 */
public class ImageMessage {

	private String PicUrl;   //ͼƬ����
	private String MediaId;  //ͼƬ��Ϣý��id�����Ե��ö�ý���ļ����ؽӿ���ȡ����
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	} 
	
}
