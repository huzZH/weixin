package com.wechat.quartz;

import org.apache.log4j.Logger;

import com.wechat.common.WeChatTask;

public class QuartzJob {
	public static Logger logger = Logger.getLogger(QuartzJob.class);
	/**
	 * @Description ����ִ�л�ȡToken
	 */
	public void workForToken() {
		try{
			WeChatTask task = new WeChatTask();
			task.getToken();
		}catch(Exception e) {
			logger.error(e, e);
		}
	}
}
