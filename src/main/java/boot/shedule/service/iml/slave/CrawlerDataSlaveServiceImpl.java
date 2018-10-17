/**
 * xcwlkj.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package boot.shedule.service.iml.slave;


import org.springframework.stereotype.Service;

import boot.shedule.mapper.slave.CrawlerDataSlaveMapper;
import boot.shedule.model.CrawlerData;
import boot.shedule.service.iml.BaseServiceImpl;
import boot.shedule.service.slave.CrawlerDataSlaveService;

/**
 * 服务
 * @author xcwlkj.HangZhou
 * @version $Id: CrawlerDataService.java, v 0.1 2018年08月23日 下午06:55:06 xcwlkj.HangZhou Exp $
 */
@Service
public class CrawlerDataSlaveServiceImpl extends BaseServiceImpl<CrawlerData, CrawlerDataSlaveMapper> implements CrawlerDataSlaveService{

	@Override
	public String sayHello(String name) {
		System.out.println(name);
		return name;
	}
}