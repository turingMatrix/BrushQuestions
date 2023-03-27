package com.turing.doc;

import java.util.Arrays;

/**
 * TODO 有序数组的平方
 *
 * @Description
 * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 *
 * 示例 1：
 * 输入：nums = [-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * 解释：平方后，数组变为 [16,1,0,9,100]
 * 排序后，数组变为 [0,1,9,16,100]
 *
 * 示例 2：
 * 输入：nums = [-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 *
 * @Author ll.lv
 * @Date 2023/3/26 16:47
 **/
public class SortedSquares {

    /**
     * TODO 直接排序
     *
     * @Description 将数组 nums 中的数平方后直接排序
     * @Return int[]
     * @Author ll.lv
     * @Date 2023/3/27 16:04
     **/

    public int[] sortedSquares(int[] nums) {
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            ans[i] = nums[i] * nums[i];
        }
        // 对数组的所有元素进行排序，并且是按从小到大的顺序
        Arrays.sort(ans);
        return ans;
    }


    /**
     * TODO 双指针
     *
     * @Description
     * 1. 先遍历做平方操作并找到第一个非负数的index赋值给n
     * 2. 从n向左右两边遍历合并两个有序数组
     * 其实就是合并两个有序数组，left数组[0,left], right数组[right, nums.length - 1]
     *
     * @Param nums
     * @Return int[]
     * @Author ll.lv
     * @Date 2023/3/27 16:08
     **/

    public int[] sortedSquares2(int[] nums) {
        int n = -1, left = 0, right = 0;

        for(int i = 0; i < nums.length; i++){
            // 将第一个非负数的位置赋值给n
            if (nums[i] >= 0 && n == -1){
                n = i;
            }
            nums[i] *= nums[i];
        }
        // 如果nums[0] >= 0, 则不需要重新排序，直接返回
        if (n == 0){
            return nums;
        }
        // n == -1说明没有非负数，即nums[]都是负数；
        // 那left数组就是[0, nums.length - 1], right数组为空[]，即[nums.length, nums.length - 1]
        if (n == -1){
            left = nums.length - 1;
            right = nums.length;
        } else { // n > 0, 则left数组是[0, n - 1], right数组是 [n, nums.length - 1]
            right = n;
            left = n - 1;
        }
        // 申请一个新的数组来合并两个有序数组
        // 还没找到合适的原地合并的方法
        int[] rets = new int[nums.length];
        for(int i = 0; i < rets.length; i++){
            // 如果 left < 0，说明left数组已经合并完成，接下来只需要将right数组剩下的元素依次填入即可
            if(left < 0){
                rets[i] = nums[right++];
                // 如果 right >= nums.length，说明right数组已经合并完成，接下来只需要将left数组剩下的元素依次填入即可
            }else if (right >= nums.length){
                rets[i] = nums[left--];
            }else{
                // left数组和right数组都还要元素，则将较小的那个填入
                if(nums[left] <= nums[right]){
                    rets[i] = nums[left--];
                } else {
                    rets[i] = nums[right++];
                }
            }
        }
        return rets;
    }


    public static void main(String[] args) {
        int nums[] = {-4,-1,0,3,10};
        SortedSquares squares = new SortedSquares();
        System.out.println(squares.sortedSquares(nums));
        System.out.println(squares.sortedSquares2(nums));
    }
}
