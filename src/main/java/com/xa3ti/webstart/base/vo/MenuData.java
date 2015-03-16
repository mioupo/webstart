package com.xa3ti.webstart.base.vo;

import java.util.List;

/**
 * @Title: MenuData.java
 * @Package com.xa3ti.shengmilu.business.vo
 * @Description: 菜单对象
 * @author hchen
 * @date 2014年8月7日 下午4:53:38
 * @version V1.0
 */
public class MenuData {

	private String title;	//菜单标题
	private List<FirstLevelMenu> items;	//一级菜单对象
	public MenuData() {
	}
	public MenuData(String title, List<FirstLevelMenu> items) {
		super();
		this.title = title;
		this.items = items;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<FirstLevelMenu> getItems() {
		return items;
	}
	public void setItems(List<FirstLevelMenu> items) {
		this.items = items;
	}
}

