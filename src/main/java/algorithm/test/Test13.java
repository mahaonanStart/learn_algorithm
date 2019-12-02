package algorithm.test;

/**
 * @Author: M˚Haonan
 * @Date: 2019-06-19 21:52
 * @Description:
 */
public class Test13 {

    /**
     * 暴力破解，求出所有可能的面积，取最大
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int max = 0;
        //i, j代表数组元素下标，两个差即为长
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int x = Math.abs(i - j);
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
        int max = 0, l = 0, r = height.length - 1;
        while (l < r){
            max = Math.max(max, Math.min(height[l], height[r]) * (r - l));
            //移动高度小的一边到下一个位置。
            //可以这样理解，假如移动高的那一边，如果遇到比高的还高的，这种不起作用，因为高度决定于小的那一方
            //如果碰到小的，那也比之前的面积小，因为宽度变窄了
            //因此只需要移动小的那边即可，保证可以不错过最大值
            if (height[l] < height[r]){
                l ++;
            }else{
                r --;
            }
        }
        return max;
    }

}
