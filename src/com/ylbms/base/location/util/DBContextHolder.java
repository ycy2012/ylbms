package com.ylbms.base.location.util;

public class DBContextHolder {

private static final String TDMIS="tdmisDataSource";
private static final String YLBMS="ylbmsDataSource";

private static ThreadLocal<Object> dataType=new ThreadLocal<Object>();

public static ThreadLocal<Object> getDataType() {
	return dataType;
}
public static void setDataType(ThreadLocal<Object> dataType) {
	DBContextHolder.dataType = dataType;
}

}
