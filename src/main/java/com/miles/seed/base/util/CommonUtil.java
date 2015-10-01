package com.gm.trade.base.util;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.gm.trade.base.exception.GMSOAException;
import com.gm.trade.base.exception.GMSOAExceptionCode;
import com.gm.trade.base.exception.GMSOAExceptionType;

/**
 * @version:	  
 */
public class CommonUtil{
	
	public static int getMaxPage(int total,int pageSize){
		return total/pageSize+((total%pageSize)>0?1:0);
	}
	
	
	public static boolean isLongValid(Long value) {
		boolean result = false;
		if(value != null && value > 0L) {
			result = true;
		}
		return result;
	}
	
	public static boolean isListValid(List<?> dataList) {
		boolean result = false;
		if(dataList != null && !dataList.isEmpty()) {
			result = true;
		}
		return result;
	}
	
	public static void validateParam(Object obj, String[] ignore){
		
		if (obj == null) {
			throw new GMSOAException(GMSOAExceptionCode.BUNIESS_EXCEPTION, GMSOAExceptionType.BUSINESS, "param is null");
		}
		
		List<String> ignoreList = Arrays.asList(ignore);
		
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			String propertyString = field.toString().substring(
					field.toString().lastIndexOf(".")+1);
			if (ignoreList.contains(propertyString) == true) {
				continue;
			}
			field.setAccessible(true);
			try {
				Object property = field.get(obj);
				field.setAccessible(false);
				
				if (property == null) {
					String errorMsg = propertyString + " is null";
					throw new GMSOAException(GMSOAExceptionCode.BUNIESS_EXCEPTION, GMSOAExceptionType.BUSINESS, errorMsg);
				}
			} catch (IllegalArgumentException e) {
				field.setAccessible(false);
				throw new GMSOAException(GMSOAExceptionCode.SYSTEM_EXCEPTION, GMSOAExceptionType.SYSTEM, e.getMessage());
			} catch (IllegalAccessException e) {
				field.setAccessible(false);
				throw new GMSOAException(GMSOAExceptionCode.SYSTEM_EXCEPTION, GMSOAExceptionType.SYSTEM, e.getMessage());
			}
		}
	}
	
	public static boolean isBigDecimalValid(BigDecimal value){
		boolean result = false;
		if (value != null && value.compareTo(BigDecimal.ZERO) > 0) {
			result = true;
		}
		return result;
	}
	
	public static Long[] intersect(Long[] a, Long[] b) {
		int index = 0;
		Long[] returnValue = new Long[Math.max(a.length, b.length)];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b.length; j++) {
				if (a[i].longValue() == b[j].longValue()) {
					returnValue[index] = a[i];
					index++;
				}
			}
		}
		Long[] copy = new Long[index];
		System.arraycopy(returnValue, 0, copy, 0, index);
		return copy;
	}
	
	public static boolean isIntegerValid(Integer value) {
		boolean result = false;
		if(value != null && value > 0) {
			result = true;
		}
		return result;
	}

	public static boolean isSetValid(Set<?> dataSet) {
		boolean result = false;
		if(dataSet != null && !dataSet.isEmpty()) {
			result = true;
		}
		return result;
	}
	
	public static boolean isMapValid(Map<?, ?> dataMap) {
		boolean result = false;
		if(dataMap != null && !dataMap.isEmpty()) {
			result = true;
		}
		return result;
	}
}
