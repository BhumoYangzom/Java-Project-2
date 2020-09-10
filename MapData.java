import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.GregorianCalendar;

/**
 * MapData class reads a file and produces the maximums, minimums, and averages
 * for the variables srad, tair, ta9m. It also produces the total for srad.
 */

public class MapData
{
    /**
     * Stores date for solar radiation.
     */
    private ArrayList<Observation> sradData = new ArrayList<Observation>();
    /**
     * Stores the data of air temperature
     */
    private ArrayList<Observation> tairData = new ArrayList<Observation>();
    /**
     * Stores the data of air temperature at 9 m
     */
    private ArrayList<Observation> ta9mData = new ArrayList<Observation>();

    /**
     * Checks the number of missing observations.
     */
    // private final int NUMBER_OF_MISSING_OBSERVATIONS = 10;
    /**
     * The number of stations.
     */
    // private Integer numberOfStations = null;
    /**
     * Air Temperature at 9 meters.
     */
    private final String TA9M = "TA9M";
    /**
     * Air temperature.
     */
    private final String TAIR = "TAIR";
    /**
     * Solar Radiation
     */
    private final String SRAD = "SRAD";
    /**
     * Station id.
     */
    private final String STID = "STID";
    /**
     * index for the station id.
     */
    private int stidPosition = -1;
    /**
     * index for air temperature data.
     */
    private int tairPosition = -1;
    /**
     * index for solar radiation data.
     */
    private int sradPosition = -1;
    /**
     * index for air temperature at 9m.
     */
    private int ta9mPosition = -1;
    /**
     * Stores the string Mesonet.
     */
    private final String MESONET = "Mesonet";
    /**
     * The dirctory in which the files are found.
     */
    String directory;
    /**
     * The minimum value for air temperature.
     */
    Statistics tairMin;
    /**
     * The maximum value for air temperature.
     */
    Statistics tairMax;
    /**
     * The average for air temperature.
     */

    Statistics tairAverage;
    /**
     * The minimum for air temperature at 9 meters.
     */
    Statistics ta9mMin;
    /**
     * The maximum for air temperature at 9 meters.
     */
    Statistics ta9mMax;
    /**
     * The average value for air temperatures at 9 meters.
     */
    Statistics ta9mAverage;
    /**
     * The minimum value for solar radiation.
     */
    Statistics sradMin;
    /**
     * The maximum value for solar radiation.
     */
    Statistics sradMax;
    /**
     * The average value for solar radiation.
     */
    Statistics sradAverage;
    /**
     * The total for solar radiation.
     */
    Statistics sradTotal;
    /**
     * The name of the file.
     */
    String fileName;
    /**
     * Date and time from the Gregorian Calendar.
     */
    private GregorianCalendar utcDateTime;
    Statistics temp;

    /**
     * 
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param directory
     */
    public MapData(int year, int month, int day, int hour, int minute, String directory)
    {
        this.utcDateTime = new GregorianCalendar(year, month, day, hour, minute);
        this.directory = "data";
        createFileName(year, month, day, hour, minute, directory);

    }

    /**
     * 
     * @return Filename that follows the format: yyyyMMddHHmm.mdf. For example a
     *         file name of 201808010700.mdf is formed of year= 2018, month = 08,
     *         day = 01, hour = 07 and minute = 00.
     */

    public String createFileName(int year, int month, int day, int hour, int minute, String directory)
    {
        String yr = Integer.toString(year);
        String mnth = String.format("0%d", month);
        String Day = Integer.toString(day);
        String hr = Integer.toString(hour);
        String mint = Integer.toString(minute);
        String fileName = String.format(("%s%s%s%s%s.mdf"), yr, mnth, Day, hr, mint);
        return fileName;

    }

    /**
     * 
     * @param inParamStr The headers in the data file.
     * @throws IOException
     */

    private void parseParamHeader(String inParamStr) throws IOException
    {

        String[] parsedParamHeader = inParamStr.trim().split("\\s+");
        for (int i = 0; i < parsedParamHeader.length; ++i)
        {
            if (parsedParamHeader[i].equalsIgnoreCase(TA9M))
            {
                ta9mPosition = i;

            } else if (parsedParamHeader[i].equalsIgnoreCase(TAIR))
            {
                tairPosition = i;

            } else if (parsedParamHeader[i].equalsIgnoreCase(SRAD))
            {
                sradPosition = i;

            } else if (parsedParamHeader[i].equalsIgnoreCase(STID))
            {
                stidPosition = i;
            }
        }

    }

