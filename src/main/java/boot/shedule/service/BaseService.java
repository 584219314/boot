package boot.shedule.service;

/**
 * xcwlkj.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */

import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import boot.shedule.mapper.BaseMapper;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 抽象服务父类
 * @author danfeng.zhou
 * @version $Id: BaseService.java, v 0.1 2018年4月10日 下午1:27:24 danfeng.zhou Exp $
 */
public interface BaseService<E, M extends BaseMapper<E>> {
	

    public int deleteByPrimaryKey(Long id);

    public int insert(E record);

    public E selectByPrimaryKey(Long id);

    public int updateByPrimaryKeySelective(E record);

    public List<E> list(E record);

    public Page<E> pageList(PageModel<E> pageModel);
}
