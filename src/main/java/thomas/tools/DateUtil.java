package thomas.tools;


import javax.activation.UnsupportedDataTypeException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Logger;


/**
 * 日期工具类
 * Created by liubo16 on 2017/1/23.
 */
public class DateUtil {
    private static final Logger log = Logger.getLogger("DateUtil");

    /**
     * 当前日期时间
     *
     * @return YYYY-MM-dd HH:mm:ss
     */
    public static String now() {
        return LocalDateTime.now().format(TypeTools.DATETIME);
    }

    /**
     * 当天日期
     *
     * @return YYYY-MM-dd
     */
    public static String today() {
        return LocalDateTime.now().format(TypeTools.DATE);
    }

    /**
     * 当前时间
     *
     * @return HH:mm:ss
     */
    public static String time() {
        return LocalDateTime.now().format(TypeTools.TIME);
    }


    /**
     * 日期格式化，默认使用系统指定的格式
     *
     * @param dateObject
     * @return
     */
    public static synchronized String format(Object dateObject) {
        if (dateObject instanceof LocalDate) {
            return format(dateObject, TypeTools.DATE_PATTERN);
        } else if (dateObject instanceof LocalDateTime) {
            return format(dateObject, TypeTools.DEFAULT_PATTERN);
        } else if (dateObject instanceof LocalTime) {
            return format(dateObject, TypeTools.TIME_PATTERN);
        } else if (dateObject instanceof Date) {
            return format(dateObject, TypeTools.DEFAULT_PATTERN);
        } else if (dateObject instanceof java.sql.Date) {
            return format(dateObject, TypeTools.DEFAULT_PATTERN);
        }
        return null;
    }

    /**
     * 日期格式化，使用给定的格式
     *
     * @param dateObject
     * @param formmater
     * @return
     */
    public static synchronized String format(Object dateObject, String formmater) {
        Objects.requireNonNull(dateObject, "dateObject");
        Objects.requireNonNull(formmater, "formmater");
        if (dateObject instanceof LocalDateTime) {
            return LocalDateTime.from((TemporalAccessor) dateObject).format(DateTimeFormatter.ofPattern(formmater));
        } else if (dateObject instanceof LocalDate) {
            return LocalDate.from((TemporalAccessor) dateObject).format(DateTimeFormatter.ofPattern(formmater));
        } else if (dateObject instanceof LocalTime) {
            return LocalTime.from((TemporalAccessor) dateObject).format(DateTimeFormatter.ofPattern(formmater));
        } else if (dateObject instanceof java.util.Date) {
            return dateFormat((Date) dateObject, formmater);
        } else {
            throw new UnsupportedTemporalTypeException("unsupport this " + dateObject);
        }
    }

    private static String dateFormat(Date date, String formatter) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatter);
        return sdf.format(date);
    }

    /**
     * 将dateTime转换成tClass的Object对象
     * @param dateTime
     * @param tClass
     * @return
     * @throws Exception
     */
    public static synchronized Object parse(String dateTime, Class<?> tClass) throws Exception {
        Objects.requireNonNull(dateTime, "dateTime");
        Object obj = null;

        switch (tClass.getSimpleName()) {
            case "LocalDateTime":
                dateTime = dateTime.trim().replaceAll(" ", "T");
                obj = LocalDateTime.parse(dateTime);
                break;
            case "LocalDate":
                dateTime = dateTime.trim().split("\\s|T")[0];
                obj = LocalDate.parse(dateTime);
                break;
            case "LocalTime":
                dateTime = dateTime.trim().split("\\s|T")[1];
                obj = LocalTime.parse(dateTime);
                break;
            case "Date":
                dateTime = dateTime.trim().replaceAll("T", " ");
                obj = strToDate(dateTime);
                break;
            default:
                throw new UnsupportedDataTypeException("unsupported " + dateTime + " for " + tClass.getName());
        }
        log.info("=====" + tClass.getName() + "    obj=" + obj);
        return obj;
    }

    private static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat(TypeTools.DEFAULT_PATTERN);
        ParsePosition pos = new ParsePosition(0);
        return formatter.parse(strDate, pos);
    }

    public static void plus(Object obj,int num){

    }

}
