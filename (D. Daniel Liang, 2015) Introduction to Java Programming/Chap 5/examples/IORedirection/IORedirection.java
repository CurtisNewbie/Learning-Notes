import java.util.Scanner;

public class IORedirection {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String name = sc.next();
        String age = sc.next();
        String interest = sc.next();

        System.out.printf("I am %s, I am %s years old and I love %s", name, age, interest);
    }
}