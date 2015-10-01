package com.gm.trade.base.exception;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

/**
 * 统一HTTP请求的异常处理
 * @author 吴春海 Molo.Wu
 * @since 2014-11-17
 */
@Component
public class ExceptionResolver extends AbstractHandlerExceptionResolver {
	final Logger log = LoggerFactory.getLogger(ExceptionResolver.class);

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		log.info(" In Function: ExceptionResolver.doResolveException1(request={}, response={}, handler={}, ex={})", new Object[] { request, response, handler, ex });
		log.info(" ExceptionResolver.doResolveException2 (getAuthType={} getPathInfo={}, getRequestURL={} getRemoteUser={} getQueryString={} getMethod={} errorMessage = {} )", 
				new Object[]{request.getAuthType(), request.getPathInfo(), request.getRequestURL(), request.getRemoteUser(), request.getQueryString(), request.getMethod(), ex.getMessage()});
		
		ModelAndView view = new ModelAndView("jsonView");
		
		InetAddress inet = null;
    	try {
    		inet = InetAddress.getByName(request.getRemoteAddr());
    	} catch(UnknownHostException e) {
    		log.info("could not get remote client ip");
		}
    	
    	if(inet != null) {
    		log.info(" ExceptionResolver.doResolveException3(requestIP={})", new Object[] { inet.getHostAddress() });
    		view.addObject("clientIp", inet.getHostAddress());
    	}
		
		if (ex instanceof GMSOAException) {
			GMSOAException e = ((GMSOAException) ex);
			view.addObject("error", true);
			view.addObject("errorType", e.getType());
			view.addObject("errorCode", e.getCode());
			view.addObject("errorMsg", 	e.getMessage());
			if(e.getErrorData() != null)view.addObject("errorData", e.getErrorData());
		} else if (ex instanceof MissingServletRequestParameterException) {
			view.addObject("error", true);
			view.addObject("errorType", GMSOAExceptionType.BUSINESS);
			view.addObject("errorCode", GMSOAExceptionCode.PARAM_REQUIRED_EXCEPTION);
			view.addObject("errorMsg", 	ex.getMessage());
		}  else {
			view.addObject("error", true);
			view.addObject("errorType", GMSOAExceptionType.SYSTEM);
			view.addObject("errorCode",	GMSOAExceptionCode.SYSTEM_EXCEPTION);
			view.addObject("errorMsg",	ex.getMessage());
			log.error("", ex);
		}
		
		return view;
	}
}