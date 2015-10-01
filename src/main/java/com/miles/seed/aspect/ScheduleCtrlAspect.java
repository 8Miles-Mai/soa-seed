package com.miles.seed.aspect;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 定时器管理切面类：通过环境变量设置当前服务器是否可以执行定时器任务
 * <br><strong>注意事项：</strong>更新环境变量需要进行重启
 * @author 吴春海 Molo.Wu
 * @since 2015-7-15
 */
public class ScheduleCtrlAspect {

    private Logger logger = LoggerFactory.getLogger(ScheduleCtrlAspect.class);
    
    /** 环境变量名称 **/
    private String envVarName = "com.gm.run.task";
    
    /** 是否执行定时任务 **/
    private boolean runTask =  false;
    
    /** 是否将执行时间输入到日志 **/
    private boolean logExecutionTime = true;
    
    public ScheduleCtrlAspect(){
    	String property = System.getProperty(envVarName);
    	
    	if("true".equals(property)) runTask = true;
    	
    	logger.info("================================================================================================");
		if(runTask){
			logger.info("schedules is started up, you can shutdown it by : export {}=false", envVarName);
			logger.info("log down schedule execution time: {}", logExecutionTime);
		} else {
			logger.info("schedules is shutdown, you can turn it on by : export {}=ture", envVarName);
		}		
		logger.info("================================================================================================");
    	
    }
    
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
    	if(!runTask) return;
    	
    	Date begin = new Date();
    	if(runTask) joinPoint.proceed();
    	
    	if(logExecutionTime) {
    		String className = joinPoint.getTarget().getClass().getSimpleName();
            String methodName = joinPoint.getSignature().getName();
            logger.info("schedule[{}-{}] is done, time costs: {}", className, methodName, System.currentTimeMillis() - begin.getTime());
    	}
    	
    }

	public String getEnvVarName() {
		return envVarName;
	}

	public void setEnvVarName(String envVarName) {
		if(StringUtils.isBlank(envVarName)) return;
		
		this.envVarName = envVarName;
	}

	public boolean isLogExecutionTime() {
		return logExecutionTime;
	}

	public void setLogExecutionTime(boolean logExecutionTime) {
		this.logExecutionTime = logExecutionTime;
	}
}