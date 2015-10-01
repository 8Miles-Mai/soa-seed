package com.miles.seed.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.miles.seed.base.exception.GMSOAException;
import com.miles.seed.base.exception.GMSOAExceptionAssert;
import com.miles.seed.base.exception.GMSOAExceptionCode;
import com.miles.seed.base.exception.GMSOAExceptionType;


public abstract class DateUtil {
	
	public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static final String DATE_FORMAT = "yyyy-MM-dd";
	
	/**
	 * 把字符串按照指定的格式转换成日期类型. 转换出错则抛出ErrorCode.EXEC_METHOD_ERROR类型的SoaException
	 * @param dateString 日期字符串, 非空
	 * @param format 日期字格式, 非空
	 * @return 日期
	 */
	public static Date parseDate(String dateString, String format){
		GMSOAExceptionAssert.isNotBlank(dateString, GMSOAExceptionCode.PARAM_REQUIRED_EXCEPTION, "dateString must not be null or empty!");
		GMSOAExceptionAssert.isNotBlank(format, GMSOAExceptionCode.PARAM_REQUIRED_EXCEPTION, "format must not be null or empty!");
		try {
			return new SimpleDateFormat(format).parse(dateString);
		} catch (ParseException e) {
			throw new GMSOAException(GMSOAExceptionCode.SYSTEM_EXCEPTION, GMSOAExceptionType.SYSTEM, String.format("error thrown when parsing a string(%s) to date by format(%s).", dateString, format));
		}
	}
	
	/**
	 * 按指定的格式将日期转为换字符串
	 * @param date 日期
	 * @param format 格式
	 * @return 日期字符串
	 * @author 吴春海 Molo.Wu
	 * @since 2014-2-17
	 */
	public static String formatDate(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}
}