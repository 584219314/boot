package boot.util;

/**
 * xcwlkj.com Inc.
 * Copyright (c) 2015-2017 All Rights Reserved.
 */

import java.math.BigDecimal;
import java.util.List;

import boot.exception.BizException;


/**
 * 参数验证工具类
 * @author danfeng.zhou
 * @version $Id: CheckParamUtil.java, v 0.1 2017年4月27日 下午6:09:13 danfeng.zhou Exp $
 */
public class CheckParamUtil {
    /**
     * 验证字符串不为空
     * 
     * @param param
     *            验证对象
     * @param bizMsg
     *            给渠道层用户展示的错误信息
     * **/
    public static void checkStringNotBlank(String param, String bizMsg) {
        if (StringUtil.isBlank(param)) {
            throw new BizException(BizException.PARAM_ERROR.getCode(), bizMsg);
        }
    }

    /**
     * 验证字符串为null,不为null抛异常
     * 
     * @param param
     * @param bizMsg
     */
    public static void checkStringBlank(String param, String bizMsg) {
        if (StringUtil.isNotBlank(param)) {
            throw new BizException(BizException.PARAM_ERROR.getCode(), bizMsg);
        }
    }

    /**
     * 判断数组是否不为空，为空抛异常
     * @param array
     * @param bizMsg
     */
    public static void checkArrayNotEmpty(Object[] array, String bizMsg) {
        if (ObjectUtil.isEmpty(array)) {
            throw new BizException(BizException.PARAM_ERROR.getCode(), bizMsg);
        }
    }

    /**
     * 验证输入参数必须要大于0
     * 
     * @param param
     * @param bizMsg
     */
    public static void checkIntNotThanZero(int param, String bizMsg) {
        checkObjectNotNull(param, bizMsg);
        if (param <= 0) {
            throw new BizException(BizException.PARAM_ERROR.getCode(), bizMsg);
        }
    }

    /**
     * 验证输入参数必须要大于0
     * 
     * @param param
     * @param bizMsg
     */
    public static void checkIntNotLessZero(int param, String bizMsg) {
        if (param < 0) {
            throw new BizException(BizException.PARAM_ERROR.getCode(), bizMsg);
        }
    }

    /**
     * 验证输入参数必须要大于0
     * 
     * @param param
     * @param bizMsg
     */
    public static void checkDoubleNotThanZero(Double param, String bizMsg) {
        if (param <= 0) {
            throw new BizException(BizException.PARAM_ERROR.getCode(), bizMsg);
        }
    }

    /**
     * 验证输入参数必须要大于0
     * 
     * @param param
     * @param bizMsg
     */
    public static void checkDoubleNotLessZero(Double param, String bizMsg) {
        if (param < 0) {
            throw new BizException(BizException.PARAM_ERROR.getCode(), bizMsg);
        }
    }

    /**
     * 验证字符串不为空且必须为数字
     * 
     * @param param
     *            验证对象
     * @param bizMsg
     *            给渠道层用户展示的错误信息
     * **/
    public static void checkStringIsNumber(String param, String bizMsg) {
        try {
            Double.parseDouble(param);
        } catch (Exception e) {
            throw new BizException(BizException.PARAM_ERROR.getCode(), bizMsg);
        }
    }

    /**
     * 判断是否是正整数
     * @param param
     * @param bizMsg
     */
    public static void checkStringIsInt(String param, String bizMsg) {
        if (!CommonValidateUtil.isValidNum(param)) {
            throw new BizException(BizException.PARAM_ERROR.getCode(), bizMsg);
        }
    }

    /**
     * 验证对象不为null，为null抛异常
     * 
     * @param param
     *            验证对象
     * @param bizMsg
     *            给渠道层用户展示的错误信息
     * **/
    public static void checkObjectNotNull(Object param, String bizMsg) {
        if (ObjectUtil.isEmpty(param)) {
            throw new BizException(BizException.PARAM_ERROR.getCode(), bizMsg);
        }
    }

    /**
     * 验证对象不为null，为null抛异常，不为null返回当前对象
     * @param param
     * @param bizMsg
     * @return
     */
    public static <T> T checkObjectNotNullAndRtn(T param, String bizMsg) {
        if (ObjectUtil.isEmpty(param)) {
            throw new BizException(BizException.PARAM_ERROR.getCode(), bizMsg);
        }
        return param;
    }

    /**
     * 验证字符串相等，相等就通过，不相等就抛异常
     * 
     * @param param1
     *            验证对象1
     * @param param2
     *            验证对象2
     * @param bizMsg
     *            给渠道层用户展示的错误信息
     */
    public static void checkStringEquals(String param1, String param2, String bizMsg) {
        if (!StringUtil.equals(param1, param2)) {
            throw new BizException(BizException.PARAM_ERROR.getCode(), bizMsg);
        }
    }

