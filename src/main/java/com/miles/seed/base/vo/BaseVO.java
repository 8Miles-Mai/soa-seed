package com.miles.seed.base.vo;

import java.util.Date;

public class BaseVO implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8647380613808000489L;
	
	private Long createBy;                //创建人
    private Long lastUpdateBy;            //修改人
    private Date createTime     = null;  //创建时间
    private Date lastUpdateTime = null;  //修改时间
	public Long getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	public Long getLastUpdateBy() {
		return lastUpdateBy;
	}
	public void setLastUpdateBy(Long lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	@Override
	public String toString() {
		return "BaseVO [createBy=" + createBy + ", lastUpdateBy=" + lastUpdateBy + ", createTime=" + createTime + ", lastUpdateTime=" + lastUpdateTime + "]";
	}
}
