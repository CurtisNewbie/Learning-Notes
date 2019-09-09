public class SearchArray {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5 };

        // linear searching
        linearSearch(arr, 4);

        // Bineary searching (Sorted and only one match)
        binarySearch(arr, 4);
    }

    public static void linearSearch(int[] arr, int n) {
        int len = arr.length;
        StringBuilder index = new StringBuilder("Found in: ");
        for (int x = 0; x < len; x++) {
            if (arr[x] == n) {
                index.append(x + " ");
            }
        }
        System.out.println(index.toString());
    }

    public static void binarySearch(int[] arr, int n) {
        int min = 0;
        int max = arr.length - 1;
        String result = "Found in :";
        boolean found = false;

        while (max >= min && !found) {
            int mid = min + (max - min) / 2;
            int midValue = arr[mid];
            if (midValue == n) {
                found = true;
                System.out.println(result + mid);
            } else if (midValue > n) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }

        if (!found)
            System.out.println("Not found");

    }
}