package com.miles.seed.debug;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miles.seed.debug.vo.ApiParam;

@Controller
public class DebugController {

	@Autowired
	private APIScanEngine apiScanEngine;

	@RequestMapping(value = "debug/{bean}.{method}.gm")
	public @ResponseBody
	List<ApiParam> getMethodParam(@PathVariable("bean") String beanName,
			@PathVariable("method") final String methodName) {
		List<ApiParam> apiParamList=apiScanEngine.getMethodParam(beanName, methodName);
		return apiParamList;
	}

}
