public class FindMax {

    public static void main(String[] args) {

        int[] arr = new int[] { 1, 2, 3, 4, 5, 6, 7 };
        int len = arr.length;

        int max = arr[0];
        int maxIndex = 0;

        // it should be O(n)
        for (int x = 1; x < len; x++) {
            int v = arr[x];
            if (v > max) {
                max = v;
                maxIndex = x;
            }
        }

        System.out.println("Max:" + max + " , " + "Index:" + maxIndex);
    }
}