    /**
     * 比较两个字符串（大小写不敏感），相等就通过，不相等就抛异常
     * 
     * @param param1
     *            验证对象1
     * @param param2
     *            验证对象2
     * @param bizMsg
     *            给渠道层用户展示的错误信息
     */
    public static void checkStringEqualsIgnoreCase(String param1, String param2, String bizMsg) {
        if (!StringUtil.equalsIgnoreCase(param1, param2)) {
            throw new BizException(BizException.PARAM_ERROR.getCode(), bizMsg);
        }
    }

    /**
     * 验证字符串不相等，相等就抛异常，不相等就通过
     * 
     * @param param1
     *            验证对象1
     * @param param2
     *            验证对象2
     * @param bizMsg
     *            给渠道层用户展示的错误信息
     */
    public static void checkStringNotEquals(String param1, String param2, String bizMsg) {
        if (StringUtil.equals(param1, param2)) {
            throw new BizException(BizException.PARAM_ERROR.getCode(), bizMsg);
        }
    }

    /**
     * 比较两个字符串（大小写不敏感），相等就抛异常，不相等就通过
     * 
     * @param param1
     *            验证对象1
     * @param param2
     *            验证对象2
     * @param bizMsg
     *            给渠道层用户展示的错误信息
     */
    public static void checkStringNotEqualsIgnoreCase(String param1, String param2, String bizMsg) {
        if (StringUtil.equalsIgnoreCase(param1, param2)) {
            throw new BizException(BizException.PARAM_ERROR.getCode(), bizMsg);
        }
    }

    /**
     * 判断输入的字符串是否是合法用户姓名，长度未限制。
     * 
     * @param userName
     *            待判断的输入字符串
     * @param bizMsg
     *            给渠道层用户展示的错误信息
     * @return True:是合法的用户姓名 False:不是合法的用户姓名
     */
    public static void isValidUserName(String userName, String bizMsg) {
        if (!CommonValidateUtil.isValidUserName(userName)) {
            throw new BizException(BizException.PARAM_ERROR.getCode(), bizMsg);
        }
    }

    /**
     * 身份证号码验证
     * 
     * @param idNo
     * @param bizMsg
     *            给渠道层用户展示的错误信息
     */
    public static void checkIdNo(String idNo, String bizMsg) {
        // 18位长度验证
        checkStringNotBlank(idNo, "证件号码不能为空!");
        if (!(idNo.length() == 15 || idNo.length() == 18)) {
            throw new BizException(BizException.PARAM_ERROR.getCode(), bizMsg);
        }
    }

    /**
     * 邮箱验证
     *
     * @param email 邮箱地址
     * @param bizMsg 业务异常
     */
    public static void checkEmail(String email, String bizMsg) {
        if (!CommonValidateUtil.isValidEmail(email)) {
            throw new BizException(BizException.PARAM_ERROR.getCode(), bizMsg);
        }
    }

    /**
     * 手机号码验证
     * @param mobile 邮箱地址
     * @param bizMsg 业务异常
     */
    public static void checkMobile(String mobile, String bizMsg) {
        if (!CommonValidateUtil.isValidMobile(mobile)) {
            throw new BizException(BizException.PARAM_ERROR.getCode(), bizMsg);
        }
    }

    /**
     * bigDecimal类型验证,数据<=0抛异常
     * 
     * @param bigDecimal
     * @param bizMsg
     *            给渠道层用户展示的错误信息
     */
    public static void checkBigDecimalNotNullAndNotThanZero(BigDecimal bigDecimal, String bizMsg) {
        checkObjectNotNull(bigDecimal, bizMsg);
        checkIntNotThanZero(bigDecimal.compareTo(BigDecimal.ZERO), bizMsg);
    }

    /**
     * bigDecimal类型验证,数据<0抛异常
     * @param bigDecimal
     * @param bizMsg
     *              给渠道层用户展示的错误信息
     */
    public static void checkBigDecimalNotNullAndNotLessZero(BigDecimal bigDecimal, String bizMsg) {
        checkObjectNotNull(bigDecimal, bizMsg);
        checkIntNotLessZero(bigDecimal.compareTo(BigDecimal.ZERO), bizMsg);
    }

    /**
     * 检验日期时间是否小于某个时间，若大于则抛异常
     * 
     * @param checkDate 检验时间
     * @param date
     */
    public static void checkDateNotGreaterThanDate(String checkDate, String date, String bizMsg) {
        if (DateUtil.compareTime(DateUtil.parse(date, DateUtil.DEFAULT_DATE_TIME), DateUtil.parse(checkDate, DateUtil.DEFAULT_DATE_TIME), 4) <= 0) {
            throw new BizException(BizException.PARAM_ERROR.getCode(), bizMsg);
        }
    }

