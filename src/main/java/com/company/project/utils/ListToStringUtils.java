package com.company.project.utils;

import java.util.List;

public class ListToStringUtils
{
	
	/**
	 * 将list 转化为String之后 的 “[]” 去掉
	 * @param list
	 * @return
	 */
	public static <T> String ListToString(List<T> list)
	{
		String str = list.toString();
		int len = str.length();
		return str.substring(1, len -1);
	}
}
