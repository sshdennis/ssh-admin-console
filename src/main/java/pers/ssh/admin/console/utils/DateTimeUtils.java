package pers.ssh.admin.console.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author:   Dennis Su
 * Date:     2019/12/5 11:34 上午
 * Description: Dta time format utility.
 */
public class DateTimeUtils {

    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final SimpleDateFormat defaultDateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);

    /**
     * Get Date from the given Date string with a default format: yyyy-MM-dd HH:mm:ss.
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public synchronized static Date getDate(final String dateStr) throws ParseException {
        return getDate(dateStr, defaultDateFormat);
    }

    /**
     * Get Date from the given Date string with the given format.
     *
     * @param dateStr
     * @param formatter
     * @return
     * @throws ParseException
     */
    public synchronized static Date getDate(final String dateStr, final SimpleDateFormat formatter) throws ParseException {
        return formatter.parse(dateStr);
    }

    /**
     * Format Date to String with a default format: yyyy-MM-dd HH:mm:ss.
     *
     * @param date
     * @return
     */
    public synchronized static String formatDate(final Date date) {
        return formatDate(date, defaultDateFormat);
    }

    /**
     * Format Date to String with the given format.
     *
     * @param date
     * @param formatter
     * @return
     */
    public synchronized static String formatDate(final Date date, final SimpleDateFormat formatter) {
        return formatter.format(date);
    }
}
