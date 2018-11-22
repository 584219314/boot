/**
 * xcwlkj.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package boot.shedule.model;

import java.math.BigDecimal;
import java.util.Date;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 
 * @author xcwlkj.HangZhou
 * @version $Id: CrawlerData.java, v 0.1 2018年08月24日 上午09:44:41 xcwlkj.HangZhou Exp $
 */

public class CrawlerData extends BaseEntity {
	/** 序列id */
	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	
	private String name;
	
	private BigDecimal pice;
	
	private String url;
	
	private Date createTime;
	
	private Date updateTime;
	
	private String code;
	
	private String codeId;

	/** Id */
	public Integer getId(){
		return this.id;
	}
	public void setId(Integer id) {
        this.id=id;
    }
	/** Name */
	public String getName(){
		return this.name;
	}
	public void setName(String name) {
        this.name=name;
    }
	/** Pice */
	public BigDecimal getPice(){
		return this.pice;
	}
	public void setPice(BigDecimal pice) {
        this.pice=pice;
    }
	/** Url */
	public String getUrl(){
		return this.url;
	}
	public void setUrl(String url) {
        this.url=url;
    }
	/** CreateTime */
	public Date getCreateTime(){
		return this.createTime;
	}
	public void setCreateTime(Date createTime) {
        this.createTime=createTime;
    }
	/** UpdateTime */
	public Date getUpdateTime(){
		return this.updateTime;
	}
	public void setUpdateTime(Date updateTime) {
        this.updateTime=updateTime;
    }
	/** Code */
	public String getCode(){
		return this.code;
	}
	public void setCode(String code) {
        this.code=code;
    }
	/** CodeId */
	public String getCodeId(){
		return this.codeId;
	}
	public void setCodeId(String codeId) {
        this.codeId=codeId;
    }

	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
