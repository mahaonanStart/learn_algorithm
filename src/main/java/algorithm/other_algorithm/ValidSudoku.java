package algorithm.other_algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: M˚Haonan
 * @Date: 2019-05-06 09:08
 * @Description:
 * Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
 *
 * Each row must contain the digits 1-9 without repetition.
 * Each column must contain the digits 1-9 without repetition.
 * Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 *
 * A partially filled sudoku which is valid.
 *
 * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
 *
 * Example 1:
 *
 * Input:
 * [
 *   ["5","3",".",".","7",".",".",".","."],
 *   ["6",".",".","1","9","5",".",".","."],
 *   [".","9","8",".",".",".",".","6","."],
 *   ["8",".",".",".","6",".",".",".","3"],
 *   ["4",".",".","8",".","3",".",".","1"],
 *   ["7",".",".",".","2",".",".",".","6"],
 *   [".","6",".",".",".",".","2","8","."],
 *   [".",".",".","4","1","9",".",".","5"],
 *   [".",".",".",".","8",".",".","7","9"]
 * ]
 * Output: true
 * Example 2:
 *
 * Input:
 * [
 *   ["8","3",".",".","7",".",".",".","."],
 *   ["6",".",".","1","9","5",".",".","."],
 *   [".","9","8",".",".",".",".","6","."],
 *   ["8",".",".",".","6",".",".",".","3"],
 *   ["4",".",".","8",".","3",".",".","1"],
 *   ["7",".",".",".","2",".",".",".","6"],
 *   [".","6",".",".",".",".","2","8","."],
 *   [".",".",".","4","1","9",".",".","5"],
 *   [".",".",".",".","8",".",".","7","9"]
 * ]
 * Output: false
 * Explanation: Same as Example 1, except with the 5 in the top left corner being
 *     modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
 *
 * A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 * Only the filled cells need to be validated according to the mentioned rules.
 * The given board contain only digits 1-9 and the character '.'.
 * The given board size is always 9x9.
 *
 * 合法的数独
 *
 * leetcode 36
 */
public class ValidSudoku {

    /**
     * 传统的依次判重
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        for (int row = 0; row < board.length; row++) {
            Set<Character> rowSet = new HashSet<>();
            Set<Character> colSet = new HashSet<>();
            Set<Character> cubeSet = new HashSet<>();
            for (int col = 0; col < board[row].length; col++) {
                //对行判断
                if ('.' != board[row][col] && !rowSet.add(board[row][col])) return false;
               //对列判断
                if ('.' != board[col][row] && !colSet.add(board[col][row])) return false;
                //对小九宫格进行判断
                int rowIndex = 3 * (row / 3);
                int colIndex = 3 * (row % 3);
                if ('.' != board[rowIndex + col / 3][colIndex + col % 3] && !cubeSet.add(board[rowIndex + col / 3][colIndex + col % 3])) return false;
            }
        }
        return true;
    }

    /**
     * 独特的字符串编码
     * 将不同位置的字符进行编码
     * 例如：第4行的7编码为：4(7)
     * 第5列的3编码为: (3)5
     * 右上角的方块中4编码为:(0)4(2)
     * 这样就可以标记每个元素的大位置，只要相同位置出现重复元素，就返回false
     * @param board
     * @return
     */
    public boolean isValidSudoku2(char[][] board) {
        Set<String> set = new HashSet<>();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                int ele = board[row][col];
                if ('.' == ele) continue;
                //对字符进行编码
                String b = "(" + ele + ")";
                //判断
                if (!set.add(b + col) || !set.add(row + b) || !set.add(row / 3 + b + col / 3)) return false;
            }
        }
        return true;
    }
}
