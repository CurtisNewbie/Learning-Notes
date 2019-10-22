import java.util.Arrays;

public class InsertionSortDemo {

    public static void main(String[] args) {

        Integer[] arr = Util.getDemoArr();
        // insertionSort(arr);
        txtBookSort(arr);
        System.out.println(Util.isSorted(arr));
        System.out.println(Arrays.toString(arr));
    }

    public static void insertionSort(Integer[] arr) {
        int len = arr.length;

        // 1 as the starting index can be compared with 0.
        for (int i = 1; i < len; i++) {
            int currentElement = arr[i];
            int backtrackIndex = i - 1;
            while (backtrackIndex >= 0 && arr[backtrackIndex] > currentElement) {

                // move the previous element (arr[k]) to the arr[k+1], while the first element
                // (arr[i]) is stored in currentElement waiting until it's finally at the
                // correct position.
                arr[backtrackIndex + 1] = arr[backtrackIndex];

                // keep backtracking until finding the correct position for this element.
                backtrackIndex--;
            }
            // it is decremented by 1 before it's found to be at the correct position.
            backtrackIndex++;

            // the currentElement is finally at the correct index
            arr[backtrackIndex] = currentElement;
        }
    }

    // textbook's version
    public static void txtBookSort(Integer[] list) {

        for (int i = 1; i < list.length; i++) {

            int currentElement = list[i];
            int k;

            for (k = i - 1; k >= 0 && list[k] > currentElement; k--) {
                list[k + 1] = list[k];

            }
            list[k + 1] = currentElement;
        }
    }
}