package com.wechat.message.resp;
/**
 * ClassName VideoMessage
 * @Description ������Ƶ��Ϣ
 * @author ������
 *
 */
public class Video {
	//ͨ���زĹ���ӿ��ϴ���ý���ļ�,�õ���ID
	private String MediaId;
	//��Ƶ��Ϣ�ı���
	private String Title;
	//��Ƶ��Ϣ������
	private String Description;
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	
}
