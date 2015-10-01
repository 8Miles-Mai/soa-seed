package com.miles.seed.hscode.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.miles.seed.base.util.DateUtil;
import com.miles.seed.hscode.remote.HscodeServiceRemote;

@ContextConfiguration(locations={"/remote-client.xml"})
public class SPUServiceRemoteTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	@Qualifier("hscodeService")
	private HscodeServiceRemote service;
	
	@Test
	public void testLoadLogisticsSetting() {
		Map<String, Object> result = this.service.loadLogisticsSetting(100609L);
		System.out.println("============= : " + result);
	}
	
	@Test
	public void testUpdateLoadLogisticsSetting() {
		
		Map<String, Object> data = new HashMap<String, Object>();
		
		data.put("date", DateUtil.formatDate(new Date(), DateUtil.TIME_FORMAT));
		
		this.service.updateLogisticsSetting(100609L, data);
	}

}
