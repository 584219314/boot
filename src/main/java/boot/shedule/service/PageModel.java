package boot.shedule.service;

/**
 * xcwlkj.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */

/**
 * 分页查询模型
 * @author danfeng.zhou
 * @version $Id: PageModel.java, v 0.1 2018年4月10日 下午1:29:26 danfeng.zhou Exp $
 */
public class PageModel<E> {
    //页码，从1开始
    private int pageNum;
    //每页数量
    private int pageSize;
    //查询入参
    private E   param;

    /**
     * 默认构造方法
     */
    private PageModel() {

    }

    /**
     * 构造方法
     * @param pageNum
     * @param pageSize
     * @param param
     */
    private PageModel(int pageNum, int pageSize, E param) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.param = param;
    }

    /**
     * 构造方法
     * @param pageNum
     * @param pageSize
     * @param param
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static <E> PageModel of(int pageNum, int pageSize, E param) {
        return new PageModel(pageNum, pageSize, param);
    }

    /**
     * Getter method for property <tt>pageNum</tt>.
     * 
     * @return property value of pageNum
     */
    public int getPageNum() {
        return pageNum;
    }

    /**
     * Getter method for property <tt>pageSize</tt>.
     * 
     * @return property value of pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Getter method for property <tt>param</tt>.
     * 
     * @return property value of param
     */
    public E getParam() {
        return param;
    }

}
