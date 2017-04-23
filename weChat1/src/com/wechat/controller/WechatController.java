package com.wechat.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wechat.Message;
import com.wechat.common.JSSDK_Config;

/**
 * 
 * @author 黄中正
 *
 */
@Controller
public class WechatController {
	
	private static Logger logger = Logger.getLogger(WechatController.class);
	@RequestMapping("/index")
	public String index() {
		System.out.println("进入controller层");
		return "index";
	}
	
	@RequestMapping("/jssdk")
	public Message jssdk_Config(@RequestParam(value = "url",required = true) String url) {
		System.out.println("URL地址： " + url);
		try {
			Map<String, String> jssdk = JSSDK_Config.chcek_Sign(url);
			return Message.success(jssdk);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e,e);
			return Message.error();
		}
		 
	}
}
