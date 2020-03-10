package cn.jiang.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转换工具
 */
public class DateFormatUtil {
    /**
     * 日期转字符串
     * @param date 需要转换的日期对象
     * @param pattern 转换的目标格式
     * @return
     */
    public static String DateToStr(Date date,String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 字符串转日期
     * @param source 需要转换的字符串
     * @param pattern 转换目标的格式
     * @return
     * @throws ParseException
     */
    public static Date StrToDate(String source,String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(source);

    }
}
