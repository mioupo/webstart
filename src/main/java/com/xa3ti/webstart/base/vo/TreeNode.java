package com.xa3ti.webstart.base.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: TreeNode.java
 * @Package com.xa3ti.shengmilu.business.vo
 * @Description: jsTree插件使用的树对象
 * @author hchen
 * @date 2014年8月2日 下午12:27:50
 * @version V1.0
 */
public class TreeNode {

	private String id;		//节点的ID
	private String text;	//节点显示的内容
	private NodeStatus state;	//节点状态
	private String icon;	//节点图标
	private List<TreeNode> children=new ArrayList<TreeNode>();	//子节点
	public TreeNode() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TreeNode(String id, String text, NodeStatus state, String icon,
			List<TreeNode> children) {
		super();
		this.id = id;
		this.text = text;
		this.state = state;
		this.icon = icon;
		this.children = children;
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
	public NodeStatus getState() {
		return state;
	}
	public void setState(NodeStatus state) {
		this.state = state;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public List<TreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}
	
}

