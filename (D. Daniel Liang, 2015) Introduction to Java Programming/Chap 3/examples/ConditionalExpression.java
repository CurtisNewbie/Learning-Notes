public class ConditionalExpression {

    public static void main(String[] args) {

        int neededTime = 10;
        int actualTime = 10;
        boolean cooked;

        // using conditional expression
        System.out.println((actualTime >= neededTime) ? "done" : "not yet done");

        // another example of using conditional expression
        boolean result = (100 - 1 == 99) ? true : false;
        System.out.println((result) ? "The result is 99" : "The result is not 99");
    }

}