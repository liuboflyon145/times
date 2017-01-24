package thomas.tools;

import java.time.format.DateTimeFormatter;

/**
 * Created by liubo16 on 2017/1/23.
 */
public class TypeTools {

    public static final String DEFAULT_PATTERN = "YYYY-MM-dd HH:mm:ss";
    public static final String DATE_PATTERN = "YYYY-MM-dd";
    public static final String TIME_PATTERN = "HH:mm:ss";

    public static final DateTimeFormatter DATETIME = DateTimeFormatter.ofPattern(DEFAULT_PATTERN);

    public static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern(DATE_PATTERN);

    public static final DateTimeFormatter TIME = DateTimeFormatter.ofPattern(TIME_PATTERN);

}
