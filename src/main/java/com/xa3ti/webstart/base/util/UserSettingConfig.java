package com.xa3ti.webstart.base.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 取userSeeting.properties中的属性
 * @author hchen
 *
 */
@Component("userSettingConfig")
public class UserSettingConfig {

	/**
	 * @Fields regirstIntegration : 网站部署的地址 如 www.shengmilu.com
	 */
	@Value("${serverPath}")
	private String serverPath;
	/**
	 * @Fields regirstIntegration : 注册积分
	 */
	@Value("${integration.regist}")
	private int regirstIntegration;
	/**
	 * @Fields postBBSIntegration : 发帖积分
	 */
	@Value("${integration.postbbs}")
	private int postBBSIntegration;
	/**
	 * @Fields replyBBSIntegration : 回复帖子积分
	 */
	@Value("${integration.replybbs}")
	private int replyBBSIntegration;
	/**
	 * @Fields essenceBBSIntegration : 精华帖积分
	 */
	@Value("${integration.essencebbs}")
	private int essenceBBSIntegration;
	/**
	 * @Fields hotBBSIntegration : 热帖积分
	 */
	@Value("${integration.hotbbs}")
	private int hotBBSIntegration;
	/**
	 * @Fields shareIntegration : 分享积分
	 */
	@Value("${integration.share}")
	private int shareIntegration;
	/**
	 * @Fields dianzhanIntegration : 点赞积分
	 */
	@Value("${integration.dianzhan}")
	private int dianzhanIntegration;
	/**
	 * @Fields signInIntegration : 签到积分
	 */
	@Value("${integration.signIn}")
	private int signInIntegration;
	/**
	 * @Fields collectIntegration : 收藏积分
	 */
	@Value("${integration.collect}")
	private int collectIntegration;
	
	
	/**
	 * @Fields qrcodeSize : 二维码大小
	 */
	@Value("${qrcodeSize}")
	private int qrcodeSize;
	
	@Value("${qrcodePath}")
	private String qrcodePath;
	
	/**
	 * @Fields picture_path : 文件上传路径
	 */
	@Value("${picture.path}")
	private String picture_path;		
	
	
	// smtp服务器
	@Value("${mail.hostName}")
	private String hostName ;
	// 帐号与密码
	@Value("${mail.userName}")
	private String userName;
	@Value("${mail.password}")
	private String password;
	// 发件人
	@Value("${mail.fromAddress}")
	private String fromAddress;
	
	
	/**
	 * @Fields industry : 厂家搜索，行业关键字
	 */
	@Value("${searchValVo.industry}")
	private String industry;
	/**
	 * @Fields industry : 厂家搜索，产品关键字
	 */
	@Value("${searchValVo.product}")
	private String product;
	/**
	 * @Fields industry : 厂家搜索，省区关键字
	 */
	@Value("${searchValVo.province}")
	private String province;
	/**
	 * @Fields industry : 厂家搜索，公司名称键字
	 */
	@Value("${searchValVo.companyName}")
	private String companyName;
	
	public String getHostName() {
		return hostName;
	}
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
	public String getFromAddress() {
		return fromAddress;
	}
	public int getQrcodeSize() {
		return qrcodeSize;
	}
	public int getRegirstIntegration() {
		return regirstIntegration;
	}
	public int getPostBBSIntegration() {
		return postBBSIntegration;
	}
	public int getReplyBBSIntegration() {
		return replyBBSIntegration;
	}
	public int getEssenceBBSIntegration() {
		return essenceBBSIntegration;
	}
	public int getHotBBSIntegration() {
		return hotBBSIntegration;
	}
	public int getShareIntegration() {
		return shareIntegration;
	}
	public int getDianzhanIntegration() {
		return dianzhanIntegration;
	}
	public int getSignInIntegration() {
		return signInIntegration;
	}
	public int getCollectIntegration() {
		return collectIntegration;
	}
	public String getPicture_path() {
		return picture_path;
	}
	public String getQrcodePath() {
		return qrcodePath;
	}
	public String getIndustry() {
		return industry;
	}
	public String getProduct() {
		return product;
	}
	public String getProvince() {
		return province;
	}
	public String getCompanyName() {
		return companyName;
	}
	public String getServerPath() {
		return serverPath;
	}
	
}
