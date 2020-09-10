import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test for Statistics for project 2
 * 
 * @author yangzomdolma
 * @version 2018-10-04
 *
 */
public class StatisticsTest
{
    protected final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss z";
    GregorianCalendar utcDateTime = new GregorianCalendar(2018, 8, 30, 17, 45, 30);

    String DateTimeStr = "2018-08-30 17:45";
    StatsType statstype;
    Statistics s = new Statistics(22.5, "JAYX", DateTimeStr, 10, statstype);

    Statistics stat = new Statistics(22.5, "JAYX", utcDateTime, 10, statstype);

    /**
     * Test for toString method
     */
    @Test
    public void testToString()
    {
        String expected = null;
        String actual = stat.toString();
        assertEquals(expected, actual);
    }

    /**
     * test for CreateDateFromString
     * 
     * @throws ParseException
     */
    @Test
    public void testCreateDateFromString() throws ParseException
    {

        SimpleDateFormat f = new SimpleDateFormat(s.DATE_TIME_FORMAT);
        Date d = f.parse("2018-08-30T17:45:30 UTC");
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(d);
        GregorianCalendar expected = s.createDateFromString("2018-08-30T17:45:30 UTC");
        assertEquals(expected, (GregorianCalendar) f.getCalendar());

    }

    /**
     * Test for getNumberOfReportingStations
     */

    @Test
    public void testGetNumberOfReportingStations()
    {
        int expected = 10;
        int actual = s.getNumberOfReportingStations();
        assertEquals("Error", expected, actual);

    }

    /**
     * Test for getUTCDateTimeString
     */
    @Test
    public void testGetUTCDateTimeString()
    {
        String expected = "gregory";

        assertEquals("Faiure", expected, stat.getUTCDateTimeString());

    }

    /**
     * Test for newThan()
     * 
     * @throws ParseException
     */

    @Test
    public void testNewerThan() 
    {
        GregorianCalendar newer = new GregorianCalendar(2017, 8, 30, 17, 45, 30);
        // GregorianCalendar utcDateTime = new GregorianCalendar(2018, 8, 30, 17, 45,
        // 30);
        boolean expected = true;
        boolean actual = newer.before(utcDateTime);
        Assert.assertEquals(expected, actual);

    }

    /**
     * Test for olderThan()
     */

    @Test
    public void testOlderThan()
    {
        GregorianCalendar newer = new GregorianCalendar(2017, 8, 30, 17, 45, 30);
        // GregorianCalendar utcDateTime = new GregorianCalendar(2018, 8, 30, 17, 45,
        // 30);
        boolean expected = false;
        boolean actual = newer.after(utcDateTime);
        Assert.assertEquals(expected, actual);
    }

    /**
     * Test for sameAs()
     */

    @Test
    public void testSameAs()
    {
        GregorianCalendar sameDate = new GregorianCalendar(2018, 8, 30, 17, 45, 30);
        // GregorianCalendar utcDateTime = new GregorianCalendar(2018, 8, 30, 17, 45,
        // 30);
        boolean expected = true;
        boolean actual = sameDate.equals(utcDateTime);
        Assert.assertEquals(expected, actual);

    }

}
