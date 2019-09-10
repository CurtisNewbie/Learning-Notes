import java.util.*;

/**
 * Generate Sudoku, read existing sudoku, and provide solutions
 */
public class SudokuWinningVerification {

    public static void main(String[] args) {
        // the game board
        int[][] sudoku = { { 9, 6, 3, 1, 7, 4, 2, 5, 8 }, { 1, 7, 8, 3, 2, 5, 6, 4, 9 }, { 2, 5, 4, 6, 8, 9, 7, 3, 1 },
                { 8, 2, 1, 4, 3, 7, 5, 9, 6 }, { 4, 9, 6, 8, 5, 2, 3, 1, 7 }, { 7, 3, 5, 9, 6, 1, 8, 2, 4 },
                { 5, 8, 9, 7, 1, 3, 4, 6, 2 }, { 3, 1, 7, 2, 4, 6, 9, 8, 5 }, { 6, 4, 2, 5, 9, 8, 1, 7, 3 } };

        for (int[] row : sudoku) {
            System.out.println(Arrays.toString(row));
        }

        System.out.println(verifyWinning(sudoku));
    }

    /**
     * Verify winning condition
     * 
     * @param sudoku the gameboard
     * @return wining condition
     */
    public static boolean verifyWinning(int[][] sudoku) {
        boolean valid = true;

        // check each row
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {

                int value = sudoku[x][y];

                for (int i = y + 1; i < 9; i++) {
                    if (value == sudoku[x][i]) {
                        return !valid;
                    }
                }
            }
        }

        // check each column
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                int value = sudoku[x][y];

                for (int i = x + 1; i < 9; i++) {
                    if (value == sudoku[i][y]) {
                        return !valid;
                    }
                }
            }
        }
        return valid;
    }
}