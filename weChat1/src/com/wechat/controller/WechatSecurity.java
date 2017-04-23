package com.wechat.controller;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.util.GlobalConstants;
import com.wechat.dispatcher.EventDispatcher;
import com.wechat.dispatcher.MsgDispatcher;
import com.wechat.menu.MenuMain;
import com.wechat.util.HttpUtil;
import com.wechat.util.MessageUtil;
import com.wechat.util.SingUtil;

/**
 * 接入微信公众平台
 * @author 黄中正
 *
 */
@Controller
/*@RequestMapping("/wechat")*/
public class WechatSecurity {
	
	private static Logger logger = Logger.getLogger(WechatSecurity.class);
	/**
	 * 服务器验证
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/security", method = RequestMethod.GET)
	public  void doGet(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "signature", required = true) String signature,
            @RequestParam(value = "timestamp", required = true) String timestamp,
            @RequestParam(value = "nonce", required = true) String nonce,
            @RequestParam(value = "echostr", required = true) String echostr) {
		try{
			if(SingUtil.checkSingature(signature, timestamp, nonce)) {
				PrintWriter out = response.getWriter();
				out.print(echostr);
				out.flush();
				out.close();
				
				logger.info("请求成功！");
			}else {
				logger.info("非法请求");
			}
		}catch(Exception e) {
			logger.error(e, e);
		}
	}
	
	@RequestMapping(value="/security", method = RequestMethod.POST)
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("进入post的方法");
/*		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");*/
		response.setCharacterEncoding("UTF-8");
		try {
			Map<String, String> map = MessageUtil.parseXmlToMap(request);
			String msgType = map.get("MsgType");
			if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				String str = EventDispatcher.processEvent(map);
				PrintWriter out = response.getWriter();
				System.out.println(str);
				out.print(str);
				out.close();
			}else {
			//	MenuMain.createMuenu();
				Double count = HttpUtil.getCount("https://api.weixin.qq.com/cgi-bin/material/get_materialcount?access_token=" + GlobalConstants.getInterfaceUrl("access_token"));
				System.out.println(count);
				String str = MsgDispatcher.processMessage(map);
				PrintWriter out = response.getWriter();
				System.out.println(str);
				out.write(str);
				out.close();
			}
			logger.info("Content:   " + map.get("Content"));
		} catch (Exception e) {
			
			logger.error(e,e);
		}
	}
}
