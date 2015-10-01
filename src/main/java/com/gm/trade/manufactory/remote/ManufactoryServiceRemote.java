package com.gm.trade.manufactory.remote;

import com.gm.trade.base.vo.PaginationVO;
import com.gm.trade.manufactory.vo.ManufactoryVO;

public interface ManufactoryServiceRemote {
	
	/**
	 * 判断M2B的厂家是否存在于跨境交易系统
	 * @author 吴春海 Molo.Wu
	 * @since 2014-11-21
	 */
	public abstract boolean isM2BManufactoryExits(Long m2bCompId);
	
	/**
	 * 查询厂家信息，分页
	 * @author Miles.Mai
	 * @since 2015-07-30
	 */
	public abstract PaginationVO<ManufactoryVO> queryMftPage(int pageSize, int pageNum, Long status);
}
