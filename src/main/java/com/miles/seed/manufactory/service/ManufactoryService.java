package com.miles.seed.manufactory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miles.seed.base.vo.PaginationVO;
import com.miles.seed.debug.annotation.APICategory;
import com.miles.seed.debug.annotation.APIMethod;
import com.miles.seed.debug.annotation.APIUser;
import com.miles.seed.manufactory.dao.ManufactoryDao;
import com.miles.seed.manufactory.remote.ManufactoryServiceRemote;
import com.miles.seed.manufactory.vo.ManufactoryVO;

@Controller("manufactoryService")
public class ManufactoryService implements ManufactoryServiceRemote {
	
	@Autowired
	private ManufactoryDao manufactoryDao;

	@Override
	@RequestMapping
    @APIMethod(name="判断M2B的厂家是否存在于(支持)跨境交易系统", category=APICategory.MANUFACTORY, apiUsers={APIUser.MVO_TRADE}, desc="判断M2B的厂家是否存在于跨境交易系统", authors={"molo"}, version="1.0")
	public @ResponseBody boolean isM2BManufactoryExits(Long m2bCompId) {
		boolean result = false;
		result = this.manufactoryDao.isM2BManufactoryExits(m2bCompId);
		return result;
	}

	@Override
	public PaginationVO<ManufactoryVO> queryMftPage(int pageSize, int pageNum, Long status) {
		PaginationVO<ManufactoryVO> result = null;
		result = manufactoryDao.queryMftPage(pageSize, pageNum, status);
		return result;
	}
}