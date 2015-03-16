package com.xa3ti.webstart.business.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * 
* @ClassName: Model 
* @Description: 模型定义表
* @author 曹文波
* @date 2014年10月11日 上午10:47:49 
*
 */
@Entity
@Table(name = "tb_xa_model")
public class Model {

	/*
	 * ID
	 */
	private Long id;
	/*
	 * 名称-中文名称
	 */
	private String name;
	/*
	 * 模型标识-类名
	 */
	private String identify;
	
	/*
	 * 描述
	 */
	private String description;
	/*
	 * 属性
	 */
	private List<Property> properties=new ArrayList<Property>();
	/*
	 * 状态，0为正常，1为删除
	 */
	private Integer status;
	/*
	 * 版本,hibernate维护
	 */
	private Integer version;
	
	
	/*
	 * 
	 * 项目ID
	 */
	private Long projectId;
	

	/**
	 * @Fields createUser : 创建者
	 */
	private Long createUser;
	/**
	 * @Fields createTime : 创建时间
	 */
	private String createTime;
	/**
	 * @Fields modifyUser : 修改者
	 */
	private Long modifyUser;
	/**
	 * @Fields modifyTime : 修改时间
	 */
	private String modifyTime;
	/**
	 * @Fields modifyDescription : 修改描述
	 */
	private String modifyDescription;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(nullable=false,length=50)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(nullable=false,length=50)
	public String getIdentify() {
		return identify;
	}
	public void setIdentify(String identify) {
		this.identify = identify;
	}
	
	@Column(nullable=false,length=100)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	//mappedBy="model"表示由property表中的model属性维护
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)//级联保存、更新、删除、刷新;延迟加载
	@JoinColumn(name="modelId")
	@OrderBy("id")
	public List<Property> getProperties() {
		return properties;
	}
	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}
	
	@Column(nullable=false)
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Version
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	
	@Column(name="createUser")
	public Long getCreateUser() {
		return createUser;
	}
	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}
	@Column(name="createTime")
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Column(name="modifyUser")
	public Long getModifyUser() {
		return modifyUser;
	}
	public void setModifyUser(Long modifyUser) {
		this.modifyUser = modifyUser;
	}
	@Column(name="modifyTime")
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	@Column(name="modifyDescription" ,length=500)
	public String getModifyDescription() {
		return modifyDescription;
	}
	public void setModifyDescription(String modifyDescription) {
		this.modifyDescription = modifyDescription;
	}
	
}
