package boot.exception;

/**
 * xcwlkj.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */

/**
 * 业务异常基类，所有业务异常都必须继承于此异常<br/>
 * 定义异常时，需要先确定异常所属模块。例如：添加用户报错 可以定义为 [10010001] 前四位数为系统模块编号，后4位为错误代码 ,唯一 <br>
 * 默认异常  9001 <br>
 * 基础异常 1001 <br>
 * 
 * @author danfeng.zhou
 * @version $Id: BizException.java, v 0.1 2017年4月27日 下午6:14:12 danfeng.zhou Exp $
 */
public class BizException extends RuntimeException {

    /**  */
    private static final long        serialVersionUID            = -252688289843643087L;

    /** 系统发生未知异常 */
    public static final BizException SYSTEM_ERROR                = new BizException(90019999, "系统发生未知异常");

    /** 参数有误 */
    public static final BizException PARAM_ERROR                 = new BizException(90010000, "参数有误");

    /** 页面500异常 */
    public static final BizException PAGE_500_ERROR              = new BizException(90015000, "500异常");

    /** 页面404异常 */
    public static final BizException PAGE_404_ERROR              = new BizException(90014040, "404异常");
    
    /** 第三方http连接通信超时 */
    public static final BizException THIRD_HTTP_TIME_OUT=new BizException(90016000, "http连接通信超时");

    /** RPC调用异常 */
    public static final BizException CALL_RPC_EXCEPTION          = new BizException(90018000, "RPC调用异常");

    /** RPC调用结果为false异常 */
    public static final BizException CALL_RPC_RESULT_FALSE       = new BizException(90018008, "RPC调用结果为false异常");

    /**数据库操作,insert返回0 */
    public static final BizException DB_INSERT_RESULT_0          = new BizException(90010001, "数据库操作,insert返回0");

    /** 数据库操作,update返回0 */
    public static final BizException DB_UPDATE_RESULT_0          = new BizException(90010002, "数据库操作,update返回0");

    /** 数据库操作,selectOne返回null */
    public static final BizException DB_SELECTONE_IS_NULL        = new BizException(90010003, "数据库操作,selectOne返回null");

    /** 数据库操作,list返回null */
    public static final BizException DB_LIST_IS_NULL             = new BizException(90010004, "数据库操作,list返回null");

    /** Token 验证不通过 */
    public static final BizException TOKEN_IS_ILLICIT            = new BizException(90010005, "Token 验证非法");

    /** 会话超时　获取session时，如果是空，throws 下面这个异常 拦截器会拦截爆会话超时页面 */
    public static final BizException SESSION_IS_OUT_TIME         = new BizException(90010006, "会话超时");

    /** 获取序列出错 */
    public static final BizException DB_GET_SEQ_NEXT_VALUE_ERROR = new BizException(90010007, "获取序列出错");

    /** 获取数据库时间出错 */
    public static final BizException DB_GET_DATABASE_TIME_ERROR  = new BizException(90010008, "获取数据库时间出错");

    /** 异常信息 */
    protected String                 msg;

    /** 具体异常码 */
    protected int                    code;

    /**
     * 构造方法
     * @param code
     * @param msgFormat
     * @param args
     */
    public BizException(int code, String msgFormat, Object... args) {
        super(String.format(msgFormat, args));
        this.code = code;
        this.msg = String.format(msgFormat, args);
    }

    /**
     * 构造方法
     */
    public BizException() {
        super();
    }

    /**
     * 构造方法
     * @param msgFormat
     * @param args
     * @return
     */
    public BizException newInstance(String msgFormat, Object... args) {
        return new BizException(this.code, msgFormat, args);
    }

    /**
     * 构造方法
     * @param message
     * @param cause
     */
    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 构造方法
     * @param cause
     */
    public BizException(Throwable cause) {
        super(cause);
    }

    /**
     * 构造方法
     * @param message
     */
    public BizException(String message) {
        super(message);
    }

    /**
     * 获取错误描述 <br/>
     * Getter method for property <tt>msg</tt>.
     * @return property value of msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 获取错误码 <br/>
     * Getter method for property <tt>code</tt>.
     * @return property value of code
     */
    public int getCode() {
        return code;
    }

}
