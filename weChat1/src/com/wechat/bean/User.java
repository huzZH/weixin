package com.wechat.bean;

public class User {
	
	//�Ƿ��Ĺ��ں�
	private Integer subscribe; 
	//�û��ı�ʶ��ͬһ���ںű�ʶΨһ
	private String openid;
	//�û����ǳ�
	private String nickname;
	//�û��Ա�ֵΪ1�����ԣ�ֵΪ2ΪŮ�ԣ�ֵΪ0δ֪
	private String sex;
	//���ڳ���
	private String city;
	//���ڹ���
	private String country;
	//����ʡ��
	private String province;
	//�û�������
	private String language;
	//�û�ͷ��
	private String headimgurl;
	//�û���עʱ��
	private String subscribe_time;
	//���΢�Ź��ںŻ�Ӧ��֮���Ψһ��ʶ
	private String unionid;
	//���ں���Ӫ�߶Է�˿�ı�ע
	private String remark;
	//�û����ڵķ���ID
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
