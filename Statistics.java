import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * 
 * @author yangzomdolma
 * @version 2018-10-04
 */
public class Statistics extends Observation implements DateTimeComparable
{
    /**
     * The format of the date: yyyy-MM-dd'T'HH:mm:ss z
     */
    protected final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss z";
    /**
     * Gregorian Calendar
     */
    private GregorianCalendar utcDateTime;
    /**
     * The number of reporting stations.
     */
    private int numberOfReportingStations;

    StatsType statType;

    /**
     * 
     * @param value
     * @param stid
     * @param dateTime
     * @param numberOfValidStations
     * @param inStatType
     */

    public Statistics(double value, String stid, GregorianCalendar dateTime, int numberOfValidStations,
            StatsType inStatType)
    {
        super(value, stid);
        this.utcDateTime = dateTime;
        this.numberOfReportingStations = numberOfValidStations;
        this.statType = inStatType;
    }

    /**
     * 
     * @param value
     * @param stid
     * @param dateTimeStr
     * @param numberOfValidStations
     * @param inStatType
     */

    public Statistics(double value, String stid, String dateTimeStr, int numberOfValidStations, StatsType inStatType)
    {
        super(value, stid);
        this.numberOfReportingStations = numberOfValidStations;
        this.statType = inStatType;

    }

    /**
     * 
     * @param average
     * @param mesonet
     */

    public Statistics(double average, String mesonet)
    {
        super(average, mesonet);
        // TODO Auto-generated constructor stub
    }

    /**
     * 
     * @param dateTimeStr
     * @return date created from string.
     * @throws ParseException
     */

    public GregorianCalendar createDateFromString(String dateTimeStr) throws ParseException
    {
        SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);
        format.parse(dateTimeStr);
        System.out.println((GregorianCalendar) format.getCalendar());
        return (GregorianCalendar) format.getCalendar();

    }

    /**
     * 
     * @param calendar
     * @return a string created from date.
     */

    public String createStringFromDate(GregorianCalendar calendar)
    {
        SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);

        return format.format(calendar);

    }

    /**
     * 
     * @return The number of reporting stations.
     */

    public int getNumberOfReportingStations()
    {

        return numberOfReportingStations;

    }

    /**
     * 
     * @return a string.
     */

    public String getUTCDateTimeString()
    {
        return utcDateTime.getCalendarType();

    }

    /**
     * Returns true if the date if before the said date
     */

    public boolean newerThan(GregorianCalendar inDateTime)
    {

        return utcDateTime.before(inDateTime);

    }

    /**
     * returns true if the date is after the present date.
     */
    public boolean olderThan(GregorianCalendar inDateTime)
    {
        return utcDateTime.after(inDateTime);

    }

    /**
     * Returns true if the date is same as present date.
     */

    public boolean sameAs(GregorianCalendar inDateTime)
    {
        return utcDateTime.equals(inDateTime);

    }

    /**
     * returns nothing.
     */

    public String toString()
    {
        return null;

    }

}
