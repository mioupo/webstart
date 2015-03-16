package com.xa3ti.webstart.base.constant;

/**
 * @Title: XaConstant.java
 * @Package com.xa3ti.shengmilu.base.constant
 * @Description: 常量类
 * @author hchen
 * @date 2014年8月2日 上午10:07:48
 * @version V1.0
 */
public class XaConstant {

	/**
	 * @ClassName: UserStatus
	 * @Description: 后台用户状态常量
	 * @author hchen
	 * @date 2014年8月2日 上午10:10:09
	 */
	public static class UserStatus {
		/**
		 * @Fields status_normal : 正常
		 */
		public static final int status_normal = 1;
		/**
		 * @Fields status_lock : 锁定或禁用
		 */
		public static final int status_lock = 0;
		/**
		 * @Fields status_delete : 已删除
		 */
		public static final int status_delete = 9;

	}

	/**
	 * @ClassName: RoleStatus
	 * @Description: 角色的状态
	 * @author hchen
	 * @date 2014年8月2日 上午10:53:08
	 *
	 */
	public static class RoleStatus {
		/**
		 * @Fields status_normal : 正常
		 */
		public static final int status_normal = 1;
		/**
		 * @Fields status_delete : 已删除
		 */
		public static final int status_delete = 0;
	}

	/**
	 * @ClassName: ResourcesStatus
	 * @Description: 资源的状态
	 * @author hchen
	 * @date 2014年8月2日 上午11:14:46
	 *
	 */
	public static class ResourcesStatus {
		/**
		 * @Fields status_normal : 正常
		 */
		public static final int status_normal = 1;
		/**
		 * @Fields status_delete : 已删除
		 */
		public static final int status_delete = 0;
	}

	/**
	 * @ClassName: ResourceShowType
	 * @Description: 资源级别
	 * @author hchen
	 * @date 2014年8月8日 下午2:23:32
	 *
	 */
	public static class ResourceShowType {
		/**
		 * @Fields page_level : 页面级资源
		 */
		public static final int page_level = 0;
		/**
		 * @Fields menu_level : 菜单级资源
		 */
		public static final int menu_level = 2;
		/**
		 * @Fields button_level : 按钮级资源
		 */
		public static final int button_level = 1;
	}

	public static class SessionKey {

		/**
		 * @Fields currentUser : session中存放和获取当前用户信息的key
		 */
		public static final String currentUser = "currentUser";

		/**
		 * @Fields currentMenuData : session中存放和获取当前用户菜单的key
		 */
		public static final String currentMenuData = "currentMenuData";

		/**
		 * @Fields currentMessageList : 发消息的队列
		 */
		public static final String currentMessageList = "messageUserList";
	}

	/**
	 * @ClassName: Status
	 * @Description: 常用状态类型
	 * @author hchen
	 * @date 2014年8月13日 下午2:45:25
	 *
	 */
	public static class Status {
		/**
		 * @Fields locked : 锁定状态
		 */
		public static final int locked = -1;

		/**
		 * @Fields invalid : 删除/无效状态
		 */
		public static final int invalid = 0;

		/**
		 * @Fields valid : 有效/正常状态
		 */
		public static final int valid = 1;

		/**
		 * @Fields publish : 发布
		 */
		public static final int publish = 2;

		/**
		 * @Fields publish : 删除
		 */
		public static final int delete = 3;
	}

	/**
	 * @ClassName: TreeNodeIcon
	 * @Description: 节点类型
	 * @author hchen
	 * @date 2014年8月13日 下午1:59:01
	 *
	 */
	public static class TreeNodeIcon {
		/**
		 * @Fields html_24 : 24*24的页面级菜单图片
		 */
		public static final String html_24 = "image/html_24.png";
		/**
		 * @Fields menu_24 : 24*24的菜单级菜单图片
		 */
		public static final String menu_24 = "image/menu_24.png";
		/**
		 * @Fields action_24 : 24*24的按钮级菜单图片
		 */
		public static final String action_24 = "image/action_24.png";
	}

	/**
	 * @ClassName: Code
	 * @Description: xaResult的code
	 * @author hchen
	 * @date 2014年8月15日 下午5:34:20
	 *
	 */
	public static final class Code {
		/**
		 * @Fields ok : 成功
		 */
		public static final int success = 1;
		/**
		 * @Fields error : 失败
		 */
		public static final int error = 0;
		/**
		 * token失效
		 */
		public static final int code_failure_token = 101;
		/**
		 * fresh_token失效
		 */
		public static final int code_failure_fresh_token = 102;

		public static final int code_system_failure = 10001;

		public static final int code_business_failure = 20001;

		public static final int code_validation_failure = 30001;

		public static final int code_database_failure = 40001;

		public static final int code_json_failure = 50001;
	}

	/**
	 * @ClassName: Message
	 * @Description: xaResult的message
	 * @author hchen
	 * @date 2014年8月15日 下午5:34:20
	 *
	 */
	public static final class Message {
		/**
		 * @Fields ok : 成功
		 */
		public static final String success = "成功!";
		/**
		 * @Fields error : 失败
		 */
		public static final String error = "系统错误!";
		public static final String object_not_find = "找不到要操作的记录!";
	}
}
