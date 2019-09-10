import java.util.*;

public class GuessNumber {

    public static void main(String[] args) {

        // stdin
        Scanner sc = new Scanner(System.in);

        // generate random number
        int randomNum = new Random().nextInt(101);
        int min = 0;
        int max = 100;

        boolean win = false;
        // guess number
        while (!win) {
            System.out.println("Guess the number between " + min + " and " + max);

            int guessedNum = sc.nextInt();

            if (guessedNum >= min && guessedNum <= max) {
                if (guessedNum == randomNum)
                    win = true;

                if (randomNum > guessedNum)
                    min = guessedNum;
                else
                    max = guessedNum;
            }
        }

        System.out.println("You Win!");
    }
}