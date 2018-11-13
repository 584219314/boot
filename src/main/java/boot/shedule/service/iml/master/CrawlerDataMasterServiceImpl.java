/**
 * xcwlkj.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package boot.shedule.service.iml.master;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import boot.aspect.RedisLock;
import boot.shedule.mapper.master.CrawlerDataMasterMapper;
import boot.shedule.mapper.slave.CrawlerDataSlaveMapper;
import boot.shedule.model.CrawlerData;
import boot.shedule.service.iml.BaseServiceImpl;
import boot.shedule.service.master.CrawlerDataMasterService;
import boot.util.DateUtil;
import boot.util.RedisUtil;

/**
 * 服务
 * @author xcwlkj.HangZhou
 * @version $Id: CrawlerDataService.java, v 0.1 2018年08月23日 下午06:55:06 xcwlkj.HangZhou Exp $
 */
@Service
public class CrawlerDataMasterServiceImpl extends BaseServiceImpl<CrawlerData, CrawlerDataMasterMapper> implements CrawlerDataMasterService{

	@Autowired
	RedisUtil redisUtil;
	
	@Override
	public String sayHello(String name) {
		System.out.println(name);
		return name;
	}
	
	@RedisLock(keyName = "testAspect")
	@Override
	public String testAspect() {
		CrawlerData record = new CrawlerData();
		record.setName(DateUtil.formatDateTime(new Date()));
		try {
			Thread.sleep(1100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int result = mapper.insert(record);
		return String.valueOf(result);
	}
}