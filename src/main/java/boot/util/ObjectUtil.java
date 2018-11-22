package boot.util;

/**
 * xcwlkj.com Inc.
 * Copyright (c) 2015-2017 All Rights Reserved.
 */

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;


/**
 * 有关<code>Object</code>处理的工具类。
 * <p>
 * 这个类中的每个方法都可以“安全”地处理<code>null</code>，而不会抛出<code>NullPointerException</code>。
 * </p>
 * @author danfeng.zhou
 * @version $Id: ObjectUtil.java, v 0.1 2017年4月27日 下午6:08:04 danfeng.zhou Exp $
 */
public class ObjectUtil {
    /* ============================================================================ */
    /*  常量和singleton。                                                           */
    /* ============================================================================ */

    /**
     * 用于表示<code>null</code>的常量。
     * 
     * <p>
     * 例如，<code>HashMap.get(Object)</code>方法返回<code>null</code>有两种可能：
     * 值不存在或值为<code>null</code>。而这个singleton可用来区别这两种情形。
     * </p>
     * 
     * <p>
     * 另一个例子是，<code>Hashtable</code>的值不能为<code>null</code>。
     * </p>
     */
    public static final Object NULL = new Serializable() {
        private static final long serialVersionUID = 7092611880189329093L;

        private Object readResolve() {
            return NULL;
        }
    };

    /* ============================================================================ */
    /*  默认值函数。                                                                */
    /*                                                                              */
    /*  当对象为null时，将对象转换成指定的默认对象。                                */
    /* ============================================================================ */

    /**
     * 如果对象为<code>null</code>，则返回指定默认对象，否则返回对象本身。
     * <pre>
     * ObjectUtil.defaultIfNull(null, null)      = null
     * ObjectUtil.defaultIfNull(null, "")        = ""
     * ObjectUtil.defaultIfNull(null, "zz")      = "zz"
     * ObjectUtil.defaultIfNull("abc", *)        = "abc"
     * ObjectUtil.defaultIfNull(Boolean.TRUE, *) = Boolean.TRUE
     * </pre>
     *
     * @param object 要测试的对象
     * @param defaultValue 默认值
     *
     * @return 对象本身或默认对象
     */
    public static Object defaultIfNull(Object object, Object defaultValue) {
        return (object != null) ? object : defaultValue;
    }

    /* ============================================================================ */
    /*  比较函数。                                                                  */
    /*                                                                              */
    /*  以下方法用来比较两个对象是否相同。                                          */
    /* ============================================================================ */


    /* ============================================================================ */
    /*  Hashcode函数。                                                              */
    /*                                                                              */
    /*  以下方法用来取得对象的hash code。                                           */
    /* ============================================================================ */



