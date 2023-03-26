package com.turing.doc;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO 只出现一次的数字 II
 *
 * @Description
 * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素
 *示例 1：
 *
 * 输入：nums = [2,2,3,2]
 * 输出：3
 *
 * 示例 2：
 *
 * 输入：nums = [0,1,0,1,0,1,99]
 * 输出：99
 *
 * @Author ll.lv
 * @Date 2023/3/26 14:51
 **/
public class SingleNumberII {

    /**
     * TODO 思路和算法
     *
     * @Description
     * 使用哈希映射统计数组中每个元素的出现次数。对于哈希映射中的每个键值对，键表示一个元素，值表示其出现的次数
     *
     * @Param nums
     * @Return int
     * @Author ll.lv
     * @Date 2023/3/26 15:15
     **/

    public int singleNumber(int[] nums) {
        Map<Integer,Integer> freq = new HashMap<>();
        for (int num:nums) {
            freq.put(num,freq.getOrDefault(num,0) + 1);
        }
        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            int num = entry.getKey(), occ = entry.getValue();
            if (occ == 1){
                ans = num;
                break;
            }
        }
        return ans;
    }


    /**
     * TODO 算法与思路
     * 
     * @Description
     * 由于数组内的元素都在int（32位整数）类型范围内，因此可以依次计算答案的每一个二进制位是0还是1，
     * 答案的第 i 个二进制位（i从0开始编号），它可能为0或1。对于数组中非答案的元素，每个元素都出现3次，对应的第 i 个二进制位的3个0或3个1，
     * 无论哪一种情况，都是3的倍数（即和都为0或者3），因此答案的第 i 个二进制位就是数组中所有元素的第 i 个二进制位之和除以 3 的余数。
     *
     * 对于数组中的每一个元素 x，可以使用运算(x >> i) & 1 得到x的第i个二进制位，并将这些二进制位进行相加后取余，这样得到的结果为0或1，这样就能得到只出现一次的数
     *
     * @Param nums
     * @Return int
     * @Author ll.lv
     * @Date 2023/3/26 15:25     
     **/
    
    public int singleNumber2(int[] nums) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int total = 0;
            for (int num:nums) {
                total +=((num >> i) & 1);
            }
            if (total % 3 != 0 ) {
                ans |= (1 << i);
            }
        }
        return ans;
    }



    public static void main(String[] args) {
        SingleNumberII numberII = new SingleNumberII();
        int nums[] = {2,2,3,2};
        System.out.println(numberII.singleNumber(nums));

        System.out.println(numberII.singleNumber2(nums));

    }
}