    /**
     * 
     * @throws IOException
     */

    public void parseFile() throws IOException
    {
        String FileName = createFileName(2018, 8, 30, 17, 45, "data");
        BufferedReader br = new BufferedReader(new FileReader(FileName));
        br.readLine();
        br.readLine();
        String headers = br.readLine();
        parseParamHeader(headers);

        while (br.readLine() != null)
        {

            String line = br.readLine();
            // splits the line of data and stores in an array tokens
            String[] tokens = line.trim().split("\\s+");
            Observation srad = new Observation(Double.parseDouble(tokens[sradPosition]), tokens[stidPosition]);

            sradData.add(srad);
            Observation tair = new Observation(Double.parseDouble(tokens[tairPosition]),
                    tokens[stidPosition].toString());

            tairData.add(tair);
            Observation ta9m = new Observation(Double.parseDouble(tokens[ta9mPosition]),
                    tokens[stidPosition].toString());

            ta9mData.add(ta9m);

        }
        br.close();

    }

    /**
     * 
     * @param inData
     * @param paramId
     */

    private void calculateStatistics(ArrayList<Observation> inData, String paramId)
    {
        String output = "";

        if (paramId.equalsIgnoreCase(TA9M))
        {
            output = String.format(
                    "========================================================\n"
                            + "Maximum Air Temperature[9.0m] = %.1f C at %s\n"
                            + "Minimum Air Temperature[9.0m] = %.1f C at %s\n"
                            + "Average Air Temperature[9.0m] = %.1f C at %s\n"
                            + "========================================================\n",
                    getTa9mMax().getValue(), getTa9mMax().getStid(), getTa9mMin().getValue(), getTa9mMin().getStid(),
                    getTa9mAverage().getValue(), MESONET);

        }

        else if (paramId.equalsIgnoreCase(TAIR))
        {
            output = String.format(
                    "========================================================\n"
                            + "Maximum Air Temperature[1.5m] = %.1f C at %s\n"
                            + "Minimum Air Temperature[1.5m] = %.1f C at %s\n"
                            + "Average Air Temperature[1.5m] = %.1f C at %s\n"
                            + "========================================================\n",
                    getTairMax().getValue(), getTairMax().getStid(), getTairMin().getValue(), getTairMin().getStid(),
                    getTairAverage().getValue(), MESONET);
        }

        else if (paramId.equalsIgnoreCase(SRAD))
        {
            output += String.format(
                    "========================================================\n"
                            + "Maximum Solar Radiation[1.5m] = %.1f W/m^2 at %s\n"
                            + "Minimum Solar Radiation[1.5m] = %.1f W/m^2 at %s\n"
                            + "Average Solar Radiation[1.5m] = %.1f W/m^2 at %s\n"
                            + "========================================================\n",
                    getSradMax().getValue(), getSradMax().getStid(), getSradMin().getValue(), getSradMin().getStid(),
                    getSradAverage().getValue(), MESONET);

        }
        System.out.print(output);

    }

    /**
     * 
     * @return The average value for solar radiation.
     */

    public Observation getSradAverage()
    {
        double average = 0.0;
        double sradTotal = 0.0;

        for (int i = 0; i < sradData.size(); ++i)
        {
            Observation data = sradData.get(i);
            if (data.isValid())
            {
                sradTotal += data.getValue();
            }

        }

        average = sradTotal / sradData.size();

        this.sradAverage = new Statistics(average, MESONET, utcDateTime, sradData.size(), StatsType.AVERAGE);
 
        return sradAverage;

    }

    /**
     * 
     * @return the maximum value for solar radiation.
     */

    public Observation getSradMax()

    {
        Observation currentMax = sradData.get(0);

        int i = 0;

        for (i = 0; i < sradData.size(); ++i)
        {
            if (sradData.get(i).isValid() && sradData.get(i).getValue() > currentMax.getValue())
            {
                currentMax = sradData.get(i);

            }
            this.sradMax = new Statistics(sradData.get(i).getValue(), sradData.get(i).getStid(), utcDateTime,
                    sradData.size(), StatsType.MAXIMUM);
        }

        return sradMax;

    }

    /**
     * 
     * @return The minimum value for solar radiation.
     */

    public Observation getSradMin()
    {
        Observation currentMin = sradData.get(0);
        String curStid = "";

        for (int i = 0; i < sradData.size(); ++i)
        {
            if (sradData.get(i).isValid() && sradData.get(i).getValue() < currentMin.getValue())
            {
                currentMin = sradData.get(i);
                curStid = sradData.get(i).getStid();

            }
        }
        this.sradMin = new Statistics(currentMin.getValue(), curStid);

        return sradMin;

    }

