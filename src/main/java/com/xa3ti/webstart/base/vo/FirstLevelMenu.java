package com.xa3ti.webstart.base.vo;

import java.util.List;

/**
 * @Title: FirstLevelMenu.java
 * @Package com.xa3ti.shengmilu.business.vo
 * @Description: 一级菜单对象
 * @author hchen
 * @date 2014年8月7日 下午4:56:37
 * @version V1.0
 */
public class FirstLevelMenu {

	private String id;		//资源ID
	private String text;	//显示文字
	private String icon;	//显示图标
	private List<SecondLevelMenu> list;	//二级子菜单
	public FirstLevelMenu() {
	}
	public FirstLevelMenu(String id, String text, String icon,
			List<SecondLevelMenu> list) {
		super();
		this.id = id;
		this.text = text;
		this.icon = icon;
		this.list = list;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public List<SecondLevelMenu> getList() {
		return list;
	}
	public void setList(List<SecondLevelMenu> list) {
		this.list = list;
	}
}

