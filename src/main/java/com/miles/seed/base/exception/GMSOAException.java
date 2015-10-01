package com.miles.seed.base.exception;

/**
 * SOA统一异常类
 * @author 吴春海 Molo.Wu
 * @since 2014-11-17
 */
public class GMSOAException extends RuntimeException {
	private static final long serialVersionUID = 6029038041214105194L;
	
	/** 异常类型 **/
	private GMSOAExceptionType type;
	/** 异常代码 **/
	private GMSOAExceptionCode code;
	/** 异常数据 **/
	private Object errorData;
	
	public GMSOAException(GMSOAExceptionCode code, GMSOAExceptionType type, String message) {
		super(message);
		
		this.type = type;
		this.code = code;
	}
	
	public GMSOAException(GMSOAExceptionCode code, GMSOAExceptionType type, String message, Object errorData) {
		super(message);
		
		this.type = type;
		this.code = code;
		this.errorData = errorData;
	}

	public GMSOAExceptionType getType() {
		return type;
	}

	public GMSOAExceptionCode getCode() {
		return code;
	}

	public Object getErrorData() {
		return errorData;
	}	
}