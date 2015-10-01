package com.gm.trade.manufactory.vo;

import com.gm.trade.base.vo.BaseVO;

public class ManufactoryVO extends BaseVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1502796984418777280L;
	
	private Long manufactoryId;
	private String mftNameCn;
	public Long getManufactoryId() {
		return manufactoryId;
	}
	public void setManufactoryId(Long manufactoryId) {
		this.manufactoryId = manufactoryId;
	}
	public String getMftNameCn() {
		return mftNameCn;
	}
	public void setMftNameCn(String mftNameCn) {
		this.mftNameCn = mftNameCn;
	}
	@Override
	public String toString() {
		return "ManufactoryVO [manufactoryId=" + manufactoryId + ", mftNameCn=" + mftNameCn + "]";
	}
}
