package com.turing.doc;

/**
 * TODO 搜索插入位置
 *
 * @Description
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 示例 1:
 *
 * 输入: nums = [1,3,5,6], target = 5
 * 输出: 2
 *
 * 示例2:
 * 输入: nums = [1,3,5,6], target = 2
 * 输出: 1
 * 示例 3:
 *
 * 输入: nums = [1,3,5,6], target = 7
 * 输出: 4
 *
 * @Author ll.lv
 * @Date 2023/3/26 16:30
 **/
public class SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        int ans = nums.length;
        // 定义左右边界
        int left = 0, right = nums.length -1;
        // 循环直至区间左右端点相同
        while (left <= right) {
            // 防止计算时溢出
            int mid  = ((right - left) >> 1) + left;
            if (target <= nums[mid]) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        SearchInsertPosition position = new SearchInsertPosition();
        int nums[] = {1,3,5,6};
        int target = 7;
        System.out.println(position.searchInsert(nums,target));
    }
}
