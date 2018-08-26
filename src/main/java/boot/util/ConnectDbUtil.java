package boot.util;

/**
 * xcwlkj.com Inc.
 * Copyright (c) 2015-2017 All Rights Reserved.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import boot.enums.DbTypeEnum;
import boot.exception.BizException;
import boot.model.db.JdbcModel;


/**
 * 数据库连接工具类
 * @author danfeng.zhou
 * @version $Id: ConnectDbUtil.java, v 0.1 2017年6月20日 上午11:23:49 danfeng.zhou Exp $
 */
public class ConnectDbUtil {

    /**
     * 获取数据库连接
     * @param dbTypeEnum
     * @return
     */
    public static Connection getConnection(DbTypeEnum dbTypeEnum) {
        CheckParamUtil.checkObjectNotNull(dbTypeEnum, "数据库类型枚举不能为空");
        Connection conn = null;
        String driverClassName = "";
        if (dbTypeEnum == DbTypeEnum.MYSQL) {
            driverClassName = "com.mysql.cj.jdbc.Driver";
        } else if (dbTypeEnum == DbTypeEnum.ORACLE) {
            driverClassName = "oracle.jdbc.OracleDriver";
        }
        //加载数据库驱动
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            throw new BizException(BizException.PARAM_ERROR.getCode(), "未找到数据库类型为:" + dbTypeEnum.getLabel() + "的驱动", e);
        }

        //获取数据库连接url
        String url = JdbcModel.url;
        CheckParamUtil.checkStringNotBlank(url, "从配置文件datasource.properties未获取到url配置");
        //获取数据库用户名
        String userName = JdbcModel.userName;
        CheckParamUtil.checkStringNotBlank(userName, "从配置文件datasource.properties未获取到username配置");
        //获取数据库密码
        String passWord = JdbcModel.password;
        CheckParamUtil.checkStringNotBlank(passWord, "从配置文件datasource.properties未获取到password配置");
        //获取数据库连接
        try {
            //durd
            //            String decryptPassWord = ConfigTools.decrypt(passWord);
            conn = DriverManager.getConnection(url, userName, passWord);
        } catch (SQLException e) {
            throw new BizException(BizException.SYSTEM_ERROR.getCode(), "获取数据库连接异常", e);
        } catch (Exception e) {
            throw new BizException(BizException.SYSTEM_ERROR.getCode(), "获取数据库连接异常", e);
        }
        return conn;
    }

}