    /**
     * 判断对象是否Empty(null或元素为0)<br>
     * 实用于对如下对象做判断:String Collection及其子类 Map及其子类
     * 
     * @param pObj 待检查对象
     * @return boolean 返回的布尔值
     */
    public static final boolean isEmpty(Object pObj) {
        if (pObj == null){
        	return true;
        }
        if (pObj == ""){
        	return true;
        }
        if (pObj instanceof String) {
            if (((String) pObj).trim().length() == 0) {
                return true;
            }
        } else if (pObj instanceof Collection<?>) {
            if (((Collection<?>) pObj).size() == 0) {
                return true;
            }
        } else if (pObj instanceof Map<?, ?>) {
            if (((Map<?, ?>) pObj).size() == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断对象是否为NotEmpty(!null或元素>0)<br>
     * 实用于对如下对象做判断:String Collection及其子类 Map及其子类
     * 
     * @param pObj 待检查对象
     * @return boolean 返回的布尔值
     */
    public static final boolean isNotEmpty(Object pObj) {
        if (pObj == null){
        	return false;
        }
        if (pObj == ""){
        	return false;
        }
        if (pObj instanceof String) {
            if (((String) pObj).trim().length() == 0) {
                return false;
            }
        } else if (pObj instanceof Collection<?>) {
            if (((Collection<?>) pObj).size() == 0) {
                return false;
            }
        } else if (pObj instanceof Map<?, ?>) {
            if (((Map<?, ?>) pObj).size() == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 取得对象的原始的hash值, 如果对象为<code>null</code>, 则返回<code>0</code>。
     * 
     * <p>
     * 该方法使用<code>System.identityHashCode</code>来取得hash值，该值不受对象本身的<code>hashCode</code>方法的影响。
     * </p>
     *
     * @param object 对象
     *
     * @return hash值
     */
    public static int identityHashCode(Object object) {
        return (object == null) ? 0 : System.identityHashCode(object);
    }



    /* ============================================================================ */
    /*  比较对象的类型。                                                            */
    /* ============================================================================ */

    /**
     * 检查两个对象是否属于相同类型。<code>null</code>将被看作任意类型。
     *
     * @param object1 对象1
     * @param object2 对象2
     *
     * @return 如果两个对象有相同的类型，则返回<code>true</code>
     */
    public static boolean isSameType(Object object1, Object object2) {
        if ((object1 == null) || (object2 == null)) {
            return true;
        }

        return object1.getClass().equals(object2.getClass());
    }

    /* ============================================================================ */
    /*  toString方法。                                                              */
    /* ============================================================================ */

    /**
     * 取得对象的<code>toString()</code>的值，如果对象为<code>null</code>，则返回空字符串<code>""</code>。
     * <pre>
     * ObjectUtil.toString(null)         = ""
     * ObjectUtil.toString("")           = ""
     * ObjectUtil.toString("bat")        = "bat"
     * ObjectUtil.toString(Boolean.TRUE) = "true"
     * ObjectUtil.toString([1, 2, 3])    = "[1, 2, 3]"
     * </pre>
     *
     * @param object 对象
     *
     * @return 对象的<code>toString()</code>的返回值，或空字符串<code>""</code>
     */
    public static String toString(Object object) {
        return (object == null) ? StringUtil.EMPTY_STRING : (object.getClass().isArray() ? arrToString(object) : object.toString());
    }

    /**
     * 取得对象的<code>toString()</code>的值，如果对象为<code>null</code>，则返回指定字符串。
     * <pre>
     * ObjectUtil.toString(null, null)           = null
     * ObjectUtil.toString(null, "null")         = "null"
     * ObjectUtil.toString("", "null")           = ""
     * ObjectUtil.toString("bat", "null")        = "bat"
     * ObjectUtil.toString(Boolean.TRUE, "null") = "true"
     * ObjectUtil.toString([1, 2, 3], "null")    = "[1, 2, 3]"
     * </pre>
     *
     * @param object 对象
     * @param nullStr 如果对象为<code>null</code>，则返回该字符串
     *
     * @return 对象的<code>toString()</code>的返回值，或指定字符串
     */
    public static String toString(Object object, String nullStr) {
        return (object == null) ? nullStr : (object.getClass().isArray() ? arrToString(object) : object.toString());
    }
    
    private static String arrToString(Object object) {
		return toString(object, "[]", "<null>");
	}

	/**
     * 将数组转换成易于阅读的字符串表示。
     * 
     * <p>
     * 如果数组是<code>null</code>则返回指定字符串，支持多维数组。 如果数组元素为<code>null</code>，则显示指定字符串。
     * <pre>
     * ArrayUtil.toString(null, "null", "NULL")                              = "null"
     * ArrayUtil.toString(new int[] {1, 2, 3}, "null", "NULL")               = "[1, 2, 3]"
     * ArrayUtil.toString(new boolean[] {true, false, true}, "null", "NULL") = "[true, false, true]"
     * ArrayUtil.toString(new Object[] {
     *                       {1, 2, 3},  // 嵌套数组
     *                       hello,      // 嵌套非数组
     *                       null,       // 嵌套null
     *                       {},         // 嵌套空数组
     *                       {2, 3, 4}   // 嵌套数组
     *                    }, "null", "NULL")                                 = "[[1, 2, 3], hello, NULL, [], [2, 3, 4]]"
     * </pre>
     * </p>
     *
     * @param array 要转换的数组
     * @param nullArrayStr 如果数组是<code>null</code>，则返回此字符串
     * @param nullElementStr 如果数组中的元素为<code>null</code>，则返回此字符串
     *
     * @return 字符串表示，或返回指定字符串表示<code>null</code>
     */
    public static String toString(Object array, String nullArrayStr, String nullElementStr) {
        if (array == null) {
            return nullArrayStr;
        }

        StringBuffer buffer = new StringBuffer();

        toString(buffer, array, nullArrayStr, nullElementStr);

        return buffer.toString();
    }
    
    
    /**
     * 将数组转换成易于阅读的字符串表示。<code>null</code>将被看作空数组。 支持多维数组。
     *
     * @param buffer 将转换后的字符串加入到这个<code>StringBuffer</code>中
     * @param array 要转换的数组
     * @param nullArrayStr 如果数组是<code>null</code>，则返回此字符串
     * @param nullElementStr 如果数组中的元素为<code>null</code>，则返回此字符串
     */
    private static void toString(StringBuffer buffer, Object array, String nullArrayStr,
                                 String nullElementStr) {
        if (array == null) {
            buffer.append(nullElementStr);
            return;
        }

        if (!array.getClass().isArray()) {
            buffer.append(ObjectUtil.toString(array, nullElementStr));
            return;
        }

        buffer.append('[');

        // array为数组
        if (array instanceof long[]) {
            long[] longArray = (long[]) array;
            int length = longArray.length;

            for (int i = 0; i < length; i++) {
                if (i > 0) {
                    buffer.append(", ");
                }

                buffer.append(longArray[i]);
            }
        } else if (array instanceof int[]) {
            int[] intArray = (int[]) array;
            int length = intArray.length;

            for (int i = 0; i < length; i++) {
                if (i > 0) {
                    buffer.append(", ");
                }

                buffer.append(intArray[i]);
            }
        } else if (array instanceof short[]) {
            short[] shortArray = (short[]) array;
            int length = shortArray.length;

            for (int i = 0; i < length; i++) {
                if (i > 0) {
                    buffer.append(", ");
                }

                buffer.append(shortArray[i]);
            }
        } else if (array instanceof byte[]) {
            byte[] byteArray = (byte[]) array;
            int length = byteArray.length;

            for (int i = 0; i < length; i++) {
                if (i > 0) {
                    buffer.append(", ");
                } else {
                    buffer.append("0x");
                }

                String hexStr = Integer.toHexString(0xFF & byteArray[i]).toUpperCase();

                if (hexStr.length() == 0) {
                    buffer.append("00");
                } else if (hexStr.length() == 1) {
                    buffer.append("0");
                }

                buffer.append(hexStr);
            }
        } else if (array instanceof double[]) {
            double[] doubleArray = (double[]) array;
            int length = doubleArray.length;

            for (int i = 0; i < length; i++) {
                if (i > 0) {
                    buffer.append(", ");
                }

                buffer.append(doubleArray[i]);
            }
        } else if (array instanceof float[]) {
            float[] floatArray = (float[]) array;
            int length = floatArray.length;

            for (int i = 0; i < length; i++) {
                if (i > 0) {
                    buffer.append(", ");
                }

                buffer.append(floatArray[i]);
            }
        } else if (array instanceof boolean[]) {
            boolean[] booleanArray = (boolean[]) array;
            int length = booleanArray.length;

            for (int i = 0; i < length; i++) {
                if (i > 0) {
                    buffer.append(", ");
                }

                buffer.append(booleanArray[i]);
            }
        } else if (array instanceof char[]) {
            char[] charArray = (char[]) array;
            int length = charArray.length;

            for (int i = 0; i < length; i++) {
                if (i > 0) {
                    buffer.append(", ");
                }

                buffer.append(charArray[i]);
            }
        } else {
            Object[] objectArray = (Object[]) array;
            int length = objectArray.length;

            for (int i = 0; i < length; i++) {
                if (i > 0) {
                    buffer.append(", ");
                }

                toString(buffer, objectArray[i], nullArrayStr, nullElementStr);
            }
        }

        buffer.append(']');
    }
    
    /**
     * 检查数组是否为<code>null</code>或空数组<code>[]</code>。
     * <pre>
     * ArrayUtil.isEmpty(null)              = true
     * ArrayUtil.isEmpty(new String[0])     = true
     * ArrayUtil.isEmpty(new String[10])    = false
     * </pre>
     *
     * @param array 要检查的数组
     *
     * @return 如果为空, 则返回<code>true</code>
     */
    public static boolean isEmpty(Object[] array) {
        return ((array == null) || (array.length == 0));
    }
}
