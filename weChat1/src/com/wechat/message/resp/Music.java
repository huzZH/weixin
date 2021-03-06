package com.wechat.message.resp;
/**
 * ClassName MusicMessage
 * @Description 返回音乐消息类
 * @author 黄中正
 *
 */
public class Music {
	//音乐标题
	private String Title;
	//音乐描述
	private String Description;
	//音乐链接
	private String MusicURL;
	//高质量音乐链接，WIFI环境优先使用该链接播放音乐
	private String HQMusicUrl;
	//缩略图的媒体id,通过素材管理接口上传多媒体文件,得到的id
	private String ThumbMediaId;
}
