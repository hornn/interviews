package org.apache.hawq.pxf.api;

import java.io.IOException;

import org.apache.hawq.pxf.api.FilterParser.FilterBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Test for FragmentsStats
 *
 * @author nhorn
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({FilterBuilder.class})
public class FragmentsStatsTest {

    /**
     * Test for zero total size
     * @throws IOException since the parser is doing IO
     */
    @Test
    public void testForZeroTotalSize() throws IOException {
        FragmentsStats fragmentsStats = new FragmentsStats(2, 4, 0);
        String actualJsonString = FragmentsStats.dataToJSON(fragmentsStats);
        String expectedJsonString = createJsonString(2,4,0);
        assertEquals(expectedJsonString,actualJsonString );
    }

    /**
     * Test for lower total size
     * @throws IOException since the parser is doing IO
     */
    @Test
    public void testForLowerTotalSize() throws IOException {
        FragmentsStats fragmentsStats = new FragmentsStats(2, 4, 256);
        String actualJsonString = FragmentsStats.dataToJSON(fragmentsStats);
        String expectedJsonString = createJsonString(2,4,256);
        assertEquals(expectedJsonString,actualJsonString );
    }

    /**
     * Test for lower high size
     * @throws IOException since the parser is doing IO
     */
    @Test
    public void testForHighTotalSize() throws IOException {
        FragmentsStats fragmentsStats = new FragmentsStats(2, 4, Long.MAX_VALUE);
        String actualJsonString = FragmentsStats.dataToJSON(fragmentsStats);
        String expectedJsonString = createJsonString(2,4,Long.MAX_VALUE);
        assertEquals(expectedJsonString,actualJsonString );
    }

    /**
     * Test for min total size
     * @throws IOException since the parser is doing IO
     */
    @Test
    public void testForMinTotalSize() throws IOException {
        FragmentsStats fragmentsStats = new FragmentsStats(2, 4, Long.MIN_VALUE);
        String actualJsonString = FragmentsStats.dataToJSON(fragmentsStats);
        String expectedJsonString = createJsonString(2,4,Long.MIN_VALUE);
        assertEquals(expectedJsonString,actualJsonString );
    }

    /**
     * Test for not equal
     * @throws IOException since the parser is doing IO
     */
    @Test
    public void testForNotEquals() throws IOException {
        FragmentsStats fragmentsStats = new FragmentsStats(2, 4, Long.MAX_VALUE);
        String actualJsonString = FragmentsStats.dataToJSON(fragmentsStats);
        String expectedJsonString = createJsonString(2,4,256);
        assertNotEquals("Test for not equals",expectedJsonString,actualJsonString );
    }

    /**
     * Test for max integer
     * @throws IOException since the parser is doing IO
     */
    @Test
    public void testForMaxIntegerValues() throws IOException {
        FragmentsStats fragmentsStats = new FragmentsStats(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
        String actualJsonString = FragmentsStats.dataToJSON(fragmentsStats);
        String expectedJsonString = createJsonString(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
        assertEquals(expectedJsonString,actualJsonString );
    }

    /**
     * Test for min integer
     * @throws IOException since the parser is doing IO
     */
    @Test
    public void testForMinIntegerValues() throws IOException {
        FragmentsStats fragmentsStats = new FragmentsStats(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE);
        String actualJsonString = FragmentsStats.dataToJSON(fragmentsStats);
        String expectedJsonString = createJsonString(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE);
        assertEquals(expectedJsonString,actualJsonString );
    }

    private String createJsonString(long fNum, long fSize, long totalSize){

        return "{\"PXFFragmentsStats\":{\"fragmentsNumber\":"+ fNum+
                ",\"firstFragmentSize\":"+fSize +
                ",\"totalSize\":\""+totalSize+ "\"}}";
    }


}
