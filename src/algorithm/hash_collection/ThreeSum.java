package algorithm.hash_collection;

import java.util.*;

/**
 * @Author: M˚Haonan
 * @Date: 2019-04-24 14:06
 * @Description:  求出一个给定数组中3个数加起来为0的所有数列
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 * a + b + c = 0
 * A solution set is:
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 * 注意：不能重复
 *
 * leetcode 15
 */

public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        //创建结果保存list
        Set<List<Integer>> res  = new HashSet<>();
        if(nums.length==0) {
            return new ArrayList<>(res);
        }
        Arrays.sort(nums);
        // 遍历数组
        for (int a = 0; a < nums.length - 2; a++) {
            int target = 0 - nums[a];
            //维护一个map，用来求两数之和
            Map<Integer, Integer> map = new HashMap<>();
            for (int b = a + 1; b < nums.length; b++) {
                int ele = target - nums[b];
                if (map.containsKey(ele)){
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[a]);
                    list.add(nums[b]);
                    list.add(nums[map.get(ele)]);
                    res.add(list);
                }
                map.put(nums[b], b);
            }
        }
        return new ArrayList<>(res);
    }


    public List<List<Integer>> threeSum2(int[] nums) {
       //创建一个set方便去重
        Set<List<Integer>> res = new HashSet<>();
        //如果数组为空，直接返回
        if (nums.length == 0){
            return new ArrayList<>(res);
        }
        //对数组进行排序，方便去重, 并且要采取两边夹的策略，必须排序
        Arrays.sort(nums);
        //遍历nums
        for (int i = 0; i < nums.length - 2; i ++) {
            //j从左边开始
            int j = i + 1;
            //k从右边开始
            int k = nums.length - 1;
            //往中间夹
            while (j < k){
                //求三数之和
                int sum = nums[i] + nums[j] + nums[k];
                //如果sum = 0，保存结果并且移动j，k
                if (sum == 0){
                    res.add(Arrays.asList(nums[i], nums[j ++], nums[k --]));
                }
                // 如果sum<0，说明j应该右移使结果增大
                if (sum < 0) j ++;
                // 如果sum<0，说明k应该左移使结果减小
                if (sum > 0) k --;
            }
        }
        return new ArrayList<>(res);

    }


    public static void main(String[] args) {
        ThreeSum threeSum = new ThreeSum();
        int [] nums = new int[]{-1, 0, 1, 2, -1, -4};
        List<List<Integer>> results = threeSum.threeSum2(nums);
        for (List<Integer> result : results) {
            for (Integer integer : result) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }

    }
}
