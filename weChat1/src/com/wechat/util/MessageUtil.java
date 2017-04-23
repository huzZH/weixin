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
	 * ������Ϣ���ͣ��ı�
	 */
	public static final String RESP_MESSAGE_TYPE_TEXT = "text";
	/**
	 * ������Ϣ���ͣ�
	 */
	public static final String RESP_MESSAGE_TYPE_IMAGE = "image";
	/**
	 * ������Ϣ���ͣ�
	 */
	public static final String RESP_MESSAGE_TYPE_VOICE = "voice";
	/**
	 * ������Ϣ���ͣ�
	 */
	public static final String RESP_MESSAGE_TYPE_VIDEO = "video";
	/**
	 * ������Ϣ���ͣ�����
	 */
	public static final String RESP_MESSAGE_TYPE_MUSIC = "music";
	/**
	 * ������Ϣ���ͣ�ͼ��
	 */
	public static final String RESP_MESSAGE_TYPE_NEWS = "news";
	
	
	
	
	
	/**
	 * ������Ϣ���ͣ��ı�
	 */
	public static final String REQ_MESSAGE_TYPE_TEXT = "text";
	/**
	 * ������Ϣ���ͣ�ͼƬ
	 */
	public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
	/**
	 * ������Ϣ���ͣ�����
	 */
	public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
	/**
	 * ������Ϣ���ͣ���Ƶ
	 */
	public static final String REQ_MESSAGE_TYPE_VIDEO = "video";
	/**
	 * ������Ϣ���ͣ�С��Ƶ
	 */
	public static final String REQ_MESSAGE_TYPE_SHORTVIDEO = "shortvideo";
	/**
	 * ������Ϣ���ͣ�
	 */
	public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
	/**
	 * ������Ϣ���ͣ�
	 */
	public static final String REQ_MESSAGE_TYPE_LINK = "link";
	
	
	
	
	/**
	 * ������Ϣ���ͣ�����
	 */
	public static final String REQ_MESSAGE_TYPE_EVENT = "event";
	/**
	 * ������Ϣ���ͣ�����
	 */
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
	/**
	 * ������Ϣ���ͣ�����
	 */
	public static final String EVENT_TYPE_UNSUBSCRIBE = "subscribe";
	/**
	 * �����¼����ͣ�δ��עɨ���ά���¼�
	 */
	public static final String EVENT_TYPE_SCAN = "SCAN";
	/**
	 * �����¼����ͣ��ϱ�����λ���¼�
	 */
	public static final String EVENT_TYPE_LOCATION = "LOCATION";
	/**
	 * �����¼����ͣ��Զ���˵�����¼�
	 */
	public static final String EVENT_TYPE_CLICK = "CLICK";
	/**
	 * �����¼����ͣ��Զ���˵�������ת�¼�
	 */
	public static final String EVENT_TYPE_VIEW = "VIEW";
	
	
	/**
	 * @Description ��xml������map��
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
	 * ����xml�Ĵ���(��Ϊ�û����ܻ����� < ,/, >,��֮����ַ��������xml��ʽ����,�����Ҫ���CDATA,CDATA�е����ݻᱻ���������ԣ��Ӷ���Ӱ��)
	 */
	private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // ������xml�ڵ��ת��������CDATA���
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
	 * @Description �ı���Ϣ����ת��xml
	 * @param textMessage
	 * @return
	 */
	public static String textMessageToXml(TextMessage textMessage) {
		XStream xs = new XStream();
		xs.alias("xml", textMessage.getClass());
		return xs.toXML(textMessage);
	}
	/**
	 * @Description ͼ����Ϣ����ת��xml
	 * @param newsMessage
	 * @return
	 */
	public static String newsMessageToXml(NewsMessage newsMessage) {
		xstream.alias("xml", newsMessage.getClass());
		xstream.alias("item", new Article().getClass());
		return xstream.toXML(newsMessage);
	}
	/**
	 * @Description ͼƬ����ת��xml
	 * @param imageMessage
	 * @return
	 */
	public static String imageMessageToXml(ImageMessage imageMessage) {
		xstream.alias("xml", imageMessage.getClass());
		return xstream.toXML(imageMessage);
	}
	/**
	 * @Description  ���ֶ���ת��xml
	 * @param musicMessage
	 * @return
	 */
	public static String musicMessageToXml(MusicMessage musicMessage) {
		xstream.alias("xml", musicMessage.getClass());
		return xstream.toXML(musicMessage);
	}
	/**
	 * @Description ��Ƶ��Ϣ����ת��xml
	 * @param videoMessage
	 * @return
	 */
	public static String videoMessageToXml(VideoMessage videoMessage) {
		xstream.alias("xml", videoMessage.getClass());
		return xstream.toXML(videoMessage);
	}
	/**
	 * @Description ������Ϣ����ת��xml
	 * @param voiceMessage
	 * @return
	 */
	public static String voiceMessageToXml(VoiceMessage voiceMessage) {
		xstream.alias("xml", voiceMessage.getClass());
		return xstream.toXML(voiceMessage);
	}
}
