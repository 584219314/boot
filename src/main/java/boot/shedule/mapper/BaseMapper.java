package boot.shedule.mapper;

/**
 * xcwlkj.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;


/**
 * 基础数据库操作层
 * @author danfeng.zhou
 * @version $Id: BaseMapper.java, v 0.1 2018年4月10日 下午1:25:23 danfeng.zhou Exp $
 */
@Mapper
public interface BaseMapper<E> {
    /**
     * 删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 添加
     * @param record
     * @return
     */
    int insert(E record);

    /**
     * 添加
     *
     * @param record
     * @return
     */

    /**
     * 更新
     *
     * @param record
     * @return
     */

    /**
     *
     * @param id
     * @return
     */
    E selectByPrimaryKey(Long id);

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(E record);

    /**
     * 列表
     * @param record
     * @return
     */
    List<E> list(E record);

    /**
     * 分页
     * @param param
     * @return
     */
    Page<E> pageList(E param);
}
