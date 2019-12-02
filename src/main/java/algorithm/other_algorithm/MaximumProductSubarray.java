package main.java.algorithm.other_algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: M˚Haonan
 * @Date: 2019-05-09 09:19
 * @Description:
 * Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.
 *
 * Example 1:
 *
 * Input: [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * Example 2:
 *
 * Input: [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 *
 * 给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。
 * leetcode 152
 */
public class MaximumProductSubarray {


    /**
     * 这个方法是可以的，只是数据量一大被报内存溢出的错误
     * 核心思路就是利用一个二维数组保存每个子序列的计算结果，然后最后求最大值
     * 使用递推来计算子序列，例如1100的序列就相当于1000的序列乘上邻近的下一个元素
     * 最后将所有的结果都求出来
     * 1111
     * 1110 0111
     * 1100 0110 0011
     * 1000 0100 0010 0001
     * (1代表取对应位置的元素，0代表不取对应位置的元素)
     * 然后将对应位置的结果都算出来,即暴力破解，将每种可能情况的乘积都算出来
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        int size = nums.length;
        //初始化情况
        int [][] result = new int[size][size];
        int max = nums[0];
        for (int i = 0; i < size; i++) {
            result[size - 1][i] = nums[i];
            max = Math.max(max, result[size - 1][i]);
        }
        //外层循环控制层数
        for (int i = size - 2; i >=0; i--) {
            //index每次的初始值，也是就是需要乘的数组元素的起始下标
            int index = size - 1 - i;
            for (int j = 0; j <= i; j++) {
                result[i][j] = result[i + 1][j] * nums[index ++];
                max = Math.max(max, result[i][j]);
            }
        }
        return max;
    }



    /**
     * dp求解
     * 因为要考虑元素的正负
     * dp[i][0]代表包含当前元素的子序列的最大乘积
     * dp[i][1]代表包含当前元素的子序列的最小乘积
     * dp方程为
     * dp[i][0] = max(dp[i - 1][0] * nums[i], dp[i - 1][1] * nums[i], nums[i])
     * dp[i][1] = min(dp[i - 1][0] * nums[i], dp[i - 1][1] * nums[i], nums[i])
     * 即如果当前元素>=0, max为dp[i-1]的max 乘以当前元素
     * @param nums
     * @return
     */
    public int maxProduct2(int[] nums) {
        //定义dp状态，0代表当前dp的最大值，1代表当前dp的最小值
        int[][] dp = new int[nums.length][2];
        //递推的初始值，只有一个元素的情况
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];
        //最终结果
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            //求解当前dp的最大值
            dp[i][0] = max(dp[i - 1][0] * nums[i], dp[i - 1][1] * nums[i], nums[i]);
            //求解当前dp的最小值
            dp[i][1] = min(dp[i - 1][0] * nums[i], dp[i - 1][1] * nums[i], nums[i]);
            res = Math.max(res, dp[i][0]);
        }
        return res;
    }

    /**
     * 简化的dp,使用数学技巧使dp的空间复杂度减小
     * @param nums
     * @return
     */
    public int maxProduct3(int[] nums) {
        //定义dp状态，0代表当前dp的最大值，1代表当前dp的最小值
        int[][] dp = new int[2][2];
        //递推的初始值，只有一个元素的情况
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];
        //最终结果
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            //x和y，0，1进行交替遍历
            //因为只需要保存上一次的dp结果即可
            int x = i % 2;
            int y = (i - 1) % 2;
            //求解当前dp的最大值
            dp[x][0] = max(dp[y][0] * nums[i], dp[y][1] * nums[i], nums[i]);
            //求解当前dp的最小值
            dp[x][1] = min(dp[y][0] * nums[i], dp[y][1] * nums[i], nums[i]);
            res = Math.max(res, dp[x][0]);
        }
        return res;
    }

    /**
     * 更加简化的dp, 但是通用型不高
     * @param nums
     * @return
     */
    public int maxProduct4(int[] nums) {
        //最终结果
        int result = nums[0];
        //当前最小值
        int currMin = nums[0];
        //当前最大值
        int currMax = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            int max = currMax;
            currMax = max(currMax * num, currMin * num, num);
            currMin = min(max * num, currMin * num, num);
            result = Math.max(currMax, result);
        }
        return result;
    }

    public int max(int a, int b, int c){
        return Math.max(Math.max(a, b), c);
    }
    public int min(int a, int b, int c){
        return Math.min(Math.min(a, b), c);
    }


    /**
     * 更加巧妙的方法
     * 这个问题的关键在于数组中有奇数个负数还是偶数个负数
     * 如果是奇数个负数，最大值其实就相当于该负数左右各自的乘积
     * 如果是偶数个负数，直接都乘起来就好了
     * 因此从左右开始遍历，依次乘下去，分别求出左右的最大值，二者较大的即是结果
     * 数组中的0就相当于分隔符,跳过去然后将乘积归1从新开始计算
     * @param nums
     * @return
     */
    public int maxProduct5(int[] nums) {
        int max = Integer.MIN_VALUE, product = 1;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(product *= nums[i], max);
            if (nums[i] == 0) product = 1;
        }
        product = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            max = Math.max(product *= nums[i], max);
            if (nums[i] == 0) product = 1;
        }
        return max;
    }


    /**
     * 使用递归求解, 数量多了以后也会超时，不过思路是对的
     * 每种元素的状态分为使用和不使用，因此就可以根据这样进行分类
     * 递归求解使用和不使用的情况
     * @param nums
     * @return
     */
    public int maxProduct6(int[] nums) {
        //当前元素使用的情况
        List<Integer> result = new ArrayList<>();
        //使用第一个元素的情况
        use(nums, 0, 1, result);
        //不使用第一个元素的情况
        notUse(nums, 0, Integer.MIN_VALUE, result);
        int max = Integer.MIN_VALUE;
        for (Integer integer : result) {
            if (max < integer) max = integer;
        }
        return max;
    }

    /**
     * 不使用当前元素的情况
     * @param nums 原数组
     * @param i   当前遍历到的元素
     * @param num   之前累乘的积
     * @param result 存放不同序列的乘积
     */
    private void notUse(int[] nums, int i, int num, List<Integer> result) {
        //不使用后，需要把之前的结果保存下来
        //这里排除连续元素都不使用的情况的情况
        if (num != Integer.MIN_VALUE){
            result.add(num);
        }
        if (i + 1 >= nums.length){
            return;
        }
        //如果不使用下一个元素，进行下一次的时候要把结果初始化，记录一个特征值
        notUse(nums, i + 1, Integer.MIN_VALUE, result);
        //如果使用下一个元素，只要把结果初始化为1即可
        use(nums, i + 1, 1, result);
    }

    /**
     * 使用当前元素的情况
     * @param nums
     * @param i
     * @param num
     * @param result
     */
    private void use(int[] nums, int i, int num, List<Integer> result) {
        //使用当前元素，先和之前的结果乘起来
        num = num * nums[i];
        //递归终止条件,最后一个元素如果仍使用，但是已经到头了， 就直接把结果保存起来
        if (i + 1 >= nums.length){
            result.add(num);
            return;
        }
        //不使用下一个的情况
        notUse(nums, i + 1, num, result);
        //使用下一个的情况
        use(nums, i + 1, num, result);
    }

    /**
     * 使用位运算, 会出现位数不够的情况，思路没问题
     * @param nums
     * @return
     */
    public int maxProduct7(int[] nums) {
        int n = nums.length;
        //定义初始值,即1111
        int first = (1 << nums.length) - 1;
        //初始化最大值
        int max = Integer.MIN_VALUE;
        //定义一个控制移位次数的变量
        int count = 0;
        //然后依次对first进行移位
        while (first > 0){
            int curr = first;
            for (int i = 0; i < count + 1; i++) {
                //获取1的位置
                List<Integer> onePosition = getOnePosition(curr, n);
                //计算当前情况的max
                int currMax = 1;
                for (Integer integer : onePosition) {
                    currMax = currMax * nums[integer];
                }
                max = Math.max(currMax, max);
                curr >>= 1;
            }
            //消掉first的最后一个1
            first &= (first - 1);
            //将移位次数 +1
            count ++;
        }
        return max;
    }

    /**
     * 获取一个数1所在的位,例如3二进制为11，则返回[0,1]
     * @param num 所求数字
     * @param n  有效位数，即上面数组的长度
     * @return
     */
    private List<Integer> getOnePosition(int num, int n) {
        List<Integer> list = new ArrayList<>();
        int index = 0;
        while (index < n){
            //如果最后一位为1
            if ((num & 1) == 1){
                list.add(index);
            }
            num >>= 1;
            index ++;
        }
        return list;
    }

    /**
     * 使用字符来代替int，弥补位数的不足
     * 和方法7的思路一样，就是弥补了位数的不足，但是数据量大了也会超时
     * 难道暴力破解都会超时吗
     * @param nums
     * @return
     */
    public int maxProduct8(int[] nums) {
        int n = nums.length;
        //定义初始值,即1111
        List<Character> first = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            first.add('1');
        }
        //初始化最大值
        int max = Integer.MIN_VALUE;
        //定义一个控制移位次数的变量
        int count = 0;
        //然后依次对first进行移位
        while (first.contains('1')){
            List<Character> curr = new ArrayList<>(first);
            for (int i = 0; i < count + 1; i++) {
                //获取1的位置
                List<Integer> onePosition = getOnePosition2(curr, n);
                //计算当前情况的max
                int currMax = 1;
                for (Integer integer : onePosition) {
                    currMax = currMax * nums[integer];
                }
                max = Math.max(currMax, max);
                //对字符串进行移位
                move(curr);
            }
            //消掉first的最后一个1
            first.set(first.size() - 1 - count, '0');
            //将移位次数 +1
            count ++;
        }
        return max;
    }

    private void move(List<Character> curr) {
        for (int i = curr.size() - 1; i > 0; i--) {
            curr.set(i, curr.get(i - 1));
        }
        curr.set(0, '0');
    }


    private List<Integer> getOnePosition2(List<Character> curr, int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < curr.size(); i++) {
            if (curr.get(i) == '1'){
                list.add(n - 1 - i);
            }
        }
        return list;
    }


    public static void main(String[] args) {
        MaximumProductSubarray max = new MaximumProductSubarray();
//        List<Character> list = Arrays.asList('0','1','0','0');
//        System.out.println(max.getOnePosition2(list, 4));
        System.out.println(max.maxProduct8(new int[]{0,-1,4,-4,5,-2,-1,-1,-2,-3,0,-3,0,1,-1,-4,4,6,2,3,0,-5,2,1,-4,-2,-1,3,-4,-6,0,2,2,-1,-5,1,1,5,-6,2,1,-3,-6,-6,-3,4,0,-2,0,2}));
    }

}
