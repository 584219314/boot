/**
 * xcwlkj.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package boot.shedule.service.slave;

import org.springframework.stereotype.Service;

import boot.shedule.mapper.slave.CrawlerDataSlaveMapper;
import boot.shedule.model.CrawlerData;
import boot.shedule.service.BaseService;

/**
 * 服务
 * @author xcwlkj.HangZhou
 * @version $Id: CrawlerDataService.java, v 0.1 2018年08月23日 下午06:55:06 xcwlkj.HangZhou Exp $
 */

public interface CrawlerDataSlaveService extends BaseService<CrawlerData, CrawlerDataSlaveMapper> {

	public String sayHello(String name);

}