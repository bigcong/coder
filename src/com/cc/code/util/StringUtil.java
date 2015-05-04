package com.cc.code.util;

public class StringUtil {
	/**
	 * 大写
	 * 
	 * @param s
	 * @return
	 */
	public static final String upperFirst(String s) {
		int len = s.length();
		if (len <= 0)
			return "";

		StringBuffer sb = new StringBuffer();
		sb.append(s.substring(0, 1).toUpperCase());
		sb.append(s.substring(1, len));
		return sb.toString();
	}

	/**
	 * 小写
	 * 
	 * @param s
	 * @return
	 */
	public static final String lowerFirst(String s) {
		int len = s.length();
		if (len <= 0)
			return "";

		StringBuffer sb = new StringBuffer();
		sb.append(s.substring(0, 1).toLowerCase());
		sb.append(s.substring(1, len));
		return sb.toString();
	}

	/**
	 * 去掉表名字中间的“_”, 然后根据驼峰规则生成表名
	 * 
	 * @param tableName
	 * @return
	 */
	public static final String newTableName(String tableName) {
		String strS[] = tableName.split("_");
		String newStr = "";
		for (String st : strS) {
			newStr += StringUtil.upperFirst(st);
		}
		return newStr;
	}

	public static void main(String[] args) {
		System.out.println("s".toUpperCase());
	}
}
