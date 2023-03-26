package com.turing.doc;

/**
 * TODO 二分查找
 *
 * @Description
 * 给定一个n个元素有序的（升序）整型数组nums 和一个目标值target ，
 * 写一个函数搜索nums中的 target，如果目标值存在返回下标，否则返回 -1。
 *
 * 示例 1:
 *
 * 输入: nums = [-1,0,3,5,9,12], target = 9
 * 输出: 4
 * 解释: 9 出现在 nums 中并且下标为 4
 * 示例 2:
 *
 * 输入: nums = [-1,0,3,5,9,12], target = 2
 * 输出: -1
 * 解释: 2 不存在 nums 中因此返回 -1
 *
 * 解题：数组为有序数组，同时数组中无重复元素，因为一旦有重复元素，使用二分查找法返回的元素下标可能不是唯一的，这些都是使用二分法的前提条件，当大家看到题目描述满足如上条件的时候，可要想一想是不是可以用二分
 *
 * @Author ll.lv
 * @Date 2023/3/25 16:10
 **/
public class BinarySearch {

    /**
     * TODO 左闭右闭区间[left, right]
     *
     * @Description
     * @Param nums
     * @Param target
     * @Return int
     * @Author ll.lv
     * @Date 2023/3/25 16:39
     **/

    public int search(int[] nums, int target) {
        // 定义target在左闭右闭的区间里【left，right】
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid -1;
            } else {
                left = mid +1;
            }
        }
        return -1;
    }

    /**
     * TODO 左闭右闭区间[left, right]
     * 
     * @Description 
     * @Param nums
     * @Param target
     * @Return int
     * @Author ll.lv
     * @Date 2023/3/25 16:31     
     **/
    
    public int search2(int[] nums, int target) {
        // 避免当 target 小于nums[0] nums[nums.length - 1]时多次循环运算
        if (target < nums[0] || target > nums[nums.length - 1]) {
            return -1;
        }
        // 定义target在左闭右闭的区间里【left，right】
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = ((right - left) >> 1) + left;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid -1;
            } else {
                left = mid +1;
            }
        }
        return -1;
    }

    /**
     * TODO 左闭右开区间 [left, right)。
     *
     * @Description
     * @Param args
     * @Return void
     * @Author ll.lv
     * @Date 2023/3/25 16:32
     **/

    public int search3(int[] nums, int target) {
        // 定义target在左闭右开的区间里【left，right)
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = ((right - left) >> 1) + left;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid;
            } else {
                left = mid +1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch();
         int nums[] = {-1,0,3,5,9,12};
         int target = 9;
        System.out.println(">>>>二分查找 左闭右闭区间>>>> : " + binarySearch.search(nums,target));
        System.out.println(">>>>二分查找 左闭右闭区间>>>> : " + binarySearch.search2(nums,target));
        System.out.println(">>>>二分查找 左闭右开区间>>>> : " + binarySearch.search3(nums,target));

    }
}
