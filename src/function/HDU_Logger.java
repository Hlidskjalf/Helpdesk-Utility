package function;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * HDU_Logger class: creates a log file, and allows for events to write to it.
 * TODO: Make the log file unique to each instance and have the close operation delete anything 30+
 */

public class HDU_Logger {

    static String logfile = "/Users/avroman/Projects/Java/HelpdeskUtility/src/logs/hdu.log";
    static FileWriter writer;

    /**
     * Overloaded method appendLog takes time and message data to append the log file.
     * @param msg
     */
    public static void appendLog(String msg) {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        try {
            writer = new FileWriter(logfile,true);
            writer.write(timeStamp + msg + "\n");
            writer.close();
        }
        catch(IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }

    }

    public static void appendLog(String msg, String storeNumber) {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        try {
            writer = new FileWriter(logfile,true);
            writer.write(timeStamp + msg + storeNumber + "\n");
            writer.close();
        }
        catch(IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }

    }
}
