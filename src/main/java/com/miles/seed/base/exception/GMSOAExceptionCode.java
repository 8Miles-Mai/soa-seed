package com.miles.seed.base.exception;

/**
 * 异常类型
 * @author 吴春海 Molo.Wu
 * @since 2014-11-17
 */
public enum GMSOAExceptionCode {
	
	/** 参数错误 **/
    PARAM_REQUIRED_EXCEPTION("PARAM_ERROR"),
	/** 业务(逻辑)错误 **/
    BUNIESS_EXCEPTION("BUSINESS_ERROR"),
	/** 系统错误 **/
    SYSTEM_EXCEPTION("SYSTEM_ERROR"),
    /** 未知异常 **/
    UNKNOWN_EXCEPTION("UNKNOWN_EXCEPTION");
    
    private String code;
    
    private GMSOAExceptionCode(String code) {
		this.code = code;
	}
    
    @Override
    public String toString() {
    	return this.code;
    }
}
