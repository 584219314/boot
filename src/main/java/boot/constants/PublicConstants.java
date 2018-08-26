package boot.constants;

/**
 * xcwlkj.com Inc.
 * Copyright (c) 2015-2017 All Rights Reserved.
 */

import java.util.Map;

import boot.util.ResourceUtil;


/**
 * 环境配置基础类
 * @author danfeng.zhou
 * @version $Id: PublicConstants.java, v 0.1 2017年4月27日 下午6:16:20 danfeng.zhou Exp $
 */
public class PublicConstants {

    //默认编码
    public final static String              DEFAULT_CHARSET        = "utf-8";
    //数据库配置 加载
    public final static Map<String, String> DB_CONFIG              = ResourceUtil.getResource("db").getMap();
}
