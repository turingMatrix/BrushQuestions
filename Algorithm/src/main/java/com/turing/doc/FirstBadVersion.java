package com.turing.doc;

/**
 * TODO 第一个错误的版本
 * @Description
 * 你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
 * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
 * 你可以通过调用bool isBadVersion(version)接口来判断版本号 version 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数
 *示例 1：
 * 输入：n = 5, bad = 4
 * 输出：4
 * 解释：
 * 调用 isBadVersion(3) -> false
 * 调用 isBadVersion(5) -> true
 * 调用 isBadVersion(4) -> true
 * 所以，4 是第一个错误的版本。
 *
 * 示例 2：
 * 输入：n = 1, bad = 1
 * 输出：1
 *
 * @Author ll.lv
 * @Date 2023/3/26 15:49
 **/
public class FirstBadVersion {

    /* The isBadVersion API is defined in the parent class VersionControl.*/
      boolean isBadVersion(int version) {
          //业务代码
          return false;
      }

    /**
     * TODO 使用二分查找解题
     *
     * @Description
     * 根据题目可知，当其中一个版本为正确版本，则该版本之前的所有版本都是正确的，反之当其中一个版本为错误版本，则往后的所有版本都是错的，根据这个特性，可以使用二分查找算法进行解决
     * 具体：
     * 可以将左右两边界设为 1 和 n，其中n为给定的版本数量，
     * 设定左右边界后，每次根据左右边界进行查找其中间的版本，都回检测是否为正确版本，
     * 若为正确版本，则错误版本一定在右边界，则缩紧做边界，再对右边界的所有版本进行操作
     * 若为错误版本，则右侧的所有版本都为错误版本，故缩紧右边界，再对左边界的所有版本进行操作
     *
     * @Param n
     * @Return int
     * @Author ll.lv
     * @Date 2023/3/26 15:56
     **/

    public int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        // 循环直至区间左右端点相同
        while (left < right) {
            // 防止计算时溢出
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) {
                // 答案在区间 [left, mid] 中
                right = mid;
            } else {
                // 答案在区间 [mid+1, right] 中
                left = mid + 1;
            }
        }
        // 此时有 left == right，区间缩为一个点，即为答案
        return left;
    }
}