    /**
     * 
     * @return The total value for solar radiation.
     */

    public Observation getSradTotal()
    {

        double total = 0.0;

        for (int i = 0; i < sradData.size(); ++i)
        {
            Observation data = sradData.get(i);
            if (data.isValid())
            {
                total += data.getValue();
            }

        }

        this.sradTotal = new Statistics(total, MESONET);

        return sradTotal;

    }

    /**
     * 
     * @return the average value for the air temperature at 9 meters.
     */

    public Observation getTa9mAverage()
    {
        double average = 0.0;
        double ta9mTotal = 0.0;

        for (int i = 0; i < ta9mData.size(); ++i)
        {
            Observation data = ta9mData.get(i);
            if (data.isValid())
            {
                ta9mTotal += data.getValue();
            }

        }

        average = ta9mTotal / ta9mData.size();

        this.ta9mAverage = new Statistics(average, MESONET);

        return ta9mAverage;

    }

    /**
     * 
     * @return the maximum value for air temperature at 9 meters.
     */
    public Observation getTa9mMax()
    {
        Observation currentMax = ta9mData.get(0);

        String curStid = "";

        for (int i = 0; i < ta9mData.size(); ++i)
        {
            if (ta9mData.get(i).isValid() && ta9mData.get(i).getValue() > currentMax.getValue())
            {
                currentMax = ta9mData.get(i);
                curStid = ta9mData.get(i).getStid();

            }
        }
        this.ta9mMax = new Statistics(currentMax.getValue(), curStid);

        return ta9mMax;

    }

    /**
     * 
     * @return The minimum for air temperature at 9 meters.
     */

    public Observation getTa9mMin()
    {
        Observation currentMin = ta9mData.get(0);

        String curStid = "";

        for (int i = 0; i < ta9mData.size(); ++i)
        {
            if (ta9mData.get(i).isValid() && ta9mData.get(i).getValue() < currentMin.getValue())
            {
                currentMin = ta9mData.get(i);
                curStid = ta9mData.get(i).getStid();

            }
        }
        this.ta9mMin = new Statistics(currentMin.getValue(), curStid);

        return ta9mMin;

    }

    /**
     * 
     * @return the average for air temperature
     */

    public Observation getTairAverage()
    {
        double average = 0.0;
        double tairTotal = 0.0;

        for (int i = 0; i < tairData.size(); ++i)
        {
            Observation data = tairData.get(i);
            if (data.isValid())
            {
                tairTotal += data.getValue();
            }

        }

        average = tairTotal / ta9mData.size();

        this.tairAverage = new Statistics(average, MESONET);

        return tairAverage;

    }

    /**
     * 
     * @return the maximum for air temperature.
     */

    public Observation getTairMax()
    {
        Observation currentMax = tairData.get(0);

        String curStid = "";

        for (int i = 0; i < tairData.size(); ++i)
        {
            if (tairData.get(i).isValid() && tairData.get(i).getValue() > currentMax.getValue())
            {
                currentMax = tairData.get(i);
                curStid = tairData.get(i).getStid();

            }
        }
        this.tairMax = new Statistics(currentMax.getValue(), curStid);

        return tairMax;

    }

    /**
     * 
     * @return the minimum for air temperature.
     */

    public Observation getTairMin()
    {
        Observation currentMin = tairData.get(0);

        String curStid = "";

        for (int i = 0; i < tairData.size(); ++i)
        {
            if (tairData.get(i).isValid() && tairData.get(i).getValue() < currentMin.getValue())
            {
                currentMin = tairData.get(i);
                curStid = tairData.get(i).getStid();

            }
        }
        this.tairMin = new Statistics(currentMin.getValue(), curStid);

        return tairMin;

    }

    /**
     * Displays the maximum, minimum, and averages for solar radiation, air
     * temperature, and air temperature at 9 meters.
     */

    public String toString()
    {
        String output = "========================================================" + "\n";
        output += "=== " + utcDateTime.get(Calendar.YEAR) + "-" + utcDateTime.get(Calendar.MONTH) + "-"
                + utcDateTime.get(Calendar.DATE) + " " + utcDateTime.get(Calendar.MINUTE) + "===";
        System.out.println(output);
        // System.out.println(getSradTotal().getValue());

        calculateStatistics(tairData, TAIR);
        calculateStatistics(ta9mData, TA9M);
        calculateStatistics(sradData, SRAD);

        return "";

    }

}