    /**
     * 判断当前数字是否在两个数字之间
     * 
     * @param checkNum
     * @param startNum
     * @param endNum
     * @param bizMsg
     */
    public static void checkNumBetweenBeginToEnd(BigDecimal checkNum, BigDecimal startNum, BigDecimal endNum, String bizMsg) {
        if (checkNum == null || startNum == null || endNum == null) {
            throw new BizException(BizException.PARAM_ERROR.getCode(), "判断当前数字是否在两个数字参数异常,存在为null情况");
        }
        if (checkNum.compareTo(startNum) < 0 || checkNum.compareTo(endNum) > 0) {
            throw new BizException(BizException.PARAM_ERROR.getCode(), bizMsg);
        }
    }

    /**
     * 验证对象为null，不为null抛异常
     * 
     * @param param
     *            验证对象
     * @param bizMsg
     *            给渠道层用户展示的错误信息
     * **/
    public static void checkObjectIsNull(Object param, String bizMsg) {
        if (!ObjectUtil.isEmpty(param)) {
            throw new BizException(BizException.PARAM_ERROR.getCode(), bizMsg);
        }
    }

    /**
     * 验证集合对象非空且长度大于0 为空抛异常
     * 
     * @param list
     *            验证对象
     * @param bizMsg
     *            给客户展示的错误信息
     */
    public static void checkListNotNullAndSizeGreaterZero(List<?> list, String bizMsg) {
        if (null == list) {
            throw new BizException(BizException.PARAM_ERROR.getCode(), bizMsg);
        }
        if (list.size() == 0) {
            throw new BizException(BizException.PARAM_ERROR.getCode(), bizMsg);
        }
    }

    /**
     * 验证数组对象非空且长度大于0 为空抛异常
     * 
     * @param list
     *            验证对象
     * @param bizMsg
     *            给客户展示的错误信息
     */
    public static void checkArrayNotNullAndSizeGreaterZero(Object[] objs, String bizMsg) {
        checkObjectNotNull(objs, bizMsg);
        if (objs.length == 0) {
            throw new BizException(BizException.PARAM_ERROR.getCode(), bizMsg);
        }
    }

    /**
     * 若num2小于num1，抛异常
     * 
     * @param num1
     * @param num2
     */
    public static void checkIntCompare(Integer num1, Integer num2, String bizMsg) {
        if (num1 == null || num2 == null || num1 <= 0 || num2 <= 0) {
            throw new BizException(BizException.PARAM_ERROR.getCode(), bizMsg);
        }
        if (num2 < num1) {
            throw new BizException(BizException.PARAM_ERROR.getCode(), bizMsg);
        }
    }

    /**
     * 若num2小于num1，抛异常
     * 
     * @param num1
     * @param num2
     */
    public static void checkDoubleCompare(Double num1, Double num2, String bizMsg) {
        if (num1 == null || num2 == null || num1 <= 0 || num2 <= 0) {
            throw new BizException(BizException.PARAM_ERROR.getCode(), bizMsg);
        }
        if (num2 < num1) {
            throw new BizException(BizException.PARAM_ERROR.getCode(), bizMsg);
        }
    }

    /**
     * 若num2小于num1，抛异常
     * 
     * @param num1
     * @param num2
     */
    public static void checkDoubleCompare2(Double num1, Double num2, String bizMsg) {
        if (num1 == null || num2 == null || num1 < 0 || num2 < 0) {
            throw new BizException(BizException.PARAM_ERROR.getCode(), bizMsg);
        }
        if (num2 < num1) {
            throw new BizException(BizException.PARAM_ERROR.getCode(), bizMsg);
        }
    }

    /**
     * 验证字符串是否以指定字符开头，不是抛异常
     * @param param 字符串
     * @param begin 开头字符
     */
    public static void checkStringBeginChar(String param, String begin, String bizMsg) {
        if (!param.startsWith(begin)) {
            throw new BizException(BizException.PARAM_ERROR.getCode(), bizMsg);
        }
    }

    /**
     * 判定字符串不能包含某字符，包含抛异常
     * @param param
     * @param containsStr
     * @param bizMsg
     */
    public static void checkStringNotContainsChar(String param, String containsStr, String bizMsg) {
        if (StringUtil.contains(param, containsStr)) {
            throw new BizException(BizException.PARAM_ERROR.getCode(), bizMsg);
        }
    }

    /**
     * 比较开始时间点与截止时间点的大小，若截止时间小于或等于开始时间，则抛异常
     * @param str 字符串
     */
    public static void checkTimeNotGreaterThanTime(String startTime, String endTime, String bizMsg) {
        if ((startTime.compareTo(endTime)) >= 0){
        	throw new BizException(BizException.PARAM_ERROR.getCode(), bizMsg);
        }
    }
}
