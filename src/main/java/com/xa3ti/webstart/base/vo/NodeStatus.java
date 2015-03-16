package com.xa3ti.webstart.base.vo;
/**
 * @Title: NodeStatus.java
 * @Package com.xa3ti.shengmilu.business.vo
 * @Description: 节点状态
 * @author hchen
 * @date 2014年8月2日 下午12:29:43
 * @version V1.0
 */
public class NodeStatus {

	private boolean opened=false;		//true表示展开，false表示折叠,默认为false
	private boolean selected=false;	//是否选中，true表示勾选复选框，false表示未勾选,默认为false
	private boolean disabled=false;	//是否禁用，true表示禁用，false表示正常
	
	public NodeStatus() {
		super();
	}
	
	public NodeStatus(boolean opened, boolean selected, boolean disabled) {
		super();
		this.opened = opened;
		this.selected = selected;
		this.disabled = disabled;
	}

	public boolean isOpened() {
		return opened;
	}
	public void setOpened(boolean opened) {
		this.opened = opened;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	
	
}

