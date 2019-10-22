
/**
 * Fantastic example of recursion.
 * 
 * https://www.hackerrank.com/challenges/java-1d-array/problem
 */
public class Java1DArrayIssue {
    public static void main(String[] args) {
        System.out.println(canWin(3, new int[] { 0, 1, 1, 0, 0, 1, 0, 1 }));
    }

    /**
     * There are three cases: <br>
     * 1. move backward <br>
     * 2. jump through leap <br>
     * 3. move forward <br>
     * 
     * This can be considered as a tree problem <br>
     * [0,1,1,0,0,1,0,1] leap=3 <br>
     * _0 1 2 3 4 5 6 7 <br>
     * 
     * 1st step: 0 -> 3 <br>
     * 2nd step: 3 -> 4 or 3 -> 6 <br>
     * 3rd step: 6 -> 9(win) or 4 -> 7 (lose)
     * 
     * @param leap leap (the number of steps you can jumped over)
     * @param game the array
     */
    public static boolean canWin(int leap, int[] game) {
        return winnable(leap, game, 0);
    }

    public static boolean winnable(int leap, int[] game, int index) {

        // moving backward (where index is less than 0) or this element is a 1
        if (index < 0 || game[index] == 1)
            return false;

        // outside the array (win)
        if (index >= game.length - 1 || index + leap > game.length - 1)
            return true;

        // make it 1 so that it cannot move back to this position again and again
        game[index] = 1;

        // try move forward, backward and jump through leap
        return winnable(leap, game, index - 1) || winnable(leap, game, index + 1) || winnable(leap, game, index + leap);
    }

}