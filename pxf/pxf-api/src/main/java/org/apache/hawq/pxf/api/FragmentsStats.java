package org.apache.hawq.pxf.api;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * FragmentsStats holds statistics for a given path.
 */
public class FragmentsStats {

    /**
     * Default fragment size. Assuming a fragment is equivalent to a block in HDFS,
     * we guess a full fragment size is 64MB.
     */
    public static final long DEFAULT_FRAGMENT_SIZE = 67108864L; // 64MB (in bytes)

    private long fragmentsNumber; // number of fragments
    private long firstFragmentSize; // first fragment size (in bytes)

    private String totalSize;
//    private int totalSizeLow; // low bits
//    private int totalSizeHigh; // high bits

    /**
     * Constructs an FragmentsStats.
     *
     * @param fragmentsNumber number of fragments
     * @param firstFragmentSize first fragment size (in bytes)
     * @param totalSize total size (in bytes)
     */
    public FragmentsStats(long fragmentsNumber, long firstFragmentSize,
                         long totalSize) {
        this.setFragmentsNumber(fragmentsNumber);
        this.setFirstFragmentSize(firstFragmentSize);
        this.setTotalSize(totalSize);
    }

    /**
     * Given a {@link FragmentsStats}, serialize it in JSON to be used as the result
     * string for HAWQ. An example result is as follows:
     * <code>{"PXFFragmentsStats":{"fragmentsNumber"
     * :3,"firstFragmentSize":67108864,"totalSize":200000000}}</code>
     *
     * @param stats the data to be serialized
     * @return the result in json format
     * @throws IOException if converting to JSON format failed
     */
    public static String dataToJSON(FragmentsStats stats) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        // mapper serializes all members of the class by default
        return "{\"PXFFragmentsStats\":" + mapper.writeValueAsString(stats)
                + "}";
    }

    /**
     * Given a stats structure, convert it to be readable. Intended for
     * debugging purposes only.
     *
     * @param stats the data to be stringify
     * @param datapath the data path part of the original URI (e.g., table name,
     *            *.csv, etc.)
     * @return the stringified data
     */
    public static String dataToString(FragmentsStats stats, String datapath) {
        return "Statistics information for \"" + datapath + "\" "
                + " Number of Fragments: " + stats.fragmentsNumber + ", first Fragment size: "
                + stats.firstFragmentSize + ", total size: "
                + stats.getTotalSize();
    }

    /**
     * Returns number of fragments for a given data source.
     *
     * @return number of fargments
     */
    public long getFragmentsNumber() {
        return fragmentsNumber;
    }

    private void setFragmentsNumber(long fragmentsNumber) {
        this.fragmentsNumber = fragmentsNumber;
    }

    /**
     * Returns the size in bytes of the first fragment.
     *
     * @return first fragment size (in byte)
     */
    public long getFirstFragmentSize() {
        return firstFragmentSize;
    }

    private void setFirstFragmentSize(long firstFragmentSize) {
        this.firstFragmentSize = firstFragmentSize;
    }

//    /**
//     * Returns the total size in bytes of a given source.
//     * Usually it means the aggregation of all its fragments size.
//     *
//     * @return total size (in bytes)
//     */
//    public long totalSize() {
//        long totalSize = getTotalSizeLow();
//        totalSize = totalSize | (getTotalSizeHigh()) << 32;
//        return totalSize;
//    }



//    private void setLowHighSize(long totalSize) {
//        if (totalSize > Integer.MAX_VALUE) {
//            setTotalSizeHigh((int) (totalSize >> 32));
//            setTotalSizeLow((int) ((totalSize << 32) >> 32));
//        }
//        else {
//            setTotalSizeHigh(0);
//            setTotalSizeLow((int) totalSize);
//        }
//    }

    private void setTotalSize(long size) {
        this.totalSize = String.valueOf(size);
    }

    public String getTotalSize() {
        return this.totalSize;
    }

//
//    public int getTotalSizeLow() {
//        return totalSizeLow;
//    }
//
//    private void setTotalSizeLow(int totalSizeLow) {
//        this.totalSizeLow = totalSizeLow;
//    }
//
//    public int getTotalSizeHigh() {
//        return totalSizeHigh;
//    }
//
//    private void setTotalSizeHigh(int totalSizeHigh) {
//        this.totalSizeHigh = totalSizeHigh;
//    }
}
