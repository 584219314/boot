package boot.util;

/**
 * xcwlkj.com Inc.
 * Copyright (c) 2015-2017 All Rights Reserved.
 */

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * 资源文件工具类
 * @author danfeng.zhou
 * @version $Id: ResourceUtil.java, v 0.1 2017年4月27日 下午6:07:30 danfeng.zhou Exp $
 */
public class ResourceUtil {
    
    private ResourceBundle resourceBundle;

    private ResourceUtil(String resource) {
        Locale locale1 = new Locale("zh", "CN"); 
        resourceBundle = ResourceBundle.getBundle(resource,locale1);
    }

    /**
     * 获取资源
     * @param resource 资源
     * @return 解析
     */
    public static ResourceUtil getResource(String resource) {
        return new ResourceUtil(resource);
    }

    /**
     * 根据key取得value
     * @param key 键值
     * @param args value中参数序列，参数:{0},{1}...,{n}
     * @return
     */
    public String getValue(String key, Object... args) {
        String temp = resourceBundle.getString(key);
        return MessageFormat.format(temp, args);
    }

    /**
     * 获取所有资源的Map表示
     * @return 资源Map
     */
    public Map<String, String> getMap() {
        Map<String, String> map = new HashMap<String, String>();
        for (String key : resourceBundle.keySet()) {
            try {
                map.put(key, new String(resourceBundle.getString(key).getBytes("ISO-8859-1"),"UTF-8"));
            }catch(Exception e) {
                throw new RuntimeException("初始化配置文件异常!");
            }
            
        }
        return map;
    }
}
