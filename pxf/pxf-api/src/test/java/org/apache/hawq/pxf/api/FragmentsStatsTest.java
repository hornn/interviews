package org.apache.hawq.pxf.api;

import org.apache.hawq.pxf.api.FilterParser.FilterBuilder;
import org.apache.hawq.pxf.api.FilterParser.Operation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({FilterBuilder.class})
public class FragmentsStatsTest {

    FragmentsStats testStats;

    @Before
    public void before() {
        testStats = new FragmentsStats(34, 15, Integer.MAX_VALUE - 1);
    }

    @Test
    public void getTotalSizeLowRange() throws Exception {
       // initialize FragmentsStats
        testStats = new FragmentsStats(34, 15, 2147483646);

        // verify output
        //assertEquals(expectedvalue, actualvalue)
        assertEquals("testing total size", "2147483646", testStats.getTotalSize());

        assertEquals("testing json", "{\"PXFFragmentsStats\":"
                + "{\"fragmentsNumber\":34,\"firstFragmentSize\":15,"
                + "\"totalSize\":\"2147483646\"}}", FragmentsStats.dataToJSON(testStats));

    }

    //@Test
    public void getTotalSizeHighRange() throws Exception {
        // initialize FragmentsStats
        testStats = new FragmentsStats(34, 15, Long.valueOf("322147483646"));

        // verify output
        //assertEquals(expectedvalue, actualvalue)
        assertEquals("testing total size", "322147483646", testStats.getTotalSize());

        assertEquals("testing json", "{\"PXFFragmentsStats\":"
                + "{\"fragmentsNumber\":34,\"firstFragmentSize\":15,"
                + "\"totalSize\":\"322147483646\"}}", FragmentsStats.dataToJSON(testStats));

    }

}
