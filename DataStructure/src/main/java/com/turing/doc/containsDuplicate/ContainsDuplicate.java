package com.turing.doc.containsDuplicate;

import java.util.*;

/**
 * TODO 存在重复元素
 *
 * @Description
 * 给你一个整数数组 nums 。如果任一值在数组中出现 至少两次 ，返回 true ；如果数组中每个元素互不相同，返回 false 。
 *
 * 示例 1：
 * 输入：nums = [1,2,3,1]
 * 输出：true
 *
 * 示例 2：
 * 输入：nums = [1,2,3,4]
 * 输出：false
 *
 * 示例3：
 * 输入：nums = [1,1,1,3,3,4,3,2,4,2]
 * 输出：true
 *
 * @Author ll.lv
 * @Date 2023/3/8 13:08
 **/
public class ContainsDuplicate {
    /**
     * TODO hashSet解法
     *
     * @Description
     * 知识点 1：Set 集合
     * 1.java.util.Set接口 extends Collection接口
     * 2.Set 是一个无序集合
     * 3.Set 不允许存储重复元素，最多包含一个null元素(添加相同的元素，add方法返回false，且新元素不会加入到集合中)
     *      > Set集合在调用add方法的时候，add方法会调用元素hashCode方法和equals方法，判断元素是否重复,重复则返回false
     * 4.Set 没有索引，没有带索引的方法，也不能使用普通的for循环遍历
     * 5.Set 判断两个对象是否相同只能使用equals()方法，不能使用==运算符
     * 6.Set 常用到的实现类：
     *      HashSet：
     *      > 集合中的元素是无序排列的，根据heshcode插入元素，且不能存储重复的元素
     *      > 通过hash算法来存储集合元素，依赖数据结构是哈希表，因而具有良好存取和查找功能
     *          1.哈希值：是一个十进制整数，由系统随机给出（可以理解为元素的地址值，是一个模拟出来得到的逻辑地址，不是数据实际存储的物理地址）
     *          2.哈希表：hashSet集合存储数据的结构
     *              哈希表 = 数组+链表；
     *              哈希表 = 数组+红黑树（提高查询的速度）
     *          数组结构：把元素进行了分组（相同哈希值的元素是一组）
     *          链表/红黑树结构：把相同哈希值的元素连到一起（如何链表的长度超过了8位，那么就会把链转换位红黑树（提高查询的速度））
     *
     *      LinkedHashSet：
     *      > LinkedHashSet是一个基于LinkedHashMap实现的有序去重集合列表，是HashSet的子类（在HashSet的基础上维护列元素添加顺序的功能）
     *      > 集合中的元素是有序排列的，可以存储null值，但不能存储重复元素
     *      > 通过hash算法来存储集合元素，依赖数据结构是哈希表
     *          1.哈希表：LinkedHashSet集合存储数据的结构,相比hashSet多了一条链表，用来记录元素的存储顺序
     *              哈希表 = 数组+链表+链表(记录元素的存储顺序)
     *              哈希表 = 数组+红黑树+链表(记录元素的存储顺序)
     *
     *      TreeSet：
     *
     *
     *
     * @Param nums
     * @Return boolean
     * @Author ll.lv
     * @Date 2023/3/8 13:18
     **/

    public boolean hashSetSolution(int[] nums) {
        Set<Integer> res = new HashSet();
        for (int i:nums){
            res.add(i);
        }
        return res.size()<nums.length?true:false;
    }

    public static void main(String[] args) {
        int[] nums ={1,2,3,1};
        ContainsDuplicate containsDuplicate = new ContainsDuplicate();

        Boolean res = containsDuplicate.hashSetSolution(nums);
        System.out.println(">>>>>> hashSetSolution >>>>>> : " +res);
    }
}
