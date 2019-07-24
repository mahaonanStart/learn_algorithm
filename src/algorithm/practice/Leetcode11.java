package algorithm.practice;

/**
 * @Author: M˚Haonan
 * @Date: 2019-06-23 13:37
 * @Description:
 */
public class Leetcode11 {


    /**
     * 暴力破解
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int x = j - i;
                int y = Math.min(height[i], height[j]);
                max = Math.max(max, x * y);
            }
        }
        return max;
    }

    /**
     * 双指针
     * @param height
     * @return
     */
    public int maxArea2(int[] height) {
        int max = 0;
        int i = 0, j = height.length - 1;
        while (i < j){
            max = Math.max(max, (j - i) * Math.min(height[i], height[j]));
            if (height[i] < height[j]){
                i ++;
            }else{
                j --;
            }
        }
        return max;
    }
}
