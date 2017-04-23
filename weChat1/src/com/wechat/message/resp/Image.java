package com.wechat.message.resp;
/**
 * ClassName Image
 * @Description 返回图片消息类
 * @author 黄中正
 *
 */
public class Image {
	//通过素材管理接口上传多媒体文件,获得的Id
	private String MediaId;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	
}
