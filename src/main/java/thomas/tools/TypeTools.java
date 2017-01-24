package thomas.tools;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;

/**
 * Created by liubo16 on 2017/1/23.
 */
public class TypeTools {

    public static final String DEFAULT_PATTERN = "YYYY-MM-dd HH:mm:ss";
    public static final String DATE_PATTERN = "YYYY-MM-dd";
    public static final String TIME_PATTERN = "HH:mm:ss";

    public static final DateTimeFormatter DATETIMEPATTERN = DateTimeFormatter.ofPattern(DEFAULT_PATTERN);

    public static final DateTimeFormatter DATEPATTERN = DateTimeFormatter.ofPattern(DATE_PATTERN);

    public static final DateTimeFormatter TIMEPATTERN = DateTimeFormatter.ofPattern(TIME_PATTERN);

    public static final String DAYS = "days";
    public static final String LOCALDATETIME = "java.time.LocalDateTime";
    public static final String LOCALDATE = "java.time.LocalDate";
    public static final String LOCALTIME = "java.time.LocalTime";
    public static final String DATE = "java.util.Date";
    public static final String MONTHS = "months";
    public static final String YEARS = "years";
    public static final String WEEKS = "weeks";
    public static final String NANOS = "nanos";
    public static final String SECONDS = "seconds";
    public static final String MINUTES = "minutes";

    public static final String HOURS = "hours";
}