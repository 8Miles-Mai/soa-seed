package com.miles.seed.hscode.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miles.seed.base.exception.GMSOAExceptionAssert;
import com.miles.seed.base.exception.GMSOAExceptionCode;
import com.miles.seed.debug.annotation.APICategory;
import com.miles.seed.debug.annotation.APIMethod;
import com.miles.seed.debug.annotation.APIParam;
import com.miles.seed.debug.annotation.APIUser;
import com.miles.seed.hscode.remote.HscodeServiceRemote;
import com.mongodb.BasicDBObject;

@Controller("hscodeService")
public class HscodeService implements HscodeServiceRemote {
	
	private static final String COLLECTION_NAME = "logistics_setting";
	
	@Autowired
	@Qualifier("hscodeMongoTemplate")
	private MongoTemplate mongoTemplate;

	@Override
	@RequestMapping
    @APIMethod(name="更新交易配置信息", category=APICategory.HSCODE, apiUsers={APIUser.MVO_TRADE}, desc="更新交易配置信息", authors={"molo"}, version="1.0")
	public @ResponseBody void updateLogisticsSetting(@APIParam("公司ID")Long compId, @APIParam("交易设置")Map<String, Object> logisticsSetting) {
		GMSOAExceptionAssert.hasValue(compId, GMSOAExceptionCode.PARAM_REQUIRED_EXCEPTION, "compId can not be null.");
		GMSOAExceptionAssert.isNotEmpty(logisticsSetting, GMSOAExceptionCode.PARAM_REQUIRED_EXCEPTION, "logistics setting can not be empty.");
		
		logisticsSetting.put("compId", compId);
		
		this.mongoTemplate.upsert(
				Query.query(Criteria.where("compId").is(compId)), 
				Update.fromDBObject(new BasicDBObject(logisticsSetting), new String[]{}), 
				COLLECTION_NAME);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@RequestMapping
    @APIMethod(name="加载交易配置信息", category=APICategory.HSCODE, apiUsers={APIUser.MVO_TRADE}, desc="key为：Boolean isCIFSupported, Long fobPortId,  Boolean isExpressSupported, String districtCode, Long cityId, String address", authors={"molo"}, version="1.0")
	public @ResponseBody Map<String, Object> loadLogisticsSetting(@APIParam("公司ID")Long compId) {
		GMSOAExceptionAssert.hasValue(compId, GMSOAExceptionCode.PARAM_REQUIRED_EXCEPTION, "compId can not be null.");
		
		List<Map> result = this.mongoTemplate.find(Query.query(Criteria.where("compId").is(compId)), Map.class, COLLECTION_NAME);
		
		return CollectionUtils.isEmpty(result) ? null : result.get(0);
	}
}
