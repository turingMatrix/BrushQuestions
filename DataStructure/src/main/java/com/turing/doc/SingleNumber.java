package com.turing.doc;

import java.util.Arrays;

/**
 * TODO 只出现一次的数字
 *
 * @Description
 * 给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。
 * 示例 1 ：
 * 输入：nums = [2,2,1]
 * 输出：1
 *
 * 示例 2 ：
 * 输入：nums = [4,1,2,1,2]
 * 输出：4
 *
 * 示例 3 ：
 * 输入：nums = [1]
 * 输出：1
 *
 * @Author ll.lv
 * @Date 2023/3/25 17:41
 **/
public class SingleNumber {
    /**
     * TODO
     *
     * @Description
     * |=：两个二进制对应位都为0时，结果等于0，否则结果等于1；
     * &=：两个二进制的对应位都为1时，结果为1，否则结果等于0；
     * ^=：两个二进制的对应位相同，结果为0，否则结果为1
     *
     * @Param nums
     * @Return int
     * @Author ll.lv
     * @Date 2023/3/25 17:50
     **/

    public int singleNumber(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }

    /**
     * TODO 采用Lambda函数表达式解决
     *
     * @Description
     * Lambda 表达式是一种匿名函数，简单地说，它是没有声明的方法，也即没有访问修饰符、返回值声明和名字
     * https://blog.csdn.net/Lou_Lan/article/details/120667822
     *
     * 并通过流的getAsInt（）方法把返回
     *
     * @Param nums
     * @Return int
     * @Author ll.lv
     * @Date 2023/3/25 17:54
     **/

    public int singleNumber2(int[] nums) {
        return Arrays.stream(nums).reduce((a, b)->a^b).getAsInt();
    }

    public static void main(String[] args) {
        int nums[] = {4,1,2,1,2};
        SingleNumber number = new SingleNumber();
        System.out.println(number.singleNumber2(nums));
    }
}
