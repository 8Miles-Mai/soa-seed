package com.miles.seed.base.constants;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 不同环境不同配置 
 * @author 吴春海 Molo.Wu
 * @since 2014-12-10
 */
public abstract class EnvConstants {

	public final static Properties p = new Properties();
	
	static {
		InputStream inputStream = EnvConstants.class.getClassLoader().getResourceAsStream("constants.properties");
		try {
			p.load(inputStream);
		} catch (IOException e1) {
			//e1.printStackTrace();
		}
	}

	public static String getProperty(String key){
		String value=p.getProperty(key);
		return value;
	}
	
	
}