import java.util.*;

public class ComputeArea {

    public static void main(String[] args) {

        double radius;
        double area;

        // 1 read radius
        final Scanner keyboard = new Scanner(System.in);
        radius = keyboard.nextDouble();

        // 2 compute area
        area = radius * radius * Math.PI;

        // 3 output area
        System.out.println(area);
    }
}