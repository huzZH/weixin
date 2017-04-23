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
 * @Description 事件调度器
 * @author 黄中正
 *
 */
public class EventDispatcher {

	public static String processEvent(Map<String, String> map) throws Exception {
		/*String openid = map.get("FromUserName"); // 用户openid
		String mpid = map.get("ToUserName"); // 公众号原始ID
		ImageMessage imgmsg = new ImageMessage();
		imgmsg.setToUserName(openid);
		imgmsg.setFromUserName(mpid);
		imgmsg.setCreateTime(new Date().getTime());
		imgmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_IMAGE);*/
		if (map.get("Event").equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) { // 关注事件
		    System.out.println("==============这是关注事件！");
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
		    	article.setTitle(user.getNickname() + "：欢迎您的到来！");
		    	article.setDescription("终于成功啦！");
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
			System.out.println("。。。。。。取消关注事件");
		}
		if(map.get("MsgType").equals(MessageUtil.EVENT_TYPE_SCAN)) {
			System.out.println("。。。。。。扫描二维码事件");
		}
		if(map.get("MsgType").equals(MessageUtil.EVENT_TYPE_CLICK)) {
			System.out.println("。。。。。。自定义菜单点击事件");
		}
		if(map.get("MsgType").equals(MessageUtil.EVENT_TYPE_LOCATION)) {
			System.out.println("。。。。。。位置上报事件");
		}
		if(map.get("MsgType").equals(MessageUtil.EVENT_TYPE_VIEW)) {
			System.out.println("。。。。。。自定义菜单view事件");
		}
		return null;
		
	}
}
