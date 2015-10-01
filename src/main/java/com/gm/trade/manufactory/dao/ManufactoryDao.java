package com.gm.trade.manufactory.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.gm.trade.base.dao.BaseDao;
import com.gm.trade.base.util.CommonUtil;
import com.gm.trade.base.vo.PaginationVO;
import com.gm.trade.manufactory.vo.ManufactoryVO;

@Repository
public class ManufactoryDao {
	
	private static final Logger logger = LoggerFactory.getLogger(ManufactoryDao.class);
	
	@Autowired
	private BaseDao baseDao;
	
	/**
	 * 判断M2B的厂家是否存在于跨境交易系统
	 * @author 吴春海 Molo.Wu
	 * @since 2014-11-21
	 */
	public boolean isM2BManufactoryExits(Long m2bCompId){
		boolean result = false;
		StringBuilder sql = new StringBuilder();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		sql.append(" SELECT COUNT(0)              ");
		sql.append(" FROM m2cchinaerp.erp$mft$mft "); 
		sql.append(" WHERE 1 = 1                  ");
		sql.append(" and is_sell_on_m2b = 1       ");
		sql.append(" AND sfa_id = :m2bCompId      ");
		
		paramMap.put("m2bCompId", m2bCompId);
		
		logger.info(" About to excute sql={}, paramMap={}", new Object[] { sql.toString(), paramMap });
		List<Integer> dataList = this.baseDao.getNamedParameterJdbcTemplate().queryForList(sql.toString(), paramMap, Integer.class);
		if(CommonUtil.isListValid(dataList)) {
			result = dataList.get(0) > 0;
		}
		return result;
	}

	public PaginationVO<ManufactoryVO> queryMftPage(int pageSize, int pageNum, Long status) {
		PaginationVO<ManufactoryVO> result = new PaginationVO<ManufactoryVO>();
		StringBuilder countSQL = new StringBuilder();
		StringBuilder selectSQL = new StringBuilder();
		StringBuilder whereSQL = new StringBuilder();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pageSize", pageSize);
		paramMap.put("pageNum", pageNum);
		paramMap.put("status", status);

		//[miles][2015/7/30]: 拼接所需字段
		selectSQL.append(" SELECT mft.mft_id manufactoryId, ");
		selectSQL.append("	mft.mft_name_cn                 ");
		
		//[miles][2015/7/30]: 拼接查询条件
		whereSQL.append(" FROM `erp$mft$mft` mft     ");
		whereSQL.append(" WHERE 1 = 1                ");
		whereSQL.append(" AND mft.`status` = :status ");

		//[miles][2015/7/30]: 拼接统计
		countSQL.append(" select count(0) ").append(whereSQL);
		logger.info(" About to excute sql={}, paramMap={}", new Object[] { countSQL.toString(), paramMap });
		List<Integer> dataList = this.baseDao.getNamedParameterJdbcTemplate().queryForList(countSQL.toString(), paramMap, Integer.class);
		Integer total = null;
		if (CommonUtil.isListValid(dataList)) {
			total = dataList.get(0);
		}
		result.setTotal(total);
		result.setPageNum(pageNum);
		result.setPageSize(pageSize);
		if (total != null && total > 0) {
			//[miles][2015/7/30]: 拼接分页
			whereSQL.append(" ORDER BY mft.last_update_time DESC ");
			whereSQL.append(" LIMIT :startNum, :pageSize         ");
			selectSQL.append(whereSQL);
			logger.info(" About to excute sql={}, paramMap={}", new Object[] { countSQL.toString(), paramMap });
			List<ManufactoryVO> itemList = this.baseDao.getNamedParameterJdbcTemplate().query(selectSQL.toString(), paramMap, new BeanPropertyRowMapper<ManufactoryVO>(ManufactoryVO.class));
			if (CommonUtil.isListValid(itemList)) {
				result.setItems(itemList);
			} else {
				result.setItems(null);
			}
			result.setMaxPage(total % pageSize);
		}
		return result;
	}
	
}