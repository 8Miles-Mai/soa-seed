package com.miles.seed.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.miles.seed.base.constants.EnvConstants;

/**
 * 监控头SOA请求，如果请求时间超长>500毫秒，则输出日志
 * @author 吴春海 Molo.Wu
 * @since 2014-12-10
 */
@Component
@Aspect
public class VisitTimeLogAspect {
	private final Logger LOGGER = LoggerFactory.getLogger(VisitTimeLogAspect.class);
	
	/** 执行提醒时长，如果查过指定的时长 ，则进行日志提醒 **/
	public final static long INFO_TIME_MILLIS_LIMITED = EnvConstants.getProperty("info.time.millis.limited") == null ? 500 : Long.parseLong(EnvConstants.getProperty("info.time.millis.limited").toString()); 
	
	/** 执行报警时长，如果查过指定的时长 ，则进行日志报警 **/
	public final static long WARNING_TIME_MILLIS_LIMITED = EnvConstants.getProperty("warning.time.millis.limited") == null ? 5000 : Long.parseLong(EnvConstants.getProperty("warning.time.millis.limited").toString()); 
	
	@Pointcut("(execution (* com.miles.seed.*.service.*Service.*(..))))")  
    public void pointcut(){}  
	
	
    //方法执行的前后调用  
    @Around("pointcut()")  
    public Object around(ProceedingJoinPoint point) throws Throwable{  
        long start = System.currentTimeMillis();
        Object object = point.proceed();
        long runTime = System.currentTimeMillis() - start;
        
        if(runTime < INFO_TIME_MILLIS_LIMITED) return object;
        
        StringBuffer log = new StringBuffer();
    		log.append(this.getPointString(point)).append(" end ").append("visitTime: ").append(runTime + " milliscond");
	    	if(RequestContextHolder.getRequestAttributes() != null) {
	    		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	    		if(request != null) {
	    			log.append(" serverName="+ request.getServerName() + ",URI=" + request.getRequestURI())
	    				.append(",remoteAddr=" + request.getRemoteAddr() + ",remoteHost=" + request.getRemoteHost());
	    		}
	    	}
    	
        if(runTime > WARNING_TIME_MILLIS_LIMITED) LOGGER.warn(log.toString());
        else if (runTime > INFO_TIME_MILLIS_LIMITED) LOGGER.info(log.toString());
        
        return object;  
    }  
    
    private String getPointString(ProceedingJoinPoint point) {
		StringBuilder result = new StringBuilder();
		String className = point.getTarget().getClass().getSimpleName();
		String methodName = point.getSignature().getName();
		result.append("[" + className + "." + methodName + "] ");
		Object[] args = point.getArgs();
		result.append(" ( ");
		int index = 0;
		for(Object obj : args) {
			if(obj != null) {
				result.append(" _" + index + ":" + obj.toString());
			}
			index++;
		}
		result.append(" ) ");
		return result.toString();
    }
}
