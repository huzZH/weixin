package com.wechat.bean;

public class User {
	
	//是否订阅公众号
	private Integer subscribe; 
	//用户的标识，同一公众号标识唯一
	private String openid;
	//用户的昵称
	private String nickname;
	//用户性别，值为1是男性，值为2为女性，值为0未知
	private String sex;
	//所在城市
	private String city;
	//所在国家
	private String country;
	//所在省份
	private String province;
	//用户的语言
	private String language;
	//用户头像
	private String headimgurl;
	//用户关注时间
	private String subscribe_time;
	//多个微信公众号或应用之间的唯一标识
	private String unionid;
	//公众号运营者对粉丝的备注
	private String remark;
	//用户所在的分组ID
	private String groupid;
	public Integer getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(Integer subscribe) {
		this.subscribe = subscribe;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getSubscribe_time() {
		return subscribe_time;
	}
	public void setSubscribe_time(String subscribe_time) {
		this.subscribe_time = subscribe_time;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	@Override
	public String toString() {
		return "User [subscribe=" + subscribe + ", openid=" + openid + ", nickname=" + nickname + ", sex=" + sex
				+ ", city=" + city + ", country=" + country + ", province=" + province + ", language=" + language
				+ ", headimgurl=" + headimgurl + ", subscribe_time=" + subscribe_time + ", unionid=" + unionid
				+ ", remark=" + remark + ", groupid=" + groupid + "]";
	}
	
}
