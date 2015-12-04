package org.apache.hawq.pxf.api;

import java.io.IOException;

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


    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testGetTotalSizeInString() {
        // set up
        long number = 9710886412L;
        // run function
        String result = FragmentsStats.getTotalSizeInString(number);
        // verify
        assertEquals("9710886412", result);

        number = 0;
        result = FragmentsStats.getTotalSizeInString(number);
        assertEquals("0", result);

        number = -7899321L;
        result = FragmentsStats.getTotalSizeInString(number);
        assertEquals("-7899321", result);

        number = (long) Integer.MAX_VALUE + 1;
        result = FragmentsStats.getTotalSizeInString(number);
        assertEquals("2147483648", result);

    }

    @Test
    public void testdataToJSON() throws IOException {
        FragmentsStats stats = new FragmentsStats(43, 53108364, 400020001);
        String output = "{\"PXFFragmentsStats\":{\"fragmentsNumber\":43,\"firstFragmentSize\":53108364,\"totalSize\":\"400020001\"}}";
        assertEquals(output, FragmentsStats.dataToJSON(stats));

        // 2147483647 + 2147483647 = 4294967294
        stats = new FragmentsStats(43, 53108364, 4294967294L);
        output = "{\"PXFFragmentsStats\":{\"fragmentsNumber\":43,\"firstFragmentSize\":53108364,\"totalSize\":\"4294967294\"}}";
        assertEquals(output, FragmentsStats.dataToJSON(stats));

        stats = new FragmentsStats(43, 53108364, 1L);
        output = "{\"PXFFragmentsStats\":{\"fragmentsNumber\":43,\"firstFragmentSize\":53108364,\"totalSize\":\"1\"}}";
        assertEquals(output, FragmentsStats.dataToJSON(stats));
    }


}
