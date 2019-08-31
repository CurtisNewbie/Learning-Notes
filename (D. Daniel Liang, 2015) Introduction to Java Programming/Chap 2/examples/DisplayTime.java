import java.util.*;

/**
 * An example of displaying the current time
 */
public class DisplayTime {
    public static void main(String[] args) {

        // UNIX epoch 01-01-1970 00:00:00 GMT

        /*
         * current time in milisec since the GMT starting time(UNIX epoch).
         */
        long timeInMil = System.currentTimeMillis();

        System.out.println("In milisec " + timeInMil);

        // current time in sec
        System.out.println("In sec " + timeInMil / 1000.0);

        // current time in min
        System.out.println("In min " + timeInMil / 1000 / 60.0);

        // current time in hours
        System.out.println("In hour " + timeInMil / 1000 / 60.0 / 60.0);

        // current time in days
        System.out.println("In day " + timeInMil / 1000 / 60.0 / 60.0 / 24.0);

        // get time zone
        Calendar c = Calendar.getInstance();

        // display the time in normal format: hh:mm:ss
        long ss = timeInMil / 1000 % 60;
        long mm = timeInMil / 1000 / 60 % 60;
        long hh = timeInMil / 1000 / 60 / 24 % 24;
        System.out.println("Current time is: " + hh + ':' + mm + ':' + ss + ' ' + c.getTimeZone().getDisplayName());

    }
}