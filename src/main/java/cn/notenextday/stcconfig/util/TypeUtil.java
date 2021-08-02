package cn.notenextday.stcconfig.util;


import org.apache.logging.log4j.util.Strings;

import java.util.Objects;

/**
 * 类型转换工具类
 *
 * @Description
 * @Author xingzhi.lv
 * @Version 1.0
 * @Date 2021/6/4 16:06
 */
public class TypeUtil {

    /**
     * 类型转换-null返回
     * long-int
     *
     * @return
     */
    public static Integer longToInt(Long num) {
        if (Objects.isNull(num)) {
            return null;
        }
        return num.intValue();
    }

    /**
     * 类型转换-null返回
     * int-long
     *
     * @return
     */
    public static Long intToLong(Integer num) {
        if (Objects.isNull(num)) {
            return null;
        }
        return num.longValue();
    }

    /**
     * 类型转换-null返回
     * int-byte
     *
     * @return
     */
    public static Byte intToByte(Integer num) {
        if (Objects.isNull(num)) {
            return null;
        }
        return num.byteValue();
    }

    /**
     * 类型转换-null返回
     * long-string
     *
     * @return
     */
    public static String longToString(Long num) {
        if (Objects.isNull(num)) {
            return null;
        }
        return num.toString();
    }

    /**
     * 类型转换-null返回
     * string-long
     *
     * @return
     */
    public static Long stringToLong(String num) {
        if (Strings.isEmpty(num)) {
            return null;
        }
        return Long.parseLong(num);
    }

    /**
     * 类型转换-null返回
     * int-string
     *
     * @return
     */
    public static String intToString(Integer num) {
        if (Objects.isNull(num)) {
            return null;
        }
        return num.toString();
    }

    /**
     * 类型转换-null返回
     * string-int
     *
     * @return
     */
    public static Integer stringToInt(String num) {
        if (Strings.isEmpty(num)) {
            return null;
        }
        return Integer.parseInt(num);
    }

    /**
     * 类型转换-null返回
     * byte-String
     *
     * @return
     */
    public static String byteToString(Byte num) {
        if (Objects.isNull(num)) {
            return null;
        }
        return num.toString();
    }

    /**
     * 类型转换-null返回
     * string-byte
     *
     * @return
     */
    public static Byte stringToByte(String num) {
        if (Strings.isEmpty(num)) {
            return null;
        }
        return Byte.valueOf(num);
    }

}
