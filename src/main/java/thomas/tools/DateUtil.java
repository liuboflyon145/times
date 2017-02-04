package thomas.tools;


import javax.activation.UnsupportedDataTypeException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalUnit;
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
        return LocalDateTime.now().format(TypeTools.DATETIMEPATTERN);
    }

    /**
     * 当前时间日期
     *
     * @return LocalDateTime
     */
    public static LocalDateTime currentDateTime() {
        return LocalDateTime.now();
    }

    /**
     * 当前日期
     *
     * @return LocalDate
     */
    public static LocalDate currentDate() {
        return LocalDate.now();
    }

    /**
     * 当天日期
     *
     * @return YYYY-MM-dd
     */
    public static String today() {
        return LocalDateTime.now().format(TypeTools.DATEPATTERN);
    }

    /**
     * 当前时间
     *
     * @return HH:mm:ss
     */
    public static String time() {
        return LocalDateTime.now().format(TypeTools.TIMEPATTERN);
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
     *
     * @param dateTime
     * @param tClass
     * @return
     * @throws Exception
     */
    public static synchronized Object parse(String dateTime, Class<?> tClass) throws Exception {
        Objects.requireNonNull(dateTime, "dateTime");
        Object obj = null;

        switch (tClass.getName()) {
            case TypeTools.LOCALDATETIME:
                dateTime = dateTime.trim().replaceAll(" ", "T");
                obj = LocalDateTime.parse(dateTime);
                break;
            case TypeTools.LOCALDATE:
                dateTime = dateTime.trim().split("\\s|T")[0];
                obj = LocalDate.parse(dateTime);
                break;
            case TypeTools.LOCALTIME:
                dateTime = dateTime.trim().split("\\s|T")[1];
                obj = LocalTime.parse(dateTime);
                break;
            case TypeTools.DATE:
                dateTime = dateTime.trim().replaceAll("T", " ");
                obj = strToDate(dateTime);
                break;
            default:
                throw new UnsupportedDataTypeException("unsupported " + dateTime + " for " + tClass.getName());
        }
        log.info("=====" + tClass.getName() + "    obj=" + obj);
        return obj;
    }

    /**
     * 将string转换成Date
     *
     * @param strDate
     * @return
     */
    private static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat(TypeTools.DEFAULT_PATTERN);
        ParsePosition pos = new ParsePosition(0);
        return formatter.parse(strDate, pos);
    }

    /**
     * 时间日期加减运算
     *
     * @param obj
     * @param num
     * @param durationType
     * @return
     */
    public static synchronized Object plus(Object obj, int num, String durationType) {
        switch (obj.getClass().getName()) {
            case TypeTools.LOCALDATETIME:
                return plus((LocalDateTime) obj, num, durationType);
            case TypeTools.LOCALDATE:
                return plus((LocalDate) obj, num, durationType);
            case TypeTools.LOCALTIME:
                return plus((LocalDate) obj, num, durationType);
            default:
                return null;
        }
    }

    private static LocalDateTime plus(LocalDateTime ldt, int num, String durationType) {
        switch (durationType) {
            case TypeTools.YEARS:
                return LocalDateTime.of(plus(ldt.toLocalDate(), num, durationType), ldt.toLocalTime());
            case TypeTools.MONTHS:
                return LocalDateTime.of(plus(ldt.toLocalDate(), num, durationType), ldt.toLocalTime());
            case TypeTools.DAYS:
                return LocalDateTime.of(plus(ldt.toLocalDate(), num, durationType), ldt.toLocalTime());
            case TypeTools.WEEKS:
                return LocalDateTime.of(plus(ldt.toLocalDate(), num, durationType), ldt.toLocalTime());
            case TypeTools.HOURS:
                return LocalDateTime.of(ldt.toLocalDate(), plus(ldt.toLocalTime(), num, durationType));
            case TypeTools.MINUTES:
                return LocalDateTime.of(ldt.toLocalDate(), plus(ldt.toLocalTime(), num, durationType));
            case TypeTools.SECONDS:
                return LocalDateTime.of(ldt.toLocalDate(), plus(ldt.toLocalTime(), num, durationType));
            case TypeTools.NANOS:
                return LocalDateTime.of(ldt.toLocalDate(), plus(ldt.toLocalTime(), num, durationType));
            default:
                return null;
        }
    }

    private static LocalDate plus(LocalDate ld, int num, String durationType) {
        switch (durationType) {
            case TypeTools.DAYS:
                return ld.plusDays(num);
            case TypeTools.MONTHS:
                return ld.plusMonths(num);
            case TypeTools.YEARS:
                return ld.plusYears(num);
            case TypeTools.WEEKS:
                return ld.plusWeeks(num);
            default:
                return null;
        }
    }

    private static LocalTime plus(LocalTime lt, int num, String durationType) {
        switch (durationType) {
            case TypeTools.HOURS:
                return lt.plusHours(num);
            case TypeTools.MINUTES:
                return lt.plusMinutes(num);
            case TypeTools.SECONDS:
                return lt.plusSeconds(num);
            case TypeTools.NANOS:
                return lt.plusNanos(num);
            default:
                return null;
        }
    }


    /**
     * 获取当月第一天、
     *
     * @return LocalDate
     */
    public static LocalDate getFirstDayOfMonth() {
        LocalDate curr = currentDate();
        return LocalDate.of(curr.getYear(), curr.getMonth(), 1);
    }

    /**
     * 当月最后一天
     *
     * @return LocalDate
     */
    public static LocalDate getLastDayOfMonth() {
        LocalDate local = currentDate();
        return LocalDate.of(local.getYear(), local.getMonth(), local.lengthOfMonth());
    }


    /**
     * 时间比较
     *
     * @param thisTime
     * @param thatTime
     * @return
     */
    public static boolean isBefore(LocalDateTime thisTime, LocalDateTime thatTime) {
        return thisTime.isBefore(thatTime);
    }

    /**
     * 时间比较
     *
     * @param thisDate
     * @param thatDate
     * @return
     */
    public static boolean isBefore(LocalDate thisDate, LocalDate thatDate) {
        return thisDate.isBefore(thatDate);
    }

    /**
     * 获取当前时间是第几周
     *
     * @param localDateTime
     * @return
     */
    public static int getNthWeek(LocalDateTime localDateTime) {
        return localDateTime.getDayOfWeek().getValue();
    }

    /**
     * 获取当前日期是第几周
     *
     * @param localDate
     * @return
     */
    public static int getNthWeek(LocalDate localDate) {
        return localDate.getDayOfWeek().getValue();
    }
}
