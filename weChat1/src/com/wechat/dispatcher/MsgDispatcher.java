package com.wechat.dispatcher;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wechat.message.resp.Article;
import com.wechat.message.resp.Image;
import com.wechat.message.resp.ImageMessage;
import com.wechat.message.resp.NewsMessage;
import com.wechat.util.MessageUtil;

/**
 * ClassName  MsgDispatcher
 * @Description ��Ϣҵ�������
 * @author ������
 *
 */
public class MsgDispatcher {
	
	public static String processMessage(Map<String, String> map) {
		
		String fromUserName = map.get("FromUserName"); //�û���OpenID
		String toUserName = map.get("ToUserName");     //���ں�ԭʼID
		ImageMessage imgmsg = new ImageMessage();
		imgmsg.setFromUserName(toUserName);
		imgmsg.setToUserName(fromUserName);
		imgmsg.setCreateTime(new Date().getTime());
		imgmsg.setMsgType("image");
		
		if(map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
			System.out.println("...............�ı���Ϣ");
			Image image = new Image();
			image.setMediaId("q6z4TnhTdZ6zBjih27bFIXU7FjVEWok_pZCsETPMbGg");
			imgmsg.setImage(image);
			System.out.println(MessageUtil.imageMessageToXml(imgmsg));
			return MessageUtil.imageMessageToXml(imgmsg);
		}
		if(map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
			System.out.println("...............ͼƬ��Ϣ");
			
			NewsMessage newsMessage = new NewsMessage();
			newsMessage.setFromUserName(map.get("ToUserName"));
			newsMessage.setToUserName(map.get("FromUserName"));
			newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
			newsMessage.setCreateTime(new Date().getTime());
			
			Article article = new Article();
			article.setDescription("��һ��ͼ����Ϣ");
			article.setTitle("ͼ��");
			article.setPicUrl("http://pic.uuhy.com/uploads/2012/01/10/2e9a1d199dedc790a4e7e2cb1d7f8013.jpg");
			article.setUrl("http://hzz.ngrok.cc/weChat1/");
			List<Article> list = new ArrayList<Article>();
			list.add(article);
			newsMessage.setArticleCount(list.size());
			newsMessage.setArticles(list);
			return MessageUtil.newsMessageToXml(newsMessage);
			
		}
		if(map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
			System.out.println("...............������Ϣ");
		}
		if(map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
			System.out.println("...............λ����Ϣ");
		}
		if(map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_SHORTVIDEO)) {
			System.out.println("...............����Ƶ��Ϣ");
		}
		if(map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
			System.out.println("...............������Ϣ");
		}
		if(map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
			System.out.println("...............������Ϣ");
		}
		if(map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
			System.out.println("...............����¼���Ϣ");
		}
		return null;
		
	}
}
