package com.xa3ti.webstart.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * 
 * @ClassName: Property
 * @Description: 模型属性定义表
 * @author 曹文波
 * @date 2014年10月11日 上午10:47:49
 *
 */
@Entity
@Table(name = "tb_xa_property")
public class Property {
	
	/*
	 * ID
	 */
	private Long id;
	
	/*
	 * 名称-中文名称
	 */
	private String name;
	/*
	 * 属性标识-变量名、属性名
	 */
	private String identify;
	/*
	 * 属性描述
	 */
	private String description;
	/*
	 * 属性类型，如String ,Integer,Object,TextMedia
	 */
	private String type;
	/*
	 * 属性状态，0为正常，1为删除
	 */
	private Integer status;
	/*
	 * 版本,hibernate维护
	 */
	private Integer version;
	
	/*
	 * 是否可为空 1为true,0为false
	 */
	private Integer nullAble;
	
	/*
	 * 长度-针对String类型
	 */
	private Integer length;
	

//	/**
//	 * @Fields model : 实体类
//	 */
	private Long modelId;
//	private Model model;
	
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

	public Property(String name, String identify, String description,
			String type, Integer nullAble, Integer length, Long modelId) {
		super();
		this.name = name;
		this.identify = identify;
		this.description = description;
		this.type = type;
		this.nullAble = nullAble;
		this.length = length;
		this.modelId = modelId;
	}

	public Property(Long id, String name, String identify, String description,
			String type, Integer nullAble, Integer length, Long modelId) {
		super();
		this.id = id;
		this.name = name;
		this.identify = identify;
		this.description = description;
		this.type = type;
		this.nullAble = nullAble;
		this.length = length;
		this.modelId = modelId;
	}

	public Property() {
		super();
		// TODO Auto-generated constructor stub
	}

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

	@Column(nullable=false,length=50)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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



	@Column(nullable=false)
	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}
	@Column(nullable=false)
	public Integer getNullAble() {
		return nullAble;
	}

	public void setNullAble(Integer nullAble) {
		this.nullAble = nullAble;
	}

	//@Column(name="model_id")
	public Long getModelId() {
		return modelId;
	}

	public void setModelId(Long modelId) {
		this.modelId = modelId;
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
	@Column(name="modifyDescription",length=500)
	public String getModifyDescription() {
		return modifyDescription;
	}
	public void setModifyDescription(String modifyDescription) {
		this.modifyDescription = modifyDescription;
	}
	
}
