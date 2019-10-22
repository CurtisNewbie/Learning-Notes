public class MultidimensionalArray {
    public static void main(String[] args) {

        // 10 int[], each has 10 int[]
        int[][] matrix = new int[10][10];

        // representing a table or matrix
        int[][] table = { { 5, 10, 1, 5, 2, }, { 7, 4, 6, 2, 1 } };

        int count = 0;
        for (int[] i : table) {
            count++;
            StringBuilder row = new StringBuilder();

            // each row
            for (int x : i) {
                row.append(x + " ");
            }
            System.out.println("[" + count + "] " + row.toString());
        }
    }
}