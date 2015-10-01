package com.miles.seed.debug;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miles.seed.base.constants.EnvConstants;
import com.miles.seed.base.util.DateUtil;
import com.miles.seed.debug.vo.ApiMethod;
import com.miles.seed.debug.vo.ApiValueObject;

@Controller
public class APIController {
	@Autowired
	private APIScanEngine apiScanEngine;

	private static Map<String, Object> categoryApiMap = new HashMap<String, Object>();
	
	@RequestMapping
	public @ResponseBody Map<String, Object> openAPI() {
		if(categoryApiMap.isEmpty()) {
			Map<String, List<ApiMethod>> api = apiScanEngine.getAllAPIMethod();
			categoryApiMap.put("api", api);
			Object value = EnvConstants.p.get("soa.sys.info");
			if(value != null) categoryApiMap.put("soaSysInfo", value.toString());
			value = EnvConstants.p.get("soa.sys.build.time");
			if(value != null && !"{soa.sys.build.time}".equals(value.toString())) {
				categoryApiMap.put("soaSysBuildTime", value.toString());
			} else {
				categoryApiMap.put("soaSysBuildTime", DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm"));
			}
		}

		return categoryApiMap;
	}

	@RequestMapping
	public @ResponseBody
	ApiValueObject getValueObjectDesc(String className) throws ClassNotFoundException {
		ApiValueObject  apiValueObject =apiScanEngine.getValueObjectDesc(className);
		return apiValueObject;
	}

	

}
