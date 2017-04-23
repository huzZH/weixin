package com.wechat.menu;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.web.util.GlobalConstants;
import com.wechat.util.HttpUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * @Description 创建菜单
 * @author 黄中正
 *
 */
public class MenuMain {
	
	private static Logger logger = Logger.getLogger(MenuMain.class);
	
	public static void createMuenu() {
		//点击按钮
		ClickButton clickButton1 = new ClickButton();
		clickButton1.setName("Click点击");
		clickButton1.setType(MenuConstants.CLICK);
		clickButton1.setKey("CLICK");
		
		//扫码推事件
		ClickButton clickButton2 = new ClickButton();
		clickButton2.setName("扫码");
		clickButton2.setType(MenuConstants.SCANCODE_PUSH);
		clickButton2.setKey("SCAN");
		
		//拍照事件
		ClickButton clickButton3 = new ClickButton();
		clickButton3.setName("拍照");
		clickButton3.setType(MenuConstants.PIC_SYSPHOTO);
		clickButton3.setKey("PHOTO");
		
		//跳转URL
		ViewButton viewButton = new ViewButton();
		viewButton.setName("view百度");
		viewButton.setType(MenuConstants.VIEW);
		viewButton.setUrl("www.manbuluo.cn");
		
		List<BaseButton> list = new ArrayList<BaseButton>();
		list.add(clickButton1);
		list.add(clickButton2);
		list.add(clickButton3);
		/*list.add(viewButton);*/
		
		SubButton subButton1 = new SubButton();
		subButton1.setName("菜单");
		subButton1.setSub_button(list);
		
		
		SubButton subButton2 = new SubButton();
		subButton2.setName("菜单2");
		subButton2.setSub_button(list);
		
		SubButton subButton3 = new SubButton();
		subButton3.setName("菜单3");
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
			logger.info("返回结果：    " + result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("菜单请求失败！");
			logger.error("菜单请求失败！",e);
		}
		try {
			Double count = HttpUtil.getCount("https://api.weixin.qq.com/cgi-bin/material/get_materialcount?access_token=" + access_token);
			System.out.println(count);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("素材数量请求失败！",e);
		}
	}
	
	
	
}
