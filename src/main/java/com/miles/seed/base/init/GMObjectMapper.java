package com.miles.seed.base.init;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.codehaus.jackson.map.ser.BeanSerializerFactory;

public class GMObjectMapper extends ObjectMapper {
	
	public void init(){
		
        setSerializerFactory(BeanSerializerFactory.instance);
        configure(Feature.FAIL_ON_EMPTY_BEANS, false);
		
		
		this.getDeserializationConfig().enable(DeserializationConfig.Feature.USE_ANNOTATIONS);
		this.getDeserializationConfig().disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);		
		this.getSerializationConfig().enable(SerializationConfig.Feature.USE_ANNOTATIONS);
		this.getSerializationConfig().disable(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS);
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		formater.setLenient(false);
		formater.setTimeZone(TimeZone.getTimeZone("GMT"));


		this.getSerializationConfig().setDateFormat(formater);
	}

}
