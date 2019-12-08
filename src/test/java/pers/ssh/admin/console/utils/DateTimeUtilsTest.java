package pers.ssh.admin.console.utils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

/**
 * Author:   Dennis Su
 * Date:     2019/12/8 10:23 上午
 * Description:
 */
public class DateTimeUtilsTest {

    @Test
    public void testGetDate() {
        try {
            final Date date = DateTimeUtils.getDate("2019-10-10 10:00:00");
            final Date assertDate = this.getAeertDate();

            Assert.assertEquals(date, assertDate);
        } catch (final ParseException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testFormatDate() {
        final Date assertDate = this.getAeertDate();
        final String dateStr = DateTimeUtils.formatDate(assertDate);

        Assert.assertEquals(dateStr, "2019-10-10 10:00:00");
    }

    private Date getAeertDate() {
        final Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2019);
        cal.set(Calendar.MONTH, 9);
        cal.set(Calendar.DATE, 10);
        cal.set(Calendar.HOUR_OF_DAY, 10);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        final Date assertDate = cal.getTime();
        return assertDate;
    }
}
