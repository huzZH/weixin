package com.wechat.message.resp;

import java.util.List;

/**
 * ClassName  NewsMessage
 * @Description  多图文消息
 * @author 黄中正
 *
 */
public class NewsMessage extends BaseMessage{
	//图文消息个数
	private Integer ArticleCount;
	// 多条图文消息信息，默认第一个item为大图   
	private List<Article> Articles;
	public Integer getArticleCount() {
		return ArticleCount;
	}
	public void setArticleCount(Integer articleCount) {
		ArticleCount = articleCount;
	}
	public List<Article> getArticles() {
		return Articles;
	}
	public void setArticles(List<Article> articles) {
		Articles = articles;
	}
	
}
