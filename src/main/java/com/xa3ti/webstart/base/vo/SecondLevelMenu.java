package com.xa3ti.webstart.base.vo;
/**
 * @Title: SecondLevelMenu.java
 * @Package com.xa3ti.shengmilu.business.vo
 * @Description: 二级子菜单对象
 * @author hchen
 * @date 2014年8月7日 下午4:58:19
 * @version V1.0
 */
public class SecondLevelMenu {

	private String id;		//对应的资源ID
	private String text;	//显示文字
	private String href;	//链接
	public SecondLevelMenu() {
	}
	public SecondLevelMenu(String id, String text, String href) {
		super();
		this.id = id;
		this.text = text;
		this.href = href;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}

