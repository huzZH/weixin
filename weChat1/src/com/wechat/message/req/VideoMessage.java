package com.wechat.message.req;
/**
 * ClassName VideoMessage
 * @Description 视频消息类
 * @author 黄中正
 *
 */
public class VideoMessage {
	//视频消息媒体ID,可以调用多媒体文件下载接口拉取数据
	private String MediaId;
	//视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据
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
