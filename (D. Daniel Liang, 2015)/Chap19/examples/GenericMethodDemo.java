public class GenericMethodDemo {

    public static void main(String[] args) {

        Integer[] numbers = new Integer[] { 1, 2, 3, 4, 5, 6, 7 };
        String[] names = new String[] { "Curtis", "Sharon", "Orange" };

        System.out.println(GenericMethodDemo.<Integer>printArray(numbers));
        System.out.println(GenericMethodDemo.printArray(names));
    }

    public static <T> String printArray(T[] arr) {

        var sb = new StringBuilder("[ ");
        for (T t : arr) {
            sb.append(t + ", ");
        }
        sb.append(" ]");
        return sb.toString();
    }
}