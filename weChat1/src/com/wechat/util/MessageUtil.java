package com.wechat.util;

import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.wechat.message.req.VoiceMessage;
import com.wechat.message.resp.Article;
import com.wechat.message.resp.ImageMessage;
import com.wechat.message.resp.MusicMessage;
import com.wechat.message.resp.NewsMessage;
import com.wechat.message.resp.TextMessage;
import com.wechat.message.resp.VideoMessage;

public class MessageUtil {
	
	/**
	 * 返回消息类型：文本
	 */
	public static final String RESP_MESSAGE_TYPE_TEXT = "text";
	/**
	 * 返回消息类型：
	 */
	public static final String RESP_MESSAGE_TYPE_IMAGE = "image";
	/**
	 * 返回消息类型：
	 */
	public static final String RESP_MESSAGE_TYPE_VOICE = "voice";
	/**
	 * 返回消息类型：
	 */
	public static final String RESP_MESSAGE_TYPE_VIDEO = "video";
	/**
	 * 返回消息类型：音乐
	 */
	public static final String RESP_MESSAGE_TYPE_MUSIC = "music";
	/**
	 * 返回消息类型：图文
	 */
	public static final String RESP_MESSAGE_TYPE_NEWS = "news";
	
	
	
	
	
	/**
	 * 请求消息类型：文本
	 */
	public static final String REQ_MESSAGE_TYPE_TEXT = "text";
	/**
	 * 请求消息类型：图片
	 */
	public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
	/**
	 * 请求消息类型：语音
	 */
	public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
	/**
	 * 请求消息类型：视频
	 */
	public static final String REQ_MESSAGE_TYPE_VIDEO = "video";
	/**
	 * 请求消息类型：小视频
	 */
	public static final String REQ_MESSAGE_TYPE_SHORTVIDEO = "shortvideo";
	/**
	 * 请求消息类型：
	 */
	public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
	/**
	 * 请求消息类型：
	 */
	public static final String REQ_MESSAGE_TYPE_LINK = "link";
	
	
	
	
	/**
	 * 请求消息类型：推送
	 */
	public static final String REQ_MESSAGE_TYPE_EVENT = "event";
	/**
	 * 请求消息类型：订阅
	 */
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
	/**
	 * 请求消息类型：推送
	 */
	public static final String EVENT_TYPE_UNSUBSCRIBE = "subscribe";
	/**
	 * 请求事件类型：未关注扫描二维码事件
	 */
	public static final String EVENT_TYPE_SCAN = "SCAN";
	/**
	 * 请求事件类型：上报地理位置事件
	 */
	public static final String EVENT_TYPE_LOCATION = "LOCATION";
	/**
	 * 请求事件类型：自定义菜单点击事件
	 */
	public static final String EVENT_TYPE_CLICK = "CLICK";
	/**
	 * 请求事件类型：自定义菜单链接跳转事件
	 */
	public static final String EVENT_TYPE_VIEW = "VIEW";
	
	
	/**
	 * @Description 将xml解析到map中
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> parseXmlToMap(HttpServletRequest request) throws Exception {
		
		Map<String, String> map = new HashMap<String, String>();
		InputStream inputStream = request.getInputStream();
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		Element root = document.getRootElement();
		@SuppressWarnings("unchecked")
		List<Element> elements = root.elements();
		for(Element el : elements) {
			map.put(el.getName(), el.getText());
		}
		inputStream.close();
		inputStream = null;
		return map;
	}
	/**
	 * 对象到xml的处理(因为用户可能或输入 < ,/, >,等之类的字符，会造成xml格式错误,因此需要添加CDATA,CDATA中的内容会被解析器忽略，从而无影响)
	 */
	private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;
 
                @SuppressWarnings("rawtypes")
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }
 
                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });
	
	/**
	 * @Description 文本消息对象转换xml
	 * @param textMessage
	 * @return
	 */
	public static String textMessageToXml(TextMessage textMessage) {
		XStream xs = new XStream();
		xs.alias("xml", textMessage.getClass());
		return xs.toXML(textMessage);
	}
	/**
	 * @Description 图文消息对象转换xml
	 * @param newsMessage
	 * @return
	 */
	public static String newsMessageToXml(NewsMessage newsMessage) {
		xstream.alias("xml", newsMessage.getClass());
		xstream.alias("item", new Article().getClass());
		return xstream.toXML(newsMessage);
	}
	/**
	 * @Description 图片对象转换xml
	 * @param imageMessage
	 * @return
	 */
	public static String imageMessageToXml(ImageMessage imageMessage) {
		xstream.alias("xml", imageMessage.getClass());
		return xstream.toXML(imageMessage);
	}
	/**
	 * @Description  音乐对象转换xml
	 * @param musicMessage
	 * @return
	 */
	public static String musicMessageToXml(MusicMessage musicMessage) {
		xstream.alias("xml", musicMessage.getClass());
		return xstream.toXML(musicMessage);
	}
	/**
	 * @Description 视频消息对象转换xml
	 * @param videoMessage
	 * @return
	 */
	public static String videoMessageToXml(VideoMessage videoMessage) {
		xstream.alias("xml", videoMessage.getClass());
		return xstream.toXML(videoMessage);
	}
	/**
	 * @Description 语音消息对象转换xml
	 * @param voiceMessage
	 * @return
	 */
	public static String voiceMessageToXml(VoiceMessage voiceMessage) {
		xstream.alias("xml", voiceMessage.getClass());
		return xstream.toXML(voiceMessage);
	}
}
