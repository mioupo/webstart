package com.xa3ti.webstart.base.util;

public interface XaDbStatus {

	/**正常*/
	int DB_STATUS_NOMAL=0;
	
	/**发布*/
	int DB_STATUS_PUBLISH=1;
	
	/**删除*/
	int DB_STATUS_DELETE=3;

    //草稿
	int DB_STATUS_DRAFT=2;

	/**注銷*/
//	int DB_STATUS_INVALID=2;
//
//	/**待审核*/
//	int DB_STATUS_AUDIT=3;
//
//	/**通过审核*/
//	int DB_STATUS_PASS=4;
//
//	/**未通过*/
//	int DB_STATUS_NOT_PASS=5;
}
