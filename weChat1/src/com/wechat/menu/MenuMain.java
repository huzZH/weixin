package com.wechat.menu;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.web.util.GlobalConstants;
import com.wechat.util.HttpUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * @Description �����˵�
 * @author ������
 *
 */
public class MenuMain {
	
	private static Logger logger = Logger.getLogger(MenuMain.class);
	
	public static void createMuenu() {
		//�����ť
		ClickButton clickButton1 = new ClickButton();
		clickButton1.setName("Click���");
		clickButton1.setType(MenuConstants.CLICK);
		clickButton1.setKey("CLICK");
		
		//ɨ�����¼�
		ClickButton clickButton2 = new ClickButton();
		clickButton2.setName("ɨ��");
		clickButton2.setType(MenuConstants.SCANCODE_PUSH);
		clickButton2.setKey("SCAN");
		
		//�����¼�
		ClickButton clickButton3 = new ClickButton();
		clickButton3.setName("����");
		clickButton3.setType(MenuConstants.PIC_SYSPHOTO);
		clickButton3.setKey("PHOTO");
		
		//��תURL
		ViewButton viewButton = new ViewButton();
		viewButton.setName("view�ٶ�");
		viewButton.setType(MenuConstants.VIEW);
		viewButton.setUrl("www.manbuluo.cn");
		
		List<BaseButton> list = new ArrayList<BaseButton>();
		list.add(clickButton1);
		list.add(clickButton2);
		list.add(clickButton3);
		/*list.add(viewButton);*/
		
		SubButton subButton1 = new SubButton();
		subButton1.setName("�˵�");
		subButton1.setSub_button(list);
		
		
		SubButton subButton2 = new SubButton();
		subButton2.setName("�˵�2");
		subButton2.setSub_button(list);
		
		SubButton subButton3 = new SubButton();
		subButton3.setName("�˵�3");
		subButton3.setSub_button(list);
		
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(subButton1);
		jsonArray.add(subButton2);
		jsonArray.add(subButton3);
		
		JSONObject json = new JSONObject();
		json.put("button", jsonArray);
		
		System.out.println(json.toString());
		
		String access_token = GlobalConstants.getInterfaceUrl("access_token");
		String regUrl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + access_token ;
		
		try {
			String result = HttpUtil.sendPostBuffer(regUrl,json.toString());
			logger.info("���ؽ����    " + result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("�˵�����ʧ�ܣ�");
			logger.error("�˵�����ʧ�ܣ�",e);
		}
		try {
			Double count = HttpUtil.getCount("https://api.weixin.qq.com/cgi-bin/material/get_materialcount?access_token=" + access_token);
			System.out.println(count);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("�ز���������ʧ�ܣ�",e);
		}
	}
	
	
	
}
