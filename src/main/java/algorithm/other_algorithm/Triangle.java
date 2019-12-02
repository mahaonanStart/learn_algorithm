package algorithm.other_algorithm;

import java.util.List;

/**
 * @Author: M˚Haonan
 * @Date: 2019-05-08 17:04
 * @Description:
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
 *
 * For example, given the following triangle
 *
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 *
 * 求一个三角形序列中从上往下累加的最小值，上一层的元素只能和下一层相邻的元素加
 *
 * leetcode 120
 */
public class Triangle {

    /**
     * 从上往下考虑
     * 所有的元素分为4种，（0，0）的元素
     * 左斜边的元素，右斜边的元素，中间的元素
     * 根据格子的递推公式求出对应的值
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null) return 0;
        //定义一个二维数组，存放对应位置的最小值
        int[][] add = new int[triangle.size()][triangle.size()];
        for (int i = 0; i < triangle.size(); i++) {
            List<Integer> curr = triangle.get(i);
            for (int j = 0; j < curr.size(); j++) {
                int ele = triangle.get(i).get(j);
                //初始化结果数组
                //如果为第一个元素
                if (i == 0 && j == 0){
                    add[i][j] = ele;
                //如果为左斜边
                }else if (j == 0){
                    add[i][j] = add[i - 1][j] + ele;
                //如果为右斜边
                }else if (i == j){
                    add[i][j] = add[i - 1][j - 1] + ele;
                //其他中间情况，等于上面两个相邻位置的最小值加上该位置的元素本身
                }else {
                    add[i][j] = Math.min(add[i - 1][j - 1], add[i - 1][j]) + ele;
                }
            }
        }
        int min = add[triangle.size() - 1][0];
        //取出最后一行的所有值中的最小值
        for (int i = 1; i < add[triangle.size() - 1].length; i++) {
            int ele = add[triangle.size() - 1][i];
            if (min > ele){
                min = ele;
            }
        }
        return min;
    }


    /**
     * 从下往上考虑
     * 其实就是我上面写的简化版本，更加优雅
     * A中存储的始终是上一次运算完后的最小值，那么在进行上一层运算的时候，只需要取出相邻两个A中的最小值再加上本身的值即是
     * 这一次的最小值
     *
     * 循环到最后，A[0]中存储的即是所有走法的最小值
     * @param triangle
     * @return
     */
    public int minimumTotal2(List<List<Integer>> triangle) {
        int[] A = new int[triangle.size()+1];
        for(int i=triangle.size()-1;i>=0;i--){
            for(int j=0;j<triangle.get(i).size();j++){
                A[j] = Math.min(A[j],A[j+1])+triangle.get(i).get(j);
            }
        }
        return A[0];
    }


}
