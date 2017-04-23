package com.wechat.dispatcher;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.web.util.GlobalConstants;
import com.wechat.bean.User;
import com.wechat.common.UserInfo;
import com.wechat.message.resp.Article;
import com.wechat.message.resp.NewsMessage;
import com.wechat.util.MessageUtil;

/**
 * ClassName EventDispatcher
 * @Description �¼�������
 * @author ������
 *
 */
public class EventDispatcher {

	public static String processEvent(Map<String, String> map) throws Exception {
		/*String openid = map.get("FromUserName"); // �û�openid
		String mpid = map.get("ToUserName"); // ���ں�ԭʼID
		ImageMessage imgmsg = new ImageMessage();
		imgmsg.setToUserName(openid);
		imgmsg.setFromUserName(mpid);
		imgmsg.setCreateTime(new Date().getTime());
		imgmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_IMAGE);*/
		if (map.get("Event").equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) { // ��ע�¼�
		    System.out.println("==============���ǹ�ע�¼���");
		    /*Image img = new Image();
		   
		    File file  = new File("G:\\test1.jpg");
		    String mediaId = HttpPostUploadUtil.upload(file);*/
		    
		   
		 // String mediaid=JSONObject.fromObject(mediaidrs).getString("media_id");
		 // String mediaid = "oF-AnyNAItjzEEldmJM2hpz8zjXVKZGQJnidwi5k3EgvVN1DF6l2p1bLUMhGfc8t";
		   /* System.out.println(mediaId);
		    img.setMediaId(mediaId);
		    imgmsg.setImage(img);
		    return MessageUtil.imageMessageToXml(imgmsg);*/
		    
		    Map<String, String> params = new HashMap<String, String>();
		    params.put("access_token", GlobalConstants.getInterfaceUrl("access_token"));
		    params.put("openid", map.get("FromUserName"));
		    params.put("lang","zh_CN");
		    String regUrl = GlobalConstants.getInterfaceUrl("OpenidUserinfoUrl");
		    User user = UserInfo.getUserInfo(regUrl, params);
		    if(user != null) {
		    	Article article = new Article();
		    	article.setPicUrl(user.getHeadimgurl());
		    	article.setTitle(user.getNickname() + "����ӭ���ĵ�����");
		    	article.setDescription("���ڳɹ�����");
		    	article.setUrl("www,baidu.com");
		    	List<Article> list = new ArrayList<Article>();
		    	list.add(article);
		    	NewsMessage newsMsg = new NewsMessage();
		    	newsMsg.setArticleCount(1);
		    	newsMsg.setArticles(list);
		    	newsMsg.setCreateTime(new Date().getTime());
		    	newsMsg.setFromUserName(map.get("ToUserName"));
		    	newsMsg.setToUserName(map.get("FromUserName"));
		    	newsMsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
		    	return MessageUtil.newsMessageToXml(newsMsg);
		    }
		    
		    
		}
		if(map.get("MsgType").equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
			System.out.println("������������ȡ����ע�¼�");
		}
		if(map.get("MsgType").equals(MessageUtil.EVENT_TYPE_SCAN)) {
			System.out.println("������������ɨ���ά���¼�");
		}
		if(map.get("MsgType").equals(MessageUtil.EVENT_TYPE_CLICK)) {
			System.out.println("�������������Զ���˵�����¼�");
		}
		if(map.get("MsgType").equals(MessageUtil.EVENT_TYPE_LOCATION)) {
			System.out.println("������������λ���ϱ��¼�");
		}
		if(map.get("MsgType").equals(MessageUtil.EVENT_TYPE_VIEW)) {
			System.out.println("�������������Զ���˵�view�¼�");
		}
		return null;
		
	}
}
