package com.wechat.menu;

import java.util.List;

public class SubButton {
	private String name;
	private List<BaseButton> sub_button;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<BaseButton> getSub_button() {
		return sub_button;
	}
	public void setSub_button(List<BaseButton> sub_button) {
		this.sub_button = sub_button;
	}
	
	
	
}
