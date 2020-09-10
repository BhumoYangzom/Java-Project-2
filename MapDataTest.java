
import java.io.IOException;


import org.junit.Assert;

import org.junit.Test;

/**
 * For Project 2 Test for mapData
 * 
 * @author yangzomdolma
 *
 */

public class MapDataTest
{
    MapData map = new MapData(2018, 8, 30, 17, 45, "data");

    /**
     * Test for createFileName()
     */

    @Test
    public void testCreateFileName()
    {
        String filename = "201808301745.mdf";
        String actual = map.createFileName(2018, 8, 30, 17, 45, "");
        Assert.assertEquals("Calculation failed", filename, actual);

    }

    /**
     * Test for getSradAverage()
     * 
     * @throws IOException
     */
    @Test
    public void testGetSradAverage() throws IOException
    {
        map.parseFile();
        double average = 829.0;
        double actual = map.getSradAverage().getValue();
        Assert.assertEquals("Failed to calculate or an error occured", average, actual, 0.1);

    }

    /**
     * Test for getSradMax()
     * 
     * @throws IOException
     */

    @Test
    public void testGetSradMax() throws IOException
    {
        map.parseFile();
        double tair = 874.0;
        double actual = map.getSradMax().getValue();
        Assert.assertEquals("Calculation failed", tair, actual, 0.01);

    }

    /**
     * Test for getSradMin()
     * 
     * @throws IOException
     */

    @Test
    public void testGetSradMin() throws IOException
    {
        map.parseFile();
        double minimum = 191.0;
        double actual = map.getSradMin().getValue();
        Assert.assertEquals("An error occurred or calculation failed", minimum, actual, 0.01);

    }

    /**
     * Test for getSradTotal()
     * 
     * @throws IOException
     */
    @Test
    public void testGetSradTotal() throws IOException
    {
        map.parseFile();
        double total = 49741.0;
        double actual = map.getSradTotal().getValue();
        Assert.assertEquals("", total, actual, 0.01);

    }

    /**
     * Test for getTa9mAverage()
     * 
     * @throws IOException
     */
    @Test
    public void testGetTa9mAverage() throws IOException
    {

        map.parseFile();
        double average = 31.7;
        double actual = map.getTa9mAverage().getValue();
        Assert.assertEquals("Calculation failure", average, actual, 0.1);

    }

    /**
     * Test for getTaa9mMax()
     * 
     * @throws IOException
     */
    @Test
    public void testGetTa9mMax() throws IOException
    {
        map.parseFile();
        double max = 34.9;
        double actual = map.getTa9mMax().getValue();
        Assert.assertEquals("Calculation error", max, actual, 0.01);

    }

    /**
     * Test for getTa9mMin()
     * 
     * @throws IOException
     */

    @Test
    public void testGetTa9mMin() throws IOException
    {
        map.parseFile();
        double min = 22.5;
        double actual = map.getTa9mMin().getValue();
        Assert.assertEquals("Calculation error", min, actual, 0.01);

    }

    @Test
    public void testGetTairAverage() throws IOException
    {
        map.parseFile();
        double average = 32.0;
        double actual = map.getTairAverage().getValue();
        Assert.assertEquals("Calculation error", average, actual, 0.01);

    }

    /**
     * Test for getTairMax()
     * 
     * @throws IOException
     */

    @Test
    public void testGetTairMax() throws IOException
    {
        map.parseFile();
        double max = 36.5;
        double actual = map.getTairMax().getValue();
        Assert.assertEquals("Calculation failed", max, actual, 0.01);

    }

    /**
     * Test for getTairMin()
     * 
     * @throws IOException
     */

    @Test
    public void testGetTairMin() throws IOException
    {
        map.parseFile();
        double min = 22.5;
        double actual = map.getTa9mMin().getValue();
        Assert.assertEquals("Calculation failed", min, actual, 0.01);
    }

    /*
     * @Test public void testToString() throws IOException { map.parseFile();
     * 
     * String expected =
     * "========================================================\n" +
     * "=== 2018-8-30 45===\n" +
     * "========================================================\n" +
     * "Maximum Air Temperature[1.5m] = 36.5 C at HOOK\n" +
     * "Minimum Air Temperature[1.5m] = 22.8 C at JAYX\n" +
     * "Average Air Temperature[1.5m] = 32.0 C at Mesonet\n" +
     * "========================================================\n" +
     * "========================================================\n" +
     * "Maximum Air Temperature[9.0m] = 34.9 C at HOOK\n" +
     * "Minimum Air Temperature[9.0m] = 22.5 C at JAYX\n" +
     * "Average Air Temperature[9.0m] = 31.7 C at Mesonet\n" +
     * "========================================================\n" +
     * "========================================================\n" +
     * "Maximum Solar Radiation[1.5m] = 874.0 W/m^2 at YUKO\n" +
     * "Minimum Solar Radiation[1.5m] = 191.0 W/m^2 at JAYX\n" +
     * "Average Solar Radiation[1.5m] = 829.0 W/m^2 at Mesonet\n" +
     * "========================================================"; String actual =
     * map.toString();
     * 
     * Assert.assertEquals(expected, actual); }
     */
}
