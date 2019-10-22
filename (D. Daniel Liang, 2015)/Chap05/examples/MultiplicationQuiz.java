import java.util.*;

public class MultiplicationQuiz {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Random rd = new Random();
        int a, b;

        String[] results = new String[10];

        long start = System.currentTimeMillis();

        // ten questions
        for (int x = 0; x < 10; x++) {

            // a * b
            a = rd.nextInt(100);
            b = rd.nextInt(100);

            // create question
            StringBuilder sb = new StringBuilder(generateQuestion(a, b));
            System.out.println(sb.toString());

            int resp = sc.nextInt();
            int correctAnswer = a * b;
            sb.append(correctAnswer);

            if (resp == correctAnswer)
                sb.append("    [Correct]");
            else
                sb.append("    [Incorrect, You answered:" + resp + "]");

            results[x] = sb.toString();
        }

        long end = System.currentTimeMillis();

        // show the final quiz results
        System.out.println("============================================");
        for (String t : results) {
            System.out.println(t);
        }
        System.out.println("Total Time Used: " + (end - start) / 1000 + " Sec");
        System.out.println("============================================");

    }

    public static String generateQuestion(int a, int b) {

        return a + " * " + b + " = ";
    }
}