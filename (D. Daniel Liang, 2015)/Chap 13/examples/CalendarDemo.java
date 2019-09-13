import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;

/**
 * Demonstrate the use of Calendar as well as the abstract class
 */
public class CalendarDemo {
    public static void main(String[] args) {

        // Calendar is an abstract class
        Calendar[] cals = new Calendar[1];

        // GregorianCalendar is subclass of the Calendar, now it's treated as a Calendar
        Calendar gcal = new GregorianCalendar();
        cals[0] = gcal;

        // Calendar and its subclasses are for detailed calendar info e.g., year, month,
        // date, hour, min and sec
        System.out.println("Month " + gcal.get(Calendar.MONTH));
        System.out.println("Day of Month" + gcal.get(Calendar.DAY_OF_MONTH));

        // add another 5 days
        gcal.add(Calendar.DAY_OF_MONTH, 5);
        System.out.println("Day of Month" + gcal.get(Calendar.DAY_OF_MONTH));

        // minus 6 days
        gcal.add(Calendar.DAY_OF_MONTH, -6);
        System.out.println("Day of Month" + gcal.get(Calendar.DAY_OF_MONTH));

        // add a month
        gcal.add(Calendar.MONTH, 1);
        System.out.println("Month " + gcal.get(Calendar.MONTH));

        // maximum day of this month
        System.out.println("Maximum of " + gcal.getActualMaximum(Calendar.DAY_OF_MONTH));

        // use Date to ini GregorianCalendar
        GregorianCalendar cal = new GregorianCalendar(new Date());

    }
}