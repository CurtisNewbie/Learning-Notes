import java.util.Date;

public class DateDemo {

    public static void main(String[] args) {

        // create Date object - now since 1 Jan 1970 GMT in seconds
        var now = new Date();
        System.out.println(now.getTime());
        long elapsedTimeInSec = now.getTime();

        // can create Date object with elapsed time
        var date = new Date(elapsedTimeInSec);
        System.out.printf("%d %d %d %d:%d:%d", date.getDate(), date.getMonth(), date.getYear() + 1900, date.getHours(),
                date.getMinutes(), date.getSeconds());

    }
}