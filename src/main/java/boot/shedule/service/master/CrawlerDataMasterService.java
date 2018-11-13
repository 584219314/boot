/**
 * xcwlkj.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package boot.shedule.service.master;


import boot.shedule.mapper.master.CrawlerDataMasterMapper;
import boot.shedule.model.CrawlerData;
import boot.shedule.service.BaseService;

/**
 * 服务
 * @author xcwlkj.HangZhou
 * @version $Id: CrawlerDataService.java, v 0.1 2018年08月23日 下午06:55:06 xcwlkj.HangZhou Exp $
 */

public interface CrawlerDataMasterService extends BaseService<CrawlerData, CrawlerDataMasterMapper> {

	public String sayHello(String name);
	
	String testAspect();

}