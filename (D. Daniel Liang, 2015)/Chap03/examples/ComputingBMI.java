import java.util.*;

/**
 * Computing the Body Mass Index
 */
public class ComputingBMI {

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);

        // get weight
        System.out.println("Enter weight in kg");
        double weight = keyboard.nextDouble();

        // get height
        System.out.println("Enter height in meters");
        double height = keyboard.nextDouble();

        // compute BMI
        double bmi = weight / (height * height);

        /**
         * BMI < 18.5________________________ Underweight <br>
         * 18.5 <= BMI < 25.0 _______________ Normal <br>
         * 25.0 <= BMI < 30.0 _______________ Overweight <br>
         * 30.0 <= BMI ______________________ Obese <br>
         */
        if (bmi < 18.5)
            System.out.println("bmi =" + bmi + " Underweight");
        else if (bmi < 25)
            System.out.println("bmi =" + bmi + " Normal");
        else if (bmi < 30)
            System.out.println("bmi =" + bmi + " Overweight");
        else
            System.out.println("bmi =" + bmi + " Obese");
    }
}