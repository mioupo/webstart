package com.xa3ti.webstart.base.util;

import com.wordnik.swagger.annotations.ApiModelProperty;
import com.xa3ti.webstart.base.constant.XaConstant;


/**
 * @Title: XaResult.java
 * @Package com.xa3ti.hhrz.base.entity
 * @Description: Service返回结果统一对象
 * @author hchen
 * @date 2014年8月13日 下午7:46:23
 * @version V1.0
 */
@SuppressWarnings("unchecked")
public class XaResult<T> {

	@ApiModelProperty(value = "code : 返回代码，1表示OK，其它的都有对应问题")
	private int code=XaConstant.Code.success;

	@ApiModelProperty(value = "message : 如果code!=1,错误信息")
	private String message=XaConstant.Message.success;	

	@ApiModelProperty(value = "code为1时返回结果集")
	private T object=(T) new Object();
 
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getObject() {
		return object;
	}
	public void setObject(T object) {
		this.object = object;
	}
	
	public XaResult(String errorMsg){
		this.message = errorMsg;
		this.code = XaConstant.Code.error;
		this.object=(T) new Object();
	}
	
	public XaResult(){
		this.object=(T) new Object();
	}
 
	public void success(T object){
		this.code = XaConstant.Code.success;
		this.message = XaConstant.Message.success;
		this.object = object;
	}
	public void error(){
		this.code = XaConstant.Code.error;
		this.message = XaConstant.Message.error;
		this.object=(T) new Object();
	}
	public void error(String message){
		this.code = XaConstant.Code.error;
		this.message = message;
		this.object=(T) new Object();
	}
}

