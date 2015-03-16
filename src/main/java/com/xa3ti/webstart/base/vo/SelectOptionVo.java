package com.xa3ti.webstart.base.vo;
/**
 * @Title: SelectOptionVo.java
 * @Package com.xa3ti.shengmilu.business.vo
 * @Description: 下拉框选中项
 * @author hchen
 * @date 2014年8月5日 上午9:48:57
 * @version V1.0
 */
public class SelectOptionVo {

	private Object selectedId;		//optionItem中选中项的ID，如果没有选中项，该值可以为空""	
	private Object optionItem;		//下拉框的选中项
	public SelectOptionVo() {
		super();
	}
	public SelectOptionVo(String selectedId, Object optionItem) {
		super();
		this.selectedId = selectedId;
		this.optionItem = optionItem;
	}
	public Object getSelectedId() {
		return selectedId;
	}
	public void setSelectedId(Object selectedId) {
		this.selectedId = selectedId;
	}
	public Object getOptionItem() {
		return optionItem;
	}
	public void setOptionItem(Object optionItem) {
		this.optionItem = optionItem;
	}
	
}

