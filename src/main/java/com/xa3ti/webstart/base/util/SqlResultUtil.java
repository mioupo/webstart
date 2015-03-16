package com.xa3ti.webstart.base.util;

import java.util.Date;

public class SqlResultUtil {
	public static String getSqlResultString(Object o){
		return String.valueOf(o);
	}
	
	public static Long getSqlResultLong(Object o){
		if(getSqlResultString(o) != null){
			try{
				return Long.parseLong(getSqlResultString(o));
			}
			catch(Exception e){
				return null;
			}
		}
		return null;
	}
	
	public static Integer getSqlResultInteger(Object o){
		if(getSqlResultString(o) != null){
			try{
				return Integer.parseInt(getSqlResultString(o));
			}
			catch(Exception e){
				return null;
			}
		}
		return null;
	}
	
	public static Double getSqlResultDouble(Object o){
		if(getSqlResultString(o) != null){
			try{
				return Double.parseDouble(getSqlResultString(o));
			}
			catch(Exception e){
				return null;
			}
		}
		return null;
	}
	
	public static Date getSqlResultDateLongToDate(Object o){
		if(getSqlResultLong(o) != null){
			try{
				return new Date(getSqlResultLong(o) * 1000);
			}
			catch(Exception e){
				return null;
			}
		}
		return null;
	}
	
//	public static Date getSqlResultDateStringToDate(Object o){
//		if(getSqlResultString(o) != null){
//			try{
//				if(getSqlResultString(o).length() >= 19){
//					return CommonUtils.getDate(getSqlResultString(o).substring(0,19));
//				}
//				else if(getSqlResultString(o).length() >= 10){
//					return CommonUtils.getDate(getSqlResultString(o).substring(0,10));
//				}
//			}
//			catch(Exception e){
//				return null;
//			}
//		}
//		return null;
//	}
	
	public static String getSqlResultDateStringToString(Object o){
		if(getSqlResultString(o) != null){
			try{
				if(getSqlResultString(o).length() >= 19){
					return getSqlResultString(o).substring(0,19);
				}
				else if(getSqlResultString(o).length() >= 10){
					return getSqlResultString(o).substring(0,10);
				}
			}
			catch(Exception e){
				return null;
			}
		}
		return null;
	}
	
	
}
