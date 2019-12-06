package pers.ssh.admin.console.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author:   dsu01
 * Date:     2019/12/5 11:34 上午
 * Description: Dta time format utility.
 */
public class DateTimeUtils {

    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final SimpleDateFormat defaultDateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);

    public synchronized static Date getDate(final String dateStr) throws ParseException {
        return getDate(dateStr, defaultDateFormat);
    }

    public synchronized static Date getDate(final String dateStr, final SimpleDateFormat formatter) throws ParseException {
        return formatter.parse(dateStr);
    }

    public synchronized static String formatDate(final Date date) {
        return formatDate(date, defaultDateFormat);
    }

    public synchronized static String formatDate(final Date date, final SimpleDateFormat formatter) {
        return formatter.format(date);
    }

}
