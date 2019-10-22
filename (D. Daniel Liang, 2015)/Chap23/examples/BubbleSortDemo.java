import java.util.*;

public class BubbleSortDemo {

    public static void main(String[] args) {

        var arr = Util.getDemoArr();
        bubbleSort(arr);
        System.out.println(Util.isSorted(arr));
        System.out.println(Arrays.toString(arr));
    }

    public static void bubbleSort(Integer[] arr) {
        boolean isSorted = false;
        int len = arr.length;

        for (int i = len - 1; i > 0 && !isSorted; i--) {
            isSorted = true;
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    // swap
                    var temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    isSorted = false;
                }
            }
        }
    }

    // textbook's implementation
    public static void textBubbleSort(Integer[] list) {
        boolean needNextPass = true;
        for (int k = 1; k < list.length && needNextPass; k++) {
            // Array may be sorted and next pass not needed
            needNextPass = false;
            // Perform the kth pass
            for (int i = 0; i < list.length - k; i++) {
                if (list[i] > list[i + 1]) {
                    // swap list[i] with list[i + 1];
                    needNextPass = true; // Next pass still needed
                }
            }
        }
    }
}