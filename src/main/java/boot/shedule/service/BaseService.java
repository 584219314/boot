package boot.shedule.service;

/**
 * xcwlkj.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import boot.shedule.mapper.BaseMapper;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 抽象服务父类
 * @author danfeng.zhou
 * @version $Id: BaseService.java, v 0.1 2018年4月10日 下午1:27:24 danfeng.zhou Exp $
 */
public abstract class BaseService<E, M extends BaseMapper<E>> {
    @Autowired
    protected M mapper;

    public int deleteByPrimaryKey(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    public int insert(E record) {
        return mapper.insert(record);
    }

    public E selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(E record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

    public List<E> list(E record) {
        return mapper.list(record);
    }

    public Page<E> pageList(PageModel<E> pageModel) {
        Page<E> page = PageHelper.startPage(pageModel.getPageNum(), pageModel.getPageSize()).doSelectPage(() -> {
            mapper.pageList(pageModel.getParam());
        });
        return page;
    }

    /**
     * 添加
     * @param record
     * @return
     */

    /**
     * 更新
     * @param record
     * @return
     */
}
