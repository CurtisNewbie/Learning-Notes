public class FormatDemo {

    public static void main(String[] args) {

        String nameOne = "Curtis";
        String nameTwo = "Sharon";
        String nameThree = "John";

        double ageOne = 24.3;
        double ageTwo = 24.4;
        double ageThree = 26.3;

        System.out.printf("%10s%10s\n", "Name", "Age");
        System.out.printf("%10s%10.2f\n", nameOne, ageOne);
        System.out.printf("%10s%10.2f\n", nameTwo, ageTwo);
        System.out.printf("%10s%10.2f\n", nameThree, ageThree);

    }
}