package thomas.tools;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by liubo16 on 2017/1/23.
 */
public class DateUtilTest {

    @Test
    public void now() throws Exception {
        log("now", DateUtil.now());
    }

    @Test
    public void today() throws Exception {
        log("today", DateUtil.now());
    }

    @Test
    public void time() throws Exception {
        log("time", DateUtil.time());
    }

    @Test
    public void format() throws Exception {
//        log("java.util.Date format",DateUtil.format(new Date(),TypeTools.DATE_PATTERN));
        log("java.sql.Date format", DateUtil.format(new java.sql.Date(1485166922910L), TypeTools.DEFAULT_PATTERN));
    }

    @Test
    public void parse() throws Exception {

        log(DateUtil.parse("2017-01-23T10:09:08", Date.class).toString());
    }

    public void log(String... params) {
        for (int i = 0; i < params.length; i++) {
            System.out.println(params[i]);
        }
    }

    @Test
    public void plus(){
        LocalDateTime ldt = DateUtil.currentDateTime();
        Object obj = DateUtil.plus(ldt,6,TypeTools.HOURS);
        log(obj.toString());
    }


    @Test
    public void test1() {
        String str = "1987-09-22 12:12:45";
        str = str.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "").replaceAll("T", "");
        int[] splitData = new int[]{4, 2, 2, 2, 2, 2};
        int stop = 0;
        for (int i = 0, len = str.length(); i < splitData.length; i++) {
            if (stop >= len)
                break;
            String tmp = str.substring(stop, stop + splitData[i]);
            log(tmp);
            stop += splitData[i];
        }
    }
}