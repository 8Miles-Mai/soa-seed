package com.miles.seed.hscode.remote;

import java.util.Map;

/**
 * Hscode业务逻辑
 * @author 吴春海 Molo.Wu
 * @since 2014-8-19
 */
public interface HscodeServiceRemote {
	
	/**
	 * 更新交易配置信息
	 * @param compId 公司ID
	 * @param logisticsSetting 配置信息
	 * @author 吴春海 Molo.Wu
	 * @since 2014-8-19
	 */
	public void updateLogisticsSetting(Long compId, Map<String, Object> logisticsSetting);
	
	/**
	 * 获取交易配置信息
	 * @param compId 公司ID
	 * @author 吴春海 Molo.Wu
	 * @since 2014-8-19
	 */
	public Map<String, Object> loadLogisticsSetting(Long compId);	
